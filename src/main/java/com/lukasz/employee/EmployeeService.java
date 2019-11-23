package com.lukasz.employee;

import com.lukasz.exception.NotFoundException;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    List<Employee> getEmployee(Long parkingId) {
        if (isIdSent(parkingId)) {
            return getEmployeesWorkingOnParking(parkingId);
        } else
            return getAllEmployees();
    }

    private boolean isIdSent(Long parkingId) {
        return parkingId != null;
    }

    private List<Employee> getEmployeesWorkingOnParking(Long parkingId) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByParking_ParkingId(parkingId).forEach(employees::add);
        return employees;
    }

    private List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    void addEmployee(Employee employee) {
        assembleTheEmployee(employee);
        employeeRepository.save(employee);
    }

    private void assembleTheEmployee(Employee employee) {
        Parking parkingOfEmployee = assembleParking(employee);
        employee.setParking(parkingOfEmployee);
    }

    private Parking assembleParking(Employee employee) {
        Long parkingIdOfEmployee = employee.getParking().getParkingId();
        String parkingNameOfEmployee = employee.getParking().getName();

        if (isThisParkingInMyRepo(parkingIdOfEmployee))
            return getParkingById(parkingIdOfEmployee);
        else
            return new Parking(parkingIdOfEmployee, parkingNameOfEmployee);
    }

    private boolean isThisParkingInMyRepo(Long parkingIdFromEmployee) {
        return parkingRepository.existsById(parkingIdFromEmployee);
    }

    private Parking getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId).get();
    }

    void updateEmployee(Employee employee) {
        assembleTheEmployee(employee);
        employeeRepository.save(employee);
    }

    void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}