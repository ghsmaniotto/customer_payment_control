package com.agrosul.customer_payment_control.service;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.dto.phone.PhoneDTO;
import com.agrosul.customer_payment_control.dto.phone.PhoneMapper;
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
    return phoneRepository.findById(phoneId).orElseThrow(
      () -> new IllegalStateException("Phone " + phoneId.toString() + " do not exists")
    );
  }

  public Phone createPhone(PhoneDTO dto){
    Phone phone = new PhoneMapper().toPhone(dto);

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

  public Phone updatePhone(Long phoneId, PhoneDTO phoneDTO) {
    Phone phone = this.getPhone(phoneId);

    new PhoneMapper().updatePhoneFromDto(phoneDTO, phone);

    phoneRepository.save(phone);
    
    return phone;
  }

  public Phone deletePhone(Long phoneId){
    Phone phone = this.getPhone(phoneId);

    phoneRepository.delete(phone);

    return phone;
  }
}
