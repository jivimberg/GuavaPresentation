package autovalue;

public class TestGround {

    public static void main(String[] args) {
        Employee employee = Employee.create("Juan", 33);
        System.out.println("employee.name() = " + employee.name());
        System.out.println("employee.age() = " + employee.age());
    }
}
