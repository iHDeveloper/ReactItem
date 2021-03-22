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

    public static boolean getBoolean(Object nbt, String key) {
        try {
            Method nbt$getBoolean = Objects.requireNonNull(getNBTClass()).getMethod("getBoolean", String.class);
            return (boolean) nbt$getBoolean.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static byte getByte(Object nbt, String key) {
        try {
            Method nbt$getByte = Objects.requireNonNull(getNBTClass()).getMethod("getByte", String.class);
            return (byte) nbt$getByte.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static short getShort(Object nbt, String key) {
        try {
            Method nbt$getShort = Objects.requireNonNull(getNBTClass()).getMethod("getShort", String.class);
            return (short) nbt$getShort.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static int getInt(Object nbt, String key) {
        try {
            Method nbt$getInt = Objects.requireNonNull(getNBTClass()).getMethod("getInt", String.class);
            return (int) nbt$getInt.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static long getLong(Object nbt, String key) {
        try {
            Method nbt$getLong = Objects.requireNonNull(getNBTClass()).getMethod("getLong", String.class);
            return (long) nbt$getLong.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static float getFloat(Object nbt, String key) {
        try {
            Method nbt$getFloat = Objects.requireNonNull(getNBTClass()).getMethod("getFloat", String.class);
            return (float) nbt$getFloat.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static double getDouble(Object nbt, String key) {
        try {
            Method nbt$getDouble = Objects.requireNonNull(getNBTClass()).getMethod("getDouble", String.class);
            return (double) nbt$getDouble.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static String getString(Object nbt, String key) {
        try {
            Method nbt$getString = Objects.requireNonNull(getNBTClass()).getMethod("getString", String.class);
            return (String) nbt$getString.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static byte[] getByteArray(Object nbt, String key) {
        try {
            Method nbt$getByteArray = Objects.requireNonNull(getNBTClass()).getMethod("getByteArray", String.class);
            return (byte[]) nbt$getByteArray.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static int[] getIntArray(Object nbt, String key) {
        try {
            Method nbt$getIntArray = Objects.requireNonNull(getNBTClass()).getMethod("getIntArray", String.class);
            return (int[]) nbt$getIntArray.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean hasKey(Object nbt, String key) {
        try {
            Method nbt$hasKey = Objects.requireNonNull(getNBTClass()).getMethod("hasKey", String.class);
            return (boolean) nbt$hasKey.invoke(nbt, key);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
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

    public static void setBoolean(Object nbt, String key, boolean value) {
        try {
            Method nbt$setBoolean = Objects.requireNonNull(getNBTClass()).getMethod("setBoolean", String.class, boolean.class);
            nbt$setBoolean.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setByte(Object nbt, String key, byte value) {
        try {
            Method nbt$setByte = Objects.requireNonNull(getNBTClass()).getMethod("setByte", String.class, byte.class);
            nbt$setByte.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setShort(Object nbt, String key, short value) {
        try {
            Method nbt$setShort = Objects.requireNonNull(getNBTClass()).getMethod("setShort", String.class, short.class);
            nbt$setShort.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setInt(Object nbt, String key, int value) {
        try {
            Method nbt$setInt = Objects.requireNonNull(getNBTClass()).getMethod("setInt", String.class, int.class);
            nbt$setInt.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setLong(Object nbt, String key, long value) {
        try {
            Method nbt$setLong = Objects.requireNonNull(getNBTClass()).getMethod("setLong", String.class, long.class);
            nbt$setLong.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setFloat(Object nbt, String key, float value) {
        try {
            Method nbt$setFloat = Objects.requireNonNull(getNBTClass()).getMethod("setFloat", String.class, float.class);
            nbt$setFloat.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setDouble(Object nbt, String key, double value) {
        try {
            Method nbt$setDouble = Objects.requireNonNull(getNBTClass()).getMethod("setDouble", String.class, double.class);
            nbt$setDouble.invoke(nbt, key, value);
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

    public static void setByteArray(Object nbt, String key, byte[] value) {
        try {
            Method nbt$setByteArray = Objects.requireNonNull(getNBTClass()).getMethod("setString", String.class, byte[].class);
            nbt$setByteArray.invoke(nbt, key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void setIntArray(Object nbt, String key, int[] value) {
        try {
            Method nbt$setIntArray = Objects.requireNonNull(getNBTClass()).getMethod("setString", String.class, int[].class);
            nbt$setIntArray.invoke(nbt, key, value);
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
