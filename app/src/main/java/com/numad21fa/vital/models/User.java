package com.numad21fa.vital.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
  final Date createdAt;
  String email;
  String password;
  String firstname;
  String lastname;
  Date lastLogIn;
  List<DailyIntake> dailyIntakeList; // chronological order
  Map<String, String> nutrientUnitMap; // key: foodNutrient.nutrient.name, value:foodNutrient.nutrient.unitName
  Map<String, Double> nutrient7Days; // key: foodNutrient.nutrient.name, value: foodNutrient.amount

  public User() {
    this.createdAt = new Date();
    this.lastLogIn = this.createdAt;
    this.dailyIntakeList = new ArrayList<>();
    this.nutrientUnitMap = new HashMap<>();
  }

  public User(String email, String password) {
    this.createdAt = new Date();
    this.lastLogIn = this.createdAt;
    this.dailyIntakeList = new ArrayList<>();
    this.nutrientUnitMap = new HashMap<>();
    this.email = email;
    this.password = password;
  }

  public User(String email, String password, String firstname, String lastname) {
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.createdAt = new Date();
    this.lastLogIn = this.createdAt;
    this.dailyIntakeList = new ArrayList<>();
    this.nutrientUnitMap = new HashMap<>();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getLastLogIn() {
    return lastLogIn;
  }

  public void setLastLogIn(Date lastLogIn) {
    this.lastLogIn = lastLogIn;
  }

  public List<DailyIntake> getDailyIntakeList() {
    return dailyIntakeList;
  }

  public void setDailyIntakeList(List<DailyIntake> dailyIntakeList) {
    this.dailyIntakeList = dailyIntakeList;
  }

  public Map<String, String> getNutrientUnitMap() {
    return nutrientUnitMap;
  }

  public void setNutrientUnitMap(Map<String, String> nutrientUnitMap) {
    this.nutrientUnitMap = nutrientUnitMap;
  }

  public Map<String, Double> getNutrient7Days() {
    if (nutrient7Days == null) {
      this.nutrient7Days = new HashMap<>();
    }
    // TODO: to be updated
    // iterate dailyIntakeList for 7 days and sum nutrients amount in to nutrient7Days
    return nutrient7Days;
  }
}
