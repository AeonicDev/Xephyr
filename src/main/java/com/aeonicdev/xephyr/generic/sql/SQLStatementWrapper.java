package com.aeonicdev.xephyr.generic.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Defines the API for any object that wishes to wrap a {@link java.sql.PreparedStatement}
 * in a different format than is usually used.
 *
 * @author maladr0it
 */
public interface SQLStatementWrapper {

    /**
     * Utility method that calls {@link java.sql.Connection#prepareStatement(String)}.
     *
     * @param sql The string containing the SQL statement.
     * @return A prepared statement.
     */
    public PreparedStatement prepare(String sql) throws SQLException;

    /**
     * Returns the SQL connection to the database, in case the user wants to directly access
     * it.
     *
     * @return The SQL database connection.
     */
    public Connection getConnection();

    /**
     * Executes the SQL statement.
     *
     * @param st The PreparedStatement to use.
     * @param callback The callback to use for results. Can be null, the query will be executed regardless.
     * @param type Whether to do a normal execution, a query execution, or an update execution.
     */
    public void execute(PreparedStatement st, SQLResultReceiver callback, SQLExecutionType type);

}
