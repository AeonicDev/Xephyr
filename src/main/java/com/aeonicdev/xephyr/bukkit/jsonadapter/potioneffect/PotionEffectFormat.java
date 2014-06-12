package com.aeonicdev.xephyr.bukkit.jsonadapter.potioneffect;

/**
 * All of the required fields to serialize and deserialize Potion Effects.
 *
 * @author maladr0it
 */
public class PotionEffectFormat {

    /**
     * The potion effect ID.
     */
    public static final String POTION_EFFECT_ID = "id";

    /**
     * The potion effect duration.
     */
    public static final String POTION_DURATION = "duration";

    /**
     * The potion effect amplifier.
     */
    public static final String POTION_AMPLIFIER = "amplifier";

    /**
     * The potion effect ambient state.
     */
    public static final String POTION_AMBIENT = "ambient";

    /**
     * The potion effect duration default.
     */
    public static final int POTION_DURATION_DEFAULT = 20;

    /**
     * The potion effect amplifier default.
     */
    public static final int POTION_AMPLIFIER_DEFAULT = 1;

    /**
     * The potion effect ambient state default.
     */
    public static final boolean POTION_AMBIENT_DEFAULT = false;

}
