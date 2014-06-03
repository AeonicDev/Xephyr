package com.dhlab.xephyr.bukkit.commands;

import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationContext;
import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrationException;
import com.dhlab.xephyr.bukkit.commands.registration.CommandRegistrator;
import com.dhlab.xephyr.bukkit.commands.registration.method.MethodBasedCommandRegistrator;
import com.dhlab.xephyr.bukkit.commands.registration.wrapper.WrapperBasedCommandRegistrator;
import net.minecraft.util.com.google.common.base.Joiner;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The basic CommandFrammework that can be used in your plugins.
 * Automatically registers your commands for this plugin with the
 * SimplePluginManager command framework within Bukkit.
 *
 * @author maladr0it
 */
public class CommandFramework {

    /**
     * The map of command executors.
     */
    protected final Map<String, XCommandExecutor> executorMap = new HashMap<String, XCommandExecutor>();

    /**
     * The map of command registrators.
     */
    protected final Map<Class, CommandRegistrator> commandRegistrators = new HashMap<Class, CommandRegistrator>();

    /**
     * The command mapping.
     */
    protected CommandMap commandMapping;

    /**
     * The plugin this framework is being used with.
     */
    protected final Plugin plugin;

    /**
     * The pattern for double quote arguments.
     * This matches things like:
     * /command "multiple spaces and such" nospaces
     * with args[0] being "multiple spaces and such" without quotes
     * and args[1] being nospaces.
     */
    protected static final Pattern doubleQuoteMatcher = Pattern.compile("(\\\"[^\\\"]+\\\"|[^\\s\\\"]+)");

    /**
     * Creates a new {@code CommandFramework} instance for the specified plugin.
     *
     * @param plugin The plugin using this command framework instance.
     */
    public CommandFramework(Plugin plugin) {
        this.plugin = plugin;
        if (!(Bukkit.getPluginManager() instanceof SimplePluginManager))
            return;
        SimplePluginManager manager = (SimplePluginManager) plugin.getServer().getPluginManager();
        // acquire the field of the commandmap that allows us to inject our own commands without modifying
        // the plugin.yml
        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMapping = (CommandMap) field.get(manager);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        addRegistrator(new MethodBasedCommandRegistrator());
        addRegistrator(new WrapperBasedCommandRegistrator());
    }

    /**
     * Bridges Bukkit's commands through to the framework and handles them.
     *
     * @param sender The command sender.
     * @param label The command label.
     * @param cmd The command.
     * @param args The command arguments.
     * @return The result of the command execution.
     */
    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (this.executorMap.get(label) != null) {
                XCommandExecutor executor = this.executorMap.get(label);
                executor.handleCommand(new CommandArgs(sender, label, args));
                return true;
            }
        }

        String joined = Joiner.on(" ").skipNulls().join(args);

        // alright, so we basically have to rejoin and then split the string again, except by double quotes and spaces to
        // provide access to what is mentioned in the comment above the declaration of the doubleQuoteMatcher.
        Matcher argMatcher = doubleQuoteMatcher.matcher(joined);
        List<String> realArgs = new ArrayList<String>();
        while (argMatcher.find()) {
            // add to the realArgs list, we will use that.toArray() to change args to our version of it.
            // also, remove quotes at beginning and end for ease of use in other plugins.
            String temp = argMatcher.group();
            if (temp.startsWith("\"") && temp.endsWith("\""))
                temp = temp.substring(1, temp.length() - 1); // remove the double quotes around the matched group.
            realArgs.add(temp);
        }
        args = realArgs.toArray(new String[realArgs.size()]);

        for (int i = args.length - 1; i > -1; i--) {
            // now we recursively backtrack, adding all of the following arguments with a .
            // to determine if we have a command executor with a sub-label.
            StringBuilder sb = new StringBuilder();
            sb.append(label);
            for (int x = 0; x <= i; x++) {
                String sub = args[x];
                if (sub == null || sub.length() == 0)
                    continue;
                sb.append("." + sub);
            }
            String templabel = sb.toString();
            if (this.executorMap.get(templabel) != null) {
                XCommandExecutor executor = executorMap.get(templabel);
                // now we have to create an array of arguments that removes all of the cruff.
                // for example, if the label is command.subcommand.sub2, we need to remove the sub-args
                // "subcommand" and "sub2" from the arguments.


                // get the current argument count
                int subCommand = args.length - i - (args.length == 1 ? 0 : 1);
                String[] finalArgs = Arrays.copyOfRange(args, subCommand, args.length);
                try {
                    // handle the command with the found executor
                    executor.handleCommand(new CommandArgs(sender, label, finalArgs));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        // as a matter of fact, no, we did not handle a command.
        return true;
    }

    /**
     * Add an executor to the executor map.
     *
     * @param executor The executor to be added.
     */
    public void addExecutor(XCommandExecutor executor) {
        this.executorMap.put(executor.label(), executor);
        String realLabel = executor.label().split("\\.")[0];
        commandMapping.register(realLabel, new BukkitCommand(realLabel, plugin));
        // TODO - Add description and Usage changes.
    }

    /**
     * Registers commands from the specified registration context.
     *
     * @param context The registration context.
     */
    public void registerCommand(CommandRegistrationContext context) {
        if (commandRegistrators.get(context.getClass()) == null)
            throw new UnsupportedOperationException();

        try {
            commandRegistrators.get(context.getClass()).registerCommands(this, context);
        } catch (CommandRegistrationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a registrator to the registrator map by context type.
     *
     * @param registrator The registrator to be added.
     */
    public void addRegistrator(CommandRegistrator registrator) {
        commandRegistrators.put(registrator.getContextType(), registrator);
    }

    /**
     * Removes a registrator from the registrator map by context type.
     *
     * @param klass The class of the registrator to be removed.
     */
    public void removeRegistrator(Class klass) {
        commandRegistrators.remove(klass);
    }

}
