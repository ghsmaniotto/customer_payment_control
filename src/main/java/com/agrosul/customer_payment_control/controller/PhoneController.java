package com.agrosul.customer_payment_control.controller;

import java.util.List;

import javax.validation.Valid;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.dto.phone.PhoneDTO;
import com.agrosul.customer_payment_control.service.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public Phone addPhone(@Valid @RequestBody PhoneDTO dto) {
      return phoneService.createPhone(dto);
  }

  @PutMapping(path="{phoneId}")
  public Phone updatePhone(
    @PathVariable("phoneId") Long phoneId, @Valid @RequestBody PhoneDTO dto
  ) {
    return phoneService.updatePhone(phoneId, dto);
  }

  @DeleteMapping(path="{phoneId}")
  public Phone deletePhone(@PathVariable("phoneId") Long phoneId) {
    return phoneService.deletePhone(phoneId);
  }
}
