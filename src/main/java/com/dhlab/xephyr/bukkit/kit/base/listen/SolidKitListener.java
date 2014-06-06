package com.dhlab.xephyr.bukkit.kit.base.listen;

import com.dhlab.xephyr.bukkit.kit.base.Kit;
import org.apache.commons.lang.Validate;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;

/**
 * Some hacker wizardry to get past the fact that we can't accept kits as arguments. Each kit, for every listener they have,
 * will have a BackwardsKitListener
 * @author maladr0it
 */
public class SolidKitListener<T extends Event> implements Listener {

    /**
     * The kit that has requested this kit listener be created.
     */
    protected final Kit realKit;
    /**
     * The listener data required to call the FluidKitListener @SolidHandler method.
     */
    protected final SolidListenerData data;

    public SolidKitListener(Kit kit, SolidListenerData data) {
        Validate.notNull(kit);
        Validate.notNull(data);
        this.realKit = kit;
        this.data = data;
    }

    /**
     * Returns the kit that this listener fights for in the name of Freedom
     * @return The kit that this listener represents in the court of law
     */
    public Kit getKit() {
        return realKit;
    }

    /**
     * Returns the SolidListenerData structure that this object uses.
     * @return The data.
     */
    public SolidListenerData getData() {
        return data;
    }

    // and now begins the really ghetto code

    /**
     * The "Monitor" event handler.
     * @param event The event to pass.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEventMonitor(T event) {
        if (!getData().getAnnotation().priority().equals(EventPriority.MONITOR))
            return;
        invoke(event);
    }

    /**
     * The "Lowest" event handler.
     * @param event The event to pass.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEventLowest(T event) {
        if (!getData().getAnnotation().priority().equals(EventPriority.LOWEST))
            return;
        invoke(event);
    }

    /**
     * The "Low" event handler
     * @param event The event to pass.
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onEventLow(T event) {
        if (!getData().getAnnotation().priority().equals(EventPriority.LOW))
            return;
        invoke(event);
    }

    /**
     * The "Normal" event handler
     * @param event The event to pass.
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEventNormal(T event) {
        if (!getData().getAnnotation().priority().equals(EventPriority.NORMAL))
            return;
        invoke(event);
    }

    /**
     * The "High" event handler
     * @param event The event to pass.
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onEventHigh(T event) {
        if (!getData().getAnnotation().priority().equals(EventPriority.HIGH))
            return;
        invoke(event);
    }

    /**
     * The "Highest" event handler
     * @param event The event to pass.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEventHighest(T event) {
        if (!getData().getAnnotation().priority().equals(EventPriority.HIGHEST))
            return;
        invoke(event);
    }

    private void invoke(T event) {
        try {
            getData().getMethod().invoke(getData().getObject(), getKit(), event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
