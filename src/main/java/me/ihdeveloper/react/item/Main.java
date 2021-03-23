package me.ihdeveloper.react.item;

import me.ihdeveloper.react.item.api.ReactItem;
import me.ihdeveloper.react.item.api.ReactItemAPI;
import me.ihdeveloper.react.item.api.ReactItemInfo;
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

    private final Map<String, ItemRegistryWrapper> registry = new HashMap<>();
    private boolean debug = false;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage("§eReact Item is§a enabled!");

        debug = getConfig().getBoolean("debug");

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e) mode is§a enabled");
            getServer().getConsoleSender().sendMessage("§eWARNING§f: §cThis mode is not recommended for production environment!");
        }
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

        Object nmsItem = ItemReflection.toNMS(itemStack);
        Object tagNBT = ItemReflection.getTag(nmsItem);
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
    public ItemStack createItem(Class<? extends ReactItem> item) {
        ReactItemInfo itemInfo = item.getAnnotation(ReactItemInfo.class);

        if (itemInfo == null)
            throw new IllegalArgumentException("The given item instance information is null! Did you forget to add @ReactItemInfo?");

        return createItem(itemInfo.id());
    }

    @Override
    public ItemStack createItem(String id) {
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

        RenderInfo renderInfo = new RenderInfo();
        renderInfo.setName(itemInfo.name());
        renderInfo.setDescription(itemInfo.description());
        renderInfo.setMaterial(itemInfo.material());
        renderInfo.setAmount(itemInfo.amount());
        renderInfo.setData(itemInfo.data());
        renderInfo.setUnbreakable(itemInfo.unbreakable());
        renderInfo.setFlags(itemInfo.flags());

        if (debug) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§6DEBUG§e)§f: §eRender Info: §7" + renderInfo);
        }

        instance.render(renderInfo, state);

        ItemStack itemStack = new ItemStack(renderInfo.getMaterial(), renderInfo.getAmount(), renderInfo.getData());
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

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item is§c disabled!");
    }

}
