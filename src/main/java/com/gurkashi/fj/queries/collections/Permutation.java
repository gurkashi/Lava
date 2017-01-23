package com.gurkashi.fj.queries.collections;

import com.gurkashi.fj.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Permutation<T> implements CollectionQuery<T,Collection<T>> {
    public Collection<Collection<T>> execute(Collection<T> collection){
        Collection<Collection<T>> permutations = new ArrayList<Collection<T>>();
        permutations.add(new ArrayList<T>());
        return permutations(permutations, collection.iterator());
    }

    private Collection<Collection<T>> permutations(Collection<Collection<T>> permutations, Iterator<T> iterator){
        if (!iterator.hasNext()){
            return permutations;
        }

        T value = iterator.next();

        Collection<Collection<T>> newPermutations = new ArrayList<Collection<T>>();
        for (Collection<T> permutation: permutations){
            Collection<T> inclusive = new ArrayList<T>();
            inclusive.addAll(permutation);
            inclusive.add(value);

            newPermutations.add(inclusive);
        }
        permutations.addAll(newPermutations);

        return permutations(permutations, iterator);
    }
}
