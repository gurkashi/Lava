package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.Collection;

public class CopyTo<T,C extends Collection<T>> implements CollectionQuery<T,T> {

    private final C target;

    public CopyTo (C target){
        this.target = target;
    }

    public Collection<T> execute(Collection<T> collection){
        for (T item: collection){
            target.add(item);
        }
        return target;
    }
}
