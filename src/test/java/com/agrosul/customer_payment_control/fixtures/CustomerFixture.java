package com.agrosul.customer_payment_control.fixtures;

import java.util.ArrayList;
import java.util.List;

import com.agrosul.customer_payment_control.domain.Customer;
import com.agrosul.customer_payment_control.dto.customer.CustomerDTO;
import com.github.javafaker.Faker;

public class CustomerFixture {

  private Faker faker = new Faker();

  private static final List<String> cpfList = new ArrayList<String>();
  private static Integer cpfListIndex = 0;
  
  static {
    List.of(
      "40501993002", "41004716028", "50273324080", "88076576008",
      "75400310001", "58738924072", "11164521039", "15624782063",
      "00811315002", "85367674054", "10357214064", "23383926044"
    ).forEach(cpf -> cpfList.add(cpf));
  }

  public CustomerDTO factoryCustomerDTO(Long id) {
    CustomerDTO customerDTO = new CustomerDTO(
      faker.name().firstName(), 
      faker.name().lastName(), 
      faker.internet().safeEmailAddress(),
      CustomerFixture.cpfList.get(cpfListIndex)
    );
    customerDTO.setId(id);

    CustomerFixture.cpfListIndex++;

    return customerDTO;
  }

  public Customer factoryCustomer(Long id) {
    Customer customer = new Customer(
      faker.name().firstName(), 
      faker.name().lastName(), 
      faker.internet().safeEmailAddress(),
      CustomerFixture.cpfList.get(cpfListIndex)
    );

    customer.setId(id);

    CustomerFixture.cpfListIndex++;

    return customer;
  }

  public List<Customer> factoryCustomers() {
    return List.of(
      this.factoryCustomer(1L), this.factoryCustomer(2L)
    );
  }
  
}