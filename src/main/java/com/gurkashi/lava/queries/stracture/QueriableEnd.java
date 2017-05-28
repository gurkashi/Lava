package com.gurkashi.lava.queries.stracture;

import java.util.Collection;

public class QueriableEnd<T,S> extends Composition<Collection<T>,S>{
    Collection<T> origin;

    public <U> QueriableEnd(Queriable<T,U> before, ScalarQuery<U, S> step, Collection<T> origin) {
        super(before, step);
        this.origin = origin;
    }

    public S execute(){
        return execute(origin);
    }
}
