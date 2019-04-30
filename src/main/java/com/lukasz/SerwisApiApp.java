package com.lukasz;

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
    ApplicationRunner applicationRunner(ParkingRepository parkingRepository) {
        return args -> {
            parkingRepository.save(new Parking(1, "parking"));
        };
    }

}
