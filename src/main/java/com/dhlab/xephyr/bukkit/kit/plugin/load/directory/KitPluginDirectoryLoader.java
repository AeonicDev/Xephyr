package com.dhlab.xephyr.bukkit.kit.plugin.load.directory;

import com.dhlab.xephyr.bukkit.kit.plugin.KitPlugin;
import com.dhlab.xephyr.generic.plugin.IPluginable;
import com.dhlab.xephyr.generic.plugin.loading.IPluginLoader;
import com.dhlab.xephyr.generic.plugin.loading.impl.context.PluginDirectoryLoadContext;
import net.minecraft.util.com.google.common.reflect.ClassPath;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads all KitPlugin's in a specified directory.
 * @author maladr0it
 */
public class KitPluginDirectoryLoader implements IPluginLoader<KitPlugin, PluginDirectoryLoadContext> {
    @Override
    public Class<KitPlugin> getPluginType() {
        return KitPlugin.class;
    }

    @Override
    public Class<PluginDirectoryLoadContext> getContextType() {
        return PluginDirectoryLoadContext.class;
    }

    @Override
    public void loadPlugins(IPluginable<KitPlugin> pluginable, PluginDirectoryLoadContext context) throws Exception {
        File[] jars = context.getJars();
        List<URL> uris = new ArrayList<>();
        for (File f : jars) {
            uris.add(f.toURI().toURL());
        }
        URLClassLoader loader = new URLClassLoader(uris.toArray(new URL[uris.size()]), this.getClass().getClassLoader());
        ClassPath cp = ClassPath.from(loader);
        for (ClassPath.ClassInfo info : cp.getAllClasses()) {
            if (info.getClass().isAssignableFrom(KitPlugin.class)) {
                try {
                    KitPlugin plugin = info.getClass().asSubclass(KitPlugin.class).newInstance();
                    pluginable.addPlugin(plugin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
