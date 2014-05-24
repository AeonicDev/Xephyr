package com.dhlab.xephyr.bukkit.commands;

/**
 * An argument holder with the ability to cast / parse the arguments into various other objects.
 * @author maladr0it
 */
public class ArgumentPool {

    protected final String[] basicArgs;

    public ArgumentPool(String[] basicArgs) {
        this.basicArgs = basicArgs;
    }

    /**
     * Returns the basicArgs[index] object parsed into a byte.
     * @param index
     * @return
     */
    public byte getByte(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Byte.parseByte(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a byte.
     * @param index
     * @return
     */
    public short getShort(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Short.parseShort(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into an integer.
     * @param index
     * @return
     */
    public int getInt(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Integer.parseInt(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a long.
     * @param index
     * @return
     */
    public long getLong(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Long.parseLong(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a float
     * @param index
     * @return
     */
    public float getFloat(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Float.parseFloat(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object parsed into a double
     * @param index
     * @return
     */
    public double getDouble(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return Double.parseDouble(basicArgs[index]);
    }

    /**
     * Returns the basicArgs[index] object
     * @param index
     * @return
     */
    public String getString(int index) {
        if (index < 0 || index >= getLength())
            throw new IllegalArgumentException("index");
        return basicArgs[index];
    }

    public String[] getBasicArgs() {
        return basicArgs;
    }

    public int getLength() {
        return basicArgs.length;
    }
}
