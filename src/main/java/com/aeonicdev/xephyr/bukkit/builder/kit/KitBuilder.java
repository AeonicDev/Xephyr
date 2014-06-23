package com.aeonicdev.xephyr.bukkit.builder.kit;

import com.aeonicdev.xephyr.bukkit.kit.base.Kit;
import com.aeonicdev.xephyr.bukkit.kit.base.listen.FluidKitListener;
import com.aeonicdev.xephyr.bukkit.kit.base.listen.KitStateListener;
import com.aeonicdev.xephyr.generic.builder.Builder;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * A builder designed to build kits quickly and easily, from configuration or otherwise.
 * @author maladr0it
 */
public class KitBuilder implements Builder<Kit> {

    /**
     * The kit this builder is creating.
     */
    protected Kit kit;

    public KitBuilder(Plugin plugin, String kitName) {
        Validate.notNull(plugin);
        Validate.notNull(kitName);
        Validate.notEmpty(kitName);
        this.kit = new Kit(plugin, kitName);
    }

    /**
     * Sets a specific slot inside the kit.
     * @param slot The slot to set.
     * @param stack The stack to set the slot to.
     * @return
     */
    public KitBuilder slot(int slot, ItemStack stack) {
        kit.setSlot(slot, stack);
        return this;
    }

    /**
     * Sets the helmet of the kit.
     * @param stack The stack to set as the helmet.
     * @return The instance of the KitBuilder
     */
    public KitBuilder helmet(ItemStack stack) {
        kit.setHelmet(stack);
        return this;
    }

    /**
     * Sets the chestplate of the kit.
     * @param stack The stack to set as the chestplate.
     * @return The instance of the KitBuilder
     */
    public KitBuilder chestplate(ItemStack stack) {
        kit.setChestplate(stack);
        return this;
    }

    /**
     * Sets the leggings of the kit.
     * @param stack The stack to set as the leggigs.
     * @return The instance of the KitBuilder
     */
    public KitBuilder leggings(ItemStack stack) {
        kit.setLeggings(stack);
        return this;
    }

    /**
     * Sets the boots of the kit.
     * @param stack The stack to set as the boots.
     * @return The instance of the KitBuilder
     */
    public KitBuilder boots(ItemStack stack) {
        kit.setBoots(stack);
        return this;
    }

    /**
     * Sets the metadata of the kit.
     * @param metadata The metadata to set on the kit
     * @return The instance of the KitBuilder
     */
    public KitBuilder metadata(String metadata) {
        Validate.notNull(metadata);
        Validate.notEmpty(metadata);
        kit.setMetadata(metadata);
        return this;
    }

    /**
     * Transforms the kit using an interface designed to apply many differnt things at once
     * to the kit.
     * @param transformer
     * @return The instance of the KitBuilder
     */
    public KitBuilder transform(KitTransformer transformer) {
        Validate.notNull(transformer);
        transformer.transform(kit);
        return this;
    }

    /**
     * Applies and sets up a FluidListener for the kit
     * @param listener The FluidKitListener
     * @return The instance of the KitBuilder
     */
    public KitBuilder fluidListener(FluidKitListener listener) {
        Validate.notNull(listener);
        kit.addFluidListener(listener);
        return this;
    }

    /**
     * Applies and sets up a KitStateListener for the kit
     * @param listener The KitStateListener
     * @return The instance of the KitBuilder
     */
    public KitBuilder stateListener(KitStateListener listener) {
        Validate.notNull(listener);
        kit.addStateListener(listener);
        return this;
    }

    /**
     * Returns the kit directly, since kits are not immutable and listeners are easier to add this way.
     * @return The fully built kit.
     */
    @Override
    public Kit build() {
        return kit;
    }
}
