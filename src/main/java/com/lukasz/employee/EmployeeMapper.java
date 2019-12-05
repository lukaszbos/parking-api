package com.lukasz.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toModel(EmployeeDto employeeDTO){
        return new Employee(employeeDTO.getName(),employeeDTO.getSurname(),
                            employeeDTO.getPosition(), employeeDTO.getParking());
    }

    public EmployeeDto toDto(Employee employee){
        return new EmployeeDto(employee.getEmployeeId(), employee.getName(), employee.getSurname(),
                               employee.getPosition(), employee.getParking());
    }

}