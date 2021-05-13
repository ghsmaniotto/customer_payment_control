package com.agrosul.customer_payment_control.domain;

public class Phone extends BaseEntity {
  private String countryCode;
  private String areaCode;
  private String number;

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
