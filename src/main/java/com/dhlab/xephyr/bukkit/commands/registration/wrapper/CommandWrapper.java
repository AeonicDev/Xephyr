package com.dhlab.xephyr.bukkit.commands.registration.wrapper;

import com.dhlab.xephyr.bukkit.commands.XCommandExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic CommandWrapper for dynamic (non-annotated) commands.
 * @author maladr0it
 */
public abstract class CommandWrapper implements XCommandExecutor {

    protected final String label;
    protected final List<String> aliases = new ArrayList<String>();
    protected String permission = "";
    protected String permissionFailureMessage = "You do not have the required permissions for this command.";
    protected String requirementFailureMessage = "You do not meet the requirements to execute this command.";

    protected boolean requiresConsole = false;
    protected boolean requiresPlayer = false;

    public CommandWrapper(String label) {
        this(label, new String[] {});
    }

    public CommandWrapper(String label, String[] aliases) {
        this.label = label;
        for (String s : aliases) {
            this.aliases.add(s);
        }
    }

    public String label() {
        return label;
    }

    public String[] aliases() {
        return aliases.toArray(new String[aliases.size()]);
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

    public String permission() {
        return this.permission;
    }

    public void setPermission(String perm) {
        this.permission = perm;
    }

    public boolean requiresPlayer() {
        return requiresPlayer;
    }

    public boolean requiresConsole() {
        return requiresConsole;
    }

    public void setRequiresPlayer(boolean req) {
        if (req)
            setRequiresConsole(false);
        this.requiresPlayer = req;
    }

    public void setRequiresConsole(boolean req) {
        if (req)
            setRequiresPlayer(false);
        this.requiresConsole = req;
    }

    public String getPermissionFailureMessage() {
        return permissionFailureMessage;
    }

    public String getRequirementFailureMessage() {
        return requirementFailureMessage;
    }

    public void setPermissionFailureMessage(String msg) {
        if (msg == null)
            throw new NullPointerException("Permission failure message cannot be null!");
        this.permissionFailureMessage = msg;
    }

    public void setRequirementFailureMessage(String msg) {
        if (msg == null)
            throw new NullPointerException("Permissions failure message cannot be null!");
        this.requirementFailureMessage = msg;
    }
}
