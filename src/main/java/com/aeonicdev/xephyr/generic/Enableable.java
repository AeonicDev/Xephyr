package com.aeonicdev.xephyr.generic;

/**
 * An interface describing objects that require access to the onEnable and onDisable methods on either
 * a plugin or another object.
 *
 * @author sc4re
 */
public interface Enableable {

    /**
     * Called when this object is enabled.
     */
    void onEnable();

    /**
     * Called when this object is disabled.
     */
    void onDisable();

}
