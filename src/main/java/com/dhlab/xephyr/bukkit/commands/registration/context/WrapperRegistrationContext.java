package com.dhlab.xephyr.bukkit.commands.registration.context;

import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.WrapperBasedCommandRegistrator;

/**
 * A registration context that allows you to register for the WrapperBasedCommandRegistrator
 * @author maladr0it
 */
public class WrapperRegistrationContext implements CommandRegistrationContext<WrapperBasedCommandRegistrator> {

    protected final CommandWrapper wrapper;

    public WrapperRegistrationContext(CommandWrapper w) {
        this.wrapper = w;
    }

    public CommandWrapper getWrapper() {
        return wrapper;
    }

    @Override
    public Class<WrapperBasedCommandRegistrator> getExecutorType() {
        return WrapperBasedCommandRegistrator.class;
    }
}
