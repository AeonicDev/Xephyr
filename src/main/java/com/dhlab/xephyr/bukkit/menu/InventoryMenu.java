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

    /**
     * The maximum inventory size before the client sees visual glitches.
     */
    public static final int MAX_INVENTORY_SIZE = 54;

    /**
     * The name of this menu.
     */
    protected final String name;

    /**
     * The size of this menu, defaults to 9.
     */
    protected int size = 9;

    /**
     * The map of slots.
     */
    protected final Map<Integer, Slot> slotMapping = new HashMap<Integer, Slot>();

    /**
     * The list of hooks that will be called when a player tries to see a slot.
     */
    protected final List<InventoryDisplayHook> hookList = new ArrayList<InventoryDisplayHook>();

    /**
     * Creates a new {@code InventoryMenu} with the specified name.
     *
     * @param name The name of the menu.
     */
    public InventoryMenu(String name) {
        Validate.notNull(name);
        this.name = name;
    }

    /**
     * Creates a new {@code InventoryMenu} with the specified name and size.
     *
     * @param name The name of the menu.
     * @param size The size of the menu.
     */
    public InventoryMenu(String name, int size) {
        this(name);
        Validate.isTrue(size % 9 == 0, "Size must be divisible by 9");
        Validate.isTrue(size >= 9, "Size must be at least 9!");
        Validate.isTrue(size < MAX_INVENTORY_SIZE, "Size cannot be larger than 54!");
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

    /**
     * Gets a {@link com.dhlab.xephyr.bukkit.menu.slot.Slot} by its map key.
     *
     * @param key The map key.
     * @return The slot that corresponds to the key.
     */
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

    /**
     * Display the menu to the specified {@link org.bukkit.entity.Player}.
     *
     * @param player The player to show the menu to.
     */
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

    /**
     * Adds an {@link com.dhlab.xephyr.bukkit.menu.InventoryDisplayHook} to this {@code InventoryMenu}.
     *
     * @param hook The hook to be added.
     */
    public void addHook(InventoryDisplayHook hook) {
        Validate.notNull(hook);
        this.hookList.add(hook);
    }

    /**
     * Removes an {@link com.dhlab.xephyr.bukkit.menu.InventoryDisplayHook} from this {@code InventoryMenu}.
     *
     * @param hook The hook to be remove.
     */
    public void removeHook(InventoryDisplayHook hook) {
        this.hookList.remove(hook);
    }

    /**
     * Populates the menu with empty {@link com.dhlab.xephyr.bukkit.menu.slot.Slot} objects.
     */
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

    /**
     * Closes the current {@link org.bukkit.inventory.Inventory} the specified {@link org.bukkit.entity.Player} has open.
     *
     * @param player The player whose inventory to close.
     */
    public static void closeMenu(final Player player) {
        player.closeInventory();
    }

}
