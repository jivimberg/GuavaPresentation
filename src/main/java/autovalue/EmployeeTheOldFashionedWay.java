package autovalue;

public class EmployeeTheOldFashionedWay {

    private final String name;
    private final int age;

    public EmployeeTheOldFashionedWay(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTheOldFashionedWay that = (EmployeeTheOldFashionedWay) o;

        if (age != that.age) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeTheOldFashionedWay{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
