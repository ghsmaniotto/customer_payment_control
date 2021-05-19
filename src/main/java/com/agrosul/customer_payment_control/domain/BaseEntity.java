package com.agrosul.customer_payment_control.domain;

import java.beans.Transient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class BaseEntity {
  @Id
  @SequenceGenerator(name = "base_entity_sequence", sequenceName = "base_entity_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_entity_sequence")
  private Long id;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Transient
  public boolean isNew() {
    return this.id == null;
  }
}
