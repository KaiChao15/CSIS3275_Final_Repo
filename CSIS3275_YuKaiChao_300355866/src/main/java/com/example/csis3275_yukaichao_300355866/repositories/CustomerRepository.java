package com.example.csis3275_yukaichao_300355866.repositories;

import com.example.csis3275_yukaichao_300355866.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
