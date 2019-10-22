package com.lukasz.client;

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

    public Client(int clientId, String name, String surname) {

    }
}