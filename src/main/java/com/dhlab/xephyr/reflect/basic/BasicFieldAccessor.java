package com.dhlab.xephyr.reflect.basic;

import com.dhlab.xephyr.reflect.IFieldAccessor;
import org.apache.commons.lang.Validate;

import java.lang.reflect.Field;

/**
 * Basic implementation of a Field accessor.
 * @author maladr0it
 */
public class BasicFieldAccessor<T> implements IFieldAccessor<T> {

    protected final Field field;

    public BasicFieldAccessor(Field f) {
        Validate.notNull(f);
        this.field = f;
        field.setAccessible(true);
    }

    public BasicFieldAccessor(Class<?> klass, String fieldname) throws NoSuchFieldException {
        this(klass.getField(fieldname));
    }

    @Override
    public boolean isValid() {
        return field != null;
    }

    @Override
    public T get(Object instance) {
        try {
            return (T)field.get(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean set(Object instance, T value) {
        try {
            field.set(instance, value);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    @Override
    public T transfer(Object from, Object to) {
        T obj = get(from);
        if (obj == null)
            return null;
        set(to, obj);
        return obj;
    }

}
