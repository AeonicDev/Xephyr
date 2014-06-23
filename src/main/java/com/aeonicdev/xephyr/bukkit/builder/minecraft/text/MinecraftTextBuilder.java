package com.aeonicdev.xephyr.bukkit.builder.minecraft.text;

import com.aeonicdev.xephyr.generic.builder.Builder;
import org.bukkit.ChatColor;

/**
 * Supports easy and fast Minecraft "Colored Text" building.
 * @author sc4re
 */
public class MinecraftTextBuilder implements Builder<String> {

    /**
     * The base stringbuilder used by this text builder.
     */
    private StringBuilder base = new StringBuilder();

    /**
     * Appends a raw string to the internally built string.
     * @param s The string to append.
     * @return The builder instance.
     */
    public MinecraftTextBuilder string(String s) {
        this.base.append(s);
        return this;
    }

    /**
     * Appends the black color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder black()  {
        return string(ChatColor.BLACK.toString());
    }

    /**
     * Appends the dark blue color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder darkBlue() {
        return string(ChatColor.DARK_BLUE.toString());
    }

    /**
     * Appends the dark green color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder darkGreen() {
        return string(ChatColor.DARK_GREEN.toString());
    }

    /**
     * Appends the dark aqua color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder darkAqua() {
        return string(ChatColor.DARK_AQUA.toString());
    }

    /**
     * Appends the dark red color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder darkRed() {
        return string(ChatColor.DARK_RED.toString());
    }

    /**
     * Appends the dark purple color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder darkPurple() {
        return string(ChatColor.DARK_PURPLE.toString());
    }

    /**
     * Appends the gold color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder gold() {
        return string(ChatColor.GOLD.toString());
    }

    /**
     * Appends the gray color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder gray() {
        return string(ChatColor.GRAY.toString());
    }

    /**
     * Appends the dark gray color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder darkGray() {
        return string(ChatColor.DARK_GRAY.toString());
    }

    /**
     * Appends the blue color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder blue() {
        return string(ChatColor.BLUE.toString());
    }

    /**
     * Appends the green color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder green() {
        return string(ChatColor.GREEN.toString());
    }

    /**
     * Appends the aqua color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder aqua() {
        return string(ChatColor.AQUA.toString());
    }

    /**
     * Appends the red color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder red(){
        return string(ChatColor.RED.toString());
    }

    /**
     * Appends the yellow color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder yellow() {
        return string(ChatColor.YELLOW.toString());
    }

    /**
     * Appends the white color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder white() {
        return string(ChatColor.WHITE.toString());
    }

    /**
     * Appends the magic color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder magic() {
        return string(ChatColor.MAGIC.toString());
    }

    /**
     * Appends the bold color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder bold() {
        return string(ChatColor.BOLD.toString());
    }

    /**
     * Appends the strikethrough color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder strikethrough() {
        return string(ChatColor.STRIKETHROUGH.toString());
    }

    /**
     * Appends the underline color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder underline() {
        return string(ChatColor.UNDERLINE.toString());
    }

    /**
     * Appends the italic color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder italic() {
        return string(ChatColor.ITALIC.toString());
    }

    /**
     * Appends the reset color code to the internally built string.
     * @return The builder instance.
     */
    public MinecraftTextBuilder reset() {
        return string(ChatColor.RESET.toString());
    }

    @Override
    public String build() {
        return this.base.toString();
    }
}
