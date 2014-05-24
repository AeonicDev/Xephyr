package com.dhlab.xephyr.bukkit.jsonadapter.fireworkeffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.craftbukkit.libs.com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Serializes FireworkEffects.
 * @author maladr0it
 */
public final class FireworkEffectSerializer implements JsonSerializer<FireworkEffect> {

    private FireworkEffectSerializer() { }

    @Override
    public JsonElement serialize(FireworkEffect fireworkEffect, Type type, JsonSerializationContext jsonSerializationContext) {
        if (fireworkEffect == null)
            return null;
        JsonObject result = new JsonObject();
        result.addProperty(FireworkEffectFormat.FLICKER, fireworkEffect.hasFlicker());
        result.addProperty(FireworkEffectFormat.TRAIL, fireworkEffect.hasTrail());
        JsonArray colors = new JsonArray();
        for (Color color : fireworkEffect.getColors()) {
            colors.add(new JsonPrimitive(color.asRGB()));
        }
        result.add(FireworkEffectFormat.COLORS, colors);
        JsonArray fadeColors = new JsonArray();
        for (Color color : fireworkEffect.getFadeColors()) {
            fadeColors.add(new JsonPrimitive(color.asRGB()));
        }
        result.add(FireworkEffectFormat.FADE_COLORS, fadeColors);
        result.addProperty(FireworkEffectFormat.TYPE, fireworkEffect.getType().name());
        return result;
    }


    private static final FireworkEffectSerializer instance = new FireworkEffectSerializer();
    public static final FireworkEffectSerializer get() { return instance; }
}
