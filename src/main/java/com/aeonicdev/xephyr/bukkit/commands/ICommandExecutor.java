package com.aeonicdev.xephyr.bukkit.commands;

/**
 * To be used by CommandExecutor types for different types of command registration.
 *
 * @author maladr0it
 */
public interface ICommandExecutor {

    /**
     * The command label.
     *
     * @return The command label.
     */
    String label();

    /**
     * Handles a command with the specified arguments.
     *
     * @param args The command arguments.
     */
    void handleCommand(CommandArgs args);

}
