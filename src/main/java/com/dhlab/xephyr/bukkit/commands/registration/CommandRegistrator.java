package com.dhlab.xephyr.bukkit.commands.registration;

import com.dhlab.xephyr.bukkit.commands.CommandFramework;

/**
 * A Generic class that allows for various types of registration to be used in the {@link com.dhlab.xephyr.bukkit.commands.CommandFramework}.
 * It takes a type argument of a {@link com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext},
 * so that it can be passed that type of context when the {@code CommandRegistrator} commands are registered.
 *
 * @author maladr0it
 */
public interface CommandRegistrator<T extends CommandRegistrationContext> {

    /**
     * Gets the type of the {@link com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext}.
     * @return The class of the registration context.
     */
    public Class<T> getContextType();

    /**
     * Registers the commands from the specified {@link com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext}.
     *
     * @param framework The command framework.
     * @param context The registration context.
     * @throws CommandRegistrationException When commands fail to register.
     */
    public void registerCommands(CommandFramework framework, T context) throws CommandRegistrationException;

}
