package com.dhlab.xephyr.bukkit.commands.registration.method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Base {@code Command} interface, allows setting methods as command handlers.
 * Provides access to setting aliases, command names, permissions, messages
 * for when players do not have correct permissions, and descriptions.
 *
 * @author maladr0it
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    /**
     * The name of the command.
     *
     * @return The command name.
     */
    public String name();

    /**
     * The permissions required to execute the command.
     *
     * @return The permission node.
     */
    public String permission() default "";

    /**
     * The message to send when the player does not have the correct permissions.
     *
     * @return The message.
     */
    public String failPerms() default "You do not have the required permissions for this command.";

    /**
     * Command aliases, so that you can do things like type /help and /? and have them
     * use the same command.
     *
     * @return The array of aliases.
     */
    public String[] aliases() default {};

    /**
     * A command description, not required for every command.
     *
     * @return The command description.
     */
    public String description() default "";

    /**
     * Usage for this command.
     * This appears in Bukkit's help command.
     *
     * @return The command usage.
     */
    public String usage() default "";

    /**
     * Returns whether the command requires a player to be using it.
     *
     * @return If the player is required.
     */
    public boolean requiresPlayer() default false;

    /**
     * Returns whether the command needs to be run from the console or not.
     *
     * @return If console is required.
     */
    public boolean requiresConsole() default false;

    /**
     * The message to show when the command fails because of requiresPlayer or requiresConsole.
     *
     * @return The message.
     */
    public String failRequirements() default "You do not meet the requirements to execute this command.";

}
