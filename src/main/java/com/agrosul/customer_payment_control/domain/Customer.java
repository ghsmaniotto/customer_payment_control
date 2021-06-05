package com.agrosul.customer_payment_control.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
  private String firstName;
  private String lastName;
  private String email;
  private String cpf;

  @OneToMany(mappedBy = "owner")
  private List<Phone> phones;

  public Customer() {
  }

  public Customer(String firstName, String lastName, String email, String cpf) {
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

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }
}
