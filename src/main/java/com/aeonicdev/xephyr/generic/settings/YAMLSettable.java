package com.aeonicdev.xephyr.generic.settings;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Defines the API for a settings.
 *
 * @author maladr0it
 */
public interface YAMLSettable {

    /**
     * Gets the settings for this object.
     *
     * @return The settings.
     */
    public YamlConfiguration getSettings();

    /**
     * Gets the settings {@link java.io.File}.
     *
     * @return The file.
     */
    public File getFile();

    /**
     * Loads settings from the specified {@link java.io.File}.
     *
     * @param f The file.
     */
    public void loadSettings(File f);

    /**
     * Saves settings to the specified file {@link java.io.File}.
     *
     * @param f The file.
     */
    public void saveSettings(File f);

    /**
     * Checks the default values.
     */
    public void checkDefaults();

}
