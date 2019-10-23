package com.lukasz;

import com.lukasz.client.Client;
import com.lukasz.client.ClientRepository;
import com.lukasz.employee.Employee;
import com.lukasz.employee.EmployeeRepository;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import com.lukasz.utils.Tariff;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import static com.lukasz.utils.ChargeCalculator.calculateCharge;
import static com.lukasz.utils.ChargeCalculator.calculateTimeSpent;
import static java.lang.Thread.sleep;

@SpringBootApplication
public class SerwisApiApp {
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime parkingData = LocalDateTime.of(2019, 10, 22, 8, 41, 1, 1);
        Tariff tariff = new Tariff(BigDecimal.valueOf(10.29), BigDecimal.valueOf(8.00));

        BigDecimal charge = calculateCharge(parkingData, tariff);
        System.out.println("Parking date: " + parkingData);
        System.out.println("Cena: " + charge);

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
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
                <scope>runtime</scope>
        </dependency>

                <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.199</version>
            <scope>test</scope>
        </dependency>

 */