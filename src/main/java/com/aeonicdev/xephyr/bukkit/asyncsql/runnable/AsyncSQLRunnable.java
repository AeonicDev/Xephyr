package com.aeonicdev.xephyr.bukkit.asyncsql.runnable;

import com.aeonicdev.xephyr.generic.sql.SQLExecutionType;
import com.aeonicdev.xephyr.generic.sql.SQLResultReceiver;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The SQL runnable that the AsyncSQL instance uses.
 * Wrapped in a {@link org.bukkit.scheduler.BukkitRunnable} for consistency.
 *
 * @author sc4re
 */
public class AsyncSQLRunnable extends BukkitRunnable {

    /**
     * The plugin to use for the scheduler.
     */
    private final JavaPlugin plugin;
    /**
     * The PreparedStatement to execute.
     */
    private final PreparedStatement statement;
    /**
     * The callback to send results to with the scheduler.
     */
    private final SQLResultReceiver callback;

    /**
     * This indicates the Statement method to call and what data to return to the callback.
     */
    private final SQLExecutionType execType;

    /**
     * Creates a new instance of a runnable SQL statement.
     *
     * @param plugin The plugin this statement is being executed for.
     * @param st The statement.
     * @param callback The callback to execute afterward (receives the results).
     * @param type The execution type of the statement.
     */
    public AsyncSQLRunnable(JavaPlugin plugin, PreparedStatement st, SQLResultReceiver callback, SQLExecutionType type) {
        Validate.notNull(plugin);
        Validate.isTrue(plugin.isEnabled()); // make sure the plugin is, in fact, enabled.
        Validate.notNull(st);
        Validate.notNull(type);
        this.plugin = plugin;
        this.statement = st;
        this.callback = callback;
        this.execType = type;
    }

    @Override
    public void run() {
        try {
            Object obj = execute(statement, execType);
            callback(callback, execType, obj);
        } catch (SQLException e) {
            e.printStackTrace();
            if (callback != null)
                callback.onFailure(e);
        }
    }

    /**
     * Actually executes the {@link java.sql.PreparedStatement}.
     *
     * @param st The statement to execute.
     * @param type The type of execution.
     * @return The result of the statement's execution.
     * @throws SQLException When an error is encountered.
     */
    private Object execute(PreparedStatement st, SQLExecutionType type) throws SQLException {
        switch (type) {
            case NORMAL_EXEC:
                return st.execute();
            case QUERY_EXEC:
                return st.executeQuery();
            case UPDATE_EXEC:
                return st.executeUpdate();
        }
        return null;
    }

    /**
     * Executes the callback provided by the statement.
     *
     * @param callback The callback.
     * @param type The type of statement.
     * @param obj The result of the statement.
     */
    private void callback(final SQLResultReceiver callback, final SQLExecutionType type, final Object obj) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (callback == null)
                    return;
                switch(execType) {
                    case NORMAL_EXEC:
                        callback.onExecute((Boolean)obj);
                        break;
                    case QUERY_EXEC:
                        callback.onExecuteQuery((ResultSet)obj);
                        break;
                    case UPDATE_EXEC:
                        callback.onExecuteUpdate((Integer)obj);
                        break;
                }
            }
        }, 1L);
    }

}
