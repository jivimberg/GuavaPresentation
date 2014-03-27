package guava.presentation.sections.functionalidioms;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * Predicates are mainly used to filter collections
 * Note that all Guava filter methods return views of the original collection.
 */
@SuppressWarnings({"SpellCheckingInspection", "UnusedDeclaration"})
public class PredicateExamples {

    // I will use a set for the examples but most methods
    // are also applicable to all Collections and Iterables
    private HashSet<String> set = Sets.newHashSet("abc", "de", "fghijk");

    public void filter(){
        // <-- Try the shortened view
        Set<String> filteredSet = Sets.filter(set, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return s.length() <= 2;
            }
        });

        System.out.println(filteredSet); // prints [de]

        set.add("o");

        System.out.println(filteredSet); // prints [de]
    }

    public boolean allNotEmpty(){
        return Iterables.all(set, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return !Strings.isNullOrEmpty(s);
            }
        }); //returns true
    }

    public boolean anyStartsWithA(){
        return Iterables.any(set, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return s.startsWith("a");
            }
        }); //returns true
    }

    public Optional<String> findEven(){
        return Iterables.tryFind(set, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return s.length() % 2 == 0;
            }
        }); //can return Optional.of(de) or Optional.of(fghijk)
    }

}
