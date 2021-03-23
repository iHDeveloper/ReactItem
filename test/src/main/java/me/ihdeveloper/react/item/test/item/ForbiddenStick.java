package me.ihdeveloper.react.item.test.item;

import me.ihdeveloper.react.item.api.ReactItem;
import me.ihdeveloper.react.item.api.ReactItemInfo;
import me.ihdeveloper.react.item.api.ReactItemState;
import me.ihdeveloper.react.item.render.RenderInfo;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ReactItemInfo(
        id = "forbidden_stick",
        name = "§dThe Forbidden Stick",
        description = {
                "§7Hit any entity with this item to",
                "§7apply the forbidden magic",
                "§7"
        },
        material = Material.STICK,
        stackable = false
)
public class ForbiddenStick extends ReactItem {

        @Override
        public void onCreate(ReactItemState state) {
                state.setShort("hits", (short) 0);
        }

        @Override
        public void render(RenderInfo renderInfo, ReactItemState state) {
                if (state.hasKey("hits")) {
                        short hits = state.getShort("hits");
                        List<String> description = new ArrayList<>();
                        description.addAll(Arrays.asList(renderInfo.getDescription()));
                        description.add("§eHits: §f" + hits);
                        renderInfo.setDescription(description.toArray(new String[0]));
                }
        }
}
