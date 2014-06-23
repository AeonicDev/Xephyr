package com.aeonicdev.xephyr.bukkit.kit.base.listen;

import com.aeonicdev.xephyr.bukkit.kit.base.Kit;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;

/**
 * Non-dynamic "State" listener, allows methods to hook into when a
 * @author maladr0it
 */
public abstract class KitStateListener  {
    /**
     * The name of this listener
     */
    protected final String name;

    public KitStateListener(String name) {
        Validate.notNull(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Called when the kit's onEnable is called.
     * @param kit The kit passed along by itself.
     */
    public void onEnable(Kit kit) { }

    /**
     * Called when the kit's onDisable is called.
     * @param kit The kit passed along by itself.
     */
    public void onDisable(Kit kit) { }

    /**
     * Allows cancellation of applications of kits, useful for permissions plugins and the lot.
     * @param kit The kit passed along by itself.
     * @param player The player the kit is being applied to.
     * @return
     */
    public boolean shouldApply(Kit kit, Player player) { return true; }

    /**
     * Called upon the application of a kit to a player.
     * @param kit The kit passed along by itself.
     * @param player The player the kit has been applied to.
     */
    public void onApply(Kit kit, Player player) { }

    /**
     * Called upon the removal of a kit from a player.
     * @param kit The kit passed along by itself.
     * @param player The player the kit has been detached from.
     */
    public void onDetach(Kit kit, Player player) { }
}
