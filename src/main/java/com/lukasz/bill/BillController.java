package com.lukasz.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "parkings/{parkingId}/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping()
    public List<Bill> getBill(@RequestParam(name = "parkingId") Integer parkingId) {
        return billService.getBills(parkingId);
    }

    @GetMapping(value = "/{billId}")
    public Bill getBillById(@PathVariable Integer billId) {
        return billService.getBillById(billId);
    }

    @PostMapping()
    public void addBill(@RequestBody Bill bill, @PathVariable Integer parkingId) {
        billService.addBill(bill, parkingId);
    }

    @PutMapping(value = "/{billId}")
    public void updateBill(@RequestBody Bill bill, @PathVariable Integer parkingId, @PathVariable Integer billId) {
        billService.updateBill(bill, parkingId);
    }

    @DeleteMapping(value = "/{billId}")
    public void deleteBill(@PathVariable Integer billId) {
        billService.deleteBill(billId);
    }
}
