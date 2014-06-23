package com.aeonicdev.xephyr.generic.helper;

import com.aeonicdev.xephyr.generic.helper.impl.HelperRegistryImpl;

/**
 * @author sc4re
 */
public interface HelperRegistry {
    /**
     * Adds a helper to the registry.
     * @param helper The helper to add.
     * @param <T> The generic type of the helper.
     */
    public <T extends Helper> void add(T helper);

    /**
     * Removes a helper from the registry.
     * @param klass The class of the helper to remove.
     * @param <T> The generic type of the helper.
     */
    public <T extends Helper> void remove(Class<T> klass);
    /**
     * Returns the helper associated with the class given.
     * @param klass The class to retrieve
     * @param <T> The generic type of the helper to return.
     * @return A helper.
     */
    public <T extends Helper> T get(Class<T> klass);


    public static final HelperRegistry INSTANCE = HelperRegistryImpl.get();
}
