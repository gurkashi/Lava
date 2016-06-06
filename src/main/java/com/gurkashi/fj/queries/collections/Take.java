package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by gur on 8/25/2015.
 */
public class Take<T> implements CollectionQuery<T,T> {
    private final int from;
    private final int count;

    public Take(int from, int count){
        this.from = from;
        this.count = count;
    }

    public Collection<T> execute(Collection<T> collection){
        Iterator<T> iterator = collection.iterator();

        for (int index = 0; index < from; index++){
            iterator.next();
        }

        ArrayList<T> result = new ArrayList<T>();
        for (int index = 0; index < count; index++){
            result.add(iterator.next());
        }

        return result;
    }
}
