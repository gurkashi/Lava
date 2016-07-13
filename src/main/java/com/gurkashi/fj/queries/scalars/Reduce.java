package com.gurkashi.fj.queries.scalars;

import com.gurkashi.fj.lambdas.Selector;
import com.gurkashi.fj.queries.stracture.ScalarQuery;

import java.util.Collection;

/**
 * Created by gur on 2/22/2016.
 */
public class Reduce<T, S> implements ScalarQuery<T,S> {
    private final Selector<Collection<T>, S> wrapper;

    public Reduce(Selector<Collection<T>, S> wrapper){ this.wrapper = wrapper; }

    public S execute(Collection<T> collection){
        return wrapper.select(collection);
    }
}