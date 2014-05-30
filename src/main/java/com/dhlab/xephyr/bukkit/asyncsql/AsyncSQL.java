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
 * @author maladr0it
 */
public class AsyncSQL implements SQLStatementWrapper {

    protected final Connection mysqlConnection;
    protected final JavaPlugin plugin;

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

    @Override
    public PreparedStatement prepare(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    @Override
    public Connection getConnection() {
        return mysqlConnection;
    }

    @Override
    public void execute(PreparedStatement st, SQLResultReceiver callback, SQLExecutionType type) {
        Validate.notNull(st);
        Validate.notNull(type);
        // the callback can be null, we don't absolutely HAVE to return anything.
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new AsyncSQLRunnable(plugin, st, callback, type));
    }
}
