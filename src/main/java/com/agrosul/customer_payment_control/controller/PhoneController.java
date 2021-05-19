package com.agrosul.customer_payment_control.controller;

import java.util.List;

import javax.validation.Valid;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.service.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/phones")
public class PhoneController {
  
  @Autowired
  private PhoneService phoneService;

  @GetMapping
  public List<Phone> getPhones() {
    return phoneService.getPhones();
  }

  @GetMapping(path="{phoneId}")
  public Phone getPhone(@PathVariable("phoneId") Long phoneId) {
    return phoneService.getPhone(phoneId);
  }

  @PostMapping
  public Phone addPhone(@Valid @RequestBody Phone phone) {
      return phoneService.createPhone(phone);
  }
}
