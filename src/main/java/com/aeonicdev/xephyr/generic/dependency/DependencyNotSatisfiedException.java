package com.aeonicdev.xephyr.generic.dependency;

/**
 * Thrown when a dependency is not satisfied by method call.
 *
 * @author sc4re
 */
public class DependencyNotSatisfiedException extends Exception {

    /**
     * The identifier that wasn't satisfied.
     */
    protected final Object unsatisfied;

    /**
     * Creates a new {@code DependencyNotSatisfiedException} with the specified unsatisfied identifier.
     *
     * @param unsatisfied The unsatisfied identifier.
     */
    public DependencyNotSatisfiedException(Object unsatisfied) {
        this.unsatisfied = unsatisfied;
    }

    /**
     * Gets the unsatisfied identifier.
     *
     * @param <T> The type of the unsatisfied identifier.
     * @return The unsatisfied identifier.
     */
    public <T> T getUnsatisfied() {
        return (T)unsatisfied;
    }

}
