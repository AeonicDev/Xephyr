package com.dhlab.xephyr.generic.management;

/**
 * To be thrown when a Manager child fails to find an internal object.
 *
 * @author maladr0it
 */
public class ManagedNotFoundException extends Exception {

    /**
     * The identifiers of the objects.
     */
    protected final Object[] identifier;

    /**
     * Creates a new {@code ManagedNotFoundException} with the specified identifiers.
     *
     * @param identifiers The identifiers for the missing objects.
     */
    public ManagedNotFoundException(Object... identifiers) {
        this.identifier = identifiers;
    }

    /**
     * Gets the identifiers of the missing objects.
     *
     * @param <T> The base type of the identifiers.
     * @return The missing object identifiers.
     */
    public <T> T[] getLostIdentifiers() {
        return (T[])identifier;
    }

}
