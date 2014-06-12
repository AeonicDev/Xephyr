package com.aeonicdev.xephyr.generic.sql;

import java.sql.ResultSet;

/**
 * Defines the API for any object that wishes to recieve the results of
 * an SQL query from a Statement object.
 *
 * @author maladr0it
 */
public interface SQLResultReceiver {

    /**
     * Called with the result from a {@link java.sql.PreparedStatement#execute()} call.
     *
     * @param success If the execution succeeded.
     */
    public void onExecute(boolean success);

    /**
     * Called with the result from a {@link java.sql.PreparedStatement#executeQuery(String)} call.
     *
     * @param results The results from the query.
     */
    public void onExecuteQuery(ResultSet results);

    /**
     * Called with the result from a Statement.executeUpdate() call.
     *
     * @param changedRows The number of rows updated.
     */
    public void onExecuteUpdate(int changedRows);

    /**
     * Called when an exception occurs.
     *
     * @param exception The exception thrown.
     */
    public abstract void onFailure(Throwable exception);

}
