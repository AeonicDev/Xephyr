package com.dhlab.xephyr.bukkit.items;

import com.dhlab.xephyr.generic.FormattingNameable;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * A class designed to provide a wrapper for the kinds of items you see in Hunger Games servers,
 * such as items with the property to summon lightning or other things.
 * Note: The name MUST have colors in it, or it will be possible for people to use anvils to acquire
 * the items. Sadly, damage values no longer work because the Bukkit team removed the ability to
 * put damage values on non-damageable items.
 *
 * @author maladr0it
 */
public class SpecialItem implements Listener,FormattingNameable {

    /**
     * The name of the special item.
     */
    protected final String name;

    /**
     * The material of the special item.
     */
    protected final Material material;

    /**
     * The lore value(s) of the special item.
     */
    protected final List<String> lore = new ArrayList<String>();

    /**
     * Creates a new {@code SpecialItem} with the specified name and {@link org.bukkit.Material}.
     *
     * @param name The name of the special item.
     * @param m The material of the special item.
     */
    public SpecialItem(String name, Material m) {
        Validate.notNull(name);
        Validate.notNull(m);
        this.name = name;
        this.material = m;
    }

    /**
     * Gets the name of this {@code SpecialItem}.
     *
     * @return The name of the special item.
     */
    public final String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public String getUnformattedName() {
        return ChatColor.stripColor(getName());
    }

    /**
     * Adds a line to this {@code SpecialItem} object's lore.
     *
     * @param line The line to add.
     */
    public void addLore(String line) {
        lore.add(line);
    }

    /**
     * Removes a line to from this {@code SpecialItem} object's lore.
     *
     * @param line The line index to remove.
     */
    public void removeLore(int line) {
        lore.remove(line);
    }

    /**
     * Gets this {@code SpecialItem} object's lore.
     *
     * @return
     */
    public String[] getLore() {
        return lore.toArray(new String[lore.size()]);
    }

    /**
     * Gets this {@code SpecialItem} object's material.
     *
     * @return
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Spawns a working version of this item into the world.
     *
     * @return The item stack of the special item.
     */
    public ItemStack spawnItem() {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(this.lore);
        stack.setItemMeta(meta);
        return stack;
    }

    /**
     * Spawns a working version of this item into the world with the specified stack amount.
     *
     * @param amount The stack size.
     * @return The item stack of the special item.
     */
    public ItemStack spawnItem(int amount) {
        ItemStack h = this.spawnItem();
        h.setAmount(amount);
        return h;
    }

    /**
     * Gets if the specified {@link org.bukkit.inventory.ItemStack} is the same as this {@code SpecialItem}.
     *
     * @param stack The item stack to check.
     * @return If the item stack is the same as this special item.
     */
    public boolean isItem(ItemStack stack) {
        if (stack == null)
            return false;
        if (!stack.getType().equals(this.material))
            return false;
        if (stack.getItemMeta() == null)
            return false;
        if (!stack.getItemMeta().getDisplayName().equals(this.getName()))
            return false;
        return true;
    }


}
