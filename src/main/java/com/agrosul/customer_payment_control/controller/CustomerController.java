package com.agrosul.customer_payment_control.controller;

import java.util.List;

import javax.validation.Valid;

import com.agrosul.customer_payment_control.domain.Customer;
import com.agrosul.customer_payment_control.dto.customer.CustomerDTO;
import com.agrosul.customer_payment_control.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @GetMapping
  public List<Customer> getCustomers(){
    return customerService.getCustomers();
  }

  @GetMapping(path = "{customerId}")
  public Customer getCustomer(@PathVariable("customerId") Long id) {
    return customerService.getCustomer(id);
  }

  @PostMapping
  public Customer addCustomer(@Valid @RequestBody CustomerDTO dto) {
      return customerService.createCustomer(dto);
  }

  @PutMapping(path = "{customerId}")
  public Customer updateCustomer(
    @PathVariable("customerId") Long id, 
    @Valid @RequestBody CustomerDTO dto
  ){
    return customerService.updateCustomer(id, dto);
  }

  @DeleteMapping(path = "{customerId}")
  public Customer deleteCustomer(@PathVariable("customerId") Long id) {
    return customerService.deleteCustomer(id);
  }
}
