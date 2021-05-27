package com.agrosul.customer_payment_control.dto.phone;

import javax.validation.constraints.Size;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.dto.BaseEntityDTO;

import org.springframework.format.annotation.NumberFormat;

public class PhoneDTO extends BaseEntityDTO {
  @NumberFormat(pattern = "##")
  @Size(min = 2, max = 2)
  private String countryCode;
  @NumberFormat(pattern = "##")
  @Size(min = 2, max = 2)
  private String areaCode;
  @Size(min = 8, max = 9)
  private String number;

  public Phone convertDTOToEntity(){
    return new PhoneMapper().toPhone(this);
  }

  public PhoneDTO(){}

  public PhoneDTO(String countryCode, String areaCode, String number) {
    this.countryCode = countryCode;
    this.areaCode = areaCode;
    this.number = number;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "PhoneDTO: +" + this.countryCode + " (" + this.areaCode + ") " + this.number; 
  }
}
