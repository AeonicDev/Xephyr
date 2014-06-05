package com.dhlab.xephyr.bukkit.kit.eventing;

import com.dhlab.xephyr.bukkit.kit.Kit;
import org.apache.commons.lang.Validate;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * An interface that can be used to automatically handle specific events, allows access to change the listener's
 * kit at runtime, and checks to determine whether the event can even be handled at whatever point in time.
 * @author maladr0it
 */
public abstract class KitListener<T extends Event> implements Listener {

    /**
     * The event class handled by this listener.
     */
    protected final Class<T> handledEvent;
    /**
     * The kit handled by this listener.
     */
    protected Kit handledKit;

    /**
     * Initializes a KitListener with the class of the event subtype.
     * @param handled The class handled by this listener
     */
    public KitListener(Class<T> handled) {
        Validate.notNull(handled);
        this.handledEvent = handled;
    }

    /**
     * Returns the currently handled event class.
     * @return The handled event class.
     */
    public Class<T> getHandledEvent() {
        return handledEvent;
    }

    /**
     * The kit handled by this listener.
     * Can be null, if null events won't be handled.
     * @return The currently handled kit.
     */
    public Kit getHandledKit() {
        return handledKit;
    }

    /**
     * Sets the handled kit, can be null, if null, events won't be handled.
     * @param kit The kit ot set the value to.
     */
    public void setHandledKit(Kit kit) {
        handledKit = kit;
    }

    /**
     * Handles the event, calling handleEvent with the current kit and the event sent by Bukkit.
     * @param event The event that will be sent by Bukkit.
     */
    @EventHandler
    public void handleEvent(T event) {
        if (getHandledKit() == null)
            return;
        if (event == null)
            return;
        handleEvent(getHandledKit(), event);
    }

    /**
     * Called to handle the event sent by the EventHandler method in this class.
     * @param kit The kit we are currently storing.
     * @param event The event that is sent by bukkit.s
     */
    protected abstract void handleEvent(Kit kit, T event);
}
