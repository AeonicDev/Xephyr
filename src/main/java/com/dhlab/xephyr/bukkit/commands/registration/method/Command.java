package com.dhlab.xephyr.bukkit.commands.registration.method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Base @Command interface, allows setting methods as command handlers.
 * Provides access to setting aliases, command names, permissions, messages
 * for when players do not have correct permissions, and descriptions.
 *
 * @author maladr0it
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * The name of the command
     * @return
     */
    public String name();

    /**
     * The permissions required to execute the command.
     * @return
     */
    public String permission() default "";

    /**
     * The message to send when the player does not have the correct permissions.
     * @return
     */
    public String failPerms() default "You do not have the required permissions for this command.";

    /**
     * Command aliases, so that you can do things like type /help and /? and have them
     * use the same command.
     * @return
     */
    public String[] aliases() default {};

    /**
     * A command description, not required for every command.
     * @return
     */
    public String description() default "";

    /**
     * This appears in /help [commandname]
     * @return
     */
    public String usage() default "";

    /**
     * Returns whether the command requires a player to be using it.
     * @return
     */
    public boolean requiresPlayer() default false;

    /**
     * Returns whether the command needs to be run from the console or not.
     * @return
     */
    public boolean requiresConsole() default false;

    /**
     * The message to show when the command fails because of requiresPlayer or requiresConsole
     * @return
     */
    public String failRequirements() default "You do not meet the requirements to execute this command.";
}
