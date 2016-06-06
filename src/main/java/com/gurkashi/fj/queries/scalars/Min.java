package com.gurkashi.fj.queries.scalars;

import com.gurkashi.fj.queries.stracture.ScalarQuery;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by gur on 8/25/2015.
 */
public class Min<T> implements ScalarQuery<T,T>{
    final Comparator<T> comparator;

    public Min(Comparator<T> comparator){ this.comparator = comparator; }

    public T execute(Collection<T> collection){
        T min = collection.iterator().next();

        for (T item: collection){
            if (comparator.compare(min, item) > 0){
                min = item;
            }
        }

        return min;
    }
}

