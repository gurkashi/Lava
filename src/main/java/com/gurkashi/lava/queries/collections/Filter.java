package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.lambdas.Predicate;
import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

/**
 * filtering query.
 * only input that passes the predicate (true) will remain in the result
 * @param <T> input type
 */
public class Filter<T> implements CollectionQuery<T,T>{
    final Predicate<T> predicate;

    public Filter(Predicate<T> predicate){
        this.predicate = predicate;
    }

    public Collection<T> execute(Collection<T> collection){
        Collection<T> result = new ArrayList<T>();

        for (T item: collection){
            if (predicate.predict(item)){
                result.add(item);
            }
        }

        return result;
    }
}

