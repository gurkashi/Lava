package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by gur on 8/25/2015.
 */
public class Copy<T> implements CollectionQuery<T,T> {
    public Collection<T> execute(Collection<T> collection){
        ArrayList<T> copy = new ArrayList<T>();
        for (T item: collection){
            copy.add(item);
        }
        return copy;
    }
}

