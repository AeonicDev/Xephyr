package com.aeonicdev.xephyr.bukkit.commands.registration;

/**
 * Thrown when a command fails to register.
 *
 * @author maladr0it
 */
public class CommandRegistrationException extends Exception {

    /**
     * Creates a new {@code CommandRegistrationException} with the specified message.
     *
     * @param message The message
     */
    public CommandRegistrationException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code CommandRegistrationException}.
     */
    public CommandRegistrationException() {
        super();
    }

}
