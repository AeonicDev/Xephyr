package com.dhlab.xephyr.bukkit.teams.color;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

/**
 * The base of the TeamIdentifier "Extensible Enum" pattern.
 * Contains methods to get team name, chat color, and
 * wool dye color.
 *
 * @author maladr0it
 */
public interface TeamIdentifier {
    public String getName();
    public ChatColor getChatColor();
    public DyeColor getDyeColor();
}
