package queries.stracture;

import lambdas.Accumulator;
import lambdas.Predicate;
import lambdas.Selector;
import queries.collections.*;
import queries.scalars.*;

import java.util.Collection;
import java.util.Comparator;

/**
 * Represents an <ExecutionChain />. each step in the chain is a <CollectionQuery />
 * @param <T> type of input of the first step
 * @param <S> type of output of the last step
 */
public class NestedQuery<T,S> extends ExecutionChain<Collection<T>,Collection<S>> implements CollectionQuery<T,S>{
    /**
     * construct a generic T to S query by separating transformations with a middle type U
     * @param before first transformation from T to U
     * @param step second transformation from U to S
     * @param <U> generic type to concatenate transformations
     */
    private <U> NestedQuery(CollectionQuery<T,U> before, CollectionQuery<U, S> step) {
        super(before, step);
    }

    /** creation **/
    /**
     * creates a generic query with type T
     * @param type the generic type
     * @param <T> generic type
     * @return creates a generic query with type T
     */
    public static <T> NestedQuery<T, T> create(Class<T> type){
        final CollectionQuery<T,T> id = new CollectionQuery<T, T>() {
            public Collection<T> execute(Collection<T> input) {
                return input;
            }
        };
        return new NestedQuery<T,T>(id, id);
    }

    /**
     * extends the execution from T to S by another extensible query so it will be from T to U
     * @param extension step to add that transforms from S to U
     * @param <U> type of new output
     * @return new chain that will go from T to U
     */
    public <U> NestedQuery<T,U> extend(CollectionQuery<S,U> extension){
        return new NestedQuery<T, U>(this, extension);
    }

    /**
     * extends the execution from T to S by a scalar query so it will be from T to U
     * after this the chain will not be extensible anymore
     * @param extension step to add that transforms from S to U
     * @param <U> type of new output
     * @return new chain that will go from T to U
     */
    public <U> ExecutionChain<Collection<T>, U> extend(ScalarQuery<S,U> extension){
        return new ExecutionChain<Collection<T>, U>(this, extension);
    }

    /** collections **/

    public <U> NestedQuery<T,U> select(Selector<S, U> selector){
        return extend(new Select<S,U>(selector));
    }

    public NestedQuery<T,S> where(Predicate<S> predicate){
        return extend(new Where<S>(predicate));
    }

    public NestedQuery<T,S> delete(Predicate<S> predicate){
        return extend(new Delete<S>(predicate));
    }

    public NestedQuery<T,S> orderBy(Comparator<S> comparator){
        return extend(new OrderBy<S>(comparator));
    }

    public NestedQuery<T,S> reverseOrderBy(Comparator<S> comparator){ return extend(new ReverseOrderBy<S>(comparator)); }

    public NestedQuery<T,S> reverse(){
        return extend(new Reverse<S>());
    }

    public NestedQuery<T,S> copy(){ return extend(new Copy<S>()); }

    public NestedQuery<T,S> distinct(){
        return extend(new Distinct<S>());
    }

    public <B> NestedQuery<T, GroupBy.Group<B,S>> groupBy(Selector<S,B> grouper){ return extend(new GroupBy<S, B>(grouper)); }

    public NestedQuery<T,S> tail(){ return extend(new Tail<S>()); }

    public NestedQuery<T,S> take(int count){ return extend(new Take<S>(0, count)); }

    public NestedQuery<T,S> take(int from, int count){ return extend(new Take<S>(from, count)); }

    /** scalars **/

    public ExecutionChain<Collection<T>,Integer> count(){ return extend(new Count<S>()); }

    public ExecutionChain<Collection<T>,S> first(){
        return extend(new First<S>());
    }

    public ExecutionChain<Collection<T>,S> last(){
        return extend(new Last<S>());
    }

    public ExecutionChain<Collection<T>,S> single(){
        return extend(new Single<S>());
    }

    public ExecutionChain<Collection<T>,S> accumulate(S initial, Accumulator<S> accumulator){ return extend(new Accumulation<S>(initial, accumulator)); }

    public ExecutionChain<Collection<T>,S> max(Comparator<S> comparator){ return extend(new Max<S>(comparator)); }

    public ExecutionChain<Collection<T>,S> min(Comparator<S> comparator){
        return extend(new Min<S>(comparator));
    }

    public ExecutionChain<Collection<T>,Boolean> empty(){
        return extend(new Empty<S>());
    }
}