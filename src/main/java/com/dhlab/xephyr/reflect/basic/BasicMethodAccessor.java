package com.dhlab.xephyr.reflect.basic;

import com.dhlab.xephyr.reflect.IMethodAccessor;
import org.apache.commons.lang.Validate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author maladr0it
 */
public class BasicMethodAccessor<T> implements IMethodAccessor<T> {

    /**
     * The method we are going to try and invoke.
     */
    protected final Method method;

    public BasicMethodAccessor(Method m) {
        Validate.notNull(m);
        this.method = m;
        method.setAccessible(true);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public T invoke(Object target, Object... args) {
        try {
            return (T)method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
