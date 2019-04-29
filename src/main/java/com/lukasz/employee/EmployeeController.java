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

    @GetMapping
    public List<Employee> getEmployee(@RequestParam(name = "parkingId") Integer parkingId) {
        return employeeService.getEmployee(parkingId);
    }

    @GetMapping(value = "/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer parkingId, @PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee, @PathVariable Integer parkingId) {
        employeeService.addEmployee(employee, parkingId);
    }

    @PutMapping(value = "/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Integer parkingId, @PathVariable Integer employeeId) {
        employee.setParking(new Parking(parkingId, ""));
        employeeService.updateEmployee(employee, parkingId);
    }

    @DeleteMapping(value = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}