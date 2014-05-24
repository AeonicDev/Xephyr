package com.dhlab.xephyr.bukkit.commands.registration.context;

import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext;
import com.dhlab.xephyr.bukkit.commands.registration.method.MethodBasedCommandRegistrator;

/**
 * A registration context that describes an annotation (method-based) command, and allows you to register commands using
 * that context.
 * @author maladr0it
 */
public class MethodRegistrationContext implements CommandRegistrationContext<MethodBasedCommandRegistrator> {

    protected final Object toRegister;
    public MethodRegistrationContext(Object obj) {
        toRegister = obj;
    }

    public Object getRegistered() {
        return toRegister;
    }

    @Override
    public Class<MethodBasedCommandRegistrator> getExecutorType() {
        return MethodBasedCommandRegistrator.class;
    }
}
