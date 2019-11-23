package com.lukasz.bill;

import org.springframework.stereotype.Component;

@Component
public class BillMapper {
    Bill toModel(BillDTO billDTO){
        return new Bill(billDTO.getNumberOfHours(), billDTO.getCost(), billDTO.getParking(), billDTO.getClient());
    }

    BillDTO toDTO(Bill bill){
        return new BillDTO(bill.getBillId(), bill.getNumberOfHours(), bill.getCost(), bill.getParking(), bill.getClient());
    }
}
