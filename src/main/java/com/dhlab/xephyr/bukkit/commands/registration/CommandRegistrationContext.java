package com.dhlab.xephyr.bukkit.commands.registration;

/**
 * An interface that describes the registrator this context is for, and it's class.
 * This is used so you do not have to guess at the arguments a {@link com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrator} takes.
 *
 * @author maladr0it
 */
public interface CommandRegistrationContext<T extends CommandRegistrator> {

    /**
     * Gets the type of the executor for this context.
     *
     * @return The class of the executor.
     */
    Class<T> getExecutorType();

}
