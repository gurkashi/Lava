package com.gurkashi.lava.queries.scalars;

import com.gurkashi.lava.queries.stracture.ScalarQuery;

import java.util.Collection;

/**
 * Created by gur on 8/25/2015.
 */
public class Empty<T> implements ScalarQuery<T, Boolean> {
    public Boolean execute(Collection<T> collection){
        return collection.isEmpty();
    }
}
