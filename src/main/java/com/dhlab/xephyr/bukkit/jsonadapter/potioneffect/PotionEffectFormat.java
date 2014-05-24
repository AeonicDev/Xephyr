package com.dhlab.xephyr.bukkit.jsonadapter.potioneffect;

/**
 * All of the required fields to serialize and deserialize Potion Effects.
 * @author maladr0it
 */
public class PotionEffectFormat {
    public static final String POTION_EFFECT_ID = "id";
    public static final String POTION_DURATION = "duration";
    public static final String POTION_AMPLIFIER = "amplifier";
    public static final String POTION_AMBIENT = "ambient";

    public static final int POTION_DURATION_DEFAULT = 20;
    public static final int POTION_AMPLIFIER_DEFAULT = 1;
    public static final boolean POTION_AMBIENT_DEFAULT = false;
}
