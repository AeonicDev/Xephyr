package com.dhlab.xephyr.plugin;

import com.dhlab.xephyr.bukkit.plugin.EnginePlugin;

/**
 * Main Plugin class for Xephyr
 * @author maladr0it
 */
public class XephyrPlugin extends EnginePlugin {
    @Override
    public void initialize() {
        bootstrap = new Xephyr(this);
    }
}
