package com.aeonicdev.xephyr.generic;

/**
 * An interface describing an object that can have a formatted and unformatted name that can be changed.
 *
 * @author maladr0it
 */
public interface FormattingNameable extends Nameable {

    /**
     * Gets the unformatted name of this object.
     *
     * @return The unformatted name.
     */
    String getUnformattedName();

}
