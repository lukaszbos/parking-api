package com.lukasz.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toModel(EmployeeDTO employeeDTO){
        return new Employee(employeeDTO.getName(),employeeDTO.getSurname(),
                            employeeDTO.getPosition(), employeeDTO.getParking());
    }

    public EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getSurname(),
                               employee.getPosition(), employee.getParking());
    }

}