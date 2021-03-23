package me.ihdeveloper.react.item.listener;

import me.ihdeveloper.react.item.Main;
import me.ihdeveloper.react.item.UnknownItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    private boolean autoReplaceUnknownItems = false;

    public JoinListener(boolean autoReplaceUnknownItems) {
        this.autoReplaceUnknownItems = autoReplaceUnknownItems;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (int index = 0; index < player.getInventory().getSize(); index++) {
            ItemStack itemStack = player.getInventory().getItem(index);

            if (itemStack == null) {
                continue;
            }

            if (itemStack.getType() == Material.AIR) {
                continue;
            }

            String id = Main.getInstance().loadItem(itemStack);

            if (id != null) {
                player.getInventory().setItem(index, Main.getInstance().updateItem(itemStack));
                continue;
            }

            if (autoReplaceUnknownItems) {
                player.getInventory().setItem(index, Main.getInstance().createItem(UnknownItem.class));
            }
        }
    }

}
