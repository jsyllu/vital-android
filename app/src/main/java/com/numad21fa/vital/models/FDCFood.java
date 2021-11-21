package com.numad21fa.vital.models;

import java.util.List;

public class FDCFood {
  String fdcId;
  String description;
  List<FDCFoodNutrient> foodNutrients;

  public FDCFood() {
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

  public List<FDCFoodNutrient> getFoodNutrients() {
    return foodNutrients;
  }

  public void setFoodNutrients(List<FDCFoodNutrient> foodNutrients) {
    this.foodNutrients = foodNutrients;
  }
}
