package com.dhlab.xephyr.bukkit.commands.executors;

import com.dhlab.xephyr.bukkit.commands.CommandArgs;
import com.dhlab.xephyr.bukkit.commands.CommandExecutionException;
import com.dhlab.xephyr.bukkit.commands.XCommandExecutor;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper;

/**
 * Executes commands embedded in {@link com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper} objects.
 *
 * @author maladr0it
 * @see com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper
 */
public class WrapperBasedCommandExecutor implements XCommandExecutor {

    /**
     * The label for this command.
     */
    protected final String label;

    /**
     * The command wrapper.
     */
    protected final CommandWrapper wrapper;

    /**
     * Creates a new {@code WrapperBasedCommandExecutor} with the specified label and wrapper.
     *
     * @param label The command label.
     * @param wrapper The command wrapper.
     */
    public WrapperBasedCommandExecutor(String label, CommandWrapper wrapper) {
        this.label = label;
        this.wrapper = wrapper;
    }

    /**
     * Gets the command label.
     *
     * @return The command label.
     */
    @Override
    public String label() {
        return label;
    }

    /**
     * Handles the command specified by the wrapper.
     *
     * @param args The arguments associated with the command.
     */
    @Override
    public void handleCommand(CommandArgs args) {
        try {
            if (!args.getSender().hasPermission(wrapper.permission())) {
                throw new CommandExecutionException(wrapper.getPermissionFailureMessage());
            }
            if (args.isPlayer() && wrapper.requiresConsole()) {
                throw new CommandExecutionException(wrapper.getRequirementFailureMessage());
            }
            if (!args.isPlayer() && wrapper.requiresPlayer()) {
                throw new CommandExecutionException(wrapper.getRequirementFailureMessage());
            }
            wrapper.handleCommand(args);
        } catch (CommandExecutionException e) {
            args.getPlayer().sendMessage(e.getMessage());
        }
    }

}
