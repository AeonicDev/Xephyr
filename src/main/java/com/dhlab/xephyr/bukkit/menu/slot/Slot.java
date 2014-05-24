package com.dhlab.xephyr.bukkit.menu.slot;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Slot class for use in InventoryMenus.
 * @author maladr0it
 */
public class Slot implements SlotAction {
    protected final int slotNumber;

    protected List<SlotAction> actions = new ArrayList<SlotAction>();

    protected ItemStack slotStack;

    public Slot(int number) {
        this.slotNumber = number;
    }

    @Override
    public void click(Player p) {
        for (SlotAction a : getContents()) {
            a.click(p);
        }
    }

    public void add(SlotAction action) {
        this.actions.add(action);
    }

    public void remove(SlotAction action) {
        this.actions.remove(action);
    }

    public SlotAction[] getContents() {
        return actions.toArray(new SlotAction[actions.size()]);
    }

    public ItemStack getStack() {
        return slotStack;
    }

    public void setStack(ItemStack stack) {
        this.slotStack = stack;
    }

    public boolean hasStack() {
        return slotStack != null;
    }
}
