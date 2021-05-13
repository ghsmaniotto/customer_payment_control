package com.agrosul.customer_payment_control.domain;

import java.beans.Transient;
import java.io.Serializable;

public class BaseEntity implements Serializable {
  private String id;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Transient
  public boolean isNew() {
    return this.id == null;
  }
}
