package com.agrosul.customer_payment_control.configuration;

import java.util.List;

import com.agrosul.customer_payment_control.domain.Phone;
import com.agrosul.customer_payment_control.repository.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhoneConfiguration {
  
  @Autowired
  private PhoneRepository phoneRepository;

  @Bean
  CommandLineRunner commandLineRunner(){
    return args -> {
      Phone phone1 = new Phone("55", "51", "997009666");
      Phone phone2 = new Phone("55", "51", "997009654");

      phoneRepository.saveAll(List.of(phone1, phone2));
    };
  }
}
