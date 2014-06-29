package com.aeonicdev.xephyr.generic.helper.impl;


import com.aeonicdev.xephyr.generic.helper.Helper;
import com.aeonicdev.xephyr.generic.helper.HelperRegistry;
import net.minecraft.util.org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of a {@link com.aeonicdev.xephyr.generic.helper.HelperRegistry}.
 *
 * @author sc4re
 */
public final class HelperRegistryImpl implements HelperRegistry {

    /**
     * The map of class -> helper.
     */
    protected final Map<Class<? extends Helper>, Helper> helpers = new HashMap<>();

    private HelperRegistryImpl() { }

    @Override
    public <T extends Helper> void add(T helper) {
        Validate.notNull(helper);
        if (helpers.get(helper.getClass().asSubclass(Helper.class)) != null)
            throw new RuntimeException("Helper already added!");
        helpers.put(helper.getClass().asSubclass(Helper.class), helper);
    }

    @Override
    public <T extends Helper> void remove(Class<T> klass) {
        Validate.notNull(klass);
        helpers.remove(klass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Helper> T get(Class<T> klass) {
        return (T)helpers.get(klass);
    }

    /**
     * The static instance of the HelperRegistry implementation.
     */
    private static final HelperRegistryImpl instance = new HelperRegistryImpl();

    /**
     * Gets the singleton instance of the registry.
     *
     * @return The helper registry instance.
     */
    public static final HelperRegistryImpl get() { return instance; }

}
