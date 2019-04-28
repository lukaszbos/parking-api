package com.lukasz.employee;

import com.lukasz.parking.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/parkings/{parkingId}/employees")
    public List<Employee> getAllEmployees(@PathVariable Integer parkingId) {
        return employeeService.getEmployees(parkingId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/parkings/{parkingId}/employees/{employeeId}")
    public Employee getEmployee(@PathVariable Integer parkingId, @PathVariable Integer employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/parkings/{parkingId}/employees")
    public void addEmployee(@RequestBody Employee employee, @PathVariable Integer parkingId) {
        employee.setParking(new Parking(parkingId, ""));
        employeeService.addEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/parkings/{parkingId}/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Integer parkingId, @PathVariable Integer employeeId) {
        employee.setParking(new Parking(parkingId,""));
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/parkings/{parkingId}/employees/{employeeId}")
    public void deleteCourse(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}