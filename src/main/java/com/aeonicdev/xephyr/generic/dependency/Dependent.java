package com.aeonicdev.xephyr.generic.dependency;

/**
 * Defines an API for objects that depend on other objects, such as Modules.
 * T is the object type that is identified by K.
 *
 * @author sc4re
 */
public interface Dependent<T, K> {

    /**
     * Returns the identifiers of the dependencies needed.
     *
     * @return An array of identifiers.
     */
    public K[] getDependencyIdentifiers();

    /**
     * Returns the total satisfied dependencies.
     *
     * @return An array of objects.
     */
    public T[] getSatisfied();

    /**
     * Attempts to satisfy the dependency requirements of the object.
     *
     * @param objects The identifiers to satisfy.
     * @throws DependencyNotSatisfiedException When an identifier isn't satisfied.
     */
    public void satisfy(T... objects) throws DependencyNotSatisfiedException;

    /**
     * For internal use by the object, uses identifier K to get the dependency returned.
     *
     * @param id The identifier.
     * @return The dependency identified.
     */
    public <H extends T> H getDependency(K id);

    /**
     * Sets up a requirement for a specific identifier.
     *
     * @param id The identifier.
     */
    public void require(K id);

}
