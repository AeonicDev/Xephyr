package com.dhlab.xephyr.bukkit.menu;

import com.dhlab.xephyr.bukkit.module.Module;
import com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper;
import com.dhlab.xephyr.generic.management.ManagedNotFoundException;
import com.dhlab.xephyr.generic.management.Manager;
import org.apache.commons.lang.Validate;

import java.util.*;

/**
 * A manager for instances of {@link com.dhlab.xephyr.bukkit.menu.InventoryMenu}.
 *
 * @author hauno
 * @see com.dhlab.xephyr.bukkit.menu.InventoryMenu
 */
public class MenuManager implements Manager<InventoryMenu, Class> {

    /**
     * The underlying storage mechanism.
     */
    protected final Map<Class<? extends InventoryMenu>, InventoryMenu> menus = new HashMap<>();

    /**
     * The PluginBootstrapper for this manager.
     */
    protected final PluginBootstrapper bootstrap;

    /**
     * Creates a new instance of a MenuManager with a {@link com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper}
     *
     * @param bootstrap The PluginBootstrapper the manager is for
     */
    public MenuManager(PluginBootstrapper bootstrap) {
        Validate.notNull(bootstrap); // THAT DIDN'T SIT TOO WELL WITH THE CAPTAIN
        this.bootstrap = bootstrap; // SO HE TIED A CANNON TO BOOTSTRAP'S BOOTSTRAPS
    }

    @Override
    public Class<InventoryMenu> getManagedType() {
        return InventoryMenu.class;
    }

    @Override
    public void add(InventoryMenu obj) {
        this.menus.put(obj.getClass(), obj);
    }

    @Override
    public void remove(InventoryMenu obj) {
        this.menus.remove(obj.getClass());
    }

    @Override
    public void removeByID(Class obj) {
        this.menus.remove(obj);
    }

    @Override
    public boolean has(Class obj) {
        return menus.get(obj) != null;
    }

    @Override
    public InventoryMenu[] getContents() {
        return this.menus.values().toArray(new InventoryMenu[this.menus.size()]);
    }

    @Override
    public InventoryMenu find(Class identifier) throws ManagedNotFoundException {
        Validate.notNull(identifier);
        InventoryMenu found = this.menus.get(identifier);
        if (found == null) throw new ManagedNotFoundException(identifier);
        return found;
    }

    @Override
    public InventoryMenu[] findAllByOne(Class identifier) throws ManagedNotFoundException {
        Validate.notNull(identifier);

        List<InventoryMenu> found = new ArrayList<>();
        for (InventoryMenu menu : this.menus.values()) {
            if (menu.getClass().isInstance(identifier))
                found.add(menu);
        }

        if (found.size() == 0)
            throw new ManagedNotFoundException(identifier);

        return found.toArray(new InventoryMenu[found.size()]);
    }

    @Override
    public InventoryMenu[] findAllByMany(Class... identifier) throws ManagedNotFoundException {
        Validate.notNull(identifier);
        List<InventoryMenu> menus = new ArrayList<InventoryMenu>();

        for (Class<? extends Module> id : identifier) {
            try {
                menus.addAll(Arrays.asList(findAllByOne(id)));
            } catch (ManagedNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (menus.size() == 0) throw new ManagedNotFoundException(identifier);
        return menus.toArray(new InventoryMenu[menus.size()]);
    }

}
