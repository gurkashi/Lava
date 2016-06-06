package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.*;

/**
 * orders the elements in the collection
 * @param <T> type of input
 */
public class OrderBy<T> implements CollectionQuery<T,T> {
    private final Comparator<T> comparator;

    public OrderBy(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public Collection<T> execute(Collection<T> collection){
        List<T> result = new ArrayList<T>(collection);

        Collections.sort(result, comparator);

        return result;
    }
}

