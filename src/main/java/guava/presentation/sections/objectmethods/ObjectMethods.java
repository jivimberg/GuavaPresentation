package guava.presentation.sections.objectmethods;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class ObjectMethods {
    /**
     * Guava has some improvements in the equals(), hashCode() and toString() methods.
     * But I'd rather use Intellij generate functionality.
     * See {@link Employee}
     */
    Employee incompleteEmployee;

    // -------------------- Comparison Chain --------------------

    @SuppressWarnings("ConstantConditions")
    private class Person {
        private String name;
        private int zipCode;
        private Age age;

        public Person(String name, int zipCode, Age age) {
            this.name = name;
            this.zipCode = zipCode;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", zipCode=" + zipCode +
                    ", age=" + age +
                    '}';
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static class VerboseComparator
            implements Comparator<Person>
    {

        @Override
        public int compare(Person o1, Person o2) {
            int cmp = o1.name.compareTo(o2.name);
            if (cmp != 0) {
                return cmp;
            }

            cmp = Integer.compare(o1.zipCode, o2.zipCode);
            if (cmp != 0) {
                return cmp;
            }

            if(o1.age == null && o2.age != null){
                return -1;
            } else if (o1.age != null && o2.age == null) {
                return 1;
            } else if (o1.age == null && o2.age == null) {
                return 0;
            } else {
                return o1.age.compareTo(o2.age);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static class GuavaComparator
            implements Comparator<Person>
    {

        @Override
        public int compare(Person o1, Person o2) {
            return ComparisonChain.start()
                    .compare(o1.name, o2.name)
                    .compare(o1.zipCode, o2.zipCode)
                    .compare(o1.age, o2.age, Ordering.natural().nullsFirst())
                    .result();
        }
    }

    public Person createPerson(String name, int zipCode, Age age){
        return new Person(name, zipCode, age);
    }

    /**
     * Don't take my word for it... Test it yourself
     */
    public static void main(String[] args) {
        ObjectMethods om = new ObjectMethods();
        ArrayList<Person> persons = Lists.newArrayList();

        persons.add(om.createPerson("Felix", 14890, Age.YOUNG));
        persons.add(om.createPerson("Felix", 14890, null));
        persons.add(om.createPerson("Felix", 11298, Age.OLD));
        persons.add(om.createPerson("Felix", 11298, Age.YOUNG));
        persons.add(om.createPerson("Andy", 11298, Age.YOUNG));
        persons.add(om.createPerson("Andy", 23099, Age.YOUNG));

        List<Person> personsSortedVerbose = Ordering.from(new VerboseComparator()).sortedCopy(persons);
        System.out.println("personsSortedVerbose = " + personsSortedVerbose);

        List<Person> personsSortedGuava = Ordering.from(new GuavaComparator()).sortedCopy(persons);
        System.out.println("personsSortedGuava = " + personsSortedGuava);
    }

    private enum Age { YOUNG, OLD }

}
