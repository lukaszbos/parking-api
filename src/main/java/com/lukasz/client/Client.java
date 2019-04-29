package com.lukasz.client;

import com.lukasz.bill.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer clientId;
    private String name;
    private String surname;

    @JoinColumn(name = "billId")
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Bill bill;

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