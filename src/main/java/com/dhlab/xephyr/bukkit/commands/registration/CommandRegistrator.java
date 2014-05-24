package com.dhlab.xephyr.bukkit.commands.registration;

import com.dhlab.xephyr.bukkit.commands.CommandFramework;

/**
 * A Generic class that allows for various types of registration to be used in the CommandFramework.
 * It takes a type argument of a CommandRegistrationContext, so that it can be passed that type of context when
 * the CommandRegistrator's commands are registered.
 * @author maladr0it
 */
public interface CommandRegistrator<T extends CommandRegistrationContext> {
    public Class<T> getContextType();
    public void registerCommands(CommandFramework framework, T context) throws CommandRegistrationException;
}
