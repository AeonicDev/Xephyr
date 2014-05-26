package com.dhlab.xephyr.reflect;

/**
 * Defines the API for a Method accessor.
 * @author maladr0it
 */
public interface IMethodAccessor<T> {
    /**
     * Returns true if this accessor can be used without any problems, i.e;
     * the method has been found and is accessible.
     * @return
     */
    boolean isValid();

    /**
     * Invokes the target method.
     * @param target object of the method to invoke.
     * @param args The arguments to call the method with.
     * @return
     */
    T invoke(Object target, Object ... args);
}
