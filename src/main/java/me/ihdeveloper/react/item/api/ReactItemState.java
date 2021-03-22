package me.ihdeveloper.react.item.api;

public interface ReactItemState {

    /**
     * Returns byte value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public byte getByte(String key);

    /**
     * Returns short value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public short getShort(String key);

    /**
     * Returns integer value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public int getInt(String key);

    /**
     * Returns long value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public long getLong(String key);

    /**
     * Returns float value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public float getFloat(String key);

    /**
     * Returns double value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public double getDouble(String key);

    /**
     * Returns string value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public String getString(String key);

    /**
     * Returns byte array value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public byte[] getByteArray(String key);

    /**
     * Returns integer array value from given key
     *
     * @param key The name of the key
     * @return The value of the given key
     */
    public int[] getIntArray(String key);

    /**
     * Returns true when the checks the key
     *
     * @param key The name of the key
     * @return True when the key exists
     */
    public boolean hasKey(String key);

    /**
     * Sets a boolean value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setBoolean(String key, boolean value);

    /**
     * Sets a byte value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setByte(String key, byte value);

    /**
     * Sets a short value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setShort(String key, short value);

    /**
     * Sets a integer value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setInt(String key, int value);

    /**
     * Sets a long value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setLong(String key, long value);

    /**
     * Sets a float value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setFloat(String key, float value);

    /**
     * Sets a double value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setDouble(String key, double value);

    /**
     * Sets a string value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setString(String key, String value);

    /**
     * Sets a byte array value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setByteArray(String key, byte[] value);

    /**
     * Sets a integer array value within a key
     *
     * @param key The name of the key
     * @param value The value of the key
     */
    public void setIntArray(String key, int[] value);

}
