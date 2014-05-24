package com.dhlab.xephyr.bukkit.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A SpecialItem that takes in a short as an argument to represent
 * the durability of the item.
 * @author maladr0it
 */
public class DamagedSpecialItem extends SpecialItem {

    protected final short damage;

    public DamagedSpecialItem(String name, Material m, short damage) {
        super(name, m);
        this.damage = damage;
    }

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
