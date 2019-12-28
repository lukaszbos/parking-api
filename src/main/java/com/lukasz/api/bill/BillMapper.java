package com.lukasz.api.bill;

import com.lukasz.api.utils.Tariff;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BillMapper {
    Bill toModel(BillDto billDTO) {

        // LocalDateTime parkingDate = billDTO.getEnteredParkingAt();
        // LocalDateTime leftParkingDate = billDTO.getLeftParkingAt();
        //Tariff tariff = new Tariff(BigDecimal.valueOf(10.29), BigDecimal.valueOf(8.00));

        //BigDecimal cost = calculateCharge(parkingDate, leftParkingDate, tariff);

        return new Bill(billDTO.getNumberOfHours(), billDTO.getCost(), billDTO.getClient());
    }

    /*
        BillDto toDto(Bill bill) {
            return new BillDto(bill.getBillId(), bill.getNumberOfHours(), bill.getCost(), bill.getParking(), bill.getClient());
        }
    */
    public BillDto toDto(Bill bill) {
        return new BillDto(bill.getBillId(),bill.getNumberOfHours(),bill.getCost(), bill.getClient());
    }


}

