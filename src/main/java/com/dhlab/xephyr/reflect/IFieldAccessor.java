package com.dhlab.xephyr.reflect;

/**
 * Defines the API for a Field accessor.
 * @author maladr0it
 */
public interface IFieldAccessor<T> {

    /**
     * Returns true if this field can be accessed in a safe manner, i.e; the field
     * has been found correctly and is accessible.
     * @return
     */
    boolean isValid();

    /**
     * Returns the value of
     * @param instance
     * @return
     */
    T get(Object instance);

    /**
     *
     * @param instance
     * @param value
     * @return
     */
    boolean set(Object instance, T value);

    /**
     * Transfersfrom
     * @param from
     * @param to
     * @return
     */
    T transfer(Object from, Object to);
}
