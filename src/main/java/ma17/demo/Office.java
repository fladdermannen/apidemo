package ma17.demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Office {

    ArrayList<Worker> workersList;
    ArrayList<Department> departmentsList;

    public Office() {
        this.workersList = new ArrayList<>();
        this.departmentsList = new ArrayList<>();
        addMockWorkers();
    }

    public List<Worker> getWorkers(String searchString) {
        if (searchString == "")
            return workersList;

        ArrayList<Worker> workers = new ArrayList<>();
        for (Worker worker : workersList) {
            if(worker.getName().contains(searchString))
                workers.add(worker);
        }

        return workers;
    }

    public List<Department> getDepartments(String searchString) {
        if (searchString == "")
            return departmentsList;

        ArrayList<Department> departments = new ArrayList<>();
        for (Department department : departmentsList) {
            if(department.getName().contains(searchString))
                departments.add(department);
        }

        return departments;
    }

    public void addWorker(Worker worker) {
        this.workersList.add(worker);
    }

    public void addMockWorkers() {
        Department ekonomi = new Department("Ekonomi");
        Department HR = new Department("HR");
        Department IT = new Department("IT");

        Worker worker = new Worker("1", "Perra", 64, ekonomi);
        workersList.add(worker);
        ekonomi.addWorker(worker);
        worker = new Worker("2", "Flerra", 66, HR);
        workersList.add(worker);
        HR.addWorker(worker);
        worker = new Worker("3", "Berra", 33, IT);
        workersList.add(worker);
        IT.addWorker(worker);

        departmentsList.add(ekonomi);
        departmentsList.add(HR);
        departmentsList.add(IT);

    }
}
