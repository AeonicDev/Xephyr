package com.dhlab.xephyr.bukkit.kit.listeners.init;

import com.dhlab.xephyr.bukkit.kit.Kit;
import com.dhlab.xephyr.bukkit.kit.eventing.KitEventListener;
import org.bukkit.entity.Player;

/**
 * Makes kits permission-based.
 * @author maladr0it
 */
public class KitPermissionListener implements KitEventListener {

    /**
     * The permission for this listener to check.
     */
    protected final String permission;

    public KitPermissionListener(String perm) {
        this.permission = perm;
    }

    @Override
    public void onEnable(Kit kit) {

    }

    @Override
    public void onDisable(Kit kit) {

    }

    @Override
    public boolean onApply(Kit kit, Player player) {
        return player.hasPermission(permission);
    }
}
