package com.dhlab.xephyr.bukkit.menu;

import com.dhlab.xephyr.bukkit.menu.slot.Slot;
import com.dhlab.xephyr.generic.FormattingNamed;
import com.dhlab.xephyr.generic.Sizeable;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic inventory menu API class.
 *
 * @author maladr0it
 */
public class InventoryMenu implements FormattingNamed, Sizeable, Listener {

    public static final int MAX_INVENTORY_SIZE = 54;

    protected final String name;
    protected int size = 9;

    protected final Map<Integer, Slot> slotMapping = new HashMap<Integer, Slot>();
    protected final List<InventoryDisplayHook> hookList = new ArrayList<InventoryDisplayHook>();

    public InventoryMenu(String name) {
        Validate.notNull(name);
        this.name = name;
    }

    public InventoryMenu(String name, int size) {
        this(name);
        setSize(size);
    }

    @Override
    public String getName() {
        return ChatColor.RESET + "" + name;
    }

    @Override
    public String getUnformattedName() {
        return ChatColor.stripColor(getName());
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int newSize) {
        if (newSize > MAX_INVENTORY_SIZE || newSize > size)
            throw new IllegalArgumentException("Invalid inventory menu size! (Greater than Max)");
        if (newSize < 1)
            throw new IllegalArgumentException("Invalid inventory menu size! (Below 1)");
        while (newSize % 9 != 0 && newSize > 9) // increase inventory size to a valid size
            newSize++;
        this.size = newSize;
        fillSlots();
    }

    public Slot getSlot(int key) {
        return this.slotMapping.get(key);
    }

    /**
     * Executes the action performed by the MenuSlot in the slot specified.
     * @param player
     * @param slot
     */
    public void click(Player player, int slot) {
        Slot s = this.getSlot(slot);
        if (s == null)
            throw new RuntimeException("Slot in slot " + slot + " is null, this should NEVER HAPPEN");
        s.click(player);
    }

    public void displayInventory(Player player) {
        closeMenu(player);
        Inventory inventory = Bukkit.createInventory(null, size, getName());
        for (int i = 0; i < size; i++) {
            Slot l = slotMapping.get(i);
            if (l == null)
                throw new RuntimeException("Slot in slot " + i + " is null, this should NEVER HAPPEN");
            boolean cancel = false;
            for (InventoryDisplayHook hook : this.hookList) {
                if (!hook.shouldDisplay(player, l)) {
                    cancel = true;
                    break;
                }
            }
            if (cancel)
                continue;
            if (l.hasStack())
                inventory.setItem(i, l.getStack());
        }
        player.openInventory(inventory);
    }

    public void addHook(InventoryDisplayHook hook) {
        if (hook == null)
            return;
        this.hookList.add(hook);
    }

    public void removeHook(InventoryDisplayHook hook) {
        if (hook == null)
            return;
        this.hookList.remove(hook);
    }

    protected void fillSlots() {
        slotMapping.clear();
        for (int i = 0; i < size; i++) {
            slotMapping.put(i, new Slot(i));
        }
    }

    /**
     * The {@link org.bukkit.event.EventHandler} for inventory changes to be passed to the menu.
     *
     * @param event The inventory event to be passed to the menu
     */
    @EventHandler
    public void onInventoryEvent(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals(this.getName())) return;
        this.click((Player)event.getWhoClicked(), event.getSlot());
        event.setCancelled(true);
    }

    public static void closeMenu(final Player player) {
        player.closeInventory();
    }
}
