package com.agrosul.customer_payment_control.dto.customer;

import com.agrosul.customer_payment_control.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerMapper {
  public CustomerMapper(){
    //Empty constructor
  }

  public String toString(final Object obj) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(obj);
  }

  public CustomerDTO toDTO(Customer entity){
    CustomerDTO dto = new CustomerDTO(
      entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getCpf()
    );
    dto.setId(entity.getId());
    return dto;
  }

  public Customer toEntity(CustomerDTO dto){
    Customer entity = new Customer(
      dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getCpf()
    );
    entity.setId(dto.getId());
    return entity;
  }

  public void updateEntityFromDTO(Customer entity, CustomerDTO dto) {
    if (dto.getId() != null) entity.setId(dto.getId());
    if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
    if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
    if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
    if (dto.getCpf() != null) entity.setCpf(dto.getCpf());
  }
}
