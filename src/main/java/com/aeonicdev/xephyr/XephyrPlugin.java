package com.aeonicdev.xephyr;

import com.aeonicdev.xephyr.bukkit.plugin.EnginePlugin;

/**
 * Main Plugin class for Xephyr
 * @author sc4re
 */
public class XephyrPlugin extends EnginePlugin {
    @Override
    public void initialize() {
        bootstrap = new Xephyr(this);
    }
}
