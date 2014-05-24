package com.dhlab.xephyr.bukkit.jsonadapter.fireworkeffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

/**
 * All of the required data to serialize and deserialize Firework Effects.
 * @author maladr0it
 */
public final class FireworkEffectFormat {
    public static final String FLICKER = "flicker";
    public static final String TRAIL = "trail";
    public static final String COLORS = "colors";
    public static final String FADE_COLORS = "fade-colors";
    public static final String TYPE = "type";

    public static final boolean FLICKER_DEFAULT = false;
    public static final boolean TRAIL_DEFAULT = false;
    public static final Color COLOR_DEFAULT = Color.GREEN;
    public static final FireworkEffect.Type TYPE_DEFAULT = FireworkEffect.Type.BALL;
}
