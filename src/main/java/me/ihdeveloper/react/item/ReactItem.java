package me.ihdeveloper.react.item;

import me.ihdeveloper.react.item.render.RenderInfo;

/**
 * Represents an instance of an item that performs stateless operations fired
 * by item events, custom events, etc...
 */
public class ReactItem {

    /**
     * Used to change the render information depending on the item
     * 
     * @param info The render informatin of the item before rendering
     */
    public void render(RenderInfo info) {
        // No need to change any render information since the item is stateless
    }

    @Override
    public String toString() {
        // TODO Show information based on the item info
        return "§7Unknown Item";
    }

}
