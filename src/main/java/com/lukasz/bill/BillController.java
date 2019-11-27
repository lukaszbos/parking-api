package com.lukasz.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/bills")
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping()
    ResponseEntity<List<BillDTO>> getBill(@RequestParam(required = false) Long parkingId, @RequestParam(required = false) UUID clientId) {
        List<BillDTO> billDTOS = billService.getBills(parkingId, clientId);
        return new ResponseEntity<>(billDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{billId}")
    ResponseEntity<BillDTO> getBillById(@PathVariable Long billId) {
        BillDTO billDTO = billService.getBillById(billId);
        return new ResponseEntity<>(billDTO, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<BillDTO> addBill(@RequestBody BillDTO billDTO) {
        BillDTO billDTO1 = billService.addBill(billDTO);
        return new ResponseEntity<>(billDTO1, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{billId}")
    ResponseEntity<BillDTO> updateBill(@RequestBody BillDTO billDTO, @PathVariable Long billId) {
        BillDTO billDTO1 = billService.updateBill(billDTO, billId);
        return new ResponseEntity<>(billDTO1, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{billId}")
    ResponseEntity<BillDTO> deleteBill(@PathVariable Long billId) {
        BillDTO billDTO = billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}