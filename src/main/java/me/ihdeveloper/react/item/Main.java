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

    private Map<String, ItemRegistryWrapper> registry = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§eReact Item§e is§a enabled!");
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
        ReactItem instance = wrapper.getInstance();
        ReactItemInfo itemInfo = wrapper.getInfo();

        if (wrapper == null)
            throw new IllegalArgumentException("The given item instance information is not registered!");

        NBTReactItemState state = new NBTReactItemState();

        instance.onCreate(state);

        RenderInfo renderInfo = new RenderInfo();
        renderInfo.setName(itemInfo.name());
        renderInfo.setDescription(itemInfo.description());
        renderInfo.setMaterial(itemInfo.material());
        renderInfo.setAmount(itemInfo.amount());
        renderInfo.setData(itemInfo.data());

        instance.render(renderInfo, state);

        // TODO Inject the states in the tag compound (e.g. id, etc...)

        ItemStack itemStack = new ItemStack(renderInfo.getMaterial(), renderInfo.getAmount(), renderInfo.getData());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(renderInfo.getName());
        itemMeta.setLore(Arrays.asList(renderInfo.getDescription()));
        itemStack.setItemMeta(itemMeta);

        Object nmsItem = ItemReflection.toNMS(itemStack);

        state.setString("id", itemInfo.id());

        Object tag = ItemReflection.getTag(nmsItem);
        NBTReflection.set(tag, "ReactData", state.getNBT());

        return ItemReflection.toCraftMirror(nmsItem);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item§e is§c disabled!");
    }

}
