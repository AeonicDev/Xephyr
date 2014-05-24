package com.dhlab.xephyr.bukkit.commands.registration;

/**
 * An interface that describes the registrator this context is for, and it's class.
 * This is used so you do not have to guess at the arguments a CommandRegistrator takes.
 * @author maladr0it
 */
public interface CommandRegistrationContext<T extends CommandRegistrator> {
    Class<T> getExecutorType();
}
