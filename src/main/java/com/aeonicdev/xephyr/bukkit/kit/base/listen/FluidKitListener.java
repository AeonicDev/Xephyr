package com.aeonicdev.xephyr.bukkit.kit.base.listen;

import com.aeonicdev.xephyr.bukkit.kit.base.Kit;
import net.minecraft.util.com.google.common.reflect.TypeToken;
import org.apache.commons.lang.Validate;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 * A "Fluid" kit listener that allows handling of events passed by a SolidKitListener, which is the one that
 * actually passes the events. The SolidKitListener is actually
 * @author maladr0it
 */
public class FluidKitListener implements Listener {
    /**
     * The name of the kit listener
     */
    protected final String name;

    public FluidKitListener(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        this.name = name;
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
}
