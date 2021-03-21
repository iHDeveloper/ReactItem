package me.ihdeveloper.react.item.test.item;

import me.ihdeveloper.react.item.ReactItem;
import me.ihdeveloper.react.item.ReactItemInfo;
import org.bukkit.Material;

@ReactItemInfo(
        id = "forbidden_stick",
        name = "§dThe Forbidden Stick",
        description = {
                "§7Hit any entity with this item to",
                "§7apply the forbidden magic"
        },
        material = Material.STICK
)
public class ForbiddenStick extends ReactItem {
}
