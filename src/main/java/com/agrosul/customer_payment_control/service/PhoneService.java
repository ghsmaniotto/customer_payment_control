package com.agrosul.customer_payment_control.service;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Phone;

import org.springframework.stereotype.Service;

@Service
public class PhoneService {
  public List<Phone> getPhones() {
    return List.of(new Phone("55", "51", "997008689"));
  }
}
