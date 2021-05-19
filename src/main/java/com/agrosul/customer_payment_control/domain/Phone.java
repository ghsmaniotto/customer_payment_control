package com.agrosul.customer_payment_control.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "phones")
public class Phone extends BaseEntity {
  @NotNull(message = "Country code must exists")
  @Length(min = 2, max = 2)
  @Column(name = "country_code", nullable = false, length = 2)
  private String countryCode;

  @NotNull(message = "Area code must exists")
  @Length(min = 2, max = 2)
  @Column(name = "area_code", nullable = false, length = 2)
  private String areaCode;

  @NotNull(message = "Number must exists")
  @Length(min = 8, max = 9)
  @Column(name = "number", nullable = false, length = 9)
  private String number;

  public Phone(){}

  public Phone(String countryCode, String areaCode, String number) {
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
}
