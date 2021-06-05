package com.agrosul.customer_payment_control.dto.phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.agrosul.customer_payment_control.domain.Phone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Phone Mapper tests")
public class PhoneMapperTest {
  Phone phone = buildPhone();
  PhoneDTO phoneDTO = buildPhoneDTO();
  
  @Nested
  @DisplayName("fromPhone method")
  class FromPhone {
    @Test
    @DisplayName("must convert entity phone to dto")
    void mustConvertPhoneEntityToDTO(){
      PhoneDTO dtoMapped = new PhoneMapper().fromPhone(phone);
      
      assertEquals(dtoMapped.getId(), phone.getId());
      assertEquals(dtoMapped.getCountryCode(), phone.getCountryCode());
      assertEquals(dtoMapped.getAreaCode(), phone.getAreaCode());
      assertEquals(dtoMapped.getNumber(), phone.getNumber());
    }
  }

  @Nested
  @DisplayName("To phone method")
  class ToPhone {
    @Test
    @DisplayName("must convert phone dto to entity")
    void mustConvertPhoneDTOToEntity() {
      Phone phoneMapped = new PhoneMapper().toPhone(phoneDTO);

      assertEquals(phoneMapped.getId(), phoneDTO.getId());
      assertEquals(phoneMapped.getCountryCode(), phoneDTO.getCountryCode());
      assertEquals(phoneMapped.getAreaCode(), phoneDTO.getAreaCode());
      assertEquals(phoneMapped.getNumber(), phoneDTO.getNumber());
    }
  }

  @Nested
  @DisplayName("updatePhoneToDTO method")
  class updatePhoneToDTO {
    @Test
    @DisplayName("must update phone entity based on DTO attr")
    void mustConvertPhoneDTOToEntity() {
      new PhoneMapper().updatePhoneFromDto(phoneDTO, phone);;

      assertEquals(phone.getId(), phoneDTO.getId());
      assertEquals(phone.getCountryCode(), phoneDTO.getCountryCode());
      assertEquals(phone.getAreaCode(), phoneDTO.getAreaCode());
      assertEquals(phone.getNumber(), phoneDTO.getNumber());
    }

    @Test
    @DisplayName("must skip null attributes")
    void mustSkipNullAttributes(){
      String areaCode = phone.getAreaCode();
      phoneDTO.setAreaCode(null);
      
      new PhoneMapper().updatePhoneFromDto(phoneDTO, phone);

      assertNotEquals(phone.getAreaCode(), phoneDTO.getAreaCode());
      assertEquals(phone.getAreaCode(), areaCode);
    }
  }

  Phone buildPhone(){
    Phone phone = new Phone("55", "51", "997112290");
    phone.setId(1L);
    return phone;
  }
  
  PhoneDTO buildPhoneDTO() {
    PhoneDTO dto = new PhoneDTO("55", "51", "997112290");
    dto.setId(2L);
    return dto;
  }

  @BeforeEach
  void init() {
    this.phone = buildPhone();
    this.phoneDTO = buildPhoneDTO();
  }
}
