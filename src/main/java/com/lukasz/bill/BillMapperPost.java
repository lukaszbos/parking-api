package com.lukasz.bill;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BillMapperPost {
    Bill toModel(BillDtoPost billDtoPost) {
       return new Bill();
        // return new Bill(billDtoPost.getBillId(),0, BigDecimal.valueOf(0),
         //       billDtoPost.getEnteredParkingAt(), null, billDtoPost.getParking(), billDtoPost.getClient());
    }

    BillDtoPost toDto(Bill bill) {
        //return new BillDtoPost(bill.getBillId(), bill.getNumberOfHours(), bill.getCost(), bill.getParking(), bill.getClient());
        return new BillDtoPost(bill.getBillId(), bill.getParking(), bill.getClient(), null);
    }
}
