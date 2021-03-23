package me.ihdeveloper.react.item.render;

import me.ihdeveloper.react.item.api.ReactItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;

/**
 * Represents information about an item to be rendered
 *
 * This is used to provide a stateful rendered when being passed into item instance aka {@link ReactItem}
 */
public class RenderInfo {
    private String name;
    private String[] description;
    private Material material;
    private int amount = 1;
    private short data = 0;
    private boolean unbreakable = false;
    private ItemFlag[] flags = null;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String[] getDescription() {
        return description;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setData(short data) {
        this.data = data;
    }

    public short getData() {
        return data;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public void setFlags(ItemFlag[] flags) {
        this.flags = flags;
    }

    public ItemFlag[] getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return "RenderInfo{" +
                "name='" + name + '\'' +
                ", description=" + Arrays.toString(description) +
                ", material=" + material +
                ", amount=" + amount +
                ", data=" + data +
                ", unbreakable=" + unbreakable +
                ", flags=" + Arrays.toString(flags) +
                '}';
    }
}
