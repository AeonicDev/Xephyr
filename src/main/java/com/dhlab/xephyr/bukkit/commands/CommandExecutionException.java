package com.dhlab.xephyr.bukkit.commands;

/**
 * To be thrown when an XCommandExecutor fails to execute, or by the methods themselves that fail.
 * @author maladr0it
 */
public class CommandExecutionException extends Exception {

    public CommandExecutionException(String message) {
        super(message);
    }
}
