package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by gur on 8/25/2015.
 */
public class Tail<T> implements CollectionQuery<T,T> {
    public Collection<T> execute(Collection<T> collection){
        Iterator<T> iterator = collection.iterator();
        iterator.next();

        ArrayList<T> tail = new ArrayList<T>();
        while (iterator.hasNext()){
            tail.add(iterator.next());
        }

        return tail;
    }
}


