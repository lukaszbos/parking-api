package com.lukasz.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,String> {

    // Crud repository domysli sie na podstawie nazwy
    // i argumentow co ma zrobic mimo ze nie ma takiej

    //np tutaj wysykamy wszystkie kursy z topika
    // po id topika
    public List<Course> findByTopicId(String topicId);

}
