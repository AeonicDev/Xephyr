package com.aeonicdev.xephyr.bukkit.kit.base;

import com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper;
import com.aeonicdev.xephyr.generic.Enableable;
import com.aeonicdev.xephyr.generic.management.ManagedNotFoundException;
import com.aeonicdev.xephyr.generic.management.Manager;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * A manager for kits, interfaces directly with
 * @author maladr0it
 */
public class KitManager implements Manager<Kit, String>, Enableable {

    /**
     * The bootstrapper to manage kits for.
     */
    protected final PluginBootstrapper bootstrapper;
    /**
     * A map of string->kit to hold all of the enabled kits
     */
    protected final Map<String, Kit> kits = new HashMap<String, Kit>();

    /**
     * Accepts a pluginBootstrapper as an argument
     * @param bootstrap
     */
    public KitManager(PluginBootstrapper bootstrap) {
        Validate.notNull(bootstrap);
        this.bootstrapper = bootstrap;
    }

    public PluginBootstrapper getBootstrapper() {
        return bootstrapper;
    }

    @Override
    public Class<Kit> getManagedType() {
        return Kit.class;
    }

    @Override
    public void add(Kit obj) {
        Validate.notNull(obj);
    }

    @Override
    public void remove(Kit obj) {
        kits.remove(obj.getName());
    }

    @Override
    public void removeByID(String obj) {
        kits.remove(obj);
    }

    @Override
    public boolean has(String obj) {
        return kits.get(obj) != null;
    }

    @Override
    public Kit[] getContents() {
        return this.kits.values().toArray(new Kit[kits.size()]);
    }

    @Override
    public Kit find(String identifier) throws ManagedNotFoundException {
        Kit k = kits.get(identifier);
        if (k == null)
            throw new ManagedNotFoundException(identifier);
        return k;
    }

    @Override
    public Kit[] findAllByOne(String identifier) throws ManagedNotFoundException {
        return new Kit[]{find(identifier)};
    }

    @Override
    public Kit[] findAllByMany(String... identifiers) throws ManagedNotFoundException {
        return null;
    }

    @Override
    public void onEnable() {
        for (Kit k : this.kits.values()){
            k.onEnable();
        }
    }

    @Override
    public void onDisable() {
        for (Kit k : this.kits.values()){
            k.onDisable();
        }
    }
}
