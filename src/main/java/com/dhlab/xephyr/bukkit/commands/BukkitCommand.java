package com.dhlab.xephyr.bukkit.commands;

import net.minecraft.server.v1_7_R3.CommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * The plugin command required to inject commands into the CommandMap.
 *
 * @author maladr0it
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

    /**
     * Executes this command.
     *
     * @param sender The sender of the command being handled.
     * @param label The label of the command being handled.
     * @param args The arguments of the command being handled.
     * @return The success of the command execution.
     */
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
