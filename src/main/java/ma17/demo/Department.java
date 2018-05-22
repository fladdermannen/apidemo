package ma17.demo;

import java.util.ArrayList;

public class Department {

    private String name;
    ArrayList<Worker> workers = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }
}
