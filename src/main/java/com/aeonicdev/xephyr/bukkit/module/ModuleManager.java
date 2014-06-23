package com.aeonicdev.xephyr.bukkit.module;

import com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper;
import com.aeonicdev.xephyr.generic.Enableable;
import com.aeonicdev.xephyr.generic.dependency.DependencyNotSatisfiedException;
import com.aeonicdev.xephyr.generic.management.ClassBasedManager;
import com.aeonicdev.xephyr.generic.management.ManagedNotFoundException;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.*;

/**
 * Module manager implementation.
 *
 * @author maladr0it
 */
public class ModuleManager implements ClassBasedManager<Module>, Enableable {

    /**
     * The internal storage mechanism for this manager.
     * Stores modules indexed by their class.
     */
    protected final Map<Class<? extends Module>, Module> modules = new HashMap<>();

    /**
     * The plugin bootstrapper for this manager.
     */
    protected final PluginBootstrapper bootstrap;

    /**
     * Creates a new {@code ModuleManager} instance for the specified {@link com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper}.
     *
     * @param bootstrap The plugin bootstrapper.
     */
    public ModuleManager(PluginBootstrapper bootstrap) {
        Validate.notNull(bootstrap);
        this.bootstrap = bootstrap;
    }

    /**
     * Gets the {@link com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper} for this manager.
     *
     * @return The plugin bootstrapper.
     */
    public PluginBootstrapper getBootstrapper() {
        return bootstrap;
    }

    @Override
    public Class<Module> getManagedType() {
        return Module.class;
    }

    @Override
    public void add(Module obj) {
        modules.put(obj.getClass(), obj);
    }

    @Override
    public void remove(Module obj) {
        modules.remove(obj.getClass());
    }

    @Override
    public void removeByID(Class<? extends Module> obj) {
        modules.remove(obj);
    }

    @Override
    public boolean has(Class<? extends Module> obj) {
        return modules.get(obj) != null;
    }

    @Override
    public Module[] getContents() {
        return modules.values().toArray(new Module[modules.size()]);
    }

    @Override
    public Module find(Class<? extends Module> identifier) throws ManagedNotFoundException {
        Validate.notNull(identifier);
        Module found = modules.get(identifier);
        if (found == null)
            throw new ManagedNotFoundException(identifier);
        return found;
    }

    @Override
    public Module[] findAllByOne(Class<? extends Module> identifier) throws ManagedNotFoundException {
        Validate.notNull(identifier);
        List<Module> found = new ArrayList<Module>();
        for (Module m : getContents()) {
            if (m.getClass().isInstance(identifier))
                found.add(m);
        }
        if (found.size() == 0)
            throw new ManagedNotFoundException(identifier);
        return found.toArray(new Module[found.size()]);
    }

    @Override
    public Module[] findAllByMany(Class<? extends Module>... identifiers) throws ManagedNotFoundException {
        Validate.notNull(identifiers);
        List<Module> modules = new ArrayList<Module>();
        for (Class<? extends Module> id : identifiers) {
            try {
                modules.addAll(Arrays.asList(findAllByOne(id)));
            } catch (ManagedNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (modules.size() == 0)
            throw new ManagedNotFoundException(identifiers);
        return modules.toArray(new Module[modules.size()]);
    }

    @Override
    public void onEnable() {
        for (Module m : this.getContents()) {
            List<Module> dependencies = new ArrayList<>();

            for (Class c : m.getDependencyIdentifiers()) {
                Class<? extends Module> klass = c.asSubclass(Module.class);
                try {
                    dependencies.add(find(klass));
                } catch (ManagedNotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                m.satisfy(dependencies.toArray(new Module[dependencies.size()]));
            } catch (DependencyNotSatisfiedException e) {
                e.printStackTrace();
                continue;
            }

            if (m instanceof Listener) {
                Bukkit.getPluginManager().registerEvents((Listener)m, bootstrap.getPlugin());
            }

            m.onEnable();
        }
    }

    @Override
    public void onDisable() {
        for (Module m : this.getContents()) {
            m.onDisable();
        }
    }

}
