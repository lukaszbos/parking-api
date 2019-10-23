package com.lukasz.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.UUID;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
    @GeneratedValue(generator = “UUID”)
	@GenericGenerator(
		name = “UUID”,
		strategy = “org.hibernate.id.UUIDGenerator”,
	)

     */
    private Integer clientId;
    private String name;
    private String surname;

    public Client( Integer clientId, String name, String surname) {
       // this.clientId = clientId;
        //this.name = name;
        //this.surname = surname;
    }
}