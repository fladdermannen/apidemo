package ma17.demo;

import java.util.ArrayList;
import java.util.List;

public class Office {

    private ArrayList<Worker> workersList;
    private ArrayList<Department> departmentsList;

    private Department ekonomi = new Department("Ekonomi");
    private Department HR = new Department("HR");
    private Department IT = new Department("IT");

    public Office() {
        this.workersList = new ArrayList<>();
        this.departmentsList = new ArrayList<>();
        addMockWorkers();
    }

    public List<Worker> getWorkers(String searchString) {
        if (searchString.equals(""))
            return workersList;

        ArrayList<Worker> workers = new ArrayList<>();
        for (Worker worker : workersList) {
            if(worker.getName().toLowerCase().contains(searchString.toLowerCase()))
                workers.add(worker);
        }

        return workers;
    }

    public List<Department> getDepartments(String searchString) {
        if (searchString.equals(""))
            return departmentsList;

        ArrayList<Department> departments = new ArrayList<>();
        for (Department department : departmentsList) {
            if(department.getName().toLowerCase().contains(searchString.toLowerCase()))
                departments.add(department);
        }

        return departments;
    }

    public void addWorker(Worker worker) {
        Department d = worker.getDepartment();
        Boolean newDep = true;

        for (Department dep : departmentsList) {
            if(dep.getName().equals(d.getName())) {
                dep.addEmployee();
                worker.setDepartment(dep);
                newDep = false;
            }
        }

        if(newDep) {
            d.addEmployee();
            departmentsList.add(d);
        }
        this.workersList.add(worker);
    }

    public void addMockWorkers() {

        Worker worker = new Worker("1", "Perra", 64, ekonomi);
        workersList.add(worker);
        worker = new Worker("2", "Flerra", 66, HR);
        workersList.add(worker);
        worker = new Worker("3", "Berra", 33, IT);
        workersList.add(worker);

        departmentsList.add(ekonomi);
        departmentsList.add(HR);
        departmentsList.add(IT);
        ekonomi.addEmployee();
        HR.addEmployee();
        IT.addEmployee();
    }

    public List<Worker> deleteWorker(String name) {
        if (name.equals(""))
            return workersList;

        int index = 0;

        for (Worker worker : workersList) {
            if(worker.getName().toLowerCase().equals(name.toLowerCase())) {
                index = workersList.indexOf(worker);
            } else {
                System.out.println("Nope");
            }
        }
        if(index != 0) {
            workersList.get(index).getDepartment().removeEmployee();
            workersList.remove(index);
        }
        return workersList;
    }

    public List<Department> deleteDepartment(String searchString) {
        if (searchString.equals(""))
            return departmentsList;

        int index = 0;

        for(Department dep : departmentsList) {
            if (dep.getName().toLowerCase().equals(searchString.toLowerCase())) {
                index = departmentsList.indexOf(dep);
            }
        }

        if(index!=0) {
            Department d = departmentsList.get(index);
            for(Worker worker : workersList) {
                if(worker.getDepartment().getName().equals(d.getName())) {
                    Department unemployed = new Department("Unemployed!");
                    worker.setDepartment(unemployed);
                    unemployed.addEmployee();
                }
            }
            departmentsList.remove(d);
        }

        return departmentsList;
    }
}
