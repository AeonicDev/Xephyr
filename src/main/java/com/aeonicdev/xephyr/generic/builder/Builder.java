package com.aeonicdev.xephyr.generic.builder;

/**
 * Defines the API for a generic "builder" pattern object.
 * @param <T> The object to finally build at the end of configuration.
 * @author maladr0it
 */
public interface Builder<T> {
    /**
     * Create an instance of a [T] object from the settings provided.
     * @author maladr0it
     * @return
     */
    T build();
}
