package com.dhlab.xephyr.bukkit.commands;

import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The arguments that are passed to every onCommand method.
 *
 * @author maladr0it
 */
public class CommandArgs {

    /**
     * The sender of the command these arguments are for.
     */
    protected final CommandSender sender;

    /**
     * The label of the command these arguments are for.
     */
    protected final String label;

    /**
     * The internal argument pool.
     */
    protected final ArgumentPool arguments;

    /**
     * Creates a new {@code CommandArgs} instance with the specified internal data.
     *
     * @param sender The sender of the command these arguments are for.
     * @param label The label of the command these arguments are for.
     * @param args The arguments from the command that will be internalized.
     */
    public CommandArgs(CommandSender sender, String label, String[] args) {
        Validate.notNull(sender);
        Validate.notNull(label);
        Validate.notNull(args);
        this.sender = sender;
        this.label = label;
        this.arguments = new ArgumentPool(args);
    }

    /**
     * Gets whether the sender is an instance of a player.
     *
     * @return If the command sender is a player.
     */
    public boolean isPlayer() {
        return (sender instanceof Player);
    }

    /**
     * Gets the CommandSender instance that this object was initialized with.
     *
     * @return The command sender.
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * Gets the sender casted to a player, but throws a RuntimeException when the sender isn't a player.
     *
     * @return The player-casted command sender.
     */
    public Player getPlayer() {
        if (!isPlayer())
            throw new RuntimeException("Sender is not a player!");
        return ((Player)getSender());
    }

    /**
     * Gets the label (base command) of these arguments.
     *
     * @return The command label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the ArgumentPool used by this CommandArgs instance.
     *
     * @return The argument pool.
     */
    public ArgumentPool getArgs() {
        return arguments;
    }

    /**
     * Sends a message to the command sender.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        sendMessage("", message);
    }

    /**
     * Sends a message to the sender with the specified prefix.
     *
     * @param prefix The prefix of the message.
     * @param message The message to be sent.
     */
    public void sendMessage(String prefix, String message) {
        getSender().sendMessage(prefix + message);
    }

}
