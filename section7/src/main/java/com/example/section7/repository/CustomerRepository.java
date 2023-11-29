package com.example.section7.repository;

import com.example.section7.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByEmailIgnoreCase(String email);

}
