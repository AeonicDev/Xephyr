package com.dhlab.xephyr.bukkit.items;

import com.dhlab.xephyr.generic.FormattingNameable;
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
 * @author maladr0it
 */
public class SpecialItem implements Listener,FormattingNameable {

    protected final String name;
    protected final Material material;
    protected final List<String> lore = new ArrayList<String>();

    public SpecialItem(String name, Material m) {
        if (name == null)
            throw new NullPointerException("Name cannot be null!");
        if (m == null)
            throw new NullPointerException("Material cannot be null!");
        this.name = name;
        this.material = m;
    }

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

    public void addLore(String line) {
        lore.add(line);
    }

    public void removeLore(int line) {
        lore.remove(line);
    }

    public String[] getLore() {
        return lore.toArray(new String[lore.size()]);
    }

    public Material getMaterial() {
        return material;
    }

    /**
     * A generic method to spawn a working version of this item into the world.
     * @return
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
     * A generic method to spawn a working version of this item into the world,
     * @param amount
     * @return
     */
    public ItemStack spawnItem(int amount) {
        ItemStack h = this.spawnItem();
        h.setAmount(amount);
        return h;
    }

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
