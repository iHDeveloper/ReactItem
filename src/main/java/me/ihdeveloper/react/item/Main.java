package me.ihdeveloper.react.item;

import me.ihdeveloper.react.item.api.ReactItem;
import me.ihdeveloper.react.item.api.ReactItemAPI;
import me.ihdeveloper.react.item.api.ReactItemInfo;
import me.ihdeveloper.react.item.api.ReactItemState;
import me.ihdeveloper.react.item.listener.InventoryListener;
import me.ihdeveloper.react.item.listener.JoinListener;
import me.ihdeveloper.react.item.reflect.ItemReflection;
import me.ihdeveloper.react.item.reflect.NBTReflection;
import me.ihdeveloper.react.item.render.RenderInfo;
import me.ihdeveloper.react.item.state.NBTReactItemState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The main class for plugin
 */
public final class Main extends JavaPlugin implements ReactItemAPI {

    private static class ItemRegistryWrapper {
        private final ReactItem instance;
        private final ReactItemInfo info;

        public ItemRegistryWrapper(ReactItem instance, ReactItemInfo info) {
            this.instance = instance;
            this.info = info;
        }

        public ReactItem getInstance() {
            return instance;
        }

        public ReactItemInfo getInfo() {
            return info;
        }
    }

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private final Map<String, ItemRegistryWrapper> registry = new HashMap<>();
    private boolean debug = false;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        debug = getConfig().getBoolean("debug");

