package com.gurkashi.fj.queries.stracture;

import java.util.Collection;

/**
 * Created by gur on 8/25/2015.
 *
 * represents a query which returns collections
 * receives a collection of T as an input
 * returns a collection of S as an output
 */
public interface CollectionQuery<T,S> extends Query<T, Collection<S>>{}
