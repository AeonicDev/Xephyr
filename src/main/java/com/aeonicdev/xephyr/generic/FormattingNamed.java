package com.aeonicdev.xephyr.generic;

/**
 * An interface describing an object that can have a formatted and unformatted name.
 *
 * @author sc4re
 */
public interface FormattingNamed extends Named {

    /**
     * Gets the unformatted name of this object.
     *
     * @return The unformatted name.
     */
    String getUnformattedName();

}
