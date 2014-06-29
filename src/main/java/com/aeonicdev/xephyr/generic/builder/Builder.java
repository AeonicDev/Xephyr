package com.aeonicdev.xephyr.generic.builder;

/**
 * Defines the API for a generic "builder" pattern object.
 * @param <T> The object to finally build at the end of configuration.
 *
 * @author sc4re
 */
public interface Builder<T> {

    /**
     * Create an instance of a [T] object from the settings provided.
     * @author sc4re
     * @return
     */
    T build();

}
