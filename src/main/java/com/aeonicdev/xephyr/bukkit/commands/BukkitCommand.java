package com.aeonicdev.xephyr.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * The plugin command required to inject commands into the CommandMap.
 *
 * @author sc4re
 */
public class BukkitCommand extends Command {

    /**
     * The plugin for this command.
     */
    protected final Plugin basePlugin;

    /**
     * Creates a new instance of {@code BukkitCommand}.
     *
     * @param name The command name.
     * @param base The plugin this command is for.
     */
    protected BukkitCommand(String name, Plugin base) {
        super(name);
        this.basePlugin = base;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        boolean success = false;
        if (!basePlugin.isEnabled())
            return false;

        try {
            success = basePlugin.onCommand(sender, this, label, args);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CommandException("Unhandled exception occured in " + basePlugin.getDescription().getFullName(), e);
        }

        if (!success && usageMessage.length() > 0) {
            for (String line : usageMessage.replace("<command>", label).split("\n")) {
                sender.sendMessage(line);
            }
        }
        return success;
    }

}
