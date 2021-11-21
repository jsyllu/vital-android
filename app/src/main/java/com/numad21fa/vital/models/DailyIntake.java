package com.numad21fa.vital.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyIntake {
  final String date; // 20211120
  List<FoodEntry> entries;
  Map<String, Double> nutrientMap; // key: foodNutrient.nutrient.name, value: foodNutrient.amount

  public DailyIntake() {
    this.date = new Date().toString(); // format it to yyyymmdd
    this.entries = new ArrayList<>();
    this.nutrientMap = new HashMap<>();
  }

  public String getDate() {
    return date;
  }

  public List<FoodEntry> getEntries() {
    return entries;
  }

  public void setEntries(List<FoodEntry> entries) {
    this.entries = entries;
  }

  public Map<String, Double> getNutrientMap() {
    return nutrientMap;
  }

  public void setNutrientMap(Map<String, Double> nutrientMap) {
    this.nutrientMap = nutrientMap;
  }
}
