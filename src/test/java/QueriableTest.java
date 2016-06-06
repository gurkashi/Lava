import junit.framework.Assert;
import com.gurkashi.fj.lambdas.Accumulator;
import com.gurkashi.fj.lambdas.Predicate;
import com.gurkashi.fj.lambdas.Selector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.gurkashi.fj.queries.collections.GroupBy;
import com.gurkashi.fj.queries.scalars.Single;
import com.gurkashi.fj.queries.stracture.Queriable;

import java.util.*;

/**
 * Created by gur on 8/26/2015.
 */
public class QueriableTest {
    static Collection<Integer> createRange(int first, int last, int step){
        Collection collection = new ArrayList();
        for (int i = first; i<last; i+= step){
            collection.add(i);
        }
        return collection;
    }

    static void print(Collection collection){
        for (Object item: collection){
            System.out.println(item);
        }
    }

    static private boolean collectionsEqual(Collection c1, Collection c2){
        if (c1 == c2) return true;
        if (c1 == null || c2 == null) return false;
        if (c1.size() != c2.size()) return false;
        Iterator i1 = c1.iterator();
        Iterator i2 = c2.iterator();
        while (i1.hasNext() && i2.hasNext()){
            if (i1.next().equals(i2.next())) continue;
            else return false;
        }
        return true;
    }

    Collection<Integer> input = null;
    Collection<Person> people = null;

    @Before
    public void before(){
        input = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            input.add(i);
        }

