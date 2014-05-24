package com.dhlab.xephyr.generic.management;

/**
 * @author maladr0it
 */
public interface Manager<T, K> {
    /**
     * Returns the class of T.
     * @return
     */
    public Class<T> getManagedType();


    /**
     * Should be used to add an object of type T to the underlying storage mechanism.
     * @param obj
     */
    public void add(T obj);

    /**
     * Should be used to remove an object of type T from the underlying storage mechanism.
     * @param obj
     */
    public void remove(T obj);

    /**
     * Should be used to remove an object with the identifier of type K
     * @param obj
     */
    public void removeByID(K obj);

    /**
     * Returns whether this manager contains an object with this specific identifier.
     * @param obj
     * @return
     */
    public boolean has(K obj);

    /**
     * Returns the array of total T[] contents from the underlying storage mechanism.
     * @return
     */
    public T[] getContents();

    /**
     * If the manager is hashmap-based or you can only identify the T object via an internal identifier,
     * this method provides the ability to find by an unspecific identifier.
     * @param identifier
     * @return
     * @throws ManagedNotFoundException
     */
    public T find(K identifier) throws ManagedNotFoundException;

    /**
     * Finds all of the T objects with the singular K identifier.
     * @param identifier
     * @return
     * @throws ManagedNotFoundException
     */
    public T[] findAllByOne(K identifier) throws ManagedNotFoundException;

    /**
     * Finds all of the T objects with any of the identifiers provided.
     * @param identifier
     * @return
     * @throws ManagedNotFoundException
     */
    public T[] findAllByMany(K... identifier) throws ManagedNotFoundException;
}
