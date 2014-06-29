package com.aeonicdev.xephyr.generic.management;

/**
 * This manager interface describes a manager that uses classes as
 * internal identifiers, useful for things like Module systems, etc
 *
 * @author sc4re
 */
public interface ClassBasedManager<T> extends Manager<T, Class<? extends T>>{

}
