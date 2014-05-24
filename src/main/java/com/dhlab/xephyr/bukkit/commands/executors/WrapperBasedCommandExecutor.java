package com.dhlab.xephyr.bukkit.commands.executors;

import com.dhlab.xephyr.bukkit.commands.CommandArgs;
import com.dhlab.xephyr.bukkit.commands.CommandExecutionException;
import com.dhlab.xephyr.bukkit.commands.XCommandExecutor;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.CommandWrapper;

/**
 * Executes commands embedded in CommandWrappers.
 * @author maladr0it
 */
public class WrapperBasedCommandExecutor implements XCommandExecutor {

    protected final String label;
    protected final CommandWrapper wrapper;

    public WrapperBasedCommandExecutor(String label, CommandWrapper wrapper) {
        this.label = label;
        this.wrapper = wrapper;
    }

    @Override
    public String label() {
        return label;
    }

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
