package com.jojo.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Among Us")
public class CrewMate {

  @Id private BigInteger id;
  private String color;
  private boolean sus;

  @PersistenceConstructor
  public CrewMate(String color, boolean sus) {
    setColor(color);
    setSus(sus);
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public boolean isSus() {
    return sus;
  }

  public void setSus(boolean sus) {
    this.sus = sus;
  }

  @Override
  public String toString() {
    return "CrewMate [color=" + color + ", sus=" + sus + "]";
  }
}
