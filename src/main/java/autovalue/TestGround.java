package autovalue;

public class TestGround {

    public static void main(String[] args) {
        EmployeeWithAutoValue employee = EmployeeWithAutoValue.create("Juan", 33);
        System.out.println("employee.getName() = " + employee.getName());
        System.out.println("employee.getAge() = " + employee.getAge());
    }
}
