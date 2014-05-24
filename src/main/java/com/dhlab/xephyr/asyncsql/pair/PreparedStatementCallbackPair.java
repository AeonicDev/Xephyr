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
 * you to do asynchronous dingles
 * @author maladr0it
 */
public class PreparedStatementCallbackPair {

    protected final PreparedStatement statement;
    protected final Channel<ResultSet> queryChannel;
    protected final Channel<Boolean> executeChannel;
    protected final Channel<Throwable> exceptionChannel;

    public PreparedStatementCallbackPair(PreparedStatement statement) {
        Validate.notNull(statement);
        this.statement = statement;
        this.queryChannel = new MemoryChannel<>();
        this.executeChannel = new MemoryChannel<>();
        this.exceptionChannel = new MemoryChannel<>();
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public Channel<ResultSet> getQueryChannel() {
        return queryChannel;
    }

    public Channel<Boolean> getExecuteChannel() {
        return executeChannel;
    }

    public Channel<Throwable> getExceptionChannel() {
        return exceptionChannel;
    }

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
