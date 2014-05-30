package com.dhlab.xephyr.generic.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Defines the API for any object that wishes to wrap an SQL preparedstatement
 * in a different format than is usually used.
 * @author maladr0it
 */
public interface SQLStatementWrapper {
    /**
     * Utility method that calls Connection.prepare()
     * @param sql
     * @return
     */
    public PreparedStatement prepare(String sql) throws SQLException;

    /**
     * Returns the SQL connection to the database, in case the user wants to directly access
     * it.
     * @return
     */
    public Connection getConnection();

    /**
     *
     * @param st The PreparedStatement to use.
     * @param callback The callback to use for results. Can be null, the query will be executed regardless.
     * @param type Whether to do a normal exec, a query exec, or an update exec.
     */
    public void execute(PreparedStatement st, SQLResultReceiver callback, SQLExecutionType type);
}
