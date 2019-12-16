package com.lukasz.api.employee;

import com.lukasz.api.exception.NotFoundException;
import com.lukasz.api.parking.Parking;
import com.lukasz.api.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ParkingRepository parkingRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ParkingRepository parkingRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.parkingRepository = parkingRepository;
        this.employeeMapper = employeeMapper;
    }

    List<EmployeeDto> getEmployee(Long parkingId) {
        if (isIdSent(parkingId)) {
            return getEmployeesWorkingOnParking(parkingId);
        } else
            return getAllEmployees();
    }

    private boolean isIdSent(Long parkingId) {
        return parkingId != null;
    }

    private List<EmployeeDto> getEmployeesWorkingOnParking(Long parkingId) {

        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByParking_ParkingId(parkingId).forEach(employee -> employees.add(employee));

        return employees
                .stream()
                .map(employee -> employeeMapper.toDto(employee))
                .collect(Collectors.toList());
    }

    private List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toModel(employeeDto);
        employee.setParking(getParkingById(employeeDto.getParking().getParkingId()));
        Employee addedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(addedEmployee);
    }

    private Parking getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
    }

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
        Employee employee = employeeMapper.toModel(employeeDto);
        employee.setEmployeeId(employeeId);
        employee.setParking(getParkingById(employeeDto.getParking().getParkingId()));
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(updatedEmployee);
    }

    EmployeeDto deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        employeeRepository.deleteById(employeeId);
        return employeeMapper.toDto(employee);
    }
}