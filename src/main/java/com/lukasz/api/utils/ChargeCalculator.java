package com.lukasz.api.utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;


public class ChargeCalculator {
    LocalDateTime parkingDate;
    Tariff tariff;

    public ChargeCalculator(LocalDateTime parkingDate, Tariff tariff){
        this.parkingDate=parkingDate;
        this.tariff=tariff;
    }

    private static final int minutesInHour = 60;
    private static final int secondsInMinute = 60;
    private static final int millisInSecond = 1000;

    protected static final Logger log = Logger.getLogger(ChargeCalculator.class.getName());

    public static BigDecimal calculateCharge(LocalDateTime parkingDate, LocalDateTime leftParkingDate, Tariff tariff) {
        BigDecimal charge;
        BigDecimal firstHourCharge;
        BigDecimal nextHoursCharge;

        log.log(INFO, "Data i czas zaparkowania pojazdu: " + parkingDate);
        int periodOnParkingInHoursCeil = calculateTimeSpent(parkingDate, leftParkingDate);
        log.log(INFO, "time spend in millis = " + periodOnParkingInHoursCeil);
        if (periodOnParkingInHoursCeil <= 1) {
            firstHourCharge = tariff.getCostOfFirstHour().multiply(BigDecimal.valueOf(periodOnParkingInHoursCeil));
            charge = firstHourCharge;
        } else {
            firstHourCharge = tariff.getCostOfFirstHour();
            nextHoursCharge = tariff.getCostOfNextHour().multiply(BigDecimal.valueOf(periodOnParkingInHoursCeil - 1));
            charge = firstHourCharge.add(nextHoursCharge);
        }
        log.log(INFO, "Opłata = " + charge);
        return charge;
    }

    //Tue Oct 22 17:00:39 CEST 2019 - format daty
    public static int calculateTimeSpent(LocalDateTime parkingDate, LocalDateTime leftParkingDate) {
        //LocalDateTime currentDate = LocalDateTime.now();
        long duration = Duration.between(parkingDate, leftParkingDate).toMillis();
        return formatFromMillisToHoursCeil(duration);
    }

    public static Integer formatFromMillisToHoursCeil(long timeInMillis) {
        double timeInHours = (double) timeInMillis / (millisInSecond * secondsInMinute * minutesInHour);
        return (Integer) (int) Math.ceil(timeInHours);
    }

}