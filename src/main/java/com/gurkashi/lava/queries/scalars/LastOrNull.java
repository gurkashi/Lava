package com.gurkashi.lava.queries.scalars;

import java.util.Collection;

public class LastOrNull<T> extends Last<T>{
    public T execute(Collection<T> collection){
        return collection.isEmpty()? null : super.execute(collection);
    }
}
