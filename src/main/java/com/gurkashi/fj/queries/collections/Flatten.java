package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

public class Flatten<S, I> implements CollectionQuery<S, I> {
    public Collection<I> execute(Collection<S> input) {
        Collection<I> flattened = new ArrayList();

        for (S item: input){
            if (item instanceof Collection){
                for (I si: (Collection<I>)item){
                    flattened.add(si);
                }
            }
            else {
                flattened.add((I) item);
            }
        }

        return flattened;
    }
}
