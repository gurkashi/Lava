import com.gurkashi.fj.lambdas.Selector;
import org.junit.Test;
import com.gurkashi.fj.queries.collections.GroupBy;
import com.gurkashi.fj.queries.stracture.Queriable;

import java.util.ArrayList;
import java.util.Collection;

public class Person {
    public Person(){}

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public String name;
    public Integer age;

    @Test
    public void test(){
        Collection<Person> people = new ArrayList<Person>();
        people.add(new Person("pie", 28));
        people.add(new Person("shush", 26));
        people.add(new Person("gur", 27));
        people.add(new Person("dagi", 25));

        Collection<Person> friends = new ArrayList<Person>();
        friends.add(new Person("dagi", 25));

        Collection<Integer> counts = Queriable.create(Person.class).groupBy(new Selector<Person, Integer>() {
            public Integer select(Person value) {
                return value.age % 4;
            }
        }).map(new Selector<GroupBy.Group<Integer, Person>, Integer>() {
            public Integer select(GroupBy.Group<Integer, Person> value) {
                return value.getGroup().size();
            }
        }).execute(people);


        int i = 0;

        /*String firstPersonBigger27 = Queriable.create(Person.class)
        .filter(new Predicate<Person>() {
            public boolean predict(Person value) {
                return value.age >= 27;
            }
        }).sortBy(new Comparator<Person>() {
                    public int compare(Person o1, Person o2) {
                        return o1.age - o2.age;
                    }
                }).map(new Selector<Person, String>() {
                    public String map(Person value) {
                        return value.name;
                    }
                }).first().execute(people);
                */
    }
}
