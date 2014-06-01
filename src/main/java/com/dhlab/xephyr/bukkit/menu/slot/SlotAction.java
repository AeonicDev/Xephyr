package com.dhlab.xephyr.bukkit.menu.slot;

import org.bukkit.entity.Player;

/**
 * An interface describing what needs to happen when a slot is clicked.
 *
 * @author maladr0it
 */
public interface SlotAction {

    /**
     * The method called when the associated slot is clicked by a {@link org.bukkit.entity.Player}.
     *
     * @param p The player who clicked the slot.
     */
    void click(Player p);

}
