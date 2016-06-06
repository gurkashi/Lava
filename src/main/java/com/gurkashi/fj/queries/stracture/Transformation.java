package com.gurkashi.fj.queries.stracture;

/**
 * Created by gur on 8/25/2015.
 *
 * represents a transformation.
 * receives T as an input
 * returns S as an output
 */
public interface Transformation<T,S> {
    S execute(T input);
}

