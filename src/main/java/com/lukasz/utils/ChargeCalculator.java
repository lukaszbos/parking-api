package com.lukasz.utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class ChargeCalculator {
    private static final int minutesInHour = 60;
    private static final int secondsInMinute = 60;
    private static final int millisInSecond = 1000;

    public static BigDecimal calculateCharge(LocalDateTime parkingDate, Tariff tariff) {
        BigDecimal charge;
        BigDecimal firstHourCharge;
        BigDecimal nextHoursCharge;

        long periodOnParkingInMillis = calculateTimeSpent(parkingDate);
        System.out.println("time spend przed zaokrogleniem " + periodOnParkingInMillis);
        int periodOnParkingInHoursCeil = formatFromMillisToHoursCeil(periodOnParkingInMillis);
        System.out.println("time spend po zaokrogleniu " + periodOnParkingInHoursCeil);

        if (periodOnParkingInHoursCeil <= 1) {
            firstHourCharge = tariff.getCostOfFirstHour().multiply(BigDecimal.valueOf(periodOnParkingInHoursCeil));
            charge = firstHourCharge;
        } else {
            firstHourCharge = tariff.getCostOfFirstHour();
            nextHoursCharge = tariff.getCostOfNextHour().multiply(BigDecimal.valueOf(periodOnParkingInHoursCeil - 1));
            charge = firstHourCharge.add(nextHoursCharge);
        }
        return charge;
    }

    //TODO  czas zaparkowania jest zawsze stały i to możemy odbierać z backandu przy restarcie appki
    //Tue Oct 22 17:00:39 CEST 2019 - format daty
    public static long calculateTimeSpent(LocalDateTime parkingDate) {
        LocalDateTime currentDate = LocalDateTime.now();
        return Duration.between(parkingDate, currentDate).toMillis();
    }

    private static Integer formatFromMillisToHoursCeil(long timeInMillis) {
        double timeInHours = (double) timeInMillis / (millisInSecond * secondsInMinute * minutesInHour);
        return (Integer) (int) Math.ceil(timeInHours);
    }

}