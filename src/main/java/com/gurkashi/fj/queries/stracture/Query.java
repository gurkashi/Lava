package com.gurkashi.fj.queries.stracture;

import java.util.Collection;

/**
 * Created by gur on 8/25/2015.
 *
 * represents a query (a transformation of data which is stored in a collection of elements).
 * receives a collection of Ts as an input
 * returns S as an output
 */
public interface Query<T,S> extends Transformation<Collection<T>, S>{}

