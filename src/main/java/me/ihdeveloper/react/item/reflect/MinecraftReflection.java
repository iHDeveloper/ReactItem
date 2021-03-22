package me.ihdeveloper.react.item.reflect;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MinecraftReflection {

    /** Used for caching the NMS version */
    private static String nmsVersion = null;

    public static Class<?> getCraftClass(String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + getVersion() + "." + name);
    }

    public static Class<?> getNMSClass(String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return Class.forName("net.minecraft.server." + getVersion() + "." + name);
    }

    public static String getVersion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (nmsVersion != null) {
            return nmsVersion;
        }

        Object server = Bukkit.getServer();
        Method server$getNMSServer = server.getClass().getMethod("getServer");

        Object nmsServer = server$getNMSServer.invoke(server);
        String[] names = nmsServer.getClass().getName().split("\\.");

        return nmsVersion = names[3];
    }

}
