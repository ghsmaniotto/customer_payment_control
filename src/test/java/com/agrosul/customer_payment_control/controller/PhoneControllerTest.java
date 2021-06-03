package com.agrosul.customer_payment_control.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.dto.phone.PhoneDTO;
import com.agrosul.customer_payment_control.dto.phone.PhoneMapper;
import com.agrosul.customer_payment_control.service.PhoneService;

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

@WebMvcTest(controllers = PhoneController.class)
public class PhoneControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PhoneService phoneService;

  @Nested
  public class IndexRoute {
    @Test
    void mustListPhones() throws Exception {
      List<Phone> phones = factoryPhones();
      when(phoneService.getPhones()).thenReturn(phones);

      mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/phones"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$").isArray())
          .andExpect(jsonPath("$[0].countryCode").value("55"))
          .andExpect(jsonPath("$[0].areaCode").value("51"))
          .andExpect(jsonPath("$[0].number").value("997008688"))
          .andExpect(jsonPath("$[1].countryCode").value("55"))
          .andExpect(jsonPath("$[1].areaCode").value("55"))
          .andExpect(jsonPath("$[1].number").value("997008689"));
    }
  }

  @Nested
  public class ShowRoute {
    @Test
    void mustShowPhone() throws Exception {
      Phone phone = factoryPhone();
      when(phoneService.getPhone(1L)).thenReturn(phone);

      mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/phones/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isMap())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.countryCode").value("55"))
        .andExpect(jsonPath("$.areaCode").value("51"))
        .andExpect(jsonPath("$.number").value("997008688"));
    }
  }

  @Nested
  public class UpdateRoute {
    @Test 
    void mustReturnErrorWhenInvalidParams() throws Exception {
      PhoneDTO dto = factoryDTOPhone(null);
      dto.setAreaCode("invalid area code");

      mockMvc.perform(
        MockMvcRequestBuilders.put("/api/v1/phones/{id}", 1L)
          .contentType(MediaType.APPLICATION_JSON)
          .content(new PhoneMapper().toString(dto))
      ).andExpect(status().isBadRequest());
    }

    @Test
    void mustUpdateWhenValidParams() throws Exception {
      Phone phone = factoryPhone();
      PhoneDTO dto = factoryDTOPhone(1L);
      new PhoneMapper().updatePhoneFromDto(dto, phone);

      when(phoneService.updatePhone(anyLong(), any(PhoneDTO.class))).thenReturn(phone);

      mockMvc.perform(
        MockMvcRequestBuilders.put("/api/v1/phones/{id}", 1L)
          .contentType(MediaType.APPLICATION_JSON)
          .content(new PhoneMapper().toString(dto))
      ).andExpect(status().isOk())
          .andExpect(jsonPath("$").isMap())
          .andExpect(jsonPath("$.id").value(1L))
          .andExpect(jsonPath("$.countryCode").value(dto.getCountryCode()))
          .andExpect(jsonPath("$.areaCode").value(dto.getAreaCode()))
          .andExpect(jsonPath("$.number").value(dto.getNumber()));
    }

    @Test
    void mustNotUpdateNullParams() throws Exception {
      Phone phone = factoryPhone();
      PhoneDTO dto = factoryDTOPhone(1L);
      dto.setNumber(null);
      
      new PhoneMapper().updatePhoneFromDto(dto, phone);

      when(phoneService.updatePhone(anyLong(), any(PhoneDTO.class))).thenReturn(phone);
      
      mockMvc
          .perform(MockMvcRequestBuilders.put("/api/v1/phones/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
              .content(new PhoneMapper().toString(dto)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.number").value(phone.getNumber()));
    } 
  }

  @Nested
  public class CreateRoute {
    @Test
    void mustReturnCreatedPhone() throws Exception {
      PhoneDTO dto = factoryDTOPhone(null);
      Phone phone = new PhoneMapper().toPhone(dto);
      phone.setId(10L);

      when(phoneService.createPhone(any(PhoneDTO.class))).thenReturn(phone);

      mockMvc
          .perform(
            MockMvcRequestBuilders.post("/api/v1/phones")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new PhoneMapper().toString(dto))
            )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(10L))
          .andExpect(jsonPath("$.number").value(dto.getNumber()))
          .andExpect(jsonPath("$.areaCode").value(dto.getAreaCode()))
          .andExpect(jsonPath("$.countryCode").value(dto.getCountryCode()));
    }

    @Test
    void mustReturnErrorWhenInvalidParams() throws Exception {
      PhoneDTO dto = factoryDTOPhone(null);
      dto.setAreaCode("invalid area code");

      mockMvc.perform(
        MockMvcRequestBuilders
          .post("/api/v1/phones")
          .contentType(MediaType.APPLICATION_JSON)
          .content(new PhoneMapper().toString(dto))
        ).andExpect(status().isBadRequest());
    }
  }

  @Nested
  public class DeleteRoute {
    @Test
    void mustReturnDeletedPhone() throws Exception {
      Phone phone = factoryPhone();
      phone.setId(10L);

      when(phoneService.deletePhone(10L)).thenReturn(phone);

      mockMvc
          .perform(
            MockMvcRequestBuilders.delete("/api/v1/phones/{id}", 10L)
          )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(10L))
          .andExpect(jsonPath("$.number").value(phone.getNumber()))
          .andExpect(jsonPath("$.areaCode").value(phone.getAreaCode()))
          .andExpect(jsonPath("$.countryCode").value(phone.getCountryCode()));
    }
  }

  PhoneDTO factoryDTOPhone(Long id) {
    PhoneDTO dto = new PhoneDTO("55", "99", "999999999");
    dto.setId(id);
    return dto;
  }

  Phone factoryPhone() {
    Phone phone = new Phone("55", "51", "997008688");
    phone.setId(1L);
    return phone;
  }

  List<Phone> factoryPhones() {
    return List.of(new Phone("55", "51", "997008688"), new Phone("55", "55", "997008689"));
  }
}
