package com.agrosul.customer_payment_control.controller.customer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import com.agrosul.customer_payment_control.controller.CustomerController;
import com.agrosul.customer_payment_control.domain.Customer;
import com.agrosul.customer_payment_control.dto.customer.CustomerDTO;
import com.agrosul.customer_payment_control.dto.customer.CustomerMapper;
import com.agrosul.customer_payment_control.fixtures.CustomerFixture;
import com.agrosul.customer_payment_control.service.CustomerService;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest{
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @Nested
  public class IndexRoute {
    @Test
    void mustListCustomers() throws Exception {
      List<Customer> customers = new CustomerFixture().factoryCustomers();

      when(customerService.getCustomers()).thenReturn(customers);

      mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")).andExpect(status().isOk())
          .andExpect(jsonPath("$").isArray())
          .andExpect(jsonPath("$[0].firstName").value(customers.get(0).getFirstName()))
          .andExpect(jsonPath("$[0].lastName").value(customers.get(0).getLastName()))
          .andExpect(jsonPath("$[0].email").value(customers.get(0).getEmail()))
          .andExpect(jsonPath("$[0].cpf").value(customers.get(0).getCpf()))
          .andExpect(jsonPath("$[1].firstName").value(customers.get(1).getFirstName()))
          .andExpect(jsonPath("$[1].lastName").value(customers.get(1).getLastName()))
          .andExpect(jsonPath("$[1].email").value(customers.get(1).getEmail()))
          .andExpect(jsonPath("$[1].cpf").value(customers.get(1).getCpf()));
    }
  }

  @Nested
  public class ShowRoute {
    @Test
    void mustShowCustomer() throws Exception {
      Customer customer = new CustomerFixture().factoryCustomer(10L);

      when(customerService.getCustomer(10L)).thenReturn(customer);

      mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{id}", 10L))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$").isMap())
          .andExpect(jsonPath("$.id").value(customer.getId()))
          .andExpect(jsonPath("$.firstName").value(customer.getFirstName()))
          .andExpect(jsonPath("$.lastName").value(customer.getLastName()))
          .andExpect(jsonPath("$.email").value(customer.getEmail()))
          .andExpect(jsonPath("$.cpf").value(customer.getCpf()));
    }
  }

  @Nested
  public class UpdateRoute {
    @Test
    void mustReturnErrorWhenInvalidParams() throws Exception {
      CustomerDTO dto = new CustomerFixture().factoryCustomerDTO(1L);
      dto.setCpf("Invalid CPF");

      mockMvc.perform(
        MockMvcRequestBuilders.put("/api/v1/customers/{id}", 1L)
          .contentType(MediaType.APPLICATION_JSON)
          .content(new CustomerMapper().toString(dto))
      ).andExpect(status().isBadRequest());
    }

    @Test
    void mustUpdateWhenValidParams() throws Exception {
      Customer customer = new CustomerFixture().factoryCustomer(10L);
      CustomerDTO dto = new CustomerFixture().factoryCustomerDTO(10L);
      new CustomerMapper().updateEntityFromDTO(customer, dto);

      when(
        customerService.updateCustomer(anyLong(), any(CustomerDTO.class))
      ).thenReturn(customer);

      mockMvc.perform(
        MockMvcRequestBuilders.put("/api/v1/customers/{id}", 10L)
          .contentType(MediaType.APPLICATION_JSON)
          .content(new CustomerMapper().toString(dto))
      ).andExpect(status().isOk())
          .andExpect(jsonPath("$").isMap())
          .andExpect(jsonPath("$.id").value(dto.getId()))
          .andExpect(jsonPath("$.firstName").value(dto.getFirstName()))
          .andExpect(jsonPath("$.lastName").value(dto.getLastName()))
          .andExpect(jsonPath("$.email").value(dto.getEmail()))
          .andExpect(jsonPath("$.cpf").value(dto.getCpf()));
    }

    @Test
    void mustNotUpdateNullParams() throws Exception {
      Customer customer = new CustomerFixture().factoryCustomer(10L);
      CustomerDTO dto = new CustomerFixture().factoryCustomerDTO(10L);

      dto.setCpf(null);

      new CustomerMapper().updateEntityFromDTO(customer, dto);

      when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer);

      mockMvc
          .perform(MockMvcRequestBuilders.put("/api/v1/customers/{id}", 10L).contentType(MediaType.APPLICATION_JSON)
              .content(new CustomerMapper().toString(dto)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$").isMap())
          .andExpect(jsonPath("$.cpf").value(customer.getCpf()));
    }
  }

  @Nested
  public class createRoute {
    @Test
    void mustReturnCreatedCustomer() throws Exception {
      CustomerDTO dto = new CustomerFixture().factoryCustomerDTO(10L);
      Customer customer = new CustomerMapper().toEntity(dto);

      when(
        customerService.createCustomer(any(CustomerDTO.class))
      ).thenReturn(customer);

      mockMvc.perform(
        MockMvcRequestBuilders.post("/api/v1/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content(new CustomerMapper().toString(dto))
      ).andExpect(jsonPath("$").isMap())
          .andExpect(jsonPath("$.id").value(dto.getId()))
          .andExpect(jsonPath("$.firstName").value(dto.getFirstName()))
          .andExpect(jsonPath("$.lastName").value(dto.getLastName()))
          .andExpect(jsonPath("$.email").value(dto.getEmail()))
          .andExpect(jsonPath("$.cpf").value(dto.getCpf()));
    }

    @Test
    void mustReturnErrorWhenInvalidParams() throws Exception {
      CustomerDTO dto = new CustomerFixture().factoryCustomerDTO(null);
      dto.setEmail("invalid email");

      mockMvc.perform(
        MockMvcRequestBuilders.post("/api/v1/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .content(new CustomerMapper().toString(dto))
      ).andExpect(status().isBadRequest());
    }
  }

  @Nested
  public class DeleteRoute {
    @Test
    void mustReturnDeletedCustomer() throws Exception {
      Customer customer = new CustomerFixture().factoryCustomer(10L);

      when(customerService.deleteCustomer(10L)).thenReturn(customer);

      mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/v1/customers/{id}", 10L)
      ).andExpect(status().isOk())
          .andExpect(jsonPath("$").isMap())
          .andExpect(jsonPath("$.id").value(customer.getId()))
          .andExpect(jsonPath("$.firstName").value(customer.getFirstName()))
          .andExpect(jsonPath("$.lastName").value(customer.getLastName()))
          .andExpect(jsonPath("$.email").value(customer.getEmail()))
          .andExpect(jsonPath("$.cpf").value(customer.getCpf()));
    }
  }
}
