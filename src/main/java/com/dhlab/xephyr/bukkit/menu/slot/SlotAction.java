package com.dhlab.xephyr.bukkit.menu.slot;

import org.bukkit.entity.Player;

/**
 * An interface describing what needs to happen when a slot is clicked.
 */
public interface SlotAction {
    void click(Player p);
}
