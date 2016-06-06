package com.gurkashi.fj.queries.stracture;

/**
 * Created by gur on 8/25/2015.
 *
 * represents a query which returns a scalar
 * receives a collection of T as an input
 * returns a scalar S as an output
 */
public interface ScalarQuery<T,S> extends Query<T, S>{ }
