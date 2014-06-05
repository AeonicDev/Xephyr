package com.dhlab.xephyr.bukkit.kit.listeners.normal;

import com.dhlab.xephyr.bukkit.kit.Kit;
import com.dhlab.xephyr.bukkit.kit.eventing.KitListener;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Removes the Kit metadata upon death.
 * @author maladr0it
 */
public class RemoveUponDeathListener extends KitListener<PlayerDeathEvent> {

    /**
     * This describes whether to remove the kit upon death or not.
     */
    private final boolean clearInventory;

    public RemoveUponDeathListener(boolean clearInv) {
        super(PlayerDeathEvent.class);
        this.clearInventory = clearInv;
    }

    public RemoveUponDeathListener() {
        this(false);
    }

    @Override
    protected void handleEvent(Kit kit, PlayerDeathEvent event) {
        Player p = event.getEntity();
        if (kit.hasKit(p)) {
            kit.removeKit(p, clearInventory);
        }
    }
}
