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

  public void setPhoneAttributes(Phone originalPhone, Phone updatedPhone){
    String newCountryCode = updatedPhone.getCountryCode();
    String newAreaCode = updatedPhone.getAreaCode();
    String newNumber = updatedPhone.getNumber();
    
    if (newCountryCode != null)
      originalPhone.setCountryCode(newCountryCode);
    if (newAreaCode != null)
      originalPhone.setAreaCode(newAreaCode);
    if (newNumber != null)
      originalPhone.setNumber(newNumber);
  }

  public Phone updatePhone(Long phoneId, Phone updatedPhone) {{
    Phone phone = phoneRepository.findById(phoneId).orElseThrow(
      () -> new IllegalStateException("Phone " + phoneId.toString() + " do not exists")
    );

    this.setPhoneAttributes(phone, updatedPhone);

    phoneRepository.save(phone);
    
    return phone;
  }}
}
