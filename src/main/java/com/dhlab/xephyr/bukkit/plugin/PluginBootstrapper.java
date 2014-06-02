package com.dhlab.xephyr.bukkit.plugin;

import com.dhlab.xephyr.bukkit.commands.CommandFramework;
import com.dhlab.xephyr.bukkit.items.ItemManager;
import com.dhlab.xephyr.bukkit.menu.MenuManager;
import com.dhlab.xephyr.bukkit.module.ModuleManager;
import com.dhlab.xephyr.generic.Enableable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * A bootstrapper for setting up modules, commands, and other things.
 *
 * @author maladr0it
 * @see com.dhlab.xephyr.generic.Enableable
 */
public abstract class PluginBootstrapper implements Enableable {

    /**
     * The Bukkit plugin being bootstrapped.
     */
    protected final JavaPlugin plugin;

    /**
     * The CommandFramework instance for this bootstrapper.
     */
    protected final CommandFramework commandFramework;

    /**
     * The ModuleManager instance for this bootstrapper.
     */
    protected final ModuleManager moduleManager;

    /**
     * The ItemManager instance for this bootstrapper.
     */
    protected final ItemManager itemManager;

    /**
     * The MenuManager instance for this bootstrapper.
     */
    protected final MenuManager menuManager;

    /**
     * Creates a new PluginBootstrapper instance with the specified Bukkit {@link org.bukkit.plugin.java.JavaPlugin} instance.
     *
     * @param plugin The Bukkit JavaPlugin to bootstrap.
     */
    public PluginBootstrapper(JavaPlugin plugin) {
        if (plugin == null) throw new NullPointerException("Plugin cannot be null.");
        this.plugin = plugin;
        commandFramework = new CommandFramework(plugin);
        moduleManager = new ModuleManager(this);
        itemManager = new ItemManager(this);
        menuManager = new MenuManager(this);
    }

    /**
     * Gets the instance of the plugin this bootstrapper is for.
     *
     * @return The instance of the plugin.
     */
    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the {@link com.dhlab.xephyr.bukkit.commands.CommandFramework} that this bootstrapper uses.
     *
     * @return The CommandFramework instance.
     */
    public CommandFramework getCommandFramework() {
        return commandFramework;
    }

    /**
     * Gets the {@link com.dhlab.xephyr.bukkit.module.ModuleManager} that this bootstrapper uses.
     *
     * @return The ModuleManager instance.
     */
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    /**
     * Gets the {@link com.dhlab.xephyr.bukkit.items.ItemManager} that this bootstrapper uses.
     *
     * @return The ItemManager instance.
     */
    public ItemManager getItemManager() {
        return itemManager;
    }

    /**
     * Gets the {@link com.dhlab.xephyr.bukkit.menu.MenuManager} that this bootstrapper uses.
     *
     * @return The MenuManager instance.
     */
    public MenuManager getMenuManager() {
        return this.menuManager;
    }


    /**
     * Gets the plugin logger.
     *
     * @return The plugin Logger instance from Bukkit.
     */
    public Logger getLogger() {
        return getPlugin().getLogger();
    }

}
