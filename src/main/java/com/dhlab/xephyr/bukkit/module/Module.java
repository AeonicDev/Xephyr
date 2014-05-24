package com.dhlab.xephyr.bukkit.module;

import com.dhlab.xephyr.generic.dependency.ClassDependent;
import com.dhlab.xephyr.generic.dependency.DependencyNotSatisfiedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic module class. Has methods for handling dependencies.
 * @author maladr0it
 */
public class Module implements ClassDependent<Module> {

    protected final Map<Class<? extends Module>, Module> dependencies = new HashMap<>();
    protected final String name;

    public Module(String name) {
        this.name = name;
    }

    @Override
    public Class<? extends Module>[] getDependencyIdentifiers() {
        return dependencies.keySet().toArray(new Class[dependencies.size()]);
    }

    @Override
    public Module[] getSatisfied() {
        return dependencies.values().toArray(new Module[dependencies.size()]);
    }

    @Override
    public void satisfy(Module... objects) throws DependencyNotSatisfiedException {
        for (Module m : objects) {
            dependencies.put(m.getClass().asSubclass(Module.class), m);
        }
        for (Map.Entry<Class<? extends Module>, Module> entry : dependencies.entrySet()) {
            if (entry.getValue() == null)
                throw new DependencyNotSatisfiedException(entry.getKey());
        }
    }

    @Override
    public <H extends Module> H getDependency(Class<? extends Module> id) {
        return (H)dependencies.get(id);
    }

    @Override
    public void require(Class<? extends Module> id) {
        dependencies.put(id, null);
    }
}
