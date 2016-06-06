package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

/**
 * filtering query.
 * only input that passes the predicate (true) will remain in the result
 * @param <T> input type
 */
public class Where<T> implements CollectionQuery<T,T>{
    final Predicate<T> predicate;

    public Where(Predicate<T> predicate){
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

