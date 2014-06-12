package com.aeonicdev.xephyr.bukkit.menu.slot;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Slot class for use with {@link com.aeonicdev.xephyr.bukkit.menu.InventoryMenu}.
 *
 * @author maladr0it
 */
public class Slot implements SlotAction {

    /**
     * The slot number.
     */
    protected final int slotNumber;

    /**
     * The actions associated with this slot.
     */
    protected List<SlotAction> actions = new ArrayList<SlotAction>();

    /**
     * The item stack in this slot.
     */
    protected ItemStack slotStack;

    /**
     * Creates a new {@code Slot} object for use with inventory menus.
     *
     * @param number The slot number in the inventory.
     */
    public Slot(int number) {
        this.slotNumber = number;
    }

    @Override
    public void click(Player p) {
        for (SlotAction a : getContents()) {
            a.click(p);
        }
    }

    /**
     * Adds a {@link com.aeonicdev.xephyr.bukkit.menu.slot.SlotAction} to this {@code Slot}.
     *
     * @param action The action to add.
     */
    public void add(SlotAction action) {
        this.actions.add(action);
    }

    /**
     * Removes a {@link com.aeonicdev.xephyr.bukkit.menu.slot.SlotAction} from this {@code Slot}.
     *
     * @param action
     */
    public void remove(SlotAction action) {
        this.actions.remove(action);
    }

    /**
     * Gets all the actions associated with this Slot.
     *
     * @return The slot actions.
     */
    public SlotAction[] getContents() {
        return actions.toArray(new SlotAction[actions.size()]);
    }

    /**
     * Gets the {@link org.bukkit.inventory.ItemStack} in this slot.
     *
     * @return The item stack.
     */
    public ItemStack getStack() {
        return slotStack;
    }

    /**
     * Sets the {@link org.bukkit.inventory.ItemStack} in this slot.
     *
     * @param stack The new item stack.
     */
    public void setStack(ItemStack stack) {
        this.slotStack = stack;
    }

    /**
     * Gets whether this {@code Slot} has an {@link org.bukkit.inventory.ItemStack} or not.
     *
     * @return If this slot is empty.
     */
    public boolean hasStack() {
        return slotStack != null;
    }

}
