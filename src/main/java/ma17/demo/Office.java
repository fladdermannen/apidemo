package ma17.demo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Office {

    private ArrayList<Worker> workersList;
    private ArrayList<Department> departmentsList;


    public Office() {
        this.workersList = new ArrayList<>();
        this.departmentsList = new ArrayList<>();
        departmentsList = readDepartments();
        workersList = readWorkers();

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

        d.write(departmentsList);
        worker.write(workersList);
    }


    public List<Worker> deleteWorker(String id) {
        if (id.equals(""))
            return workersList;

        int index = 0;

        for (Worker worker : workersList) {
            if(worker.getId().equals(id)) {
                index = workersList.indexOf(worker);
            } else {
                System.out.println("Nope");
            }
        }
        if(index != 0) {
            workersList.get(index).getDepartment().removeEmployee();
            workersList.remove(index);
            workersList.get(index-1).getDepartment().write(departmentsList);
            workersList.get(0).write(workersList);
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
                    Department unemployed = new Department("Unemployed!", 0);
                    worker.setDepartment(unemployed);
                    unemployed.addEmployee();
                }
            }
            departmentsList.remove(d);
            departmentsList.get(index).write(departmentsList);
            workersList.get(0).write(workersList);
        }


        return departmentsList;
    }

    public ArrayList<Worker> readWorkers() {
        JSONParser parser = new JSONParser();

        ArrayList<JSONObject> jsons = new ArrayList<>();
        String line = null;
        JSONObject jsonObject;
        ArrayList<Worker> workers = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader("workers.text");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                jsonObject = (JSONObject) parser.parse(line);

                jsons.add(jsonObject);
            }
            bufferedReader.close();

            for(JSONObject json : jsons) {
                String id = (String) json.get("id");
                String name = (String) json.get("name");
                long age = (long) json.get("age");
                JSONObject dep = (JSONObject) json.get("department");

                String depName = (String) dep.get("name");
                Department department = null;

                for(Department depart : departmentsList) {
                    if(depart.getName().equals(depName)) {
                        department = depart;
                    }
                }

                Worker worker = new Worker(id, name, age, department);
                workers.add(worker);
            }

            System.out.println(workers);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }

        return workers;

    }

    public ArrayList<Department> readDepartments() {
        JSONParser parser = new JSONParser();

        ArrayList<JSONObject> jsons = new ArrayList<>();
        String line = null;
        JSONObject jsonObject;
        ArrayList<Department> deps = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader("departments.text");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                jsonObject = (JSONObject) parser.parse(line);

                jsons.add(jsonObject);
            }
            bufferedReader.close();

            for(JSONObject json : jsons) {
                String name = (String) json.get("name");
                long employees = (long) json.get("employees");

                Department department = new Department(name, employees);
                deps.add(department);
            }

        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }

        return deps;

    }

}
