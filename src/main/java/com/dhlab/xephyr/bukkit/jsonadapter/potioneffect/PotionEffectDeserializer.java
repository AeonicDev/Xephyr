package com.dhlab.xephyr.bukkit.jsonadapter.potioneffect;


import org.bukkit.craftbukkit.libs.com.google.gson.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;

/**
 * Deserializes JSON strings into potion effects.
 * @author maladr0it
 */
public final class PotionEffectDeserializer implements JsonDeserializer<PotionEffect> {

    private PotionEffectDeserializer() { }

    @Override
    public PotionEffect deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null)
            return null;
        if (!jsonElement.isJsonObject())
            return null;
        JsonObject obj = jsonElement.getAsJsonObject();
        if (!obj.has(PotionEffectFormat.POTION_EFFECT_ID))
            return null;
        int effectID = obj.get(PotionEffectFormat.POTION_EFFECT_ID).getAsInt();
        PotionEffectType pet = PotionEffectType.getById(effectID);
        if (pet == null)
            return null;
        int duration = PotionEffectFormat.POTION_DURATION_DEFAULT;
        int amplifier = PotionEffectFormat.POTION_AMPLIFIER_DEFAULT;
        boolean ambient = PotionEffectFormat.POTION_AMBIENT_DEFAULT;
        if (obj.has(PotionEffectFormat.POTION_DURATION))
            duration = obj.get(PotionEffectFormat.POTION_DURATION).getAsInt();
        if (obj.has(PotionEffectFormat.POTION_AMPLIFIER))
            amplifier = obj.get(PotionEffectFormat.POTION_AMPLIFIER).getAsInt();
        if (obj.has(PotionEffectFormat.POTION_AMBIENT))
            ambient = obj.get(PotionEffectFormat.POTION_AMBIENT).getAsBoolean();
        return new PotionEffect(pet, duration, amplifier, ambient);
    }

    private static final PotionEffectDeserializer instance = new PotionEffectDeserializer();

    public static final PotionEffectDeserializer get() { return instance; }
}
