package com.gurkashi.lava.queries.scalars;

import com.gurkashi.lava.lambdas.Predicate;
import com.gurkashi.lava.queries.stracture.ScalarQuery;

import java.util.Collection;

public class Exists<T> implements ScalarQuery<T, Boolean> {
    private final Predicate<T> predicate;

    public Exists(Predicate<T> predicate){
        this.predicate = predicate;
    }

    public Boolean execute(Collection<T> collection){
        for (T item : collection) {
            if (predicate.predict(item)){
                return true;
            }
        }
        return false;
    }
}
