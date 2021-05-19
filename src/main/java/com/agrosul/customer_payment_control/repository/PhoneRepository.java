package com.agrosul.customer_payment_control.repository;

import com.agrosul.customer_payment_control.domain.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}