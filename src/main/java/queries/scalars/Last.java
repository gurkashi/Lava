package queries.scalars;

import queries.stracture.ScalarQuery;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by gur on 8/25/2015.
 */
public class Last<T> implements ScalarQuery<T,T> {
    public T execute(Collection<T> collection){
        T current = null;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()){
            current = iterator.next();
        }
        return current;
    }
}