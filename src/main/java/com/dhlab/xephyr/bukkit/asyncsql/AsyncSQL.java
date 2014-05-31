package com.dhlab.xephyr.bukkit.asyncsql;

import com.dhlab.xephyr.bukkit.asyncsql.runnable.AsyncSQLRunnable;
import com.dhlab.xephyr.generic.sql.SQLExecutionType;
import com.dhlab.xephyr.generic.sql.SQLResultReceiver;
import com.dhlab.xephyr.generic.sql.SQLStatementWrapper;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Uses threading and the magic of the Bukkit scheduler to provide an asynchronous SQL framework.
 *
 * @author maladr0it
 */
public class AsyncSQL implements SQLStatementWrapper {

    /**
     * The SQL server connection.
     */
    protected final Connection mysqlConnection;

    /**
     * The plugin this statement will be executed for.
     */
    protected final JavaPlugin plugin;

    /**
     * Creates a new asynchronous SQL statement.
     *
     * @param plugin The plugin this statement will be executed for
     * @param host The database host
     * @param database The database name
     * @param username The name of the database user
     * @param password The password of the database user
     * @throws SQLException When an error connecting to the server occurs.
     */
    public AsyncSQL(JavaPlugin plugin, String host, String database, String username, String password) throws SQLException {
        Validate.notNull(plugin);
        Validate.isTrue(plugin.isEnabled());
        Validate.notNull(host);
        Validate.notNull(database);
        Validate.notNull(username);
        Validate.notNull(password);
        mysqlConnection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?user=" +
                username + "&password=" + password);
        this.plugin = plugin;
    }

    /**
     * Creates and returns a new {@link java.sql.PreparedStatement} to be executed later.
     *
     * @param sql The string containing the SQL statement
     * @return The prepared statement
     * @throws SQLException
     */
    @Override
    public PreparedStatement prepare(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    /**
     * Gets the connection to the database.
     *
     * @return The database connection
     */
    @Override
    public Connection getConnection() {
        return mysqlConnection;
    }

    /**
     * Executes a {@link java.sql.PreparedStatement} asynchronously.
     *
     * @param st       The PreparedStatement to use
     * @param callback The callback to use for results. Can be null, the query will be executed regardless
     * @param type     Whether to do a normal execution, a query execution, or an update execution
     */
    @Override
    public void execute(PreparedStatement st, SQLResultReceiver callback, SQLExecutionType type) {
        Validate.notNull(st);
        Validate.notNull(type);
        // the callback can be null, we don't absolutely HAVE to return anything.
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new AsyncSQLRunnable(plugin, st, callback, type));
    }
}
