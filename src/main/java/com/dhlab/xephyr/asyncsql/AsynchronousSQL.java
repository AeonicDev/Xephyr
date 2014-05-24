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

    public AsynchronousSQL(String host, String database, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatementCallbackPair prepareCallback(String sql) throws SQLException {
        PreparedStatement st = connection.prepareStatement(sql);
        return new PreparedStatementCallbackPair(st);
    }
}
