package queries.collections;

import lambdas.Predicate;
import queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;

/**
 * filtering query.
 * input that passes the predicate (true) will be removed from the result
 * @param <T> input type
 */
public class Delete<T> implements CollectionQuery<T,T> {
    final Predicate<T> predicate;

    public Delete(Predicate<T> predicate){
        this.predicate = predicate;
    }

    public Collection<T> execute(Collection<T> collection){
        Collection<T> result = new ArrayList<T>();

        for (T item: collection){
            if (!predicate.predict(item)){
                result.add(item);
            }
        }

        return result;
    }
}
