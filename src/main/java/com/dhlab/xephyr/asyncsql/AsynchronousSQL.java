package com.dhlab.xephyr.asyncsql;


import com.dhlab.xephyr.asyncsql.pair.PreparedStatementCallbackPair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Provides a way to do asynchronous MySQL queries.
 * Not for the faint of heart.
 * @author maladr0it
 */
public class AsynchronousSQL {

    protected final Connection connection;

    /**
     * Create a new instance of the asynchronous SQL object.
     *
     * @param host The SQL database host.
     * @param database The SQL database name.
     * @param user The user for the SQL server.
     * @param password The password for the user.
     * @throws SQLException
     */
    public AsynchronousSQL(String host, String database, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
    }

    /**
     * Gets the object that represents the {@link java.sql.Connection} to the SQL server.
     *
     * @return The connection to the SQL server
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Execute a statement and get the results via a {@link com.dhlab.xephyr.asyncsql.pair.PreparedStatementCallbackPair}.
     *
     * @param sql The SQL statement to be executed
     * @return The callback pair.
     * @throws SQLException
     */
    public PreparedStatementCallbackPair prepareCallback(String sql) throws SQLException {
        PreparedStatement st = connection.prepareStatement(sql);
        return new PreparedStatementCallbackPair(st);
    }
}
