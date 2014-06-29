package com.aeonicdev.xephyr.bukkit.commands.registration.context;

import com.aeonicdev.xephyr.bukkit.commands.registration.CommandRegistrationContext;
import com.aeonicdev.xephyr.bukkit.commands.registration.method.MethodBasedCommandRegistrator;

/**
 * A registration context that describes an annotation (method-based) command, and allows you to register commands using
 * that context.
 *
 * @author sc4re
 */
public class MethodRegistrationContext implements CommandRegistrationContext<MethodBasedCommandRegistrator> {

    /**
     * The object to register.
     */
    protected final Object toRegister;

    /**
     * Creates a new registration context for the specified object.
     *
     * @param obj The object to register.
     */
    public MethodRegistrationContext(Object obj) {
        toRegister = obj;
    }

    /**
     * Gets the object we're registering.
     *
     * @return The object to register.
     */
    public Object getRegistered() {
        return toRegister;
    }

    @Override
    public Class<MethodBasedCommandRegistrator> getExecutorType() {
        return MethodBasedCommandRegistrator.class;
    }

}
