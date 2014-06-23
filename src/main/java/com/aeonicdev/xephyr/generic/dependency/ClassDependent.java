package com.aeonicdev.xephyr.generic.dependency;

/**
 * Defines an API where dependencies are identified by a Class that extends T.
 *
 * @author maladr0it
 */
public interface ClassDependent<T> extends Dependent<T, Class<? extends T>> {

}
