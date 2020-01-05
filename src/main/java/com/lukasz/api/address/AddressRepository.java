package com.lukasz.api.address;

import com.lukasz.api.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
