package com.gurkashi.lava.queries.scalars;

import com.gurkashi.lava.queries.stracture.ScalarQuery;

import java.util.Collection;

public class SingleOrNull<T> implements ScalarQuery<T,T> {
    public T execute(Collection<T> collection){
        if (collection.size() > 1){
            throw new NoSingleOrNoneElementException(collection.size());
        }
        if (collection.isEmpty()){
            return null;
        }
        return collection.iterator().next();
    }

    public static class NoSingleOrNoneElementException extends RuntimeException{
        public NoSingleOrNoneElementException(int count){
            super("Collection had " + count + " elements");
        }
    }
}