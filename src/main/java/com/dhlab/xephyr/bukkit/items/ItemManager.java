package com.dhlab.xephyr.bukkit.items;

import com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper;
import com.dhlab.xephyr.generic.Enableable;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;

/**
 * The item manager class that handles registration of all SpecialItems
 * @author maladr0it
 */
public class ItemManager implements Enableable {

    protected final Map<Class<? extends SpecialItem>, SpecialItem>
            items = new HashMap<Class<? extends SpecialItem>, SpecialItem>();
    protected final PluginBootstrapper bootstrap;

    public ItemManager(PluginBootstrapper bootstrap) {
        this.bootstrap = bootstrap;
    }

    public <T extends SpecialItem> void add(T item) {
        items.put(item.getClass(), item);
    }

    @Override
    public void onEnable() {
        for (SpecialItem item : items.values()) {
            Bukkit.getPluginManager().registerEvents(item, bootstrap.getPlugin());
        }
    }

    @Override
    public void onDisable() {
        for (SpecialItem item : items.values()) {
            // we really don't actually need to do this.
            HandlerList.unregisterAll(item);
        }
    }
}
