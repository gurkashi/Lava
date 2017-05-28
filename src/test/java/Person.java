import com.gurkashi.lava.lambdas.Selector;
import org.junit.Test;
import com.gurkashi.lava.queries.collections.GroupBy;
import com.gurkashi.lava.queries.stracture.Queriable;

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

    }
}
