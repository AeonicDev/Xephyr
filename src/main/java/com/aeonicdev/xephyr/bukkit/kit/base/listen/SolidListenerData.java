package com.aeonicdev.xephyr.bukkit.kit.base.listen;

import org.apache.commons.lang.Validate;

import java.lang.reflect.Method;

/**
 * A structure that contains all of the required materials for invoking a SolidHandler-annotated method.
 * @author maladr0it
 */
public class SolidListenerData {

    /**
     * The object to invoke the method on.
     */
    protected final FluidKitListener object;
    /**
     * The annotation to use.
     */
    protected final SolidHandler annotation;
    /**
     * The method to invoke.
     */
    protected final Method m;

    /**
     * Basic constructor, takes arguments needed for invoking a method.
     * @param listener The object to perform the method on.
     * @param handler The SolidHandler annotation of the method.
     * @param m The method to invoke upon the object.
     */
    public SolidListenerData(FluidKitListener listener, SolidHandler handler, Method m) {
        Validate.notNull(listener);
        Validate.notNull(handler);
        Validate.notNull(m);
        // and for the fringe cases
        Validate.isTrue(handler.equals(m.getAnnotation(SolidHandler.class)));
        this.object = listener;
        this.annotation = handler;
        this.m = m;
    }

    public FluidKitListener getObject() {
        return object;
    }

    public SolidHandler getAnnotation() {
        return annotation;
    }

    public Method getMethod() {
        return m;
    }
}
