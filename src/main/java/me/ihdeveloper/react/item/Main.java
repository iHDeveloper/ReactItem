package me.ihdeveloper.react.item;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class for plugin
 */
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§eReact Item§e is§a enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item§e is§c disabled!");
    }

}
