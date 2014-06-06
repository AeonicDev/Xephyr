package com.dhlab.xephyr.generic.plugin;


import com.dhlab.xephyr.generic.plugin.loading.IPluginLoadContext;
import com.dhlab.xephyr.generic.plugin.loading.IPluginLoader;

/**
 * An interface describing an object that can have plugins added to it.
 * @author maladr0it
 */
public interface IPluginable<T extends IPlugin> {
    /**
     * Adds a plugin loader to the pluginable instance, allowing more contexts to be used when registering plugins
     * for this pluginable instance.
     * @param loader The plugin loader to add.
     */
    public void addPluginLoader(IPluginLoader<T, ?> loader);

    /**
     * Removes a plugin loader by the plugin load context it uses.
     * @param context The context to remove the loader by.
     */
    public void removePluginLoader(Class<? extends IPluginLoadContext> context);

    /**
     * Adds a plugin to the internal mapping
     * @param plugin The plugin to add
     */
    public void addPlugin(T plugin);

    /**
     * Removes a plugin from the internal mapping.
     * @param name The key to remove by
     */
    public void removePlugin(String name);

    /**
     * Returns a plugin from the internal mapping.
     * @param name The key to use to get the plugin
     * @param <K> The type argument to automatically cast to
     * @return The plugin or NULL if the plugin is not found.
     */
    public <K extends T> K getPlugin(String name);

    /**
     * Attempts to load plugins using the plugin load context provided.
     * @param context The context with which to load plugins.
     */
    public void load(IPluginLoadContext context);
}
