package com.dhlab.xephyr.bukkit.kit.base.config;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import java.util.HashMap;
import java.util.Map;

/**
 * A basic configuration
 * @author maladr0it
 */
public class KitConfig implements ConfigurationSerializable {

    // he does it for free
    static {
        ConfigurationSerialization.registerClass(KitConfig.class);
    }

    /**
     * A map of String->Object that will be serialized upon a serialize() call
     */
    protected final Map<String, Object> items = new HashMap<>();
    protected final Map<String, Object> tempItems = new HashMap<>();

    /**
     * Returns an (unsafely) casted object from the Items map.
     * @param key The key to check in the "items" map for.
     * @param <T> The object type to cast to.
     * @return
     */
    public <T> T get(String key) {
        return (T)items.get(key);
    }

    /**
     * Returns an (unsafely) casted object from the tempItems map.
     * @param key The key to check in the "tempItems" map for.
     * @param <T> The object type to cast to.
     * @return
     */
    public <T> T getTemp(String key) {
        return (T)tempItems.get(key);
    }

    /**
     * Sets an item in the items map to the specified value.
     * @param key The key to set the value for.
     * @param obj The object to set the value to.
     */
    public void set(String key, Object obj) {
        Validate.notNull(key);
        Validate.notNull(obj);
        Validate.notEmpty(key);
        items.put(key, obj);
    }

    /**
     * Sets an item in the temp items map to the specified value.
     * @param key The key to set the value for.
     * @param obj The object to set the value to.
     */
    public void setTemp(String key, Object obj) {
        Validate.notNull(key);
        Validate.notNull(obj);
        Validate.notEmpty(key);
        tempItems.put(key, obj);
    }

    /**
     * Removes an item from the items map based on the key
     * @param key The key to remove from the items map
     */
    public void remove(String key) {
        Validate.notNull(key);
        Validate.notEmpty(key);
        items.remove(key);
    }

    /**
     * Removes an item from the tempItems map based on the key
     * @param key The key to remove from the temp items map.
     */
    public void removeTemp(String key) {
        Validate.notNull(key);
        Validate.notEmpty(key);
        tempItems.remove(key);
    }

    /**
     * Serializes all of the
     * @return
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> copy = new HashMap<>();
        for (Map.Entry<String, Object> entry : this.items.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    public static KitConfig deserialize(Map<String, Object> serialized) {
        KitConfig conf = new KitConfig();
        for (Map.Entry<String, Object> entry : serialized.entrySet()) {
            conf.set(entry.getKey(), entry.getValue());
        }
        return conf;
    }
}
