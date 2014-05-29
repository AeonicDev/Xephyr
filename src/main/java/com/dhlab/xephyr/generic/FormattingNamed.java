package com.dhlab.xephyr.generic;

/**
 * An interface describing an object that can have a formatted and unformatted name.
 *
 * @author maladr0it
 */
public interface FormattingNamed extends Named {

    /**
     * Gets the unformatted name of this object.
     *
     * @return The unformatted name
     */
    String getUnformattedName();

}
