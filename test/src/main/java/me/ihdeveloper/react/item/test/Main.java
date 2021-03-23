package me.ihdeveloper.react.item.test;

import me.ihdeveloper.react.item.api.ReactItem;
import me.ihdeveloper.react.item.api.ReactItemAPI;
import me.ihdeveloper.react.item.test.command.TestGiveCommand;
import me.ihdeveloper.react.item.test.item.ForbiddenStick;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private ReactItemAPI reactItemAPI;

    @Override
    public void onEnable() {
        instance = this;

        reactItemAPI = (ReactItemAPI) getServer().getPluginManager().getPlugin("ReactItem");

        if (reactItemAPI != null) {
            getServer().getConsoleSender().sendMessage("§eReact Item(§bTEST§e)§f: §aAPI detected!");

            getServer().getConsoleSender().sendMessage("§eReact Item(§bTEST§e)§f:§e Registring items...");
            registerItem(new ForbiddenStick());
        } else {
            getServer().getConsoleSender().sendMessage("§eReact Item(§bTEST§e)§f: §cAPI not detected!");
        }

        getCommand("test-give").setExecutor(new TestGiveCommand());

        getServer().getConsoleSender().sendMessage("§eReact Item(§bTEST§e)§e is§a enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§eReact Item(§bTEST§e)§e is§c disabled!");
    }

    public ReactItemAPI getReactItemAPI() {
        return reactItemAPI;
    }

    private void registerItem(ReactItem item) {
        getServer().getConsoleSender().sendMessage("§eReact Item(§bTEST§e)§f:§e Registering§7 " + item.getClass().getName() + "...");
        reactItemAPI.registerItem(item);
    }
}
