package com.gurkashi.fj.queries.scalars;

import java.util.Collection;
import java.util.Iterator;

public class LastOrNull<T> extends Last<T>{
    public T execute(Collection<T> collection){
        return collection.isEmpty()? null : super.execute(collection);
    }
}
