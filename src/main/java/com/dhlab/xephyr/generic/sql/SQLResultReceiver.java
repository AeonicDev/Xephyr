package com.dhlab.xephyr.generic.sql;

import java.sql.ResultSet;

/**
 * Defines the API for any object that wishes to recieve the results of
 * an SQL query from a Statement object.
 * @author maladr0it
 */
public abstract class SQLResultReceiver {
    /**
     * Called with the result from a Statement.execute() call.
     * @param success
     */
    public void onExecute(boolean success) { }

    /**
     * Called with the result from a Statement.executeQuery() call.
     * @param results
     */
    public void onExecuteQuery(ResultSet results) { }

    /**
     * Called with the result from a Statement.executeUpdate() call.
     * @param changedRows
     */
    public void onExecuteUpdate(int changedRows) { }

    /**
     * Called when an exception occurs.
     * @param exception
     */
    public abstract void onFailure(Throwable exception);
}
