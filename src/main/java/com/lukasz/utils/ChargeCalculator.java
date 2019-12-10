package com.lukasz.utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;


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
        long periodOnParkingInMillis = calculateTimeSpent(parkingDate, leftParkingDate);
        log.log(INFO, "time spend in millis = " + periodOnParkingInMillis);
        int periodOnParkingInHoursCeil = formatFromMillisToHoursCeil(periodOnParkingInMillis);
        log.log(INFO, "time spend in h = " + periodOnParkingInHoursCeil);
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

    //TODO  czas zaparkowania jest zawsze stały i to możemy odbierać z backandu przy restarcie appki
    //Tue Oct 22 17:00:39 CEST 2019 - format daty
    public static long calculateTimeSpent(LocalDateTime parkingDate, LocalDateTime leftParkingDate) {
        //LocalDateTime currentDate = LocalDateTime.now();
        return Duration.between(parkingDate, leftParkingDate).toMillis();
    }

    public static Integer formatFromMillisToHoursCeil(long timeInMillis) {
        double timeInHours = (double) timeInMillis / (millisInSecond * secondsInMinute * minutesInHour);
        return (Integer) (int) Math.ceil(timeInHours);
    }

}