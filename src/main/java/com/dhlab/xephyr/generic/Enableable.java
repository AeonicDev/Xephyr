package com.dhlab.xephyr.generic;

/**
 * An interface describing objects that require access to the onEnable and onDisable methods on either a plugin
 * or another object.
 * @author maladr0it
 */
public interface Enableable {
    void onEnable();
    void onDisable();
}
