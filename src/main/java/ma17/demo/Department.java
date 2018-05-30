package ma17.demo;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Department {

    private String name;
    private long employees;

    public Department(String name, long employees) {
        this.name = name;
        this.employees = employees;
    }

    public Department() {

    }

    public String getName() {
        return name;
    }

    public long getEmployees() {
        return employees;
    }

    public void addEmployee() {
        this.employees++;
    }

    public void removeEmployee () {
        this.employees--;
    }

    public JSONObject toJSON() {

        JSONObject obj = new JSONObject();

        obj.put("name", this.name);
        obj.put("employees", this.employees);

        return obj;
    }

    public void write(List<Department> deps) {
        try {
            PrintWriter writer = new PrintWriter("departments.text", "UTF-8");
            for (Department dep : deps){
                writer.println(dep.toJSON().toString());
            }
            writer.close();
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.UnsupportedEncodingException f) {
            f.printStackTrace();
        }
    }
}
