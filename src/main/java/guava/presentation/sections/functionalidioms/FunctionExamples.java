package guava.presentation.sections.functionalidioms;

import com.google.common.base.Function;
import com.google.common.collect.*;

import java.util.List;

/**
 * Functions are mainly used to transform collections
 * Note that all Guava transform methods return views of the original collection.
 */
public class FunctionExamples {

    // I will use a list for the examples but most methods
    // are also applicable to all Collections and Iterables
    private List<String> list = Lists.newArrayList("abc", "de", "fghijk");

    public void transform(){
        List<Integer> transformedList = Lists.transform(list, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });

        System.out.println(transformedList); // prints [de]

        list.remove(0);

        System.out.println(transformedList); // prints [de]
    }

    public void transformOnMultiMaps(){
        // maps last names to all first names of people with that last name
        Multimap<String, String> lastNamesToFirstNames = HashMultimap.create();
        lastNamesToFirstNames.put("Lopez", "Jonathan");
        lastNamesToFirstNames.put("Lopez", "Lucas");
        lastNamesToFirstNames.put("Lopez", "Maxi");
        lastNamesToFirstNames.put("Cruz", "Rodo");

        System.out.println(lastNamesToFirstNames);

        Multimap<String, String> lastNameToName = Multimaps.transformEntries(lastNamesToFirstNames,
                new Maps.EntryTransformer<String, String, String>() {
                    public String transformEntry(String lastName, String firstName) {
                        return firstName + " " + lastName;
                    }
                });

        System.out.println(lastNameToName);
    }

    public static void main(String[] args) {
        FunctionExamples functionExamples = new FunctionExamples();
        functionExamples.transformOnMultiMaps();
    }

    /**
     * A transform operation for Set is omitted, since an efficient contains(Object) operation could not
     * be supported. Instead, use Sets.newHashSet(Collections2.transform(set, function)) to create a copy
     * of a transformed set.
     */
}
