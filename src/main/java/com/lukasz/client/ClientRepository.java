package com.lukasz.client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}