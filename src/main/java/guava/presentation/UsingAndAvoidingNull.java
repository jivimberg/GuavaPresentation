package guava.presentation;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.sun.istack.internal.Nullable;

import java.util.Map;
import java.util.Random;

/**
 * "I call it my billion-dollar mistake." - Sir C. A. R. Hoare, on his invention of the null reference
 */
@SuppressWarnings({"UnusedDeclaration", "UnusedAssignment", "StatementWithEmptyBody"})
public class UsingAndAvoidingNull {

    void nullIsAmbiguous(Map map){
        Object value = map.get("Key");
        if(value == null){
            // The value in the map is null?
            // or the value is not in the map?
        }
    }

    /**
     * Studying the Google code base, we found that 95% of collections weren't supposed to
     * have any null values in them.
     */
    static void guavaCollectionsFailWithNull(){
        ImmutableList.of("one", "two", null);
    }

    // -------------------- OPTIONALS --------------------

    /**
     * The biggest advantage of Optional is its idiot-proof-ness. It forces you to actively think about the absent
     * case if you want your program to compile at all, since you have to actively unwrap the Optional and address that case.
     */

    @Nullable
    String findString(){
        Random rdm = new Random();
        boolean found = rdm.nextBoolean();
        return found ? "The string you were looking" : null;
    }

    Optional<String> creatingAnOptional(){
        Optional<String> result;

        String value = findString();
        result = Optional.fromNullable(value);

        // which is the same as doing:
        if(value != null){
            result = Optional.of(value);
        } else {
            result = Optional.absent();
        }

        return result;
    }

    void queryingOptionals(){
        Optional<String> optional = creatingAnOptional();

        if(optional.isPresent()){
            String value = optional.get(); //We are sure is the value is there
            System.out.println("The string is: " + value);
        }

        //or
        System.out.println("Value or default: " + optional.or("Default value"));
    }

    /**
     * Don't believe me? Try it yourself
     */
    public static void main(String[] args) {
        guavaCollectionsFailWithNull();
    }
}
