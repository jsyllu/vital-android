package com.numad21fa.vital.models;

import java.util.Date;

public class FoodEntry {
  final Date createdAt;
  String fdcId; // unique & from FoodCentral
  String description; // food description
  Integer portion; // unit: gram

  public FoodEntry() {
    this.createdAt = new Date();
  }

  public FoodEntry(String fdcId, String description, Integer portion) {
    this.fdcId = fdcId;
    this.description = description;
    this.portion = portion;
    this.createdAt = new Date();
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getFdcId() {
    return fdcId;
  }

  public void setFdcId(String fdcId) {
    this.fdcId = fdcId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPortion() {
    return portion;
  }

  public void setPortion(Integer portion) {
    this.portion = portion;
  }
}
