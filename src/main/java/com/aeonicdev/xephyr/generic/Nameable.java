package com.aeonicdev.xephyr.generic;

/**
 * Allows a named object to have its name changed.
 *
 * @author sc4re
 */
public interface Nameable extends Named {

    /**
     * Sets the name of this object.
     *
     * @param name The new name.
     */
    void setName(String name);

}
