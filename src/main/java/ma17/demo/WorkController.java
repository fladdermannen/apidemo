package ma17.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkController {

    Office office = new Office();

    @RequestMapping(value = "/office" , method = RequestMethod.GET)
    public List<Worker> getWorkers(@RequestParam(value="searchstring", defaultValue = "") String searchString) {

        return office.getWorkers(searchString);
    }

    @RequestMapping(value = "/office" , method = RequestMethod.POST)
    public Worker postWorker(@RequestBody Worker worker) {

        office.addWorker(worker);
        return worker;
    }

    @RequestMapping(value = "/office" , method = RequestMethod.DELETE)
    public List<Worker> deleteWorker(@RequestParam(value="searchstring", defaultValue = "") String searchString) {

        return office.deleteWorker(searchString);
    }



    @RequestMapping(value = "/department" , method = RequestMethod.GET)
    public List<Department> getDepartments(@RequestParam(value="searchstring", defaultValue = "") String searchString) {

        return office.getDepartments(searchString);
    }
    @RequestMapping(value = "/department" , method = RequestMethod.DELETE)
    public List<Department> deleteDepartment(@RequestParam(value="searchstring", defaultValue = "") String searchString) {

        return office.deleteDepartment(searchString);
    }

}