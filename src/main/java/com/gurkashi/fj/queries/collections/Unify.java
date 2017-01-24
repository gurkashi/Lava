package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

public class Unify<T> implements CollectionQuery<T,T> {

    private final Collection<T> with;

    public Unify(Collection<T> with){
        this.with = with;
    }

    public Collection<T> execute(Collection<T> collection){
        Collection<T> result = new ArrayList<T>();

        result.addAll(collection);

        for (T item : with){
            if (!result.contains(item)){
                result.add(item);
            }
        }

        return result;
    }
}
