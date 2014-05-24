package com.dhlab.xephyr.generic.management;

/**
 * To be thrown when a Manager child fails to find an internal object.
 * @author maladr0it
 */
public class ManagedNotFoundException extends Exception {

    protected final Object[] identifier;

    public ManagedNotFoundException(Object... identifier) {
        this.identifier = identifier;
    }

    public <T> T[] getLostIdentifiers() {
        return (T[])identifier;
    }

}
