package com.dhlab.xephyr.bukkit.commands.registration;

/**
 * Thrown when a command fails to register.
 * @author maladr0it
 */
public class CommandRegistrationException extends Exception {

    public CommandRegistrationException(String h) {
        super(h);
    }
}
