package com.aeonicdev.xephyr.bukkit.kit.base;

import com.aeonicdev.xephyr.bukkit.helper.inventory.InventoryHelper;
import com.aeonicdev.xephyr.bukkit.kit.base.config.KitConfig;
import com.aeonicdev.xephyr.bukkit.kit.base.listen.FluidKitListener;
import com.aeonicdev.xephyr.bukkit.kit.base.listen.KitStateListener;
import com.aeonicdev.xephyr.bukkit.kit.base.listen.SolidKitListener;
import com.aeonicdev.xephyr.bukkit.utilities.MinecraftConstants;
import com.aeonicdev.xephyr.generic.Enableable;
import com.aeonicdev.xephyr.generic.helper.HelperRegistry;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A generic "kit" class that allows for registration of listeners, modification of configurations, and different items.
 * It also sets up an environment in which
 * @author maladr0it
 */
public class Kit implements Enableable {

    /**
     * The configuration that this Kit has.
     */
    protected KitConfig configuration;
    /**
     * The "inventory" to use with this kit (mapping of slot # -> itemstack)
     */
    protected final Map<Integer, ItemStack> items = new HashMap<>();

    /**
     * The metadata to set on the player when a player has this kit applied.
     */
    protected String metadata;

    /**
     * The name for this kit, if the kit is unavailable
     */
    protected String name;

    /**
     * The plugin to use for registering various events.
     */
    protected final Plugin plugin;

    /**
     * The ItemStack to use as the player's helmet.
     */
    protected ItemStack helmet;
    /**
     * The ItemStack to use as the player's chestplate.
     */
    protected ItemStack chestplate;
    /**
     * The ItemStack to use as the player's leggings.
     */
    protected ItemStack leggings;
    /**
     * The ItemStack to use as the player's boots.
     */
    protected ItemStack boots;

    /**
     * Determines internally if onEnable has already been called.
     */
    private boolean alreadyEnabled = false;

    /**
     * The FluidKitListener-related
     */
    protected final Map<String, FluidKitListener> fluidListners = new HashMap<>();
    protected final List<SolidKitListener<?>> solidListeners = new ArrayList<>();
    protected final List<KitStateListener> stateListeners = new ArrayList<>();

    /**
     * Creates a new instance of a Kit object, using the string provided as the "kit metadata",
     * which allows us to track who has and doesn't have this kit.
     * @param name The name of the kit
     */
    public Kit(Plugin plugin, String name) {
        Validate.notNull(plugin);
        Validate.isTrue(plugin.isEnabled(), "Plugin must be enabled before initializing a kit!");
        Validate.notNull(name);
        this.plugin = plugin;
        this.configuration = new KitConfig();
    }

    /**
     * Accessor for the metadata string of this kit.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the metadata if it exists, else it returns
     * @return
     */
    public String getMetadata() {
        return metadata == null ? getPlugin().getName() + "_kit_" + getName() : metadata;
    }

    /**
     * Sets the specific metadata for this kit.
     * @param md
     */
    public void setMetadata(String md) {
        this.metadata = md;
    }

    /**
     * Returns the plugin that this kit is using.
     * @return
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Returns the configuration
     * @return
     */
    public KitConfig getConfiguration() { return configuration; }

    /**
     * Sets a specific slot
     * @param slot The slot to set (can be an armor or player inventory slot)
     * @param stack The itemstack to set the slot to.
     */
    public void setSlot(int slot, ItemStack stack) {
        // if the stack is null, simply remove that slot
        if (stack == null) {
            removeSlot(slot);
            return;
        }
        // make sure it isn't a crash item.
        Validate.isTrue(stack.getAmount() > 0, "Stack amount must be a valid number!");
        boolean inventory = isInventorySlot(slot);
        boolean armor = isArmorSlot(slot);
        Validate.isTrue(inventory || armor, "Slot must be a valid player slot!");
        if (inventory) {
            items.put(slot, stack);
        } else if (armor) {
            setArmorSlot(slot, stack);
        }
    }

    /**
     * Removes the slot specified from the inventory.
     * @param slot The slot to remove.
     */
    public void removeSlot(int slot) {
        if (isInventorySlot(slot)) {
            items.remove(slot);
        } else if (isArmorSlot(slot)) {
            setArmorSlot(slot, null);
        }
    }

    /**
     * Retrieves the boots object
     * @return The boots of this kit
     */
    public ItemStack getBoots() {
        return boots;
    }

    /**
     * Retrieves the leggings object
     * @return The leggings of this kit
     */
    public ItemStack getLeggings() {
        return leggings;
    }

    /**
     * Retrieves the chestplate object
     * @return The chestplate of this kit
     */
    public ItemStack getChestplate() {
        return chestplate;
    }

    /**
     * Retrieves the helmet object.
     * @return The helmet of this kit
     */
    public ItemStack getHelmet() {
        return helmet;
    }

    /**
     * Sets the boots of the player.
     * @param stack The stack to use for boots.
     */
    public void setBoots(ItemStack stack) {
        Validate.notNull(stack);
        Validate.isTrue(stack.getAmount() > 0, "Stack amount cannot be 0!");
        this.boots = stack;
    }

    /**
     * Sets the leggings of the player.
     * @param stack The stack use for leggings.
     */
    public void setLeggings(ItemStack stack) {
        Validate.notNull(stack);
        Validate.isTrue(stack.getAmount() > 0, "Stack amount cannot be 0!");
        this.leggings = stack;
    }

