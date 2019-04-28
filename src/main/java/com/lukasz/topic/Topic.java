package com.lukasz.topic;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Topic {
    @Id
    private String id;
    private String name;
    private String desription;


    public Topic() {
    }

    public Topic(String id, String name, String desription) {
        this.id = id;
        this.name = name;
        this.desription = desription;
    }


}
