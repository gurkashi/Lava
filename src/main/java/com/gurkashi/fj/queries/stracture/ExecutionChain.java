package com.gurkashi.fj.queries.stracture;

/**
 * class that enforces chain execution of commands
 * @param <T> input type
 * @param <S> output type
 */
public class ExecutionChain<T,S> implements Transformation<T,S>{
    private final Transformation<T,S> chain;

    public <U> ExecutionChain(final Transformation<T,U> before, final Transformation<U,S> step) {
        this.chain = new Transformation<T, S>() {
            public S execute(T input) {
                return step.execute(before.execute(input));
            }
        };
    }

    public S execute(T input){
        return chain.execute(input);
    }
}

