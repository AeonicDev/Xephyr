package com.dhlab.xephyr.bukkit.commands.registration.wrapper;

import com.dhlab.xephyr.bukkit.commands.CommandFramework;
import com.dhlab.xephyr.bukkit.commands.executors.WrapperBasedCommandExecutor;
import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationException;
import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrator;
import com.dhlab.xephyr.bukkit.commands.registration.context.WrapperRegistrationContext;

/**
 * Registrator for {@link com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper} objects.
 *
 * @author maladr0it
 */
public class WrapperBasedCommandRegistrator implements CommandRegistrator<WrapperRegistrationContext> {

    /**
     * Gets the type of the registration context.
     *
     * @return The class of the registration context.
     */
    @Override
    public Class<WrapperRegistrationContext> getContextType() {
        return WrapperRegistrationContext.class;
    }

    /**
     * Registers commands in the specified context.
     *
     * @param framework The command framework.
     * @param context The registration context.
     * @throws CommandRegistrationException
     */
    @Override
    public void registerCommands(CommandFramework framework, WrapperRegistrationContext context) throws CommandRegistrationException {
        CommandWrapper wrapper = context.getWrapper();
        // register the base label
        framework.addExecutor(new WrapperBasedCommandExecutor(wrapper.label(), wrapper));

        for (String s : wrapper.aliases()) {
            // register all of the aliases too
            framework.addExecutor(new WrapperBasedCommandExecutor(s, wrapper));
        }
    }

}
