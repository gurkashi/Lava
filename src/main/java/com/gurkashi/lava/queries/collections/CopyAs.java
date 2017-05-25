package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.Collection;

public class CopyAs<T,C extends Collection> implements CollectionQuery<T,T> {

    private final Class<C> collectionType;

    public CopyAs (Class<C> collectionType){
        this.collectionType = collectionType;
    }

    public Collection<T> execute(Collection<T> collection){
        try {

            Collection<T> result = collectionType.newInstance();
            for (T item: collection){
                result.add(item);
            }
            return result;

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}


