package me.ihdeveloper.react.item.reflect;

import java.lang.reflect.Method;
import java.util.Objects;

public final class NBTReflection {

    private static Class<?> nbtClass;
    private static Class<?> nbtBaseClass;

    public static Object newInstance() {
        try {
            return Objects.requireNonNull(getNBTClass()).newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void set(Object nbt, String key, Object value) {
        try {
            Method nbt$set = Objects.requireNonNull(getNBTClass()).getMethod("set", String.class, getNBTBaseClass());
            nbt$set.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setString(Object nbt, String key, String value) {
        try {
            Method nbt$setString = Objects.requireNonNull(getNBTClass()).getMethod("setString", String.class, String.class);
            nbt$setString.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Class<?> getNBTClass() {
        if (nbtClass != null) {
            return nbtClass;
        }

        try {
            return nbtClass = MinecraftReflection.getNMSClass("NBTTagCompound");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static Class<?> getNBTBaseClass() {
        if (nbtBaseClass != null) {
            return nbtBaseClass;
        }

        try {
            return nbtBaseClass = MinecraftReflection.getNMSClass("NBTBase");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
