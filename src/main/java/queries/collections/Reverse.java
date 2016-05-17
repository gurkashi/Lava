package queries.collections;

import queries.stracture.CollectionQuery;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by gur on 8/26/2015.
 */
public class Reverse<T> implements CollectionQuery<T,T> {
    public Collection<T> execute(Collection<T> input) {
        LinkedList<T> reversed = new LinkedList<T>();
        for (T item: input){
            reversed.addFirst(item);
        }
        return reversed;
    }
}
