package com.aeonicdev.xephyr.bukkit.builder.item;

import com.aeonicdev.xephyr.generic.builder.Builder;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

/**
 * A builder for a Bukkit {@link org.bukkit.inventory.ItemStack}.
 *
 * @author maladr0it
 */
public class ItemStackBuilder implements Builder<ItemStack> {

    /**
     * The item stack being built.
     */
    private ItemStack stack;

    /**
     * Starts off with a {@link org.bukkit.inventory.ItemStack} of the given {@link org.bukkit.Material}.
     *
     * @param mat The material to start with.
     */
    public ItemStackBuilder(Material mat) {
        Validate.notNull(mat);
        this.stack = new ItemStack(mat);
    }

    /**
     * Starts off with a default {@link org.bukkit.inventory.ItemStack} provided by the user.
     *
     * @param defaultStack The item stack to start from.
     */
    public ItemStackBuilder(ItemStack defaultStack) {
        Validate.notNull(defaultStack);
        this.stack = defaultStack;
    }

    /**
     * Changes the amount of the item stack being built.
     *
     * @param amount The amount to set of the item stack. Must be > 0.
     * @return The updated builder.
     */
    public ItemStackBuilder amount(int amount) {
        Validate.isTrue(amount > 0, "");
        stack.setAmount(amount);
        return this;
    }

    /**
     * Changes the name of the stack using the item meta.
     *
     * @param name The name to give the stack.
     * @return The updated builder.
     */
    public ItemStackBuilder name(String name) {
        Validate.notNull(name);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return this;
    }

    /**
     * Changes the List[String] values of the stack's item meta.
     *
     * @param lines The array of lines to set for the lore.
     * @return The updated builder.
     */
    public ItemStackBuilder lore(String...lines) {
        Validate.notNull(lines);
        Validate.noNullElements(lines);
        Validate.isTrue(lines.length > 1, "Cannot build with no lines!");
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(Arrays.asList(lines));
        stack.setItemMeta(meta);
        return this;
    }

    /**
     * Adds a safe (or unsafe) enchant to the stack being built.
     *
     * @param ench The enchantment to use.
     * @param level The level to set the enchantment.
     * @return The updated builder.
     */
    public ItemStackBuilder enchant(Enchantment ench, int level) {
        Validate.notNull(ench);
        Validate.isTrue(level > 0, "Level must be above zero!");
        if (ench.getMaxLevel() < level) {
            stack.addEnchantment(ench, level);
        } else {
            stack.addUnsafeEnchantment(ench, level);
        }
        return this;
    }

    /**
     * Modifies ItemStack meta on the fly using the MetaTransformer interface.
     *
     * @param transform The "MetaTransformer" to use.
     * @return The updated builder.
     */
    public ItemStackBuilder meta(MetaTransformer transform) {
        Validate.notNull(transform);
        ItemMeta meta = stack.getItemMeta();
        try {
            meta = transform.transform(meta);
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }
        stack.setItemMeta(meta);
        return this;
    }

    /**
     * Adds a potion effect to the item stack, but only if the item stack is an instance of a Potion (i.e; has PotionMeta).
     * If the item is not a potion, the function simply returns the item stack builder.
     *
     * @param effect The effect to apply. Cannot be null.
     * @return The updated builder.
     */
    public ItemStackBuilder potion(PotionEffect effect) {
        Validate.notNull(effect);
        if (!stack.getType().equals(Material.POTION))
            return this;
        PotionMeta meta = (PotionMeta)stack.getItemMeta();
        meta.addCustomEffect(effect, true);
        return this;
    }

    @Override
    public ItemStack build() {
        return stack.clone();
    }

}
