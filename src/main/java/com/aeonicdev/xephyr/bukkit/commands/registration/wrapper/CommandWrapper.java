package com.aeonicdev.xephyr.bukkit.commands.registration.wrapper;

import com.aeonicdev.xephyr.bukkit.commands.ICommandExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic CommandWrapper for dynamic (non-annotated) commands.
 *
 * @author sc4re
 */
public abstract class CommandWrapper implements ICommandExecutor {

    /**
     * The command label.
     */
    protected final String label;

    /**
     * The aliases for this command.
     */
    protected final List<String> aliases = new ArrayList<String>();

    /**
     * The permission for this command.
     */
    protected String permission = "";

    /**
     * The message displayed upon not having permission for this command.
     */
    protected String permissionFailureMessage = "You do not have the required permissions for this command.";

    /**
     * The messaged displayed upon failing requirement checks for this command.
     */
    protected String requirementFailureMessage = "You do not meet the requirements to execute this command.";

    /**
     * If this command requires a non-player sender.
     */
    protected boolean requiresConsole = false;

    /**
     * If this command requires a player sender.
     */
    protected boolean requiresPlayer = false;

    /**
     * Creates a new {@code CommandWrapper} with the specified label.
     *
     * @param label The command label.
     */
    public CommandWrapper(String label) {
        this(label, new String[] {});
    }

    /**
     * Creates a new {@code CommandWrapper} with the specified label and aliases.
     *
     * @param label The command label.
     * @param aliases Aliases of this command.
     */
    public CommandWrapper(String label, String[] aliases) {
        this.label = label;
        for (String s : aliases) {
            this.aliases.add(s);
        }
    }

    /**
     * Gets the command label.
     *
     * @return The command label.
     */
    public String label() {
        return label;
    }

    /**
     * Gets the command aliases.
     *
     * @return The command aliases.
     */
    public String[] aliases() {
        return aliases.toArray(new String[aliases.size()]);
    }

    /**
     * Adds an alias to this command.
     *
     * @param alias The alias to add.
     */
    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    /**
     * Removes an alias from this command.
     *
     * @param alias The alias to remove.
     */
    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

    /**
     * Gets the permission node for this command.
     *
     * @return The permission node.
     */
    public String permission() {
        return this.permission;
    }

    /**
     * Sets the permission node for this command.
     *
     * @param perm The permission node.
     */
    public void setPermission(String perm) {
        this.permission = perm;
    }

    /**
     * Gets if this command requires a player sender.
     *
     * @return If the command requires a player sender.
     */
    public boolean requiresPlayer() {
        return requiresPlayer;
    }

    /**
     * Gets if this command requires a non-player sender.
     *
     * @return If the command requires a non-player sender.
     */
    public boolean requiresConsole() {
        return requiresConsole;
    }

    /**
     * Sets if this command requires a player sender.
     *
     * @param req If this command should require a player sender.
     */
    public void setRequiresPlayer(boolean req) {
        if (req)
            setRequiresConsole(false);
        this.requiresPlayer = req;
    }

    /**
     * Sets if this command requires a non-player sender.
     *
     * @param req If this command should require a non-player sender.
     */
    public void setRequiresConsole(boolean req) {
        if (req)
            setRequiresPlayer(false);
        this.requiresConsole = req;
    }

    /**
     * Gets the message sent upon not having the permission for this command.
     *
     * @return The message.
     */
    public String getPermissionFailureMessage() {
        return permissionFailureMessage;
    }

    /**
     * Gets the message sent upon not meeting the requirement checks for this command.
     *
     * @return The message.
     */
    public String getRequirementFailureMessage() {
        return requirementFailureMessage;
    }

    /**
     * Sets the message sent upon not having the permission for this command.
     *
     * @param msg The message.
     */
    public void setPermissionFailureMessage(String msg) {
        if (msg == null)
            throw new NullPointerException("Permission failure message cannot be null!");
        this.permissionFailureMessage = msg;
    }

    /**
     * Sets the message sent upon not meeting the requirement checks for this command.
     *
     * @param msg The message.
     */
    public void setRequirementFailureMessage(String msg) {
        if (msg == null)
            throw new NullPointerException("Permissions failure message cannot be null!");
        this.requirementFailureMessage = msg;
    }

}
