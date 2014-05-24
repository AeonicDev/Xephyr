package com.dhlab.xephyr.bukkit.menu;

import com.dhlab.xephyr.bukkit.menu.slot.Slot;
import org.bukkit.entity.Player;

/**
 * An interface describing whether a slot should be displayed to a user in the inventory menu.
 * @author maladr0it
 */
public interface InventoryDisplayHook {
    boolean shouldDisplay(Player player, Slot slot);
}
