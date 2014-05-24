package com.dhlab.xephyr.bukkit.teams.color;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

/**
 * Some default team colors commonly used on CTF servers and the like
 * @author maladr0it
 */
public enum GenericTeamColor implements TeamIdentifier {
    RED("Red", ChatColor.DARK_RED, DyeColor.RED),
    ORANGE("Orange", ChatColor.GOLD, DyeColor.ORANGE),
    YELLOW("Yellow", ChatColor.YELLOW, DyeColor.YELLOW),
    GREEN("Green", ChatColor.GREEN, DyeColor.GREEN),
    BLUE("Blue", ChatColor.BLUE, DyeColor.BLUE),
    PURPLE("Purple", ChatColor.DARK_PURPLE, DyeColor.PURPLE);

    private final String colorName;
    private final ChatColor chatColor;
    private final DyeColor dyeColor;

    private GenericTeamColor(String name, ChatColor chat, DyeColor color) {
        this.colorName = name;
        this.chatColor = chat;
        this.dyeColor = color;
    }

    @Override
    public String getName() {
        return colorName;
    }

    @Override
    public ChatColor getChatColor() {
        return chatColor;
    }

    @Override
    public DyeColor getDyeColor() {
        return dyeColor;
    }
}
