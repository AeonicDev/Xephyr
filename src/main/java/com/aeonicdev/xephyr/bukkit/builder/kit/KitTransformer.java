package com.aeonicdev.xephyr.bukkit.builder.kit;

import com.aeonicdev.xephyr.bukkit.kit.base.Kit;

/**
 * A kit "transformer" class, allows kits to be modified externally in one class, rather than just a builder.
 * @author maladr0it
 */
public interface KitTransformer {
    void transform(Kit kit);
}
