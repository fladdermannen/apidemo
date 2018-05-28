package ma17.demo;

import java.util.ArrayList;

public class Department {

    private String name;
    private int employees;

    public Department(String name) {
        this.name = name;
        this.employees = 0;
    }

    public Department() {

    }

    public String getName() {
        return name;
    }

    public int getEmployees() {
        return employees;
    }

    public void addEmployee() {
        this.employees++;
    }

    public void removeEmployee () {
        this.employees--;
    }
}
