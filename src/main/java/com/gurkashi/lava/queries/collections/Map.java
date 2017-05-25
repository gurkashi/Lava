package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.lambdas.Selector;
import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

/**
 * selection query
 * @param <T> input type
 * @param <S> output type
 */
public class Map<T,S> implements CollectionQuery<T,S>{
    final Selector<T,S> selector;

    public Map(Selector<T, S> selector){
        this.selector = selector;
    }

    public Collection<S> execute(Collection<T> collection){
        ArrayList<S> selection = new ArrayList<S>();
        for (T item: collection){
            selection.add(selector.select(item));
        }
        return selection;
    }
}
