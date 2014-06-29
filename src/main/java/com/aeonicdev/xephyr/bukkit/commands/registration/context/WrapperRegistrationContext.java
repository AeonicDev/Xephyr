package com.aeonicdev.xephyr.bukkit.commands.registration.context;

import com.aeonicdev.xephyr.bukkit.commands.registration.CommandRegistrationContext;
import com.aeonicdev.xephyr.bukkit.commands.registration.wrapper.CommandWrapper;
import com.aeonicdev.xephyr.bukkit.commands.registration.wrapper.WrapperBasedCommandRegistrator;

/**
 * A registration context that allows you to register for the WrapperBasedCommandRegistrator.
 *
 * @author sc4re
 */
public class WrapperRegistrationContext implements CommandRegistrationContext<WrapperBasedCommandRegistrator> {

    /**
     * The command wrapper to register.
     */
    protected final CommandWrapper wrapper;

    /**
     * Creates a new {@code WrapperRegistrationContext} for the specified {@link com.aeonicdev.xephyr.bukkit.commands.registration.wrapper.CommandWrapper}.
     *
     * @param w The command wrapper.
     */
    public WrapperRegistrationContext(CommandWrapper w) {
        this.wrapper = w;
    }

    /**
     * Gets the {@link com.aeonicdev.xephyr.bukkit.commands.registration.wrapper.CommandWrapper} for this context.
     *
     * @return The command wrapper.
     */
    public CommandWrapper getWrapper() {
        return wrapper;
    }

    @Override
    public Class<WrapperBasedCommandRegistrator> getExecutorType() {
        return WrapperBasedCommandRegistrator.class;
    }

}