        people = new ArrayList<Person>();
        Person roy = new Person("roy", 25);
        Person gur = new Person("gur", 27);
        Person mush = new Person("mush", 26);
        people.add(roy);
        people.add(gur);
        people.add(mush);
    }

    @After
    public void after(){
        input = null;
        people = null;
    }

    @Test
    public void flatten(){
        Collection lst1 = new ArrayList<Integer>();
        lst1.add(1);
        lst1.add(2);
        lst1.add(3);
        lst1.add(4);
        lst1.add(5);

        Collection lst2 = new ArrayList<List<Integer>>();
        List<Integer> sub1 = new ArrayList<Integer>();
        List<Integer> sub2 = new ArrayList<Integer>();
        sub1.add(1);
        sub2.add(2);
        sub2.add(3);
        sub2.add(4);
        sub2.add(5);
        lst2.add(sub1);
        lst2.add(sub2);

        Queriable<Collection, Integer> flatten = Queriable.create(Collection.class).flatten(Integer.class);

        Collection<Integer> execute1 = flatten.execute(lst1);
        Collection<Integer> execute2 = flatten.execute(lst2);

        collectionsEqual(execute1, execute2);
    }

    @Test
    public void what() {
        Collection<Integer> input = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++) {
            input.add(i);
        }

        Collection<Integer> output = Queriable.create(Integer.class).execute(input);
        print(output);
    }

    @Test
    public void select() {
        Collection<Integer> output = Queriable.create(Integer.class).select(new Selector<Integer, Integer>() {
            public Integer select(Integer value) {
                return value * 2;
            }
        }).execute(input);

        Assert.assertTrue(collectionsEqual(createRange(0, 10, 2), output));
    }

    @Test
    public void where() {
        Collection<Integer> output = Queriable.create(Integer.class).where(new Predicate<Integer>() {
            public boolean predict(Integer value) {
                return value % 2 == 0;
            }
        }).execute(input);

        Assert.assertTrue(collectionsEqual(createRange(0, 5, 2), output));
    }

    @Test
    public void delete() {
        Collection<Integer> output = Queriable.create(Integer.class).delete(new Predicate<Integer>() {
            public boolean predict(Integer value) {
                return value % 2 == 0;
            }
        }).execute(input);

        Assert.assertTrue(collectionsEqual(createRange(1, 5, 2), output));
    }

    @Test
    public void orderBy() {
        this.input.clear();
        this.input.add(3);
        this.input.add(2);
        this.input.add(1);
        Collection<Integer> output = Queriable.create(Integer.class).orderBy(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        }).execute(input);

        Assert.assertTrue(collectionsEqual(createRange(1, 4, 1), output));
    }

    @Test
    public void reverseOrderBy() {
        this.input.clear();
        this.input.add(1);
        this.input.add(2);
        this.input.add(3);
        Collection<Integer> output = Queriable.create(Integer.class).reverseOrderBy(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        }).execute(input);

        Collection<Integer> expected = new ArrayList<Integer>();
        expected.add(3);
        expected.add(2);
        expected.add(1);
        Assert.assertTrue(collectionsEqual(expected, output));
    }

    @Test
    public void distinct(){
        this.input.clear();
        this.input.add(1);
        this.input.add(1);
        this.input.add(2);
        this.input.add(2);
        Collection<Integer> output = Queriable.create(Integer.class).distinct().execute(input);
        Assert.assertTrue(collectionsEqual(createRange(1, 3, 1), output));
    }

    @Test
    public void groupBy(){
        Collection<GroupBy.Group<Boolean, Integer>> output = Queriable.create(Integer.class).groupBy(new Selector<Integer, Boolean>() {
            public Boolean select(Integer value) {
                return value % 2 == 0;
            }
        }).orderBy(new Comparator<GroupBy.Group<Boolean, Integer>>() {
            public int compare(GroupBy.Group<Boolean, Integer> o1, GroupBy.Group<Boolean, Integer> o2) {
                if (o1.getBy()) return -1; else return 1;
            }
        }).execute(input);

        Assert.assertEquals(2, output.size());
        Iterator<GroupBy.Group<Boolean, Integer>> iterator = output.iterator();
        Collection<Integer> evens = iterator.next().getGroup();
        Collection<Integer> odds = iterator.next().getGroup();

        Assert.assertEquals(2, odds.size());
        Assert.assertEquals(3, evens.size());
    }

    @Test
    public void count(){
        Assert.assertEquals(5, (int) Queriable.create(Integer.class).count().execute(input));
    }

    @Test
    public void first(){
        Assert.assertEquals(26, (int)getAges().first().execute(people));
    }

    @Test
    public void last(){
        Assert.assertEquals(27, (int)getAges().last().execute(people));
    }

    @Test
    public void sum(){
        Assert.assertEquals(27+26, (int)getAges().accumulate(0, new Accumulator<Integer>() {
            public Integer accumulate(Integer a, Integer b) {
                return a + b;
            }
        }).execute(people));
    }

    @Test
    public void reverse(){
        Collection<Integer> expected = new ArrayList<Integer>();
        for (int i = 4; i>=0; i--){
            expected.add(i);
        }

        Collection<Integer> result = Queriable.create(Integer.class).reverse().execute(input);

        Assert.assertTrue(collectionsEqual(expected, result));
    }

    @Test
    public void copy(){
        Collection<Person> copy = Queriable.create(Person.class).copy().execute(people);

        copy.clear();

        Assert.assertEquals(0, copy.size());
        Assert.assertEquals(3, people.size());
    }

    private static Queriable<Person, Integer> getAges(){
        return Queriable.create(Person.class).orderBy(new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        }).delete(new Predicate<Person>() {
            public boolean predict(Person value) {
                return value.name.equals("roy");
            }
        }).select(new Selector<Person, Integer>() {
            public Integer select(Person value) {
                return value.age;
            }
        });
    }

    @Test
    public void single_empty_fail(){
        Collection<Object> objects = new ArrayList<Object>();

        boolean hadError = false;
        try {
            Queriable.create(Object.class).single().execute(objects);
        }
        catch (Single.NoSingleElementException ex){ hadError = true; }

        Assert.assertTrue(hadError);
    }

    @Test
    public void single_tooMuch_fail(){
        Collection<Object> objects = new ArrayList<Object>();
        objects.add("Too");
        objects.add("Much");

        boolean hadError = false;
        try {
            Queriable.create(Object.class).single().execute(objects);
        }
        catch (Single.NoSingleElementException ex){ hadError = true; }

        Assert.assertTrue(hadError);
    }

    @Test
    public void single_vanilla(){
        Collection<Object> objects = new ArrayList<Object>();
        objects.add("good");

        Queriable.create(Object.class).single().execute(objects);
    }

    @Test
    public void min(){
        Assert.assertEquals(0, (int) Queriable.create(Integer.class).min(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }).execute(input));
    }

    @Test
    public void max(){
        Assert.assertEquals(4, (int) Queriable.create(Integer.class).max(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }).execute(input));
    }


    @Test
    public void empty_true(){
        Assert.assertTrue(Queriable.create(Integer.class).empty().execute(new ArrayList<Integer>()));
    }

    @Test
    public void empty_false(){
        Assert.assertFalse(Queriable.create(Integer.class).empty().execute(input));
    }

    @Test
    public void tail_single_empty(){
        Assert.assertTrue(Queriable.create(Integer.class).tail().execute(createRange(1, 2, 1)).isEmpty());
    }

    @Test
    public void tail_collectionOf3_collectionOf2(){
        Assert.assertTrue(collectionsEqual(createRange(2,4,1), Queriable.create(Integer.class).tail().execute(createRange(1,4,1))));
    }
}