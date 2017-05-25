package com.gurkashi.lava.queries.scalars;

import com.gurkashi.lava.queries.stracture.ScalarQuery;

import java.util.Collection;

/**
 * Created by gur on 8/26/2015.
 */
public class Single<T> implements ScalarQuery<T,T> {
    public T execute(Collection<T> collection){
        if (collection.size() != 1){
            throw new NoSingleElementException(collection.size());
        }
        return collection.iterator().next();
    }

    public static class NoSingleElementException extends RuntimeException{
        public NoSingleElementException(int count){
            super("Collection had " + count + " elements");
        }
    }
}

