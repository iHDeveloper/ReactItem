package me.ihdeveloper.react.item.listener;

import me.ihdeveloper.react.item.Main;
import me.ihdeveloper.react.item.api.ReactItemInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void preventUnstackableItemsFromBeingStacked(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryAction action = event.getAction();

        if (action != InventoryAction.PLACE_ALL && action != InventoryAction.PLACE_ONE)
            return;

        ItemStack cursor = event.getCursor();

        if (cursor == null)
            return;

        String id = Main.getInstance().loadItem(cursor);

        if (id == null)
            return;

        ReactItemInfo info = Main.getInstance().getItemInfo(id);

        if (info.stackable())
            return;

        ItemStack destination = event.getCurrentItem();

        if (destination == null || destination.getType() != cursor.getType())
            return;

        String destinationId = Main.getInstance().loadItem(destination);

        // When the destination is vanilla item
        if (destinationId == null)
            return;

        // When the destination is react item but with different ID
        if (!destinationId.equals(id))
            return;

//        event.getClickedInventory().setItem(event.getSlot(), cursor);
//        player.setItemOnCursor(destination);
        event.setCancelled(true);
    }

}
