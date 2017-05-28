import com.gurkashi.lava.lambdas.Predicate;
import com.gurkashi.lava.lambdas.Selector;
import com.gurkashi.lava.queries.stracture.Queriable;

/**
 * Created by Gur on 5/26/2017.
 */
public class Example {
    public void remove(){
        String[] heroes = { "Ned Stark", "Arya Stark", "Tyrion Lannister"};

        Queriable.create(String.class)
                .filter(new Predicate<String>() {
                    public boolean predict(String value) {
                        return value.endsWith("Stark");
                    }
                })
                .map(new Selector<String, String>() {
                    public String select(String value) {
                        return value.split(" ")[0];
                    }
                })
                .execute(heroes);

        // result = { Ned, Arya }
    }
}
