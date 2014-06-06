package com.dhlab.xephyr.generic.plugin.loading;


import com.dhlab.xephyr.generic.plugin.IPlugin;
import com.dhlab.xephyr.generic.plugin.IPluginable;

/**
 * A context-specific plugin loader API that accepts a specific plugin type and
 * load context. These can be registered in the IPluginable interface.
 * @author maladr0it
 */
public interface IPluginLoader<T extends IPlugin, K extends IPluginLoadContext> {

    /**
     * The plugin type this plugin loader returns.
     * @return The class of the plugin type used.
     */
    public Class<T> getPluginType();

    /**
     * The context type that this plugin loader accepts.
     * @return The class of the context that is accepted by this plugin loader.
     */
    public Class<K> getContextType();

    /**
     * Loads the plugins into the plugin manager using the context provided.
     * @param pluginable The plugin manager to load plugins into.
     * @param context The context to use when loading plugins.
     */
    public void loadPlugins(IPluginable<T> pluginable, IPluginLoadContext context) throws Exception;
}
