package com.lukasz.client;


import com.lukasz.parking.Parking;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {
    @GeneratedValue
    @Id
    private Integer clientId;

    @Column
    private String name;

    @Column
    private String surname;

    @JoinColumn(name = "parkingId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Parking parking;

    //@OneToMany(mappedBy = "client")
    //private List<Bill> bills;

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + clientId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}