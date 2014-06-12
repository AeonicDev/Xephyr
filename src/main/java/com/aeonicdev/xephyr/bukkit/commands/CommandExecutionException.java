package com.aeonicdev.xephyr.bukkit.commands;

/**
 * To be thrown when an XCommandExecutor fails to execute, or by the methods themselves that fail.
 *
 * @author maladr0it
 */
public class CommandExecutionException extends Exception {

    /**
     * Creates a new {@code CommandExecutionException} with the specified message.
     *
     * @param message The message.
     */
    public CommandExecutionException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code CommandExecutionException}.
     */
    public CommandExecutionException() {
        super();
    }

}
