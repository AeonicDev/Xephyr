package com.dhlab.xephyr.generic;

/**
 * An interface describing an object that can have a formatted and unformatted name.
 */
public interface FormattingNameable extends Nameable {
    String getUnformattedName();
}
