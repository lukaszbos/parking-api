package com.lukasz;

import com.lukasz.client.Client;
import com.lukasz.client.ClientRepository;
import com.lukasz.employee.Employee;
import com.lukasz.employee.EmployeeRepository;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SerwisApiApp {
    public static void main(String[] args) {
        SpringApplication.run(SerwisApiApp.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ParkingRepository parkingRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        Parking parking = new Parking(1, "parking");
        return args -> {
            parkingRepository.save(parking);
            clientRepository.save(new Client(1, "Jan", "Gora"));
            clientRepository.save(new Client(2, "Zbigniew", "Jankowski"));
            employeeRepository.save(new Employee(1, "Henryk", "Topor", "parkingowy", parking));
        };
    }
}
/*


 */
