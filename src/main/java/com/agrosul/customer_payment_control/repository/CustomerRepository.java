package com.agrosul.customer_payment_control.repository;

import com.agrosul.customer_payment_control.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  
}
