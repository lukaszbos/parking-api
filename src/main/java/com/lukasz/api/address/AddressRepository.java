package com.lukasz.api.address;

import com.lukasz.api.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
