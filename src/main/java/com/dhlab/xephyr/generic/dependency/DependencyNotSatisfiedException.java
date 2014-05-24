package com.dhlab.xephyr.generic.dependency;

/**
 * Thrown when a dependency is not satisfied by a satisfyRequirements call.
 * @author maladr0it
 */
public class DependencyNotSatisfiedException extends Exception {

    protected final Object unsatisfied;

    public DependencyNotSatisfiedException(Object unsatisfied) {
        this.unsatisfied = unsatisfied;
    }

    public <T> T getUnsatisfied() {
        return (T)unsatisfied;
    }
}
