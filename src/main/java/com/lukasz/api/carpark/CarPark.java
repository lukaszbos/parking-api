package com.lukasz.api.carpark;
import com.lukasz.api.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carParkId;
    private String name;

    @JoinColumn(name = "addressId")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    public CarPark(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}