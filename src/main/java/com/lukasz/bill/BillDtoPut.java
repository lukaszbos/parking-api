package com.lukasz.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDtoPut {
    private Long billId;
    private LocalDateTime leftParkingAt;
}
