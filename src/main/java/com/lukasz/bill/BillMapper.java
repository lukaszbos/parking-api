package com.lukasz.bill;

import org.springframework.stereotype.Component;

@Component
public class BillMapper {
    Bill toModel(BillDto billDTO) {
        return new Bill(billDTO.getNumberOfHours(), billDTO.getCost(), billDTO.getParking(), billDTO.getClient());
    }

    BillDto toDto(Bill bill) {
        return new BillDto(bill.getBillId(), bill.getNumberOfHours(), bill.getCost(), bill.getParking(), bill.getClient());
    }
}
