package com.dhlab.xephyr.bukkit.commands.executors;

import com.dhlab.xephyr.bukkit.commands.CommandArgs;
import com.dhlab.xephyr.bukkit.commands.CommandExecutionException;
import com.dhlab.xephyr.bukkit.commands.XCommandExecutor;
import com.dhlab.xephyr.bukkit.commands.registration.method.Command;
import net.minecraft.util.org.apache.commons.lang3.Validate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A command executor that can be used with specific methods that use the @Command interface.
 *
 * @author maladr0it
 * @see com.dhlab.xephyr.bukkit.commands.registration.method.Command
 */
public class MethodBasedCommandExecutor implements XCommandExecutor {

    /**
     * The object containing the method we execute.
     */
    protected final Object obj;

    /**
     * The method we're executing.
     */
    protected final Method m;

    /**
     * The actual command object containing requirements and information.
     */
    protected final Command commandInterface;

    /**
     * The command label.
     */
    protected final String label;

    /**
     * Creates a new {@code MethodBasedCommandExecutor} with the specified arguments.
     *
     * @param label The command label.
     * @param obj The object containing the method to invoke.
     * @param m The method to invoke.
     */
    public MethodBasedCommandExecutor(String label, Object obj, Method m) {
        Validate.notNull(label);
        Validate.notNull(obj);
        Validate.notNull(m);
        this.label = label;
        this.obj = obj;
        Validate.isTrue(!(m.getParameterTypes().length != 1 || !m.getParameterTypes()[0].equals(CommandArgs.class)), "Method must take proper arguments!");
        this.m = m;
        commandInterface = m.getAnnotation(Command.class);
        Validate.notNull(commandInterface, "Method was not annotated as a Command!");
        Validate.isTrue(!(commandInterface.requiresConsole() && commandInterface.requiresPlayer()), "Command cannot require both player and console!");
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
