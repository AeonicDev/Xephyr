package com.dhlab.xephyr.bukkit.commands.executors;

import com.dhlab.xephyr.bukkit.commands.CommandArgs;
import com.dhlab.xephyr.bukkit.commands.CommandExecutionException;
import com.dhlab.xephyr.bukkit.commands.XCommandExecutor;
import com.dhlab.xephyr.bukkit.commands.registration.method.Command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A command executor that can be used with specific methods that use the @Command interface.
 * @author maladr0it
 */
public class MethodBasedCommandExecutor implements XCommandExecutor {

    protected final Object obj;
    protected final Method m;
    protected final Command commandInterface;
    protected final String label;

    public MethodBasedCommandExecutor(String label, Object obj, Method m) {
        if (label == null)
            throw new NullPointerException("label");
        if (obj == null)
            throw new NullPointerException("obj");
        if (m == null)
            throw new NullPointerException("m");
        this.label = label;
        this.obj = obj;
        if (m.getParameterTypes().length != 1 || !m.getParameterTypes()[0].equals(CommandArgs.class))
            throw new IllegalArgumentException("m");
        this.m = m;
        commandInterface = m.getAnnotation(Command.class);
        if (commandInterface == null)
            throw new NullPointerException("m does not have Command annotation!");
        if (commandInterface.requiresConsole() && commandInterface.requiresPlayer())
            throw new IllegalArgumentException("Command annotation cannot require both player and console.");
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public void handleCommand(CommandArgs args) {
        try {
            if (!args.getSender().hasPermission(commandInterface.permission())) {
                throw new CommandExecutionException(commandInterface.failPerms());
            }
            if (args.isPlayer() && commandInterface.requiresConsole()) {
                throw new CommandExecutionException(commandInterface.failRequirements());
            }
            if (!args.isPlayer() && commandInterface.requiresPlayer()) {
                throw new CommandExecutionException(commandInterface.failRequirements());
            }
            m.invoke(obj, args);
        } catch (IllegalAccessException e) {
            args.getPlayer().sendMessage(e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            args.getPlayer().sendMessage(e.getTargetException().getMessage());
            e.printStackTrace();
        } catch (CommandExecutionException e) {
            args.getPlayer().sendMessage(e.getMessage());
            e.printStackTrace();
        }
    }
}
