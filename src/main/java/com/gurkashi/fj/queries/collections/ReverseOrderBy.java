package com.gurkashi.fj.queries.collections;

import java.util.Comparator;

/**
 * orders the elements in the collection
 * @param <T> type of input
 */
public class ReverseOrderBy<T> extends OrderBy<T> {
    public ReverseOrderBy(final Comparator<T> comparator) {
        super(new Comparator<T>() {
            public int compare(T o1, T o2) {
                return comparator.compare(o2, o1);
            }
        });
    }
}
