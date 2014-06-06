package com.dhlab.xephyr.generic.plugin.loading.impl.context;

import com.dhlab.xephyr.generic.plugin.loading.IPluginLoadContext;
import net.minecraft.util.org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.FileFilter;

/**
 * A context that loads all of the jar files from a directory.
 * @author maladr0it
 */
public class PluginDirectoryLoadContext implements IPluginLoadContext {
    /**
     * The directory path to use.
     */
    protected final String path;
    /**
     * The file object created by the path.
     */
    protected final File file;

    /**
     * This constructor takes a String as a parameter, where the String is the path to read all of the jars from.
     * @param path
     */
    public PluginDirectoryLoadContext(String path) {
        Validate.notNull(path); // make sure the path isn't illegal
        File f = new File(path); // create a new file
        Validate.isTrue(f.isDirectory() && f.exists() && f.canRead()); // test for illegalness
        this.file = f; // okay we're good
        this.path = f.getAbsolutePath(); // make sure we get the absolute path, too, just in case.
    }

    public PluginDirectoryLoadContext(File path) {
        Validate.notNull(path);
        Validate.isTrue(path.isDirectory() && path.exists() && path.canRead());
        this.path = path.getAbsolutePath();
        this.file = path;
    }

    /**
     * Returns the directory from which we are loading the jars.
     * @return
     */
    public File getDirectory() {
        return file;
    }

    /**
     * Gets all of the jars in the directory.
     * @return An array of file objects that are jars.
     */
    public File[] getJars() {
        File[] files = getDirectory().listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory() && pathname.canRead() && FilenameUtils.isExtension(pathname.getAbsolutePath(), "jar");
            }
        });
        return files;
    }
}
