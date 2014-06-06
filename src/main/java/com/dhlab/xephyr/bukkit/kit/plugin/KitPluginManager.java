package com.dhlab.xephyr.bukkit.kit.plugin;

import com.dhlab.xephyr.bukkit.kit.base.listen.FluidKitListener;
import com.dhlab.xephyr.bukkit.kit.base.listen.KitStateListener;
import com.dhlab.xephyr.bukkit.kit.plugin.load.directory.KitPluginDirectoryLoader;
import com.dhlab.xephyr.bukkit.kit.plugin.load.internal.KitPluginInternalLoader;
import com.dhlab.xephyr.generic.plugin.impl.BasicPluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides an API for loading "KitPlugin"s through various sources.
 * Acts like the command system in that you can give different contexts for loading plugins and
 * have them act correctly.
 * @author maladr0it
 */
public class KitPluginManager extends BasicPluginManager<KitPlugin> {
    /**
     * The fluid kit listeners provided by the plugins.
     */
    protected final Map<String, List<FluidKitListener>> listeners = new HashMap<>();
    /**
     * The state listeners provided by the plugins.
     * Mapping is KitPlugin name -> List of state listeners
     */
    protected final Map<String, List<KitStateListener>> stateListeners = new HashMap<>();

    private KitPluginManager() {
        // do the do of a default
        addPluginLoader(new KitPluginDirectoryLoader());
        addPluginLoader(new KitPluginInternalLoader());
    }

    /**
     * Adds a FluitKitListener to the plugin manager.
     * @param listener The listener to add.
     */
    public void addFluidListener(FluidKitListener listener) {
        KitPlugin plugin = listener.getBasePlugin();
        List<FluidKitListener> current = this.listeners.get(plugin.getName());
        if (current == null)
            current = new ArrayList<>();
        current.add(listener);
        listeners.put(plugin.getName(), current);
    }
    /**
     * Adds a KitStateListener to the plugin manager.
     * @param listener The listener to add
     */
    public void addStateListener(KitStateListener listener) {
        KitPlugin plugin = listener.getPlugin();
        List<KitStateListener> current = stateListeners.get(plugin.getName());
        if (current == null)
            current = new ArrayList<>();
        current.add(listener);
        stateListeners.put(plugin.getName(), current);
    }

    public FluidKitListener findFluidListenerByPlugin(KitPlugin plugin, String name) {
        List<FluidKitListener> listeners = this.listeners.get(plugin.getName());
        if (listeners == null)
            return null;
        for (FluidKitListener listener : listeners) {
            if (listener.getName().equals(name))
                return listener;
        }
        return null;
    }

    public KitStateListener findStateListenerByPlugin(KitPlugin plugin, String name) {
        List<KitStateListener> listeners = this.stateListeners.get(plugin.getName());
        if (listeners == null)
            return null;
        for (KitStateListener l : listeners) {
            if (l.getName().equals(name))
                return l;
        }
        return null;
    }

    private static final KitPluginManager instance = new KitPluginManager();
    public static final KitPluginManager get() { return instance; }
}
