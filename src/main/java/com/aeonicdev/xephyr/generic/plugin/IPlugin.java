package com.aeonicdev.xephyr.generic.plugin;

/**
 * The basic interface describing the barest of APIs for a plugin to have.
 * @param <T> The "pluginable" interface for this class to be a plugin for.
 * @author sc4re
 */
public interface IPlugin<T extends IPluginable> {
    /**
    /**
     * Called when the plugin is loaded.
     * @param obj The IPluginable instance to load with.
     */
    public void onLoad(T obj);

    /**
     * Called when the plugin is unloaded.
     * @param obj The IPluginable instance to unload from.
     */
    public void onUnload(T obj);

    /**
     * Returns the name of the
     * @return
     */
    public String getName();
}
