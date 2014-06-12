package com.aeonicdev.xephyr.bukkit.jsonadapter.potioneffect;


import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonSerializationContext;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonSerializer;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Type;

/**
 * Serializes PotionEffects into JSON strings.
 *
 * @author maladr0it
 */
public final class PotionEffectSerializer implements JsonSerializer<PotionEffect> {

    /**
     * Private constructor for the singleton instance.
     */
    private PotionEffectSerializer() { }

    @Override
    public JsonElement serialize(PotionEffect potionEffect, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty(PotionEffectFormat.POTION_EFFECT_ID, potionEffect.getType().getId());
        obj.addProperty(PotionEffectFormat.POTION_DURATION, potionEffect.getDuration());
        obj.addProperty(PotionEffectFormat.POTION_AMPLIFIER, potionEffect.getAmplifier());
        obj.addProperty(PotionEffectFormat.POTION_AMBIENT, potionEffect.isAmbient());
        return obj;
    }

    /**
     * The singleton serializer instance.
     */
    private static final PotionEffectSerializer instance = new PotionEffectSerializer();

    /**
     * Gets the singleton instance of this serializer.
     *
     * @return The serializer instance.
     */
    public static final PotionEffectSerializer get() { return instance; }

}
