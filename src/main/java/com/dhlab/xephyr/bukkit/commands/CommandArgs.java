package com.dhlab.xephyr.bukkit.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The arguments that are passed to every onCommand method.
 * @author maladr0it
 */
public class CommandArgs {

    protected final CommandSender sender;
    protected final String label;
    protected final ArgumentPool arguments;

    public CommandArgs(CommandSender sender, String label, String[] args) {
        if (sender == null)
            throw new NullPointerException("sender");
        if (label == null)
            throw new NullPointerException("label");
        if (args == null)
            throw new NullPointerException("args");
        this.sender = sender;
        this.label = label;
        this.arguments = new ArgumentPool(args);
    }

    /**
     * Returns whether the sender is an instance of a player.
     * @return
     */
    public boolean isPlayer() {
        return (sender instanceof Player);
    }

    /**
     * Returns the CommandSender instance that this object was initialized with.
     * @return
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * Returns the sender cast to a player, but throws a RuntimeException when the sender isn't a player.
     * @return
     */
    public Player getPlayer() {
        if (!isPlayer())
            throw new RuntimeException("Sender is not a player!");
        return ((Player)getSender());
    }

    /**
     * Returns the label (base command) of these arguments.
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the ArgumentPool used by this CommandArgs instance.
     * @return
     */
    public ArgumentPool getArgs() {
        return arguments;
    }

    /**
     * Sends a message to the sender.
     * @param message
     */
    public void sendMessage(String message) {
        sendMessage("", message);
    }

    /**
     * Sends a message to the sender with the specified prefix.
     * @param prefix
     * @param message
     */
    public void sendMessage(String prefix, String message) {
        getSender().sendMessage(prefix + message);
    }
}
