package me.ihdeveloper.react.item;

import me.ihdeveloper.react.item.api.ReactItem;
import me.ihdeveloper.react.item.api.ReactItemInfo;
import org.bukkit.Material;

@ReactItemInfo(
        id = "@null",
        name = "§7Unknown Item",
        description = {
                "§7A useless item",
        },
        material = Material.STICK,
        data = Short.MAX_VALUE
)
public class UnknownItem extends ReactItem {
}
