package com.dhlab.xephyr.bukkit.jsonadapter.itemstack;

import com.dhlab.xephyr.bukkit.jsonadapter.fireworkeffect.FireworkEffectSerializer;
import com.dhlab.xephyr.bukkit.jsonadapter.potioneffect.PotionEffectSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.com.google.gson.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * A serialization class to turn ItemStacks into JSON elements using Gson.
 * @author maladr0it
 */
public final class ItemStackSerializer implements JsonSerializer<ItemStack> {

    private static final Gson gson = new Gson();

    private ItemStackSerializer() { }

    @Override
    public JsonElement serialize(ItemStack stk, Type type, JsonSerializationContext jsonSerializationContext) {
        if (stk == null)
            return null;
        if (stk.getType().equals(Material.AIR))
            return null;
        if (stk.getAmount() == 0)
            return null;
        JsonObject obj = new JsonObject();
        copyID(stk, obj);
        copyCount(stk, obj);
        copyDamage(stk, obj);
        copyName(stk, obj);
        copyLore(stk, obj);
        copyEnchants(stk, obj);
        copyRepairCost(stk, obj);
        copySpecificMeta(stk, obj);
        return obj;
    }

    public void copyID(ItemStack stack, JsonObject obj) {
        obj.addProperty(ItemStackFormat.ID, stack.getType().getId());
    }

    public void copyCount(ItemStack stack, JsonObject obj) {
        obj.addProperty(ItemStackFormat.COUNT, stack.getAmount());
    }

    public void copyDamage(ItemStack stack, JsonObject obj) {
        obj.addProperty(ItemStackFormat.DAMAGE, stack.getDurability());
    }

    public void copyName(ItemStack stack, JsonObject obj) {
        if (stack.getItemMeta() == null)
            return;
        if (stack.getItemMeta().getDisplayName() == null)
            return;
        obj.addProperty(ItemStackFormat.NAME, stack.getItemMeta().getDisplayName());
    }

    public void copyLore(ItemStack stack, JsonObject obj) {
        if (stack.getItemMeta() == null)
            return;
        if (stack.getItemMeta().getLore() == null)
            return;
        obj.add(ItemStackFormat.LORE, gson.toJsonTree(stack.getItemMeta().getLore()));
    }

    public void copyEnchants(ItemStack stack, JsonObject obj) {
        if (stack.getEnchantments().isEmpty())
            return;
        JsonObject enchantTree = new JsonObject();
        for (Map.Entry<Enchantment, Integer> enchants : stack.getEnchantments().entrySet()) {
            enchantTree.addProperty("" + enchants.getKey().getId(), enchants.getValue());
        }
        obj.add(ItemStackFormat.ENCHANTS, enchantTree);
    }

    public void copyRepairCost(ItemStack stack, JsonObject obj) {
        if (!(stack.getItemMeta() instanceof Repairable))
            return;
        Repairable rp = (Repairable)stack.getItemMeta();
        if (rp.hasRepairCost())
            obj.addProperty(ItemStackFormat.REPAIRCOST, rp.getRepairCost());
    }

    public void copySpecificMeta(ItemStack stack, JsonObject obj) {
        if (!stack.hasItemMeta())
            return;
        ItemMeta baseMeta = stack.getItemMeta();
        if (baseMeta instanceof BookMeta) {
            BookMeta bm = (BookMeta)baseMeta;
            obj.addProperty(ItemStackFormat.BOOK_TITLE, bm.getTitle());
            obj.addProperty(ItemStackFormat.BOOK_AUTHOR, bm.getAuthor());
            JsonArray arr = new JsonArray();
            for (String s : bm.getPages()) {
                arr.add(new JsonPrimitive(s));
            }
            obj.add(ItemStackFormat.BOOK_PAGES, arr);
        }
        if (baseMeta instanceof LeatherArmorMeta) {
            LeatherArmorMeta lam = (LeatherArmorMeta)baseMeta;
            Color c = lam.getColor();
            if (!c.equals(Bukkit.getItemFactory().getDefaultLeatherColor()))
                obj.addProperty(ItemStackFormat.LEATHER_ARMOR_COLOR, c.asRGB());
        }
        if (baseMeta instanceof MapMeta) {
            MapMeta mm = (MapMeta)baseMeta;
            obj.addProperty(ItemStackFormat.MAP_SCALING, mm.isScaling());
        }
        if (baseMeta instanceof SkullMeta) {
            SkullMeta sm = (SkullMeta)baseMeta;
            obj.addProperty(ItemStackFormat.SKULL_OWNER, sm.getOwner());
        }
        if (baseMeta instanceof PotionMeta) {
            PotionMeta pm = (PotionMeta)baseMeta;
            JsonArray potions = new JsonArray();
            for (PotionEffect effect : pm.getCustomEffects()) {
                JsonObject pobj = PotionEffectSerializer.get().serialize(effect, null, null).getAsJsonObject();
                potions.add(pobj);
            }
            obj.add(ItemStackFormat.POTION_EFFECTS, potions);
        }
        if (baseMeta instanceof FireworkEffectMeta) {
            FireworkEffectMeta fem = (FireworkEffectMeta)baseMeta;
            obj.add(ItemStackFormat.FIREWORK_EFFECT, FireworkEffectSerializer.get().serialize(fem.getEffect(), null, null));
        }
        if (baseMeta instanceof FireworkMeta) {
            FireworkMeta fm = (FireworkMeta)baseMeta;
            JsonArray effects = new JsonArray();
            for (FireworkEffect effect : fm.getEffects()) {
                effects.add(FireworkEffectSerializer.get().serialize(effect, null, null));
            }
            obj.add(ItemStackFormat.FIREWORK_EFFECTS, effects);
            obj.addProperty(ItemStackFormat.FIREWORK_POWER, fm.getPower());
        }
        if (baseMeta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta esm = (EnchantmentStorageMeta)baseMeta;
            JsonObject enchantTree = new JsonObject();
            for (Map.Entry<Enchantment, Integer> enchants : esm.getStoredEnchants().entrySet()) {
                enchantTree.addProperty("" + enchants.getKey().getId(), enchants.getValue());
            }
            obj.add(ItemStackFormat.STORED_ENCHANTS, enchantTree);
        }
    }

    private static final ItemStackSerializer instance = new ItemStackSerializer();
    public static final ItemStackSerializer get() { return instance; }
}
