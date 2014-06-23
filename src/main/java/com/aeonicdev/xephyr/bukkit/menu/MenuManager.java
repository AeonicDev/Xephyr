package com.aeonicdev.xephyr.bukkit.menu;

import com.aeonicdev.xephyr.bukkit.module.Module;
import com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper;
import com.aeonicdev.xephyr.generic.Enableable;
import com.aeonicdev.xephyr.generic.management.ClassBasedManager;
import com.aeonicdev.xephyr.generic.management.ManagedNotFoundException;
import org.apache.commons.lang.Validate;

import java.util.*;

/**
 * A manager for instances of {@link com.aeonicdev.xephyr.bukkit.menu.InventoryMenu}.
 *
 * @author hauno
 * @see com.aeonicdev.xephyr.bukkit.menu.InventoryMenu
 */
public class MenuManager implements ClassBasedManager<InventoryMenu>, Enableable {

    /**
     * The underlying storage mechanism.
     */
    protected final Map<Class<? extends InventoryMenu>, InventoryMenu> menus = new HashMap<>();

    /**
     * The PluginBootstrapper for this manager.
     */
    protected final PluginBootstrapper bootstrap;

    /**
     * Creates a new instance of a MenuManager with a {@link com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper}
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
        Validate.notNull(obj);
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
        InventoryMenu found = this.menus.get(identifier);
        if (found == null) throw new ManagedNotFoundException(identifier);
        return found;
    }

    @Override
    public InventoryMenu[] findAllByOne(Class identifier) throws ManagedNotFoundException {
        List<InventoryMenu> found = new ArrayList<>();
        for (InventoryMenu menu : this.menus.values()) {
            if (menu.getClass().isInstance(identifier))
                found.add(menu);
        }

        if (found.size() == 0) throw new ManagedNotFoundException(identifier);

        return found.toArray(new InventoryMenu[found.size()]);
    }

    @Override
    public InventoryMenu[] findAllByMany(Class... identifiers) throws ManagedNotFoundException {
        Validate.notNull(identifiers);
        List<InventoryMenu> menus = new ArrayList<InventoryMenu>();

        for (Class<? extends Module> id : identifiers) {
            try {
                menus.addAll(Arrays.asList(findAllByOne(id)));
            } catch (ManagedNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (menus.size() == 0) throw new ManagedNotFoundException(identifiers);
        return menus.toArray(new InventoryMenu[menus.size()]);
    }

    @Override
    public void onEnable() {
        for (InventoryMenu menu : this.getContents()) {
            this.bootstrap.getPlugin().getServer().getPluginManager().registerEvents(menu, this.bootstrap.getPlugin());
        }
    }

    @Override
    public void onDisable() {

    }

}
