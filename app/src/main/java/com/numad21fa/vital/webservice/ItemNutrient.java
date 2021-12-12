package com.numad21fa.vital.webservice;

import androidx.appcompat.app.AppCompatActivity;

public class ItemNutrient extends AppCompatActivity {
    private final String nutrientName;
    private final String nutrientUnit;
    private final Double nutrientAmount;


    public String getNutrientName() {
        return nutrientName;
    }

    public String getNutrientUnit() {
        return nutrientUnit;
    }

    public Double getNutrientAmount() {
        return nutrientAmount;
    }

    public ItemNutrient(String nutrientName, Double nutrientAmount, String nutrientUnit) {
        this.nutrientName = nutrientName;
        this.nutrientAmount = nutrientAmount;
        this.nutrientUnit = nutrientUnit;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

}