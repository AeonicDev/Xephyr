package com.dhlab.xephyr.bukkit.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin with automatically set-up CommandFramework and PluginBootstrapper usage
 * @author maladr0it
 */
public abstract class EnginePlugin extends JavaPlugin {

    protected PluginBootstrapper bootstrap;

    @Override
    public void onEnable() {
        try {
            initialize();
            if (bootstrap != null)
                bootstrap.onEnable();
        } catch (Exception e) {
            // disable, print the error
            setEnabled(false);
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        if (bootstrap != null)
            bootstrap.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender snd, Command cmd, String lbl, String[] args) {
        return bootstrap != null && isEnabled() ? bootstrap.getCommandFramework().handleCommand(snd, cmd, lbl, args) : false;
    }

    /**
     * Must override this to create a PluginBootstrapper instance.
     */
    public abstract void initialize();
}
