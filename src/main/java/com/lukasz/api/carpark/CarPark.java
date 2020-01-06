package com.lukasz.api.carpark;
import com.lukasz.api.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carParkId;
    private String name;


    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "address_id", referencedColumnName = "id")
    @JoinColumn(name = "addressId")
    @OneToOne()
    private Address address;

    public CarPark(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}