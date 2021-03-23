package me.ihdeveloper.react.item.state;

import me.ihdeveloper.react.item.api.ReactItemState;
import me.ihdeveloper.react.item.reflect.NBTReflection;

public class NBTReactItemState implements ReactItemState {

    private Object nbt;

    public NBTReactItemState() {
        this.nbt = NBTReflection.newInstance();
    }

    @Override
    public boolean getBoolean(String key) {
        return NBTReflection.getBoolean(nbt, key);
    }

    @Override
    public byte getByte(String key) {
        return NBTReflection.getByte(nbt, key);
    }

    @Override
    public short getShort(String key) {
        return NBTReflection.getShort(nbt, key);
    }

    @Override
    public int getInt(String key) {
        return NBTReflection.getInt(nbt, key);
    }

    @Override
    public long getLong(String key) {
        return NBTReflection.getLong(nbt, key);
    }

    @Override
    public float getFloat(String key) {
        return NBTReflection.getFloat(nbt, key);
    }

    @Override
    public double getDouble(String key) {
        return NBTReflection.getDouble(nbt, key);
    }

    @Override
    public String getString(String key) {
        return NBTReflection.getString(nbt, key);
    }

    @Override
    public byte[] getByteArray(String key) {
        return NBTReflection.getByteArray(nbt, key);
    }

    @Override
    public int[] getIntArray(String key) {
        return NBTReflection.getIntArray(nbt, key);
    }

    @Override
    public boolean hasKey(String key) {
        return NBTReflection.hasKey(nbt, key);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        NBTReflection.setBoolean(nbt, key, value);
    }

    @Override
    public void setByte(String key, byte value) {
        NBTReflection.setByte(nbt, key, value);
    }

    @Override
    public void setShort(String key, short value) {
        NBTReflection.setShort(nbt, key, value);
    }

    @Override
    public void setInt(String key, int value) {
        NBTReflection.setInt(nbt, key, value);
    }

    @Override
    public void setLong(String key, long value) {
        NBTReflection.setLong(nbt, key, value);
    }

    @Override
    public void setFloat(String key, float value) {
        NBTReflection.setFloat(nbt, key, value);
    }

    @Override
    public void setDouble(String key, double value) {
        NBTReflection.setDouble(nbt, key, value);
    }

    @Override
    public void setString(String key, String value) {
        NBTReflection.setString(nbt, key, value);
    }

    @Override
    public void setByteArray(String key, byte[] value) {
        NBTReflection.setByteArray(nbt, key, value);
    }

    @Override
    public void setIntArray(String key, int[] value) {
        NBTReflection.setIntArray(nbt, key, value);
    }

    public Object getNBT() {
        return nbt;
    }

    @Override
    public String toString() {
        return "NBTReactItemState{" +
                "nbt=" + nbt +
                '}';
    }
}
