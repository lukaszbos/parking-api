package com.lukasz.api.parking;
import com.lukasz.api.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingId;
    private String name;

    @JoinColumn(name = "addressId")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    public Parking(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}