package com.gurkashi.lava.queries.scalars;

import com.gurkashi.lava.queries.stracture.ScalarQuery;

import java.util.Collection;

/**
 * Created by gur on 8/25/2015.
 */
public class First<T> implements ScalarQuery<T,T>{
    public T execute(Collection<T> collection){
        return collection.iterator().next();
    }
}
