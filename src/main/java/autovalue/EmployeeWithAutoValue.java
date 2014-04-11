package autovalue;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class EmployeeWithAutoValue {
    public static EmployeeWithAutoValue create(String name, int age) {
        return new AutoValue_EmployeeWithAutoValue(name, age);
    }

    public abstract String getName();

    public abstract int getAge();
}
