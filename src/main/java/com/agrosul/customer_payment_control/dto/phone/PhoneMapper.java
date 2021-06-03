package com.agrosul.customer_payment_control.dto.phone;

import com.agrosul.customer_payment_control.domain.Phone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PhoneMapper {
  public PhoneMapper(){
    // Empty constructor
  }

  public String toString(final Object obj) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(obj);
  }

  public Phone toPhone(PhoneDTO phoneDTO){
    Phone phone = new Phone(
      phoneDTO.getCountryCode(),
      phoneDTO.getAreaCode(),
      phoneDTO.getNumber()
    );
    phone.setId(phoneDTO.getId());
    return phone;
  }

  public PhoneDTO fromPhone(Phone phone){
    PhoneDTO dto = new PhoneDTO(
      phone.getCountryCode(),
      phone.getAreaCode(),
      phone.getNumber()
    );
    dto.setId(phone.getId());
    return dto;
  }

  public void updatePhoneFromDto(PhoneDTO dto, Phone entity){
    if(dto.getId() != null) entity.setId(dto.getId());
    if (dto.getCountryCode() != null) 
      entity.setCountryCode(dto.getCountryCode());
    if (dto.getAreaCode() != null) entity.setAreaCode(dto.getAreaCode());
    if (dto.getNumber() != null) entity.setNumber(dto.getNumber());
  }
}
