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
 *
 * @author maladr0it
 */
public abstract class Module implements ClassDependent<Module>, YAMLSettable, Enableable {

    /**
     * Map of modules this one depends on.
     */
    protected final Map<Class<? extends Module>, Module> dependencies = new HashMap<>();

    /**
     * The configuration for this module.
     */
    protected YamlConfiguration config = new YamlConfiguration();

    /**
     * The plugin this module is associated with.
     */
    protected final Plugin plugin;

    /**
     * The name of this module.
     */
    protected final String name;


    /**
     * Creates a new module for the specified {@link org.bukkit.plugin.Plugin} with the specified name.
     *
     * @param plugin The plugin.
     * @param name The name of this module.
     */
    public Module(Plugin plugin, String name) {
        Validate.notNull(plugin);
        Validate.notNull(name);
        this.plugin = plugin;
        this.name = name;
    }

    /**
     * Gets the {@link org.bukkit.plugin.Plugin} this module is associated with.
     *
     * @return The plugin.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the name of this module.
     *
     * @return
     */
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

    /**
     * Called when this module's individual settings are loaded.
     */
    protected void onSettingsLoad() {}

    /**
     * Called right before this module's individual settings are saved.
     */
    protected void preSettingsSave() { }
}
