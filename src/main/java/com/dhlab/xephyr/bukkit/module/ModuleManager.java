package com.dhlab.xephyr.bukkit.module;

import com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper;
import com.dhlab.xephyr.generic.Enableable;
import com.dhlab.xephyr.generic.dependency.DependencyNotSatisfiedException;
import com.dhlab.xephyr.generic.management.ClassBasedManager;
import com.dhlab.xephyr.generic.management.ManagedNotFoundException;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.*;

/**
 * Module manager implementation.
 * @author maladr0it
 */
public class ModuleManager implements ClassBasedManager<Module>, Enableable {
    protected final Map<Class<? extends Module>, Module> modules = new HashMap<>();

    protected final PluginBootstrapper bootstrap;

    public ModuleManager(PluginBootstrapper bootstrap) {
        Validate.notNull(bootstrap);
        this.bootstrap = bootstrap;
    }

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
    public Module[] findAllByMany(Class<? extends Module>... identifier) throws ManagedNotFoundException {
        Validate.notNull(identifier);
        List<Module> modules = new ArrayList<Module>();
        for (Class<? extends Module> id : identifier) {
            try {
                modules.addAll(Arrays.asList(findAllByOne(id)));
            } catch (ManagedNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (modules.size() == 0)
            throw new ManagedNotFoundException(identifier);
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

            m.loadSettings(m.getFile());
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
