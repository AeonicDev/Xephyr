package com.dhlab.xephyr.bukkit.commands;

/**
 * An argument holder with the ability to cast / parse the arguments into various other objects.
 *
 * @author maladr0it
 */
public class ArgumentPool {

    /**
     * The argument array.
     */
    protected final String[] basicArgs;

    /**
     * Creates a new argument pool with the specified argument array.
     *
     * @param basicArgs The argument array.
     */
    public ArgumentPool(String[] basicArgs) {
        this.basicArgs = basicArgs;
    }

    /**
     * Returns the basicArgs[index] object parsed into a byte.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public byte getByte(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Byte.parseByte(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a short.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public short getShort(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Short.parseShort(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into an integer.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public int getInt(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Integer.parseInt(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a long.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public long getLong(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Long.parseLong(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a float.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public float getFloat(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Float.parseFloat(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a double.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public double getDouble(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Double.parseDouble(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a string.
     *
     * @param index The array index.
     * @return The argument at the specified index.
     */
    public String getString(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return basicArgs[index];
    }

    /**
     * Gets the internal argument array.
     *
     * @return The argument array.
     */
    public String[] getBasicArgs() {
        return basicArgs;
    }

    /**
     * Gets the length of the internal argument array.
     *
     * @return The array length.
     */
    public int getLength() {
        return basicArgs.length;
    }

}
