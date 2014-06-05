package com.dhlab.xephyr.bukkit.kit.property;

import com.dhlab.xephyr.bukkit.kit.Kit;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Allows access to properties and values that can be shared between listeners.
 * @author maladr0it
 */
public class KitProperties implements ConfigurationSerializable {

    /**
     * The registry using strings as keys and properties as values.
     */
    protected final Map<String, KitProperty<?>> properties = new HashMap<>();

    /**
     * The kit that these properties are for.
     */
    protected final Kit kit;

    /**
     * The constructor of the KitProperties, takes in a non-null Kit as the only argument.
     * @param kit The kit to use.
     */
    public KitProperties(Kit kit) {
        Validate.notNull(kit);
        this.kit = kit;
    }

    /**
     * Add a property to the property map, ONLY CALL THIS WHEN ADDING A NEW PROPERTY, use set() for everything else!
     * @param property The property to add.
     */
    public void add(KitProperty<?> property) {
        String name = property.getName();
        if (properties.get(name) != null)
            throw new RuntimeException("Property is already set in property map!");
        properties.put(name, property);
    }

    /**
     * Returns the KitProperty[T] object associated with the String property.
     * @param name The name of the property.
     * @param <T> The subtype of the kitproperty in the map
     * @return
     */
    public <T> KitProperty<T> get(String name) {
        return (KitProperty<T>)properties.get(name);
    }

    /**
     * Sets the value of the KitProperty obtained by using the String name given.
     * @param name The name of the property
     * @param obj The object to set the properties' value to
     * @param <T> The subtype of the kit property.
     */
    public <T> void set(String name, T obj) {
        if (get(name) == null)
            throw new NullPointerException("Property does not exist!");
        get(name).setProperty(obj);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new LinkedHashMap<>();
        for (KitProperty<?> entry : this.properties.values()) {
            serialized.put(entry.getName(), entry.serialize());
        }
        return serialized;
    }
}
