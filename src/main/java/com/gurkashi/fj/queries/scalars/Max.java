package com.gurkashi.fj.queries.scalars;

import com.gurkashi.fj.queries.stracture.ScalarQuery;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by gur on 8/25/2015.
 */
public class Max<T> implements ScalarQuery<T,T> {
    final Comparator<T> comparator;

    public Max(Comparator<T> comparator){ this.comparator = comparator; }

    public T execute(Collection<T> collection){
        T max = collection.iterator().next();

        for (T item: collection){
            if (comparator.compare(max, item) < 0){
                max = item;
            }
        }

        return max;
    }
}