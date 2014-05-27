package com.dhlab.xephyr.bukkit.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The base plugin class that all Xephyr plugins must extend.
 * @author maladr0it
 */
public abstract class EnginePlugin extends JavaPlugin {

    protected PluginBootstrapper bootstrap;

    /**
     * Called when the plugin is enabled.
     * Should not be directly called.
     */
    @Override
    public void onEnable() {
        try {
            initialize();
            if (bootstrap != null)
                bootstrap.onEnable();
        } catch (Exception e) {
            setEnabled(false);
            e.printStackTrace();
        }

    }

    /**
     * Called when the plugin is disabled.
     * Should not be directly called.
     */
    @Override
    public void onDisable() {
        if (bootstrap != null)
            bootstrap.onDisable();
    }

    /**
     * Bridge method that hands commands to Xephyr's framework.
     *
     * @param snd The CommandSender
     * @param cmd The Command
     * @param lbl The command label
     * @param args Array of arguments
     * @return The result of the command; true for success, false if an error was encountered.
     */
    @Override
    public boolean onCommand(CommandSender snd, Command cmd, String lbl, String[] args) {
        return bootstrap != null && isEnabled() ? bootstrap.getCommandFramework().handleCommand(snd, cmd, lbl, args) : false;
    }

    /**
     * Method called upon being enabled; plugin MUST override this to create a PluginBootstrapper instance.
     */
    public abstract void initialize();
}
