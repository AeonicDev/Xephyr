package com.dhlab.xephyr.bukkit.kit.base.listen;

import org.bukkit.event.EventPriority;

/**
 * An interface that denotes a "Solid" handler, meaning it takes in a kit and an event as an argument.
 * @author maladr0it
 */
public @interface SolidHandler {
    EventPriority priority() default EventPriority.NORMAL;
}
