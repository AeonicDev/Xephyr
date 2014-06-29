package com.aeonicdev.xephyr.generic.dependency;

/**
 * Defines an API where dependencies are identified by a Class that extends T.
 *
 * @author sc4re
 */
public interface ClassDependent<T> extends Dependent<T, Class<? extends T>> {

}
