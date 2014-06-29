package com.aeonicdev.xephyr.bukkit.jsonadapter.fireworkeffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

/**
 * All of the required data to serialize and deserialize Firework Effects.
 *
 * @author sc4re
 */
public final class FireworkEffectFormat {

    /**
     * Firework effect flicker data.
     */
    public static final String FLICKER = "flicker";

    /**
     * Firework effect trail data.
     */
    public static final String TRAIL = "trail";

    public static final String COLORS = "colors";

    public static final String FADE_COLORS = "fade-colors";

    /**
     * Firework effect type data.
     */
    public static final String TYPE = "type";

    /**
     * Firework effect flicker default.
     */
    public static final boolean FLICKER_DEFAULT = false;

    /**
     * Firework effect trail default.
     */
    public static final boolean TRAIL_DEFAULT = false;

    /**
     * Firework effect color default.
     */
    public static final Color COLOR_DEFAULT = Color.GREEN;

    /**
     * Firework effect type default.
     */
    public static final FireworkEffect.Type TYPE_DEFAULT = FireworkEffect.Type.BALL;

}
