package me.ihdeveloper.react.item.api;

import me.ihdeveloper.react.item.render.RenderInfo;

/**
 * Represents an instance of an item that performs stateless operations fired
 * by item events, custom events, etc...
 */
public abstract class ReactItem {

    /**
     * Triggered when a new item has been created from the API.
     * Used to add default values to the state of the item.
     *
     * @param state The item state container
     */
    public void onCreate(ReactItemState state) {
    }

    /**
     * Used to change the render information depending on the item
     *
     * Note: No need to change any render information since the item is stateless
     * 
     * @param renderInfo The render information of the item before rendering
     * @param state The state of the item
     */
    public void render(RenderInfo renderInfo, ReactItemState state) {
    }

    @Override
    public String toString() {
        ReactItemInfo info = this.getClass().getAnnotation(ReactItemInfo.class);
        if (info != null) {
            return "ReactItem{id=" + info.id() + ", name=" + info.name() + "}";
        }

        return "§7Unknown Item (aka doesn't have any information)";
    }

}
