package com.agrosul.customer_payment_control.dto.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.agrosul.customer_payment_control.domain.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CustomerMapperTest {
  Customer customer = buildCustomer(null);
  CustomerDTO customerDTO = buildCustomerDTO(null);

  CustomerMapper mapper = new CustomerMapper();
  
  @Nested
  class ToDTO {
    @Test
    void mustConvertCustomerEntityToDTO(){
      Customer entity = buildCustomer(11L);

      CustomerDTO dto = mapper.toDTO(entity);

      assertEquals(entity.getId(), dto.getId());
      assertEquals(entity.getFirstName(), dto.getFirstName());
      assertEquals(entity.getLastName(), dto.getLastName());
      assertEquals(entity.getCpf(), dto.getCpf());
      assertEquals(entity.getEmail(), dto.getEmail());
    }
  }

  @Nested
  class ToEntity {
    @Test
    void mustConvertCustomerDTOToEntity(){
      CustomerDTO dto = buildCustomerDTO(10L);

      Customer entity = mapper.toEntity(dto);

      assertEquals(entity.getId(), dto.getId());
      assertEquals(entity.getFirstName(), dto.getFirstName());
      assertEquals(entity.getLastName(), dto.getLastName());
      assertEquals(entity.getCpf(), dto.getCpf());
      assertEquals(entity.getEmail(), dto.getEmail());
    }
  }

  @Nested
  class UpdateEntityFromDTO {
    @Test
    void mustUpdateEntityUsingDTOAttributes(){
      assertNotEquals(customer.getFirstName(), customerDTO.getFirstName());
      assertNotEquals(customer.getLastName(), customerDTO.getLastName());
      assertNotEquals(customer.getCpf(), customerDTO.getCpf());
      assertNotEquals(customer.getEmail(), customerDTO.getEmail());

      mapper.updateEntityFromDTO(customer, customerDTO);

      assertEquals(customer.getFirstName(), customerDTO.getFirstName());
      assertEquals(customer.getLastName(), customerDTO.getLastName());
      assertEquals(customer.getCpf(), customerDTO.getCpf());
      assertEquals(customer.getEmail(), customerDTO.getEmail());
    }

    @Test
    void mustSkipNullDTOAttributes(){
      String customerEmail = customer.getEmail();
      customerDTO.setEmail(null);

      mapper.updateEntityFromDTO(customer, customerDTO);

      assertNotEquals(customer.getEmail(), customerDTO.getEmail());
      assertEquals(customer.getEmail(), customerEmail);
      assertEquals(customer.getFirstName(), customerDTO.getFirstName());
      assertEquals(customer.getLastName(), customerDTO.getLastName());
    }
  }

  Customer buildCustomer(Long id){
    Customer customer = new Customer(
      "Gu", "Smani", "gu@smani.com", "69409610036"
    );
    customer.setId(id);
    return customer;
  }

  CustomerDTO buildCustomerDTO(Long id) {
    CustomerDTO customerDTO = new CustomerDTO(
      "Smani", "Gu", "smani@gu.com", "90559479034"
    );
    customerDTO.setId(id);
    return customerDTO;
  }

  @BeforeEach
  void init() {
    this.customer = buildCustomer(null);
    this.customerDTO = buildCustomerDTO(null);
  }
}
