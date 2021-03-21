package me.ihdeveloper.react.item.test;

import me.ihdeveloper.react.item.ReactItem;
import me.ihdeveloper.react.item.ReactItemAPI;
import me.ihdeveloper.react.item.test.item.ForbiddenStick;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ReactItemAPI reactItemAPI;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§e is§a enabled!");

        reactItemAPI = (ReactItemAPI) getServer().getPluginManager().getPlugin("ReactItem");

        if (reactItemAPI != null) {
            getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§f: §aAPI detected!");

            getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§f:§e Registring items...");
            registerItem(new ForbiddenStick());
        } else {
            getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§f: §cAPI not detected!");
        }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§e is§c disabled!");
    }

    private void registerItem(ReactItem item) {
        getServer().getConsoleSender().sendMessage("§eReact Item §bTEST§f:§e Registering§7 " + item.getClass().getName() + "...");
        reactItemAPI.registerItem(item);
    }
}