        if (getConfig().getBoolean("scan-inventory-on-join")) {
            getServer().getConsoleSender().sendMessage("§eReact Item:§7 Scan player inventory on join =>§a true");

            boolean autoReplaceUnknownItems = getConfig().getBoolean("auto-replace-unknown-items");
            getServer().getConsoleSender().sendMessage("§eReact Item:§7 Auto replace unknown items =>§" + (autoReplaceUnknownItems ? "a true" : "c false"));
            getServer().getPluginManager().registerEvents(new JoinListener(autoReplaceUnknownItems), this);
        }

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e) mode is§a enabled");
            getServer().getConsoleSender().sendMessage("§eWARNING§f: §cThis mode is not recommended for production environment!");
        }

        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        if (getConfig().getBoolean("add-default-unknown-item")) {
            getServer().getConsoleSender().sendMessage("§eReact Item:§7 Add default unknown item =>§a true");
            registerItem(new UnknownItem());
        }

        getServer().getConsoleSender().sendMessage("§eReact Item is§a enabled!");
    }

    @Override
    public void registerItem(ReactItem item) {
        Class<?> instanceClass = item.getClass();
        ReactItemInfo itemInfo = instanceClass.getAnnotation(ReactItemInfo.class);

        if (itemInfo == null)
            throw new IllegalArgumentException("Information of item instance (" + item + ") is null! Did you forget to add @ReactItemInfo?");

        String id = itemInfo.id();

        if (this.isItemInRegistry(id))
            throw new IllegalArgumentException("Item instance (" + item + ") with id (" + id + ") already exist!");

        ItemRegistryWrapper wrapper = new ItemRegistryWrapper(item, itemInfo);
        this.registry.put(id, wrapper);

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f:§e Registering item §7" + item);
        }
    }

    @Override
    public String loadItem(ItemStack itemStack) {
        if (itemStack == null)
            return null;

        Object nmsItem = ItemReflection.toNMS(itemStack);
        Object tagNBT = ItemReflection.getTag(nmsItem);

        if (tagNBT == null)
            return null;

        Object dataNBT = NBTReflection.get(tagNBT, "ReactData");

        if (dataNBT == null)
            return null;

        String id = NBTReflection.getString(dataNBT, "id");

        if (id == null)
            return null;

        if (!this.isItemInRegistry(id)) {
            getServer().getConsoleSender().sendMessage("§eReact Item§f:§e Failed to load item with ID §7" + id + "§7(Not registered?)");
            return null;
        }

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f:§e Loading item §7" + this.registry.get(id).getInstance());
        }

        return id;
    }

    @Override
    public ReactItemInfo getItemInfo(String id) {
        ItemRegistryWrapper wrapper = this.registry.get(id);

        if (wrapper == null)
            throw new IllegalArgumentException("Information of item (" + id + ") doesn't exist!");

        return wrapper.getInfo();
    }

    @Override
    public boolean isItemInRegistry(String id) {
        return this.registry.containsKey(id);
    }

    @Override
    public ItemStack updateItem(ItemStack itemStack) {
        Object nmsItem = ItemReflection.toNMS(itemStack);
        Object tagNBT = ItemReflection.getTag(nmsItem);
        Object dataNBT = NBTReflection.get(tagNBT, "ReactData");

        if (dataNBT == null)
            return null;

        String id = NBTReflection.getString(dataNBT, "id");

        if (id == null)
            return null;

        if (!this.isItemInRegistry(id)) {
            getServer().getConsoleSender().sendMessage("§eReact Item§f:§e Failed to update item with ID §7" + id + "§7(Not registered?)");
            return null;
        }

        ItemRegistryWrapper wrapper = this.registry.get(id);

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f:§e Updating item §7" + wrapper.getInstance());
        }

        NBTReactItemState state = new NBTReactItemState(dataNBT);
        RenderInfo renderInfo = renderItem(wrapper.getInstance(), wrapper.getInfo(), state);
        return toBukkitItem(wrapper.getInfo(), state, renderInfo, itemStack.getAmount());
    }

    @Override
    public ItemStack createItem(Class<? extends ReactItem> item) {
        return createItem(item, 1);
    }

    @Override
    public ItemStack createItem(Class<? extends ReactItem> item, int amount) {
        ReactItemInfo itemInfo = item.getAnnotation(ReactItemInfo.class);

        if (itemInfo == null)
            throw new IllegalArgumentException("The given item instance information is null! Did you forget to add @ReactItemInfo?");

        return createItem(itemInfo.id());
    }

    @Override
    public ItemStack createItem(String id) {
        return createItem(id, 1);
    }

    @Override
    public ItemStack createItem(String id, int amount) {
        ItemRegistryWrapper wrapper = this.registry.get(id);

        if (wrapper == null)
            throw new IllegalArgumentException("The given item instance information is not registered!");

        ReactItem instance = wrapper.getInstance();
        ReactItemInfo itemInfo = wrapper.getInfo();

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f:§e Creating item §7" + instance);
        }

        NBTReactItemState state = new NBTReactItemState();

        instance.onCreate(state);

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f: §eCreation State: §7" + state);
        }

        RenderInfo renderInfo = renderItem(instance, itemInfo, state);
        return toBukkitItem(itemInfo, state, renderInfo, amount);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item is§c disabled!");
    }

    private RenderInfo renderItem(ReactItem instance, ReactItemInfo info, ReactItemState state) {
        RenderInfo renderInfo = new RenderInfo();
        renderInfo.setName(info.name());
        renderInfo.setDescription(info.description());
        renderInfo.setMaterial(info.material());
        renderInfo.setData(info.data());
        renderInfo.setUnbreakable(info.unbreakable());
        renderInfo.setFlags(info.flags());

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f: §eRender Info: §7" + renderInfo);
        }

        instance.render(renderInfo, state);
        return renderInfo;
    }

    private ItemStack toBukkitItem(ReactItemInfo itemInfo, NBTReactItemState state, RenderInfo renderInfo, int amount) {
        ItemStack itemStack = new ItemStack(renderInfo.getMaterial(), amount, renderInfo.getData());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(renderInfo.getName());
        itemMeta.setLore(Arrays.asList(renderInfo.getDescription()));
        itemMeta.spigot().setUnbreakable(renderInfo.isUnbreakable());
        itemMeta.addItemFlags(renderInfo.getFlags());

        itemStack.setItemMeta(itemMeta);

        Object nmsItem = ItemReflection.toNMS(itemStack);

        state.setString("id", itemInfo.id());

        Object tag = ItemReflection.getTag(nmsItem);
        NBTReflection.set(tag, "ReactData", state.getNBT());

        return ItemReflection.toCraftMirror(nmsItem);
    }
}
