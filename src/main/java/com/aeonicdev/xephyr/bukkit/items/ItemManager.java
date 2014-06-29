package com.aeonicdev.xephyr.bukkit.items;

import com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper;
import com.aeonicdev.xephyr.generic.Enableable;
import com.aeonicdev.xephyr.generic.management.ClassBasedManager;
import com.aeonicdev.xephyr.generic.management.ManagedNotFoundException;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.*;

/**
 * The item manager class that handles registration of all {@link com.aeonicdev.xephyr.bukkit.items.SpecialItem} objects.
 *
 * @author sc4re
 */
public class ItemManager implements ClassBasedManager<SpecialItem>, Enableable {

    /**
     * The managed special items.
     */
    protected final Map<Class<? extends SpecialItem>, SpecialItem> items = new HashMap<Class<? extends SpecialItem>, SpecialItem>();

    /**
     * The bootstrapper this manager is for.
     */
    protected final PluginBootstrapper bootstrap;

    /**
     * Creates a new instance of this manager for the specified {@link com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper}.
     *
     * @param bootstrap The plugin bootstrapper.
     */
    public ItemManager(PluginBootstrapper bootstrap) {
        Validate.notNull(bootstrap);
        this.bootstrap = bootstrap;
    }

    @Override
    public void onEnable() {
        for (SpecialItem item : items.values()) {
            if (item instanceof Listener) {
                Bukkit.getPluginManager().registerEvents(item, bootstrap.getPlugin());
            }
        }
    }

    @Override
    public void onDisable() {
        for (SpecialItem item : items.values()) {
            if (item instanceof Listener) {
                HandlerList.unregisterAll(item);
            }
        }
    }

    @Override
    public Class<SpecialItem> getManagedType() {
        return SpecialItem.class;
    }

    @Override
    public void add(SpecialItem obj) {
        Validate.notNull(obj);
        this.items.put(obj.getClass(), obj);
    }

    @Override
    public void remove(SpecialItem obj) {
        this.removeByID(obj.getClass());
    }

    @Override
    public void removeByID(Class obj) {
        this.items.remove(obj);
    }

    @Override
    public boolean has(Class obj) {
        return this.items.containsKey(obj);
    }

    @Override
    public SpecialItem[] getContents() {
        return this.items.values().toArray(new SpecialItem[this.items.size()]);
    }

    @Override
    public SpecialItem find(Class identifier) throws ManagedNotFoundException {
        SpecialItem found = this.items.get(identifier);
        if (found == null) throw new ManagedNotFoundException();
        return found;
    }

    @Override
    public SpecialItem[] findAllByOne(Class identifier) throws ManagedNotFoundException {
        List<SpecialItem> found = new ArrayList<SpecialItem>();

        for (SpecialItem item : this.items.values()) {
            if (item.getClass().equals(identifier)) {
                found.add(item);
            }
        }

        if (found.size() == 0) throw new ManagedNotFoundException(identifier);

        return found.toArray(new SpecialItem[found.size()]);
    }

    @Override
    public SpecialItem[] findAllByMany(Class... identifiers) throws ManagedNotFoundException {
        Validate.notNull(identifiers);

        List<SpecialItem> found = new ArrayList<SpecialItem>();

        for (Class<? extends SpecialItem> id : identifiers) {
            try {
                found.addAll(Arrays.asList(this.findAllByOne(id)));
            } catch(ManagedNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (found.size() == 0) throw new ManagedNotFoundException(identifiers);

        return found.toArray(new SpecialItem[found.size()]);
    }
}
