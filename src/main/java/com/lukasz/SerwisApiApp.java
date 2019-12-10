package com.lukasz;
//TODO https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3

import com.lukasz.client.Client;
import com.lukasz.client.ClientDto;
import com.lukasz.client.ClientRepository;
import com.lukasz.client.ClientService;
import com.lukasz.employee.Employee;
import com.lukasz.employee.EmployeeRepository;
import com.lukasz.parking.Parking;
import com.lukasz.parking.ParkingRepository;
import com.lukasz.utils.ChargeCalculator;
import com.lukasz.utils.Tariff;
import jdk.nashorn.internal.codegen.ClassEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.lukasz.utils.ChargeCalculator.calculateCharge;
import static com.lukasz.utils.ChargeCalculator.calculateTimeSpent;
import static com.lukasz.utils.ChargeCalculator.formatFromMillisToHoursCeil;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lukasz"})
public class SerwisApiApp {
    @Autowired
    private ClientService clientService;


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SerwisApiApp.class, args);

        //LocalDateTime parkingData = LocalDateTime.of(2019, 10, 22, 8, 41, 1, 1);
        //ROK-MIESIAC-DZIEN-- T --GODZINA-MINUTA-SEKUNDA-MILISEKUNDA
        LocalDateTime parkingData = LocalDateTime.parse("2019-12-08T21:21:00.492");
        Tariff tariff = new Tariff(BigDecimal.valueOf(10.29), BigDecimal.valueOf(8.00));

        //BigDecimal charge = calculateCharge(parkingData, tariff);

        LocalDateTime test = LocalDateTime.parse("2019-07-15T01:09:44.492");
        //BigDecimal charge2 = calculateCharge(test, tariff);
    }

    @Bean
    ApplicationRunner applicationRunner(ParkingRepository parkingRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        Parking parking = new Parking((long) 1, "parking");
        return args -> {
            parkingRepository.save(parking);

            String uuidValue = "8e93ab4a-98f5-4097-8c91-9bd71cb2c688";
            UUID uuidd = UUID.fromString(uuidValue);
            Client mockClient = new Client("Jan", "Gora", "janek@gmail.com");
            mockClient.setClientId(uuidd);
            ClientDto mockClientDto = new ClientDto(uuidd,"Jan", "Gora", "janek@gmail.com");
            clientService.updateClient(mockClientDto, uuidd);
            clientRepository.save(mockClient);
            employeeRepository.save(new Employee((long) 1, "Henryk", "Topor", "parkingowy", parking));
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



*/

/*

                <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.199</version>
            <scope>test</scope>
        </dependency>

# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2

# Datasource
spring.datasource.url=jdbc:h2:file:~/test
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
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