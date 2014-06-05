package com.dhlab.xephyr.bukkit.builder.kit;

import com.dhlab.xephyr.bukkit.kit.Kit;

/**
 * A kit "transformer" class, allows kits to be modified externally in one class, rather than just a builder.
 * @author maladr0it
 */
public interface KitTransformer {
    void transform(Kit kit);
}
