package com.aeonicdev.xephyr.generic.plugin.impl;

import com.aeonicdev.xephyr.generic.plugin.IPlugin;
import com.aeonicdev.xephyr.generic.plugin.IPluginable;
import com.aeonicdev.xephyr.generic.plugin.loading.IPluginLoadContext;
import com.aeonicdev.xephyr.generic.plugin.loading.IPluginLoader;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Barebones implementation of a plugin manager.
 * @author maladr0it
 */
public class BasicPluginManager<T extends IPlugin<?>> implements IPluginable<T> {

    /**
     * The map that is populated with loaders.
     */
    protected final Map<Class<? extends IPluginLoadContext>, IPluginLoader<T, ?>> loaders = new HashMap<>();
    /**
     * The map containing all of the plugins.
     */
    protected final Map<String, T> plugins = new HashMap<>();


    @Override
    public void addPluginLoader(IPluginLoader<T, ?> loader) {
        Class<? extends IPluginLoadContext> klass = loader.getContextType();
        if (loaders.get(klass) != null)
            throw new UnsupportedOperationException("Loader already implemented for that context!");
        loaders.put(klass, loader);
    }

    @Override
    public void removePluginLoader(Class<? extends IPluginLoadContext> context) {
        Validate.notNull(context);
        loaders.remove(context);
    }

    @Override
    public void addPlugin(T plugin) {
        Validate.notNull(plugin);
        if (plugins.get(plugin.getName()) != null)
            throw new UnsupportedOperationException("Cannot replace already-added plugin!");
        plugins.put(plugin.getName(), plugin);
    }

    @Override
    public void removePlugin(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        plugins.remove(name);
    }

    @Override
    public <K extends T> K getPlugin(String name) {
        return (K)plugins.get(name);
    }

    @Override
    public void load(IPluginLoadContext context) {
        if (loaders.get(context.getClass().asSubclass(IPluginLoadContext.class)) == null)
            throw new UnsupportedOperationException("A loader for that context type has not yet been implemented!");
        try {
            loaders.get(context.getClass().asSubclass(IPluginLoadContext.class)).loadPlugins(this, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
