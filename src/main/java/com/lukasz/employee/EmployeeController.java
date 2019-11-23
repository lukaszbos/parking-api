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
    /*public List<Employee> getEmployee(@RequestParam(name = "parkingId") Long parkingId) {
        return employeeService.getEmployee(parkingId);
    }*/
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(name = "parkingId") Long parkingId) {
        List<EmployeeDTO> employees = employeeService.getEmployee(parkingId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
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
    /*public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }*/
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTOo) {
        EmployeeDTO employeeDTO = employeeService.addEmployee(employeeDTOo);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTOo, @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updateEmployee(employeeDTOo, employeeId);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}