package com.aeonicdev.xephyr.bukkit.utilities;

/**
 * A static class containing various Minecraft constants, currently mostly dealing with inventory bounds and
 * other limits on players.
 * @author maladr0it
 */
public final class MinecraftConstants {
    /**
     * Private constructor, this makes this essentially a static class (like in C#)
     */
    private MinecraftConstants() { }

    /**
     * The minimum slot value in a player inventory (inclusive)
     */
    public static final int PLAYER_INVENTORY_MIN_BOUND = 0;
    /**
     * The maximum slot value in a player inventory (inclusive)
     */
    public static final int PLAYER_INVENTORY_MAX_BOUND = 35;

    /**
     * The minimum slot value in a player hotbar (inclusive)
     */
    public static final int PLAYER_INVENTORY_HOTBAR_MIN_BOUND = 0;

    /**
     * The slot value of the player's boots.
     */
    public static final int PLAYER_INVENTORY_BOOTS_SLOT = 100;

    /**
     * The slot value of the player's leggings.
     */
    public static final int PLAYER_INVENTORY_LEGGINGS_SLOT = 101;

    /**
     * The slot value of the player's chestplate.
     */
    public static final int PLAYER_INVENTORY_CHESTPLATE_SLOT = 102;

    /**
     * The slot value of the player's helmet.
     */
    public static final int PLAYER_INVENTORY_HELMET_SLOT = 103;
}
