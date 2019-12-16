package com.lukasz.api.employee;

import com.lukasz.api.parking.Parking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long employeeId;
    private String name;
    private String surname;
    private String position;
    private Parking parking;
}
