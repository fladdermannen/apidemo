package ma17.demo;


import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Worker {

    private String id;
    private String name;
    private long age;
    private Department department;

    public Worker(String id, String name, long age, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public Worker () {

    }

    public Department getDepartment() {
        return department;
    }

    public long getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
            obj.put("id", this.id);
            obj.put("name", this.name);
            obj.put("age", this.age);
            obj.put("department", this.department.toJSON());
        return obj;
    }

    public void write(List<Worker> workers) {
        try {
            PrintWriter writer = new PrintWriter("workers.text", "UTF-8");
            for (Worker w : workers){
                writer.println(w.toJSON().toString());
            }
            writer.close();
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.UnsupportedEncodingException f) {
            f.printStackTrace();
        }
    }
}
