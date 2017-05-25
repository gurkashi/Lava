package com.gurkashi.lava.queries.scalars;

import com.gurkashi.lava.lambdas.Accumulator;
import com.gurkashi.lava.queries.stracture.ScalarQuery;

import java.util.Collection;

/**
 * Created by gur on 8/25/2015.
 */
public class Accumulation<T> implements ScalarQuery<T,T> {
    private final Accumulator<T> accumulator;
    private final T initial;

    public Accumulation(T initial, Accumulator<T> accumulator){
        this.accumulator = accumulator;
        this.initial = initial;
    }

    public T execute(Collection<T> collection){
        T accumulation = initial;
        for (T item: collection){
            accumulation = accumulator.accumulate(accumulation, item);
        }
        return accumulation;
    }
}
