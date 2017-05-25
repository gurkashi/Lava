package com.gurkashi.lava.lambdas;

/**
 * Created by gur on 8/25/2015.
 */
public interface Accumulator<T> {
    T accumulate(T a, T b);
}
