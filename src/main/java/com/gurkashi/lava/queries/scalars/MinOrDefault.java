package com.gurkashi.lava.queries.scalars;

import java.util.Collection;
import java.util.Comparator;

public class MinOrDefault<T> extends Min<T>{
    final T defaultValue;

    public MinOrDefault(Comparator<T> comparator, T defaultValue){
        super(comparator);
        this.defaultValue = defaultValue;
    }

    public T execute(Collection<T> collection){
        if (collection.isEmpty()){
            return defaultValue;
        }

        return super.execute(collection);
    }
}

