package com.dhlab.xephyr.bukkit.jsonadapter.potioneffect;


import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonSerializationContext;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonSerializer;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Type;

/**
 * Serializes PotionEffects into JSON strings.
 * @author maladr0it
 */
public final class PotionEffectSerializer implements JsonSerializer<PotionEffect> {

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

    private static final PotionEffectSerializer instance = new PotionEffectSerializer();

    public static final PotionEffectSerializer get() { return instance; }
}
