package com.lukasz.client;

import com.lukasz.employee.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findByParking_ParkingId(Integer parkingId);
}