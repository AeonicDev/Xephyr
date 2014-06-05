package com.dhlab.xephyr.bukkit.kit;

import com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper;
import com.dhlab.xephyr.generic.Enableable;
import com.dhlab.xephyr.generic.management.ManagedNotFoundException;
import com.dhlab.xephyr.generic.management.Manager;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * A manager for kits and other such things.
 * @author maladr0it
 */
public class KitManager implements Manager<Kit, String>, Enableable {

    protected final PluginBootstrapper bootstrapper;
    protected final Map<String, Kit> kits = new HashMap<String, Kit>();

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
