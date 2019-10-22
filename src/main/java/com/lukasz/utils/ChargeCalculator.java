package com.lukasz.utils;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCalculator {
    private static final int minutesInHour = 60;
    private static final int secondsInMinute = 60;
    private static final int millisInSecond = 1000;
    Date parkingDate = new Date();

    public static BigDecimal calculateCharge(Date parkingDate, Tariff tariff) {
        BigDecimal charge;
        BigDecimal firstHourCharge;
        BigDecimal nextHoursCharge;

        Double periodOnParkingInMillis = calculateTimeSpent(parkingDate);
        int periodOnParkingInHoursCeil = formatFromMillisToHoursCeil(periodOnParkingInMillis);

        if (periodOnParkingInHoursCeil < 1) {
            return BigDecimal.valueOf(0);
        }
        if (periodOnParkingInHoursCeil == 1) {
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
    public static Double calculateTimeSpent(Date parkingDate) {
        Date currentDate = new Date();
        return (Double) (double) (currentDate.getTime() - parkingDate.getTime());
    }

    private static Integer formatFromMillisToHoursCeil(Double timeInMillis) {
        double timeInHours = timeInMillis / (millisInSecond * secondsInMinute * minutesInHour);
        return (Integer) (int) Math.ceil(timeInHours);
    }

}