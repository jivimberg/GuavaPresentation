package guava.presentation.sections.objectmethods;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@SuppressWarnings("UnusedDeclaration")
public class ObjectMethods {
    /**
     * Guava has some improvements in the equals(), hashCode() and toString() methods.
     * But I'd rather use Intellij generate functionality.
     * See {@link Employee}
     */
    Employee incompleteEmployee;

    // -------------------- Comparison Chain --------------------

    private class Person {
        private String name;
        private String lastName;
        private int zipCode;

        public int verboseCompareTo(Person other) {
            int cmp = name.compareTo(other.name);
            if (cmp != 0) {
                return cmp;
            }
            cmp = lastName.compareTo(other.lastName);
            if (cmp != 0) {
                return cmp;
            }
            return Integer.compare(zipCode, other.zipCode);
        }

        public int guavaCompareTo(Person other){
            return ComparisonChain.start()
                    .compare(this.name, other.name)
                    .compare(this.lastName, other.lastName)
                    .compare(this.zipCode, other.zipCode, Ordering.natural().nullsLast())
                    .result();
        }
    }

}