package com.dhlab.xephyr.bukkit.kit.plugin;

import com.dhlab.xephyr.generic.plugin.IPlugin;

/**
 * Defines the API for a basic "Kit Plugin", allowing access to various different plugins
 * @author maladr0it
 */
public abstract class KitPlugin implements IPlugin<KitPluginManager> {

    private boolean enabled = false;

    @Override
    public void onLoad(KitPluginManager obj) {

    }

    @Override
    public void onUnload(KitPluginManager obj) {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
