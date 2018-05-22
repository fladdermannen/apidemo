package ma17.demo;

public class Worker {

    private String id;
    private String name;
    private int age;
    private Department department;

    public Worker(String id, String name, int age, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
