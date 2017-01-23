package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


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

