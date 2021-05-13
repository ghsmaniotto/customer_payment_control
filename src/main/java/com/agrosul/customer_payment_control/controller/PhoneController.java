package com.agrosul.customer_payment_control.controller;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.service.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/phones")
public class PhoneController {
  
  @Autowired
  private PhoneService phoneService;

  @GetMapping
  public List<Phone> getPhones() {
    return phoneService.getPhones();
  }
}
