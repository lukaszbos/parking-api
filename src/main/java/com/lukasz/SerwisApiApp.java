package com.lukasz;
//TODO https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3

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
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static com.lukasz.utils.ChargeCalculator.calculateCharge;
import static com.lukasz.utils.ChargeCalculator.calculateTimeSpent;
import static java.lang.Thread.sleep;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lukasz"})
public class SerwisApiApp {
    public static void main(String[] args) throws InterruptedException {
      /*  LocalDateTime parkingData = LocalDateTime.of(2019, 10, 22, 8, 41, 1, 1);
        Tariff tariff = new Tariff(BigDecimal.valueOf(10.29), BigDecimal.valueOf(8.00));

        BigDecimal charge = calculateCharge(parkingData, tariff);
        System.out.println("Parking date: " + parkingData);
        System.out.println("Cena: " + charge);
       */
         SpringApplication.run(SerwisApiApp.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ParkingRepository parkingRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        Parking parking = new Parking(1, "parking");
        return args -> {
            parkingRepository.save(parking);
            //String uuidValue = "c237bec1-19ef-4858-a98e-521cf0aad4c0";
            //UUID uuid = UUID.fromString(uuidValue);
            //clientRepository.save(new Client(uuid, "Jan", "Gora"));
            //clientRepository.save(new Client( "Zbigniew", "Jankowski"));
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

spring.datasource.url=jdbc:postgresql://ec2-54-225-106-93.compute-1.amazonaws.com:5432/d3eer22abovarl
spring.datasource.username=lfdpcfowgtvtpg
spring.datasource.password=2d06efe6ebb452f206b62c3a950f82e1a0ae0fd93320b0ad70f4447ad7c05788

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update




                <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.199</version>
            <scope>test</scope>
        </dependency>

 */

/*
spring.datasource.url=jdbc:mysql://localhost:3306/stgdb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=validate
spring.jpa.open-in-view=false

 */