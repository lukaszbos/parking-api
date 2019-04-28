package com.lukasz.course;

import com.lukasz.topic.Topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//to mowi ze bedzie encja w bazie danych z kluczem
/// podstawowym id - oznaczonym nizej
@Entity
public class Course {
    @Id
    private String id;
    private String name;
    private String desription;
    //dzieki temu aplikacja wie tez to nie klucz podstawowy tylko
    //ten drugi
    @ManyToOne
    private Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


    public Course() {
    }

    public Course(String id, String name, String desription, String topicId) {
        super();
        this.id = id;
        this.name = name;
        this.desription = desription;
        this.topic = new Topic(topicId,"","");
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesription() {
        return desription;
    }



}
