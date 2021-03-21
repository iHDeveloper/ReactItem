package me.ihdeveloper.react.item.test;

import me.ihdeveloper.react.item.ReactItemAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ReactItemAPI reactItemAPI;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§e is§a enabled!");

        reactItemAPI = (ReactItemAPI) getServer().getPluginManager().getPlugin("ReactItem");

        if (reactItemAPI != null) {
            getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§f: §aAPI detected!");
        } else {
            getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§f: §cAPI not detected!");
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§e is§c disabled!");
    }

}
