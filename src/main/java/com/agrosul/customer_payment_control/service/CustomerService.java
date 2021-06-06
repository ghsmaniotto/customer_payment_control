package com.agrosul.customer_payment_control.service;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Customer;
import com.agrosul.customer_payment_control.dto.customer.CustomerDTO;
import com.agrosul.customer_payment_control.dto.customer.CustomerMapper;
import com.agrosul.customer_payment_control.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository repository;

  public List<Customer> getCustomers() {
    return repository.findAll();
  }

  public Customer getCustomer(Long customerId) {
    return repository.findById(customerId)
        .orElseThrow(() -> new IllegalStateException("Customer " + customerId.toString() + " do not exists"));
  }

  public Customer createCustomer(CustomerDTO dto) {
    Customer entity = new CustomerMapper().toEntity(dto);

    return repository.save(entity);
  }

  public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
    Customer customer = this.getCustomer(customerId);
    
    new CustomerMapper().updateEntityFromDTO(customer, customerDTO);

    return repository.save(customer);
  }

  public Customer deleteCustomer(Long customerId){
    Customer customer = this.getCustomer(customerId);

    repository.delete(customer);

    return customer;
  }
}
