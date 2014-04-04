package autovalue;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Employee {
    public static Employee create(String name, int age) {
        return new AutoValue_Employee(name, age);
    }

    public abstract String name();

    public abstract int age();
}
