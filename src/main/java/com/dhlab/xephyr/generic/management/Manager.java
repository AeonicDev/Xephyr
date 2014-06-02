package com.dhlab.xephyr.generic.management;

/**
 * A generic manager class used for a variety of things.
 *
 * @author maladr0it
 */
public interface Manager<T, K> {

    /**
     * Returns the class of T.
     *
     * @return The managed type.
     */
    public Class<T> getManagedType();


    /**
     * Should be used to add an object of type T to the underlying storage mechanism.
     *
     * @param obj The object to add.
     */
    public void add(T obj);

    /**
     * Should be used to remove an object of type T from the underlying storage mechanism.
     *
     * @param obj The object to remove.
     */
    public void remove(T obj);

    /**
     * Should be used to remove an object with the identifier of type K
     *
     * @param obj The identifier of the object to remove.
     */
    public void removeByID(K obj);

    /**
     * Returns whether this manager contains an object with this specific identifier or object.
     *
     * @param obj The object to check for.
     * @return If the manager contains the specific identifier or object.
     */
    public boolean has(K obj);

    /**
     * Returns the array of total T[] contents from the underlying storage mechanism.
     *
     * @return An array of all objects being managed.
     */
    public T[] getContents();

    /**
     * If the manager is hashmap-based or you can only identify the T object via an internal identifier,
     * this method provides the ability to find by an unspecific identifier.
     *
     * @param identifier The identifier.
     * @return The object.
     * @throws ManagedNotFoundException When the object isn't found by the identifier.
     */
    public T find(K identifier) throws ManagedNotFoundException;

    /**
     * Finds all of the T objects with the singular K identifier.
     *
     * @param identifier The identifier.
     * @return All objects identified.
     * @throws ManagedNotFoundException When no objects were identified.
     */
    public T[] findAllByOne(K identifier) throws ManagedNotFoundException;

    /**
     * Finds all of the T objects with any of the identifiers provided.
     *
     * @param identifiers The identifiers.
     * @return The objects identified.
     * @throws ManagedNotFoundException When no objects were identified.
     */
    public T[] findAllByMany(K... identifiers) throws ManagedNotFoundException;

}
