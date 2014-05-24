package com.dhlab.xephyr.generic;

/**
 * Toggleable interface (usually for modules and the like)
 * @author maladr0it
 */
public interface Toggleable extends Enableable {
    boolean isEnabled();
    void setEnabled(boolean enabled);
    void toggle();
}
