package me.ihdeveloper.react.item.test.command;

import me.ihdeveloper.react.item.ReactItemAPI;
import me.ihdeveloper.react.item.test.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestGiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou need to be player to execute this command!");
            return true;
        }

        if (args.length <= 0) {
            sender.sendMessage("§c/test-give <ID>");
            return true;
        }

        // TODO add argument for amount
        String id = args[0];

        ReactItemAPI reactItemAPI = Main.getInstance().getReactItemAPI();

        try {
            ItemStack itemStack = reactItemAPI.createItem(id);

            sender.sendMessage("§eGiving you item with ID §7" + id);
            ((Player) sender).getInventory().addItem(itemStack);

        } catch (IllegalArgumentException exception) {
            sender.sendMessage("§cFailed to execute the command! Check the console log for more info.");
            exception.printStackTrace();
        } catch (NullPointerException exception) {
            sender.sendMessage("§cFailed to find custom item with ID§7 (" + id + ")§c!");
            exception.printStackTrace();
        }

        return true;
    }
}
