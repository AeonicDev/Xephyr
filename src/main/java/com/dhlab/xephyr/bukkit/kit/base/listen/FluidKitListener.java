package com.dhlab.xephyr.bukkit.kit.base.listen;

import com.dhlab.xephyr.bukkit.kit.base.Kit;
import com.dhlab.xephyr.bukkit.kit.plugin.KitPlugin;
import com.dhlab.xephyr.bukkit.kit.plugin.KitPluginManager;
import net.minecraft.util.com.google.common.reflect.TypeToken;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A "Fluid" kit listener that allows handling of events passed by a SolidKitListener, which is the one that
 * actually passes the events. The SolidKitListener is actually
 * @author maladr0it
 */
public class FluidKitListener implements ConfigurationSerializable {

    // he does it for FREE
    static {
        ConfigurationSerialization.registerClass(FluidKitListener.class);
    }

    /**
     * The name of the kit listener
     */
    protected final String name;
    /**
     * The base plugin that this listener was created from.
     */
    protected final KitPlugin basePlugin;

    public FluidKitListener(String name, KitPlugin basePlugin) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        Validate.notNull(basePlugin);
        this.basePlugin = basePlugin;
        this.name = name;
    }

    /**
     * The base plugin this kitlistener is for.
     * @return The base plugin.
     */
    public KitPlugin getBasePlugin() {
        return basePlugin;
    }

    /**
     * The name of the listener.
     * @return the name of the listener
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an array of "Solid Kit Listeners" based upon a given kit.
     * This takes into account all @SolidHandler method interfaces in this specific class.
     * @param kit The kit to generate solid kit listeners for.
     * @return
     */
    public SolidKitListener<?>[] getSolidListeners(Kit kit) {
        Validate.notNull(kit);
        Validate.isTrue(basePlugin.isEnabled());
        List<SolidKitListener<?>> listeners = new LinkedList<>();
        for (Method m : this.getClass().getDeclaredMethods()) {
            SolidHandler handler = m.getAnnotation(SolidHandler.class);
            if (handler == null)
                continue;
            // ok, so it has the annotation, now we need to check the param types.
            if (m.getParameterTypes().length != 2)
                continue; // lol fail
            if (!m.getParameterTypes()[0].isAssignableFrom(Kit.class))
                continue; // lol fail again
            if (!m.getParameterTypes()[1].isAssignableFrom(Event.class))
                continue; // jesus christ mary they're minerals
            if (Modifier.isStatic(m.getModifiers()))
                continue; // wtf is this person doing
            if (Modifier.isPrivate(m.getModifiers()) || Modifier.isProtected(m.getModifiers()))
                m.setAccessible(true);
            // okay, after all those if statements, we have determined that, in fact, we are able to use this
            SolidListenerData data = new SolidListenerData(this, handler, m);
            // time for some reflection magic
            TypeToken<SolidKitListener<? extends Event>> tok = new TypeToken<SolidKitListener<? extends Event>>(){};
            try {
                listeners.add(tok.constructor(SolidListenerData.class.getConstructor(Kit.class,
                        SolidListenerData.class)).invoke(null, kit, data));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return listeners.toArray(new SolidKitListener[listeners.size()]);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("plugin", this.getBasePlugin().getName());
        result.put("name", this.getName());
        return result;
    }

    /**
     * Okay so this technically doesn't deserialize anything.
     * It just grabs the already-instantiated kit listener. lol.
     * @param serialized
     * @return
     */
    public static FluidKitListener deserialize(Map<String, Object> serialized) {
        if (!serialized.containsKey("name") || !serialized.containsKey("plugin"))
            throw new UnsupportedOperationException("Invalid serialized data!");
        String plugin = (String) serialized.get("plugin");
        String name = (String) serialized.get("name");
        KitPlugin pl = KitPluginManager.get().getPlugin(plugin);
        if (pl == null)
            throw new UnsupportedOperationException("Invalid plugin!");
        FluidKitListener fl = KitPluginManager.get().findFluidListenerByPlugin(pl, name);
        if (fl == null)
            throw new UnsupportedOperationException("Invalid fluid listener name!");
        return fl;
    }
}
