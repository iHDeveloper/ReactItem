package me.ihdeveloper.react.item.reflect;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.util.Objects;

public final class ItemReflection {
    private static Class<?> craftClass;
    private static Class<?> nmsClass;

    public static ItemStack toCraftMirror(Object nmsItem) {
        try {
            Class<?> craftClass = Objects.requireNonNull(getCraftClass());
            Method craft$asCraftMirror = craftClass.getMethod("asCraftMirror", getNMSClass());
            Object craftItem = craft$asCraftMirror.invoke(null, nmsItem);
            return (ItemStack) craftItem;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Object toNMS(ItemStack itemStack) {
        try {
            Class<?> itemClass = Objects.requireNonNull(getCraftClass());
            Method item$asNMSCopy = itemClass.getMethod("asNMSCopy", ItemStack.class);

            return item$asNMSCopy.invoke(null, itemStack);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Object getTag(Object nmsItem) {
        try {
            Class<?> itemClass = Objects.requireNonNull(getNMSClass());
            Method item$getTag = itemClass.getMethod("getTag");
            return item$getTag.invoke(nmsItem);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static Class<?> getCraftClass() {
        if (craftClass != null) {
            return craftClass;
        }

        try {
            return craftClass = MinecraftReflection.getCraftClass("inventory.CraftItemStack");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static Class<?> getNMSClass() {
        if (nmsClass != null) {
            return nmsClass;
        }

        try {
            return nmsClass = MinecraftReflection.getNMSClass("ItemStack");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
