package com.agrosul.customer_payment_control.dto.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.agrosul.customer_payment_control.dto.BaseEntityDTO;

import org.hibernate.validator.constraints.br.CPF;

public class CustomerDTO extends BaseEntityDTO {
  @Size(min = 2, max = 56)
  private String firstName;
  @Size(min = 2, max = 56)
  private String lastName;
  @Email
  private String email;
  @CPF
  private String cpf;

  public CustomerDTO(){}

  public CustomerDTO(String firstName, String lastName, String email, String cpf) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.cpf = cpf;
  }
  
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
