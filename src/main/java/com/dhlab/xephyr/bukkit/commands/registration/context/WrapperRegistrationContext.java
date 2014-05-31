package com.dhlab.xephyr.bukkit.commands.registration.context;

import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.WrapperBasedCommandRegistrator;

/**
 * A registration context that allows you to register for the WrapperBasedCommandRegistrator.
 *
 * @author maladr0it
 */
public class WrapperRegistrationContext implements CommandRegistrationContext<WrapperBasedCommandRegistrator> {

    /**
     * The command wrapper to register.
     */
    protected final CommandWrapper wrapper;

    /**
     * Creates a new {@code WrapperRegistrationContext} for the specified {@link com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper}.
     *
     * @param w The command wrapper.
     */
    public WrapperRegistrationContext(CommandWrapper w) {
        this.wrapper = w;
    }

    /**
     * Gets the {@link com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper} for this context.
     *
     * @return The command wrapper.
     */
    public CommandWrapper getWrapper() {
        return wrapper;
    }

    /**
     * Returns the type of the registrator this context is for.
     *
     * @return The class of the registrator.
     */
    @Override
    public Class<WrapperBasedCommandRegistrator> getExecutorType() {
        return WrapperBasedCommandRegistrator.class;
    }

}
