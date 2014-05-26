package com.dhlab.xephyr.bukkit.module;

import com.dhlab.xephyr.generic.Enableable;
import com.dhlab.xephyr.generic.dependency.ClassDependent;
import com.dhlab.xephyr.generic.dependency.DependencyNotSatisfiedException;
import com.dhlab.xephyr.generic.settings.YAMLSettable;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic module class. Has methods for handling dependencies.
 * @author maladr0it
 */
public abstract class Module implements ClassDependent<Module>, YAMLSettable, Enableable {

    protected final Map<Class<? extends Module>, Module> dependencies = new HashMap<>();
    protected YamlConfiguration config = new YamlConfiguration();
    protected final Plugin plugin;
    protected final String name;


    public Module(Plugin plugin, String name) {
        Validate.notNull(plugin);
        Validate.notNull(name);
        this.plugin = plugin;
        this.name = name;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
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

    @Override
    public YamlConfiguration getSettings() {
        return config;
    }

    @Override
    public File getFile() {
        return new File(plugin.getDataFolder() + File.separator + "modules", getName() + ".yml");
    }

    @Override
    public void loadSettings(File f) {
        this.config = YamlConfiguration.loadConfiguration(f);
        onSettingsLoad();
    }

    @Override
    public void saveSettings(File f) {
        try {
            preSettingsSave();
            this.config.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onSettingsLoad() {}
    protected void preSettingsSave() { }
}
