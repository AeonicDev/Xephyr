package com.dhlab.xephyr.generic;

/**
 * Toggleable interface (usually for modules and the like).
 *
 * @author maladr0it
 */
public interface Toggleable extends Enableable {

    /**
     * Gets if this object is enabled.
     *
     * @return The enabled state.
     */
    boolean isEnabled();

    /**
     * Sets the enabled state of this object.
     *
     * @param enabled The new enabled state.
     */
    void setEnabled(boolean enabled);

    /**
     * Toggles the object.
     */
    void toggle();

}
