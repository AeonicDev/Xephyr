package com.aeonicdev.xephyr.bukkit.jsonadapter.fireworkeffect;

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.craftbukkit.libs.com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Serializes FireworkEffects.
 *
 * @author maladr0it
 */
public final class FireworkEffectSerializer implements JsonSerializer<FireworkEffect> {

    /**
     * Private constructor for the singleton instance.
     */
    private FireworkEffectSerializer() { }

    @Override
    public JsonElement serialize(FireworkEffect fireworkEffect, Type type, JsonSerializationContext jsonSerializationContext) {
        Validate.notNull(fireworkEffect);

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


    /**
     * The singleton serializer instance.
     */
    private static final FireworkEffectSerializer instance = new FireworkEffectSerializer();

    /**
     * Gets the singleton instance of this serializer.
     *
     * @return The serializer instance.
     */
    public static final FireworkEffectSerializer get() { return instance; }
}
