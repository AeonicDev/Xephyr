package com.aeonicdev.xephyr.bukkit.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A SpecialItem that takes in a short as an argument to represent
 * the durability of the item.
 *
 * @author maladr0it
 */
public class DamagedSpecialItem extends SpecialItem {

    /**
     * The damage value of the item.
     */
    protected final short damage;

    /**
     * Creates a new {@code DamagedSpecialItem}.
     *
     * @param name The item name.
     * @param m The item material.
     * @param damage The damage value of the item.
     */
    public DamagedSpecialItem(String name, Material m, short damage) {
        super(name, m);
        this.damage = damage;
    }

    @Override
    public ItemStack spawnItem() {
        ItemStack stack = super.spawnItem();
        stack.setDurability(damage);
        return stack;
    }

    @Override
    public boolean isItem(ItemStack stack) {
        return super.isItem(stack) && this.damage == stack.getDurability();
    }

}
