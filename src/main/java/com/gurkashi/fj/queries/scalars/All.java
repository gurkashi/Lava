package com.gurkashi.fj.queries.scalars;

import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.queries.stracture.ScalarQuery;

import java.util.Collection;

public class All<T> implements ScalarQuery<T, Boolean> {
    private final Predicate<T> predicate;

    public All(Predicate<T> predicate){
        this.predicate = predicate;
    }

    public Boolean execute(Collection<T> collection){
        for (T item : collection) {
            if (!predicate.predict(item)){
                return false;
            }
        }
        return true;
    }
}
