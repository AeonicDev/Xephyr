package com.dhlab.xephyr.bukkit.kit.base.listen;

import com.dhlab.xephyr.bukkit.kit.base.Kit;
import com.dhlab.xephyr.bukkit.kit.plugin.KitPlugin;
import com.dhlab.xephyr.bukkit.kit.plugin.KitPluginManager;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Non-dynamic "State" listener, allows methods to hook into when a
 * @author maladr0it
 */
public abstract class KitStateListener implements ConfigurationSerializable {

    // H E  D O E S  I T   F O R  F R E E
    static {
        ConfigurationSerialization.registerClass(KitStateListener.class);
    }

    /**
     * The name of this listener
     */
    protected final String name;
    /**
     * The kit plugin from whence this listener came.
     */
    protected final KitPlugin plugin;

    public KitStateListener(String name, KitPlugin plugin) {
        Validate.notNull(name);
        Validate.notNull(plugin);
        this.name = name;
        this.plugin = plugin;
    }

    public String getName() {
        return name;
    }

    /**
     * Ret
     * @return
     */
    public KitPlugin getPlugin() {
        return plugin;
    }

    /**
     * Called when the kit's onEnable is called.
     * @param kit The kit passed along by itself.
     */
    public void onEnable(Kit kit) { }

    /**
     * Called when the kit's onDisable is called.
     * @param kit The kit passed along by itself.
     */
    public void onDisable(Kit kit) { }

    /**
     * Allows cancellation of applications of kits, useful for permissions plugins and the lot.
     * @param kit The kit passed along by itself.
     * @param player The player the kit is being applied to.
     * @return
     */
    public boolean shouldApply(Kit kit, Player player) { return true; }

    /**
     * Called upon the application of a kit to a player.
     * @param kit The kit passed along by itself.
     * @param player The player the kit has been applied to.
     */
    public void onApply(Kit kit, Player player) { }

    /**
     * Called upon the removal of a kit from a player.
     * @param kit The kit passed along by itself.
     * @param player The player the kit has been detached from.
     */
    public void onDetach(Kit kit, Player player) { }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("name", getName());
        result.put("plugin", getPlugin().getName());
        return result;
    }

    public static KitStateListener deserialize(Map<String, Object> serialized) {
        if (!serialized.containsKey("name") || !serialized.containsKey("plugin"))
            throw new UnsupportedOperationException("Invalid serialized data!");
        String plugin = (String) serialized.get("plugin");
        String name = (String) serialized.get("name");
        KitPlugin pl = KitPluginManager.get().getPlugin(plugin);
        if (pl == null)
            throw new UnsupportedOperationException("Invalid plugin!");
        KitStateListener sl = KitPluginManager.get().findStateListenerByPlugin(pl, name);
        if (sl == null)
            throw new UnsupportedOperationException("Invalid fluid listener name!");
        return sl;
    }

}
