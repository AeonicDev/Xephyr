package com.dhlab.xephyr.asyncsql.pair;

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.jetlang.channels.Channel;
import org.jetlang.channels.MemoryChannel;
import org.jetlang.fibers.Fiber;
import org.jetlang.fibers.ThreadFiber;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class takes a PreparedStatement as an argument and essentially allows
 * for asynchronous queries.
 *
 * @author maladr0it
 */
public class PreparedStatementCallbackPair {

    /**
     * The PreparedStatement to be wrapped around.
     */
    protected final PreparedStatement statement;

    /**
     * The result channel.
     */
    protected final Channel<ResultSet> queryChannel;

    /**
     * The execution channel.
     */
    protected final Channel<Boolean> executeChannel;

    /**
     * The exception channel.
     */
    protected final Channel<Throwable> exceptionChannel;

    /**
     * Creates a new {@code PreparedStatementCallbackPair}, wrapping around the given {@link java.sql.PreparedStatement}.
     *
     * @param statement The given SQL statement
     */
    public PreparedStatementCallbackPair(PreparedStatement statement) {
        Validate.notNull(statement);
        this.statement = statement;
        this.queryChannel = new MemoryChannel<>();
        this.executeChannel = new MemoryChannel<>();
        this.exceptionChannel = new MemoryChannel<>();
    }

    /**
     * Get the wrapped {@link java.sql.PreparedStatement}.
     *
     * @return The wrapped SQL statement
     */
    public PreparedStatement getStatement() {
        return statement;
    }

    /**
     * Get the {@link ResultSet} channel.
     *
     * @return The query result channel
     */
    public Channel<ResultSet> getQueryChannel() {
        return queryChannel;
    }

    /**
     * Get the execution channel.
     *
     * @return The result of actual execution; false if an error was encountered
     */
    public Channel<Boolean> getExecuteChannel() {
        return executeChannel;
    }

    /**
     * Get the exception channel.
     *
     * @return The channel where all thrown objects were sent
     */
    public Channel<Throwable> getExceptionChannel() {
        return exceptionChannel;
    }

    /**
     * Execute the SQL statement.
     */
    public void execute() {
        Fiber fbr = new ThreadFiber();
        Runnable h = new Runnable() {
            @Override
            public void run() {
                try {
                    executeChannel.publish(statement.execute());
                } catch (SQLException e) {
                    exceptionChannel.publish(e);
                }
            }
        };
        fbr.execute(h);
    }

    /**
     * Execute the SQL statement and pipe the returned ResultSet to its given channel.
     */
    public void executeQuery() {
        Fiber fbr = new ThreadFiber();
        Runnable h = new Runnable() {
            @Override
            public void run() {
                try {
                    queryChannel.publish(statement.executeQuery());
                } catch (SQLException e) {
                    exceptionChannel.publish(e);
                }
            }
        };
        fbr.execute(h);
    }
}
