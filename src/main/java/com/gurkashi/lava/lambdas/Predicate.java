package com.gurkashi.lava.lambdas;

/**
 * Created by gur on 8/25/2015.
 */
public interface Predicate<T> {
    boolean predict(T value);
}
