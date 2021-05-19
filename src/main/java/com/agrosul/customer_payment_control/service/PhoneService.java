package com.agrosul.customer_payment_control.service;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.repository.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
  @Autowired
  private PhoneRepository phoneRepository;

  public List<Phone> getPhones() {
    return phoneRepository.findAll();
  }

  public Phone getPhone(Long phoneId){
    Phone phone = phoneRepository.findById(phoneId).orElseThrow(
      () -> new IllegalStateException("Phone " + phoneId.toString() + " do not exists")
    );
    
    return phone;
  }

  public Phone createPhone(Phone phone){
    return phoneRepository.save(phone);
  }
}
