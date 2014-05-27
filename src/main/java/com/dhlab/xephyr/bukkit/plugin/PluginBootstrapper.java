package com.dhlab.xephyr.bukkit.plugin;

import com.dhlab.xephyr.bukkit.commands.CommandFramework;
import com.dhlab.xephyr.bukkit.items.ItemManager;
import com.dhlab.xephyr.bukkit.module.ModuleManager;
import com.dhlab.xephyr.generic.Enableable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * A bootstrapper for setting up modules, commands, and other things.
 * @author maladr0it
 */
public abstract class PluginBootstrapper implements Enableable {

    protected final JavaPlugin plugin;
    protected final CommandFramework commandFramework;
    protected final ModuleManager moduleManager;
    protected final ItemManager itemManager;

    public PluginBootstrapper(JavaPlugin plugin) {
        if (plugin == null)
            throw new NullPointerException("Plugin cannot be null.");
        this.plugin = plugin;
        commandFramework = new CommandFramework(plugin);
        moduleManager = new ModuleManager(this);
        itemManager = new ItemManager(this);
    }

    /**
     * Gets the instance of the plugin this bootstrapper is for.
     * @return The instance of the plugin
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the CommandFramework that this bootstrapper uses.
     * @return The {@link CommandFramework} instance
     */
    public CommandFramework getCommandFramework() {
        return commandFramework;
    }

    /**
     * Gets the {@link ModuleManager} that this bootstrapper uses.
     * @return The ModuleManager instance
     */
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    /**
     * Gets the {@link ItemManager} that this bootstrapper uses.
     * @return
     */
    public ItemManager getItemManager() {
        return itemManager;
    }


    /**
     * Returns the plugin logger.
     * @return
     */
    public Logger getLogger() {
        return getPlugin().getLogger();
    }
}
