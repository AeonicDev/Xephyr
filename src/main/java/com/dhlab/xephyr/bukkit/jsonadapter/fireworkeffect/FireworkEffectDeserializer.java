package com.dhlab.xephyr.bukkit.jsonadapter.fireworkeffect;


import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.craftbukkit.libs.com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Deserialized JSON strings into a FireworkEffect
 * @author maladr0it
 */
public final class FireworkEffectDeserializer implements JsonDeserializer<FireworkEffect> {

    private FireworkEffectDeserializer() { }

    @Override
    public FireworkEffect deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null)
            return null;
        if (!jsonElement.isJsonObject())
            return null;
        JsonObject obj = jsonElement.getAsJsonObject();
        boolean flicker = FireworkEffectFormat.FLICKER_DEFAULT;
        boolean trail = FireworkEffectFormat.TRAIL_DEFAULT;
        List<Color> colors = new ArrayList<Color>();
        colors.add(FireworkEffectFormat.COLOR_DEFAULT);
        List<Color> fadeColors = new ArrayList<Color>();
        fadeColors.add(FireworkEffectFormat.COLOR_DEFAULT);

        if (obj.has(FireworkEffectFormat.FLICKER))
            flicker = obj.get(FireworkEffectFormat.FLICKER).getAsBoolean();
        if (obj.has(FireworkEffectFormat.TRAIL))
            trail = obj.get(FireworkEffectFormat.TRAIL).getAsBoolean();
        if (obj.has(FireworkEffectFormat.COLORS))
            colors = getColorList(obj.get(FireworkEffectFormat.COLORS).getAsJsonArray());
        if (obj.has(FireworkEffectFormat.FADE_COLORS))
            fadeColors = getColorList(obj.get(FireworkEffectFormat.FADE_COLORS).getAsJsonArray());
        return FireworkEffect.builder().flicker(flicker).trail(trail).withColor(colors).withFade(fadeColors).build();
    }


    private List<Color> getColorList(JsonArray arr) {
        List<Color> result = new ArrayList<Color>();
        Iterator<JsonElement> it = arr.iterator();
        while (it.hasNext()) {
            JsonElement el = it.next();
            if (!el.isJsonPrimitive())
                continue;
            int rgb = el.getAsJsonPrimitive().getAsInt();
            result.add(Color.fromRGB(rgb));
        }
        return result;
    }

    private static final FireworkEffectDeserializer instance = new FireworkEffectDeserializer();
    public static FireworkEffectDeserializer get() { return instance; }
}
