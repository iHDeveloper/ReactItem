package me.ihdeveloper.react.item;

import org.bukkit.Material;

/**
 * Represents information about certain {@link ReactItem}
 */
public @interface ReactItemInfo {
    String id();
    String name();
    String description();

    Material material();
    short data();
    // TODO add flags
    // TODO add unbreakable
    // TODO add stackable
}
