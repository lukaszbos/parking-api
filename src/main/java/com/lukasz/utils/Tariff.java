package com.lukasz.utils;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;


public class Tariff {
    private BigDecimal costOfFirstHour;
    private BigDecimal costOfNextHour;

    public Tariff(BigDecimal costOfFirstHour, BigDecimal costOfNextHour) {
        this.costOfFirstHour = costOfFirstHour;
        this.costOfNextHour = costOfNextHour;
    }

    public BigDecimal getCostOfFirstHour() {
        return costOfFirstHour;
    }

    public void setCostOfFirstHour(BigDecimal costOfFirstHour) {
        this.costOfFirstHour = costOfFirstHour;
    }

    public BigDecimal getCostOfNextHour() {
        return costOfNextHour;
    }

    public void setCostOfNextHour(BigDecimal costOfNextHour) {
        this.costOfNextHour = costOfNextHour;
    }
}
