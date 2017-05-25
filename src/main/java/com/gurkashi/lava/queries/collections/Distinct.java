package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gur on 8/25/2015.
 */
public class Distinct <T> implements CollectionQuery<T,T> {

    public Collection<T> execute(Collection<T> collection){
        Collection<T> result = new ArrayList<T>();

        for (T item: collection){
            if (!result.contains(item)){
                result.add(item);
            }
        }

        return result;
    }
}