package com.dhlab.xephyr.bukkit.commands;

/**
 * To be used by CommandExecutor types for different types of command registration.
 * @author maladr0it
 */
public interface XCommandExecutor {
    String label();
    void handleCommand(CommandArgs args);
}
