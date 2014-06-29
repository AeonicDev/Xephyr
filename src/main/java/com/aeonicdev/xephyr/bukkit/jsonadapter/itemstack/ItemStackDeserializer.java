package com.aeonicdev.xephyr.bukkit.jsonadapter.itemstack;

import com.aeonicdev.xephyr.bukkit.jsonadapter.fireworkeffect.FireworkEffectDeserializer;
import com.aeonicdev.xephyr.bukkit.jsonadapter.potioneffect.PotionEffectDeserializer;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.com.google.gson.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Deserializes ItemStacks from JSON strings.
 *
 * @author sc4re
 */
public final class ItemStackDeserializer implements JsonDeserializer<ItemStack> {

    /**
     * Private constructor for the singleton instance.
     */
    private ItemStackDeserializer() { }

    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Validate.notNull(jsonElement);
        Validate.isTrue(jsonElement.isJsonObject());

        JsonObject obj = jsonElement.getAsJsonObject();
        ItemStack stk = new ItemStack(Material.AIR, 1);
        copyID(stk, obj);
        copyCount(stk, obj);
        copyDamage(stk, obj);
        copyName(stk, obj);
        copyLore(stk, obj);
        copyEnchants(stk, obj);
        copyRepairCost(stk, obj);
        copySpecificMeta(stk, obj);
        return stk;
    }

    /**
     * Copies the item ID from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyID(ItemStack stack, JsonObject obj) {
        if (obj.has(ItemStackFormat.ID))
            stack.setTypeId(obj.get(ItemStackFormat.ID).getAsInt());
    }

    /**
     * Copies the item amount from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyCount(ItemStack stack, JsonObject obj) {
        if (obj.has(ItemStackFormat.COUNT))
            stack.setAmount(obj.get(ItemStackFormat.COUNT).getAsInt());
    }

    /**
     * Copies the item damage value from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyDamage(ItemStack stack, JsonObject obj) {
        if (obj.has(ItemStackFormat.DAMAGE))
            stack.setDurability(obj.get(ItemStackFormat.DAMAGE).getAsShort());
    }

    /**
     * Copies the item name from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyName(ItemStack stack, JsonObject obj) {
        if (obj.has(ItemStackFormat.NAME)) {
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(obj.get(ItemStackFormat.NAME).getAsString());
            stack.setItemMeta(meta);
        }
    }

    /**
     * Copies the item lore from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyLore(ItemStack stack, JsonObject obj) {
        if (obj.has(ItemStackFormat.LORE)) {
            List<String> lore = new ArrayList<String>();
            JsonArray arr = obj.get(ItemStackFormat.LORE).getAsJsonArray();
            if (arr == null)
                return;
            Iterator<JsonElement> iter = arr.iterator();
            while (iter.hasNext()) {
                JsonElement el = iter.next();
                if (el.isJsonPrimitive())
                    lore.add(el.getAsJsonPrimitive().getAsString());
            }
            ItemMeta meta = stack.getItemMeta();
            meta.setLore(lore);
        }
    }

    /**
     * Copies the item enchants from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyEnchants(ItemStack stack, JsonObject obj) {
        if (stack.getEnchantments().isEmpty())
            return;
        if (obj.has(ItemStackFormat.ENCHANTS)) {
            JsonObject enchants = obj.getAsJsonObject(ItemStackFormat.ENCHANTS);
            for (Map.Entry<String, JsonElement> entry : obj.entrySet())
            {
                int id = Integer.valueOf(entry.getKey());
                Enchantment ench = Enchantment.getById(id);
                int lvl = entry.getValue().getAsInt();
                stack.addUnsafeEnchantment(ench, lvl);
            }
        }
    }

    /**
     * Copies the item repair cost from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copyRepairCost(ItemStack stack, JsonObject obj) {
        if (!(stack instanceof Repairable))
            return;
        if (obj.has(ItemStackFormat.REPAIRCOST)) {
            if (stack instanceof Repairable) {
                ((Repairable)stack).setRepairCost(obj.get(ItemStackFormat.REPAIRCOST).getAsInt());
            }
        }
    }

    /**
     * Copies the specific item meta from the specified {@link org.bukkit.craftbukkit.libs.com.google.gson.JsonObject} to the specified {@link org.bukkit.inventory.ItemStack}.
     *
     * @param stack The item stack.
     * @param obj The JSON object.
     */
    public void copySpecificMeta(ItemStack stack, JsonObject obj) {
        if (!stack.hasItemMeta())
            return;
        ItemMeta baseMeta = stack.getItemMeta();
        if (baseMeta instanceof BookMeta) {
            BookMeta bm = (BookMeta)baseMeta;
            if (obj.has(ItemStackFormat.BOOK_TITLE))
                bm.setTitle(obj.get(ItemStackFormat.BOOK_TITLE).getAsString());
            if (obj.has(ItemStackFormat.BOOK_AUTHOR))
                bm.setAuthor(obj.get(ItemStackFormat.BOOK_AUTHOR).getAsString());
            if (obj.has(ItemStackFormat.BOOK_PAGES)) {
                List<String> pages = new ArrayList<String>();
                JsonArray arr = obj.getAsJsonArray(ItemStackFormat.BOOK_PAGES);
                if (arr == null)
                    return;
                Iterator<JsonElement> iter = arr.iterator();
                while (iter.hasNext()) {
                    JsonElement next = iter.next();
                    if (next.isJsonPrimitive())
                        pages.add(next.getAsJsonPrimitive().getAsString());
                }
                bm.setPages(pages);
            }
        }
        if (baseMeta instanceof LeatherArmorMeta) {
            LeatherArmorMeta lam = (LeatherArmorMeta)baseMeta;
            if (obj.has(ItemStackFormat.LEATHER_ARMOR_COLOR))
                lam.setColor(Color.fromRGB(obj.get(ItemStackFormat.LEATHER_ARMOR_COLOR).getAsInt()));
        }
        if (baseMeta instanceof MapMeta) {
            MapMeta mm = (MapMeta)baseMeta;
            if (obj.has(ItemStackFormat.MAP_SCALING))
                mm.setScaling(obj.get(ItemStackFormat.MAP_SCALING).getAsBoolean());
        }
        if (baseMeta instanceof SkullMeta) {
            SkullMeta sm = (SkullMeta)baseMeta;
            if (obj.has(ItemStackFormat.SKULL_OWNER))
                sm.setOwner(obj.get(ItemStackFormat.SKULL_OWNER).getAsString());
        }
        if (baseMeta instanceof PotionMeta) {
            PotionMeta pm = (PotionMeta)baseMeta;
            if (obj.has(ItemStackFormat.POTION_EFFECTS)) {
                JsonArray arr = obj.getAsJsonArray(ItemStackFormat.POTION_EFFECTS);
                Iterator<JsonElement> iter = arr.iterator();
                while (iter.hasNext()) {
                    pm.addCustomEffect(PotionEffectDeserializer.get().deserialize(iter.next(), null, null), true);
                }
            }
        }
        if (baseMeta instanceof FireworkEffectMeta) {
            FireworkEffectMeta fem = (FireworkEffectMeta)baseMeta;
            if (obj.has(ItemStackFormat.FIREWORK_EFFECT))
                fem.setEffect(FireworkEffectDeserializer.get().deserialize(obj.getAsJsonObject(ItemStackFormat.FIREWORK_EFFECT), null, null));
        }
        if (baseMeta instanceof FireworkMeta) {
            FireworkMeta fm = (FireworkMeta)baseMeta;
            if (obj.has(ItemStackFormat.FIREWORK_EFFECTS)) {
                JsonArray arr = obj.getAsJsonArray(ItemStackFormat.FIREWORK_EFFECTS);
                Iterator<JsonElement> iter = arr.iterator();
                while (iter.hasNext()) {
                    fm.addEffect(FireworkEffectDeserializer.get().deserialize(iter.next(), null, null));
                }
            }
        }
        if (baseMeta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta esm = (EnchantmentStorageMeta)baseMeta;
            if (obj.has(ItemStackFormat.STORED_ENCHANTS)) {
                JsonObject enchants = obj.getAsJsonObject(ItemStackFormat.STORED_ENCHANTS);
                for (Map.Entry<String, JsonElement> entry : enchants.entrySet())
                {
                    int id = Integer.valueOf(entry.getKey());
                    Enchantment ench = Enchantment.getById(id);
                    int lvl = entry.getValue().getAsInt();
                    esm.addStoredEnchant(ench, lvl, true);
                }
            }
        }
    }

    /**
     * The singleton deserializer instance.
     */
    private static final ItemStackDeserializer instance = new ItemStackDeserializer();

    /**
     * Gets the singleton instance of this deserializer.
     *
     * @return The deserializer instance.
     */
    public static ItemStackDeserializer get() { return instance; }

}
