package com.lukasz.employee;

import com.lukasz.parking.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkings/{parkingId}/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public List<Employee> getAllEmployees(@PathVariable Integer parkingId) {
        return employeeService.getEmployees(parkingId);
    }

    @GetMapping(value = "/{employeeId}")
    public Employee getEmployee(@PathVariable Integer parkingId, @PathVariable Integer employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee, @PathVariable Integer parkingId) {
        employee.setParking(new Parking(parkingId, ""));
        employeeService.addEmployee(employee);
    }

    @PutMapping(value = "/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Integer parkingId, @PathVariable Integer employeeId) {
        employee.setParking(new Parking(parkingId, ""));
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "/{employeeId}")
    public void deleteCourse(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}