package com.dhlab.xephyr.generic;

/**
 * An interface that describes an object that can vary in size.
 *
 * @author maladr0it
 */
public interface Sizeable {

    /**
     * Gets the size of this object.
     *
     * @return The size.
     */
    int getSize();

    /**
     * Set the size of this object.
     *
     * @param newSize The new size of the object.
     */
    void setSize(int newSize);

}
