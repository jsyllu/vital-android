package com.numad21fa.vital.models;

public class FDCFoodNutrient {
  FDCNutrient nutrient;
  Double amount;

  public FDCFoodNutrient() {
  }

  public FDCNutrient getNutrient() {
    return nutrient;
  }

  public void setNutrient(FDCNutrient nutrient) {
    this.nutrient = nutrient;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
