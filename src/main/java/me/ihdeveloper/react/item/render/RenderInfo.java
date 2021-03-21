package me.ihdeveloper.react.item.render;

import org.bukkit.Material;

/**
 * Represents information about an item to be rendered
 *
 * This is used to provide a stateful rendered when being passed into item instance aka {@link me.ihdeveloper.react.item.ReactItem}
 */
public class RenderInfo {
    private String name;
    private String[] description;
    private Material material;
    private int amount = 1;
    private short data = 0;

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
}
