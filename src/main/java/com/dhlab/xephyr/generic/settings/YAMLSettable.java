package com.dhlab.xephyr.generic.settings;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Defines the API for a settings
 * @author maladr0it
 */
public interface YAMLSettable {
    public YamlConfiguration getSettings();
    public File getFile();
    public void loadSettings(File f);
    public void saveSettings(File f);
}
