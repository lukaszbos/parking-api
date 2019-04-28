package com.lukasz.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(Integer parkingId) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByParking_ParkingId(parkingId).forEach(employees::add);
        return employees;
    }

/*
    public Resources<Resource<Employee>> getLocations() {

        List<Resource<Employee>> locations = employeeRepository.getOne(Id).getLocations().stream()
                .map(locationResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(locations);
    }
    */

    public Employee getEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
