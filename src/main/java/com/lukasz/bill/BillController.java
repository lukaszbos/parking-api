package com.lukasz.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping()
    public List<Bill> getBill(@RequestParam(required = false) Long parkingId, @RequestParam(required = false) UUID clientId) {
        return billService.getBills(parkingId, clientId);
    }

    @GetMapping(value = "/{billId}")
    public Bill getBillById(@PathVariable Long billId) {
        return billService.getBillById(billId);
    }

    @PostMapping()
    public void addBill(@RequestBody Bill bill) {
        billService.addBill(bill);
    }

    @PutMapping(value = "/{billId}")
    public void updateBill(@RequestBody Bill bill) {
        billService.updateBill(bill);
    }

    @DeleteMapping(value = "/{billId}")
    public void deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
    }
}