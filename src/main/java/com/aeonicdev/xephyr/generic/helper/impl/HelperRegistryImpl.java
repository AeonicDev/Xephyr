package com.aeonicdev.xephyr.generic.helper.impl;

import dev.aeonic.seraphim.api.HackedClient;
import dev.aeonic.seraphim.api.helper.Helper;
import dev.aeonic.seraphim.api.helper.HelperRegistry;
import dev.aeonic.seraphim.api.helper.impl.java.RandomHelper;
import dev.aeonic.seraphim.api.helper.impl.java.RegexHelper;
import dev.aeonic.seraphim.api.helper.impl.misc.*;
import dev.aeonic.seraphim.api.helper.impl.network.NetworkHelper;
import dev.aeonic.seraphim.api.helper.impl.player.InventoryHelper;
import dev.aeonic.seraphim.api.helper.impl.player.PlayerHelper;
import dev.aeonic.seraphim.api.helper.impl.player.PositionHelper;
import dev.aeonic.seraphim.api.helper.impl.player.RotationHelper;
import dev.aeonic.seraphim.api.helper.impl.reflection.minecraft.SessionHelper;
import dev.aeonic.seraphim.api.helper.impl.reflection.minecraft.TimerHelper;
import dev.aeonic.seraphim.api.helper.impl.reflection.packet.handshake.ProtocolVersionHelper;
import dev.aeonic.seraphim.api.helper.impl.render.ColorHelper;
import dev.aeonic.seraphim.api.helper.impl.render.Render2DHelper;
import dev.aeonic.seraphim.api.helper.impl.render.Render3DHelper;
import dev.aeonic.seraphim.api.helper.impl.world.WorldHelper;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of a Helper Registry.
 * @author sc4re
 */
public final class HelperRegistryImpl implements HelperRegistry {

    protected final Map<Class<? extends Helper>, Helper> helpers = new HashMap<>();
    protected final HackedClient client;

    public HelperRegistryImpl(HackedClient client) {
        Validate.notNull(client);
        this.client = client;
    }

    @Override
    public <T extends Helper> void add(T helper) {
        Validate.notNull(helper);
        if (helpers.get(helper.getClass().asSubclass(Helper.class)) != null)
            throw new RuntimeException("Helper already added!");
        helpers.put(helper.getClass().asSubclass(Helper.class), helper);
    }

    @Override
    public <T extends Helper> void remove(Class<T> klass) {
        Validate.notNull(klass);
        helpers.remove(klass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Helper> T get(Class<T> klass) {
        return (T)helpers.get(klass);
    }

    @Override
    public void initialize() {
        add(new BoundingBoxHelper());
        add(new ChatHelper());
        add(new ColorHelper());
        add(new FontHelper());
        add(new InventoryHelper());
        add(new MinecraftHelper());
        add(new NetworkHelper());
        add(new PlayerHelper());
        add(new PositionHelper());
        add(new ProtocolVersionHelper());
        add(new RandomHelper());
        add(new RegexHelper());
        add(new Render2DHelper());
        add(new Render3DHelper());
        add(new RotationHelper());
        add(new SessionHelper());
        add(new TimerHelper());
        add(new WorldHelper());
    }
}
