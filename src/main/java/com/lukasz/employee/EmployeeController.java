package com.lukasz.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployee(@RequestParam(name = "parkingId") Long parkingId) {
        return employeeService.getEmployee(parkingId);
    }

    @GetMapping(value = "/{employeeId}")
    /*public Employee getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }*/
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);

    }

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping(value = "/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value = "/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}