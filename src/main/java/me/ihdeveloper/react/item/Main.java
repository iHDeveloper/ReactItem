package me.ihdeveloper.react.item;

import org.bukkit.plugin.java.JavaPlugin;

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

        String name = itemInfo.name();

        if (this.isItemInRegistry(name))
            throw new IllegalArgumentException("Item instance (" + item + ") with name (" + name + ") already exist!");

        ItemRegistryWrapper wrapper = new ItemRegistryWrapper(item, itemInfo);
        this.registry.put(name, wrapper);
    }

    @Override
    public boolean isItemInRegistry(String name) {
        return this.registry.containsKey(name);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item§e is§c disabled!");
    }

}
