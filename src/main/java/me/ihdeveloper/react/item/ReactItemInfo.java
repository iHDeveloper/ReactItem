package me.ihdeveloper.react.item;

import org.bukkit.Material;

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
    int amount() default 1;
    short data() default 0;
    // TODO add flags
    // TODO add unbreakable
    // TODO add stackable
}
