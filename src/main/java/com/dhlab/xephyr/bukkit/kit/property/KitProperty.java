package com.dhlab.xephyr.bukkit.kit.property;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A generic implementation of a kit property, is used to get and set various options in a kit that listeners may use to
 * modify their behavior.
 * @author maladr0it
 */
public class KitProperty<T> implements ConfigurationSerializable {

    /**
     * The currently-held property value.
     */
    protected T contained;
    /**
     * The class of the property's object.
     */
    protected final Class<T> klass;

    /**
     * The name of this property.
     */
    protected final String name;

    /**
     * This constructor uses an existing T object to both set the value and the class of the contained object.
     * @param name The name of the property.
     * @param obj The object to initialize this kit property with. Cannot be null.
     */
    public KitProperty(String name, T obj) {
        Validate.notNull(obj);
        Validate.notNull(name);
        Validate.notEmpty(name);
        this.name = name;
        this.contained = obj;
        this.klass = (Class<T>)obj.getClass();
    }

    /**
     * This constructor sets the currently contained object to be null, but since the internal class object cannot
     * be set due to type erasure, the constructor takes a non-null Class[T] object in.
     * @param name The name of the property.
     * @param klass The class of the object being held.
     */
    public KitProperty(String name, Class<T> klass) {
        Validate.notNull(klass);
        Validate.notNull(name);
        Validate.notEmpty(name);
        this.name = name;
        this.klass = klass;
        this.contained = null;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the currently-held value of the property.
     * @return The current property value
     */
    public T getProperty() {
        return contained;
    }

    /**
     * Sets the held value of the property.
     * @param obj The object to set the value to.
     */
    public void setProperty(T obj) {
        this.contained = obj;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new LinkedHashMap<>();
        serialized.put("value", getProperty());
        return serialized;
    }
}
