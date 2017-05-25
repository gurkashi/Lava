package com.gurkashi.lava.queries.collections;

import com.gurkashi.lava.lambdas.Selector;
import com.gurkashi.lava.queries.stracture.CollectionQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

/**
 * Created by gur on 8/25/2015.
 *
 * groups all items in a collection by a selector
 */
public class GroupBy<T,S> implements CollectionQuery<T, GroupBy.Group<S,T>> {
    private final Selector<T,S> grouper;

    public GroupBy(Selector<T,S> grouper){
        this.grouper = grouper;
    }

    public Collection<Group<S,T>> execute(Collection<T> collection){
        Hashtable<S, Group<S, T>> groups = new Hashtable<S, Group<S, T>>();

        for (T item: collection){
            S classification = grouper.select(item);

            if (!groups.containsKey(classification)){
                groups.put(classification, new Group<S, T>(classification));
            }
            groups.get(classification).add(item);
        }

        return groups.values();
    }

    /**
     * create by gur on 8/25/2015.
     *
     * represents a group of objects that are related somehow
     * two groups are considered equal if their by is equal regardless of their items
     * @param <B> by. type of how the items are related
     * @param <G> group. type of the items
     */
    public static class Group<B,G>{
        private final B by;
        private final Collection<G> group;

        public Group(B by){
            this.by = by;
            this.group = new ArrayList<G>();
        }

        public Collection<G> getGroup(){
            return group;
        }

        public B getBy(){
            return by;
        }

        public void add(G item){
            group.add(item);
        }

        @Override
        public int hashCode(){
            return by.hashCode();
        }

        @Override
        public boolean equals(Object other){
            return by.equals(other);
        }
    }
}

