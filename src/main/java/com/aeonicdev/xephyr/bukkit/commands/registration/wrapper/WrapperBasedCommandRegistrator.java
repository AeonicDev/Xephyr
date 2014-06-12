package com.aeonicdev.xephyr.bukkit.commands.registration.wrapper;

import com.aeonicdev.xephyr.bukkit.commands.CommandFramework;
import com.aeonicdev.xephyr.bukkit.commands.executors.WrapperBasedCommandExecutor;
import com.aeonicdev.xephyr.bukkit.commands.registration.CommandRegistrationException;
import com.aeonicdev.xephyr.bukkit.commands.registration.CommandRegistrator;
import com.aeonicdev.xephyr.bukkit.commands.registration.context.WrapperRegistrationContext;

/**
 * Registrator for {@link com.aeonicdev.xephyr.bukkit.commands.registration.wrapper.CommandWrapper} objects.
 *
 * @author maladr0it
 */
public class WrapperBasedCommandRegistrator implements CommandRegistrator<WrapperRegistrationContext> {

    @Override
    public Class<WrapperRegistrationContext> getContextType() {
        return WrapperRegistrationContext.class;
    }

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
