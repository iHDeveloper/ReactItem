package me.ihdeveloper.react.item.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents information about certain {@link ReactItem}
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactItemInfo {
    String id();
    String name();
    String[] description();

    Material material();
    short data() default 0;
    boolean unbreakable() default false;
    ItemFlag[] flags() default { };
    boolean stackable() default true;
}