    /**
     * Sets the chestplate of the player.
     * @param stack The stack to use for a chestplate.
     */
    public void setChestplate(ItemStack stack) {
        Validate.notNull(stack);
        Validate.isTrue(stack.getAmount() > 0, "Stack amount cannot be 0!");
        this.chestplate = stack;
    }

    /**
     * Sets the helmet of the player.
     * @param stack The stack to use for a helmet.
     */
    public void setHelmet(ItemStack stack) {
        Validate.notNull(stack);
        Validate.isTrue(stack.getAmount() > 0, "Stack amount cannot be 0!");
        this.helmet = stack;
    }

    /**
     * Sets a specific armor slot, uses a switch statement to confirm which slot is being set.
     * TODO: use an enum that does this.
     * @param slot The armor slot to set.
     * @param stack The stack to set the armor to.
     */
    public void setArmorSlot(int slot, ItemStack stack) {
        Validate.notNull(stack);
        Validate.isTrue(isArmorSlot(slot), "Slot must be an armor slot!");
        switch (slot) {
            case MinecraftConstants.PLAYER_INVENTORY_BOOTS_SLOT:
                setBoots(stack);
                break;
            case MinecraftConstants.PLAYER_INVENTORY_LEGGINGS_SLOT:
                setLeggings(stack);
                break;
            case MinecraftConstants.PLAYER_INVENTORY_CHESTPLATE_SLOT:
                setChestplate(stack);
                break;
            case MinecraftConstants.PLAYER_INVENTORY_HELMET_SLOT:
                setHelmet(stack);
                break;
            default:
                break;
        }
    }

    /**
     * Adds a "Fluid" listener, which creates our actual Solid listeners, allowing our Fluid listener to be somewhat of a flyweight.
     * @param listener The listener to add.
     */
    public void addFluidListener(FluidKitListener listener) {
        Validate.notNull(listener);
        SolidKitListener<?>[] arr = listener.getSolidListeners(this);
        for (SolidKitListener<?> k : arr) {
            this.solidListeners.add(k);
        }
    }

    /**
     * Adds a State listener, which a
     * @param listener
     */
    public void addStateListener(KitStateListener listener) {
        stateListeners.add(listener);
    }

    /**
     * Returns true if the player has the kit metadata of this kit.
     * @param player The player check the metadata of.
     * @return
     */
    public boolean hasKit(Player player) {
        Validate.notNull(player);
        return player.hasMetadata(metadata);
    }

    /**
     * Removes the kit from the player.
     * @param player
     * @param clearInv Whether to clear the player's inventory or not
     */
    public void removeKit(Player player, boolean clearInv) {
        Validate.notNull(player);
        if (!hasKit(player))
            return;
        player.removeMetadata(metadata, plugin);
        if (clearInv) {
            player.getInventory().clear();
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
        }
    }

    /**
     * Applies the kit to the player.
     * @param player The player to apply the kit to.
     * @param clearInv Whether to clear the player's inventory or not.
     */
    public void applyKit(Player player, boolean clearInv) {
        Validate.notNull(player);
        player.setMetadata(metadata, new FixedMetadataValue(plugin, true));
        PlayerInventory inv = player.getInventory();
        if (clearInv) {
            inv.clear();
            inv.setHelmet(null);
            inv.setChestplate(null);
            inv.setLeggings(null);
            inv.setBoots(null);
        }
        for (Map.Entry<Integer, ItemStack> entry : this.items.entrySet()) {
            if (inv.getItem(entry.getKey()) != null && !clearInv) {
                if (HelperRegistry.INSTANCE.get(InventoryHelper.class).hasSpace(inv, entry.getValue())) {
                    HelperRegistry.INSTANCE.get(InventoryHelper.class).addAllItems(inv, entry.getValue());
                }
            } else {
                player.getInventory().setItem(entry.getKey(), entry.getValue());
            }
        }
        if (this.helmet != null)
            player.getInventory().setHelmet(helmet);
        if (this.chestplate != null)
            player.getInventory().setChestplate(chestplate);
        if (this.leggings != null)
            player.getInventory().setLeggings(leggings);
        if (this.boots != null)
            player.getInventory().setBoots(boots);
    }

    /**
     * Returns true if the slot provided is a valid "Player Inventory" slot.
     * @param slot The slot number to check.
     * @return
     */
    private boolean isInventorySlot(int slot) {
        return (slot >= MinecraftConstants.PLAYER_INVENTORY_MIN_BOUND && slot <= MinecraftConstants.PLAYER_INVENTORY_MAX_BOUND);
    }

    /**
     * Returns true if the slot provided is a valid armor slot.
     * @param slot The slot number to check.
     * @return
     */
    private boolean isArmorSlot(int slot) {
        return (slot >= MinecraftConstants.PLAYER_INVENTORY_BOOTS_SLOT && slot <= MinecraftConstants.PLAYER_INVENTORY_HELMET_SLOT);
    }

    @Override
    public void onEnable() {
        for (Listener l : this.solidListeners) {
            Bukkit.getPluginManager().registerEvents(l, this.getPlugin());
        }
    }

    @Override
    public void onDisable() {
        for (Listener l : this.solidListeners) {
            HandlerList.unregisterAll(l);
        }
    }
}
