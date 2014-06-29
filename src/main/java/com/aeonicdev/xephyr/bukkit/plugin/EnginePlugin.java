package com.aeonicdev.xephyr.bukkit.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The base plugin class that all Xephyr plugins must extend.
 * @author sc4re
 */
public abstract class EnginePlugin extends JavaPlugin {

    /**
     * The plugin bootstrapper for this {@link org.bukkit.plugin.Plugin}.
     */
    protected PluginBootstrapper bootstrap;

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
     * Method called upon being enabled; plugin should override this to set the protected {@link com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper} instance.
     */
    public abstract void initialize();

}
