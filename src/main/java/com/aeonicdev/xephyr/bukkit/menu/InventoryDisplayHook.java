package com.aeonicdev.xephyr.bukkit.menu;

import com.aeonicdev.xephyr.bukkit.menu.slot.Slot;
import org.bukkit.entity.Player;

/**
 * An interface describing whether a slot should be displayed to a user in the inventory menu.
 *
 * @author sc4re
 */
public interface InventoryDisplayHook {

    /**
     * Gets whether a {@link com.aeonicdev.xephyr.bukkit.menu.slot.Slot} should be displayed to a {@link org.bukkit.entity.Player} in the menu.
     *
     * @param player The player to check conditions with.
     * @param slot The slot that may or may not be seen.
     * @return If the slot should be shown to the player.
     */
    boolean shouldDisplay(Player player, Slot slot);

}
