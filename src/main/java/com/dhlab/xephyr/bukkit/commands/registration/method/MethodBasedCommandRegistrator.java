package com.dhlab.xephyr.bukkit.commands.registration.method;

import com.dhlab.xephyr.bukkit.commands.CommandArgs;
import com.dhlab.xephyr.bukkit.commands.CommandFramework;
import com.dhlab.xephyr.bukkit.commands.executors.MethodBasedCommandExecutor;
import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationException;
import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrator;
import com.dhlab.xephyr.bukkit.commands.registration.context.MethodRegistrationContext;

import java.lang.reflect.Method;

/**
 * Registers methods on an object with the @Command annotation.
 *
 * @author maladr0it
 */
public class MethodBasedCommandRegistrator implements CommandRegistrator<MethodRegistrationContext> {

    /**
     * Get the type of registration context.
     *
     * @return The class of the context type.
     */
    @Override
    public Class<MethodRegistrationContext> getContextType() {
        return MethodRegistrationContext.class;
    }

    /**
     * Register commands from the context.
     *
     * @param framework The command framework instance.
     * @param context The context in which to register the commands.
     * @throws CommandRegistrationException
     */
    @Override
    public void registerCommands(CommandFramework framework, MethodRegistrationContext context) throws CommandRegistrationException {
        Object toRegister = context.getRegistered();

        for (Method m : toRegister.getClass().getMethods()) {
            Command annotation = m.getAnnotation(Command.class);
            if (annotation == null)
                continue;
            // check parameter types, make sure we don't crap a brick later on due to incompatible method parameters
            if (m.getParameterTypes().length != 1 || !m.getParameterTypes()[0].equals(CommandArgs.class)) {
                // TODO - Add logging in a decent manner upon failure
                continue;
            }

            // add the base executor
            framework.addExecutor(new MethodBasedCommandExecutor(annotation.name(), toRegister, m));

            // All of the aliases, too.
            for (String s : annotation.aliases()) {
                framework.addExecutor(new MethodBasedCommandExecutor(s, toRegister, m));
            }
        }
    }

}
