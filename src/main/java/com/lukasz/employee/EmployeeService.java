package com.lukasz.employee;

import com.lukasz.exception.NotFoundException;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
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

    List<EmployeeDTO> getEmployee(Long parkingId) {
        if (isIdSent(parkingId)) {
            return getEmployeesWorkingOnParking(parkingId);
        } else
            return getAllEmployees();
    }

    private boolean isIdSent(Long parkingId) {
        return parkingId != null;
    }

    private List<EmployeeDTO> getEmployeesWorkingOnParking(Long parkingId) {

        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByParking_ParkingId(parkingId).forEach(employee -> employees.add(employee));

        return employees
                .stream()
                .map(employee -> employeeMapper.toDTO(employee))
                .collect(Collectors.toList());
    }

    private List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toModel(employeeDTO);
        employee.setParking(getParkingById(employeeDTO.getParking().getParkingId()));
        Employee addedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(addedEmployee);
    }

    private Parking getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId).orElseThrow(() -> new NotFoundException("Parking not Found :D :D :D"));
    }

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long employeeId) {
        Employee employee = employeeMapper.toModel(employeeDTO);
        employee.setEmployeeId(employeeId);
        employee.setParking(getParkingById(employeeDTO.getParking().getParkingId()));
        Employee addedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(addedEmployee);
    }

    EmployeeDTO deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        employeeRepository.deleteById(employeeId);
        return employeeMapper.toDTO(employee);
    }
}