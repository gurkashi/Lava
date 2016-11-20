package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

public class Intersect<T> implements CollectionQuery<T,T> {

    private final Collection<T> with;

    public Intersect(Collection<T> with){
        this.with = with;
    }

    public Collection<T> execute(Collection<T> collection){
        Collection<T> result = new ArrayList<T>();

        for (T item : collection){
            if (with.contains(item)){
                result.add(item);
            }
        }

        return result;
    }
}
