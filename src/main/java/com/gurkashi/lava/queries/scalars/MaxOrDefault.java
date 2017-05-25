package com.gurkashi.lava.queries.scalars;

import java.util.Collection;
import java.util.Comparator;

public class MaxOrDefault<T> extends Max<T>{
    final T defaultValue;

    public MaxOrDefault(Comparator<T> comparator, T defaultValue){
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
