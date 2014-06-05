package com.dhlab.xephyr.bukkit.kit.eventing;

import com.dhlab.xephyr.bukkit.kit.Kit;
import org.bukkit.entity.Player;

/**
 * This interface describes an object that is used by the Kit class whose methods are called onEnable and onDisable.
 * This can be used to set initial defaults to kit data.
 * @author maladr0it
 */
public interface KitEventListener {
    /**
     * Called when the kit's onEnable method is called.
     * @param kit The kit being initialized
     */
    public void onEnable(Kit kit);

    /**
     * Called when the kit's onDisable method is called.
     * @param kit The kit being disabled.
     */
    public void onDisable(Kit kit);

    /**
     * Called when a kit is applied to a player.
     * @param kit The kit being applied
     * @param player The player the kit is being applied to
     * @return Whether to cancel the application or not.
     */
    public boolean onApply(Kit kit, Player player);
}
