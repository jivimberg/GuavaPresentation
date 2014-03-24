package guava.presentation.sections;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Why immutable?
 *  - Thread safe
 *  - Memory and space efficient
 *  - Can be treated as constants as they remain fixed
 */
@SuppressWarnings("UnusedDeclaration")
public class ImmutableCollections {

    /**
     * The JDK provides Collections.unmodifiableXXX methods
     */
    public Set<String> jdkUnmodifiable(){
        Set<String> modifiableSet = new HashSet<>();
        modifiableSet.add("a");
        modifiableSet.add("b");
        modifiableSet.add("c");
        return Collections.unmodifiableSet(modifiableSet); //Inefficient according to Guava Documentation
    }

    public Set<String> guavaUnmodifiable(){
        return ImmutableSet.of("a", "b", "c");
    }

    /**
     * the JDK collections are only truly immutable if nobody holds a reference to the original collection
     */
    public static void main(String[] args) {
        HashSet<String> modifiableSet = Sets.newHashSet("a", "b", "c");

        Set<String> jdkUnmodifiable = Collections.unmodifiableSet(modifiableSet);
        ImmutableSet<String> guavaUnmodifiable = ImmutableSet.copyOf(modifiableSet);

        modifiableSet.remove("a");
        System.out.println(jdkUnmodifiable.size()); // prints 2
        System.out.println(guavaUnmodifiable.size()); // prints 3
    }

    public ImmutableSet<String> usingBuilder(){
        HashSet<String> modifiableSet = Sets.newHashSet("a", "b", "c");

        return ImmutableSet.<String>builder()
                        .addAll(modifiableSet)
                        .add("d")
                        .add("e", "f", "g")
                        .build();
    }

}
