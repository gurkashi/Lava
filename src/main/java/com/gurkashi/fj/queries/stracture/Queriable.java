package com.gurkashi.fj.queries.stracture;

import com.gurkashi.fj.lambdas.Accumulator;
import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.lambdas.Selector;
import com.gurkashi.fj.queries.collections.*;
import com.gurkashi.fj.queries.scalars.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Represents an <ExecutionChain />. each step in the chain is a <CollectionQuery />
 * @param <T> type of input of the first step
 * @param <S> type of output of the last step
 */
public class Queriable<T,S> extends ExecutionChain<Collection<T>,Collection<S>> implements CollectionQuery<T,S>{
    /**
     * construct a generic T to S query by separating transformations with a middle type U
     * @param before first transformation from T to U
     * @param step second transformation from U to S
     * @param <U> generic type to concatenate transformations
     */
    private <U> Queriable(CollectionQuery<T, U> before, CollectionQuery<U, S> step) {
        super(before, step);
    }

    /** creation **/
    /**
     * creates a generic query with type T
     * @param type the generic type
     * @param <T> generic type
     * @return creates a generic query with type T
     */
    public static <T> Queriable<T, T> create(Class<T> type){
        final CollectionQuery<T,T> id = new CollectionQuery<T, T>() {
            public Collection<T> execute(Collection<T> input) {
                return input;
            }
        };
        return new Queriable<T,T>(id, id);
    }

    /**
     * extends the execution from T to S by another extensible query so it will be from T to U
     * @param extension step to add that transforms from S to U
     * @param <U> type of new output
     * @return new chain that will go from T to U
     */
    public <U> Queriable<T,U> extend(CollectionQuery<S,U> extension){
        return new Queriable<T, U>(this, extension);
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

    /**
     * converts the array into a collection and executes
     * @param input array of data
     * @return <see>Queriable.execute(Collection>)</see>
     */
    public Collection<S> execute(T[] input){
        Collection<T> collection = new ArrayList<T>();
        for (T item: input){
            collection.add(item);
        }
        return execute(collection);
    }

    /** collections **/

    public <U> Queriable<T,U> map(Selector<S, U> selector){
        return extend(new Map<S,U>(selector));
    }

    public Queriable<T,S> filter(Predicate<S> predicate){
        return extend(new Filter<S>(predicate));
    }

    public Queriable<T,S> delete(Predicate<S> predicate){
        return extend(new Delete<S>(predicate));
    }

    public Queriable<T,S> sortBy(Comparator<S> comparator){
        return extend(new SortBy<S>(comparator));
    }

    public Queriable<T,S> reverseSortBy(Comparator<S> comparator){ return extend(new ReverseSortBy<S>(comparator)); }

    public Queriable<T,S> reverse(){
        return extend(new Reverse<S>());
    }

    public Queriable<T,S> copy(){ return extend(new Copy<S>()); }

    public <U extends Collection> Queriable<T,S> copyAs(Class<U> collectionType){ return extend(new CopyAs<S, U>(collectionType)); }

    public <U extends Collection<S>> Queriable<T,S> copyTo(U collection){ return extend(new CopyTo<S,U>(collection)); }

    public Queriable<T,S> distinct(){
        return extend(new Distinct<S>());
    }

    public <B> Queriable<T, GroupBy.Group<B,S>> groupBy(Selector<S,B> grouper){ return extend(new GroupBy<S, B>(grouper)); }

    public Queriable<T,S> tail(){ return extend(new Tail<S>()); }

    public Queriable<T,S> take(int count){ return extend(new Take<S>(0, count)); }

    public Queriable<T,S> take(int from, int count){ return extend(new Take<S>(from, count)); }

    public <SI> Queriable<T, SI> flatten(Class<SI> itemType){ return extend(new Flatten<S, SI>()); }

    public Queriable<T, Collection<T>> permutations() { return extend(new Permutation()); }

    public Queriable<T, S> intersect(Collection<S> with){ return extend(new Intersect<S>(with)); }

    /** scalars **/

    public ExecutionChain<Collection<T>,Boolean> all(Predicate<T> predicate){ return extend(new All(predicate)); }

    public ExecutionChain<Collection<T>,Boolean> exists(Predicate<T> predicate){ return extend(new Exists(predicate)); }

    public ExecutionChain<Collection<T>,Integer> count(){ return extend(new Count<S>()); }

    public ExecutionChain<Collection<T>,S> first(){
        return extend(new First<S>());
    }

    public ExecutionChain<Collection<T>,S> firstOrNull(){
        return extend(new FirstOrNull<S>());
    }

    public ExecutionChain<Collection<T>,S> last(){
        return extend(new Last<S>());
    }

    public ExecutionChain<Collection<T>,S> lastOrNull(){
        return extend(new LastOrNull<S>());
    }

    public ExecutionChain<Collection<T>,S> single(){
        return extend(new Single<S>());
    }

    public ExecutionChain<Collection<T>,S> singleOrNull(){
        return extend(new SingleOrNull<S>());
    }

    public ExecutionChain<Collection<T>,S> accumulate(S initial, Accumulator<S> accumulator){ return extend(new Accumulation<S>(initial, accumulator)); }

    public ExecutionChain<Collection<T>,S> max(Comparator<S> comparator){ return extend(new Max<S>(comparator)); }

    public ExecutionChain<Collection<T>,S> min(Comparator<S> comparator){
        return extend(new Min<S>(comparator));
    }

    public ExecutionChain<Collection<T>,S> minOrDefault(Comparator<S> comparator, S defaultValue){
        return extend(new MinOrDefault<S>(comparator, defaultValue));
    }

    public ExecutionChain<Collection<T>,S> maxOrDefault(Comparator<S> comparator, S defaultValue){
        return extend(new MaxOrDefault<S>(comparator, defaultValue));
    }
	
	public <U> ExecutionChain<Collection<T>, U> reduce(Selector<Collection<S>, U> reducer){ return extend(new Reduce<S, U>(reducer)); }

    public ExecutionChain<Collection<T>,Boolean> empty(){
        return extend(new Empty<S>());
    }
}