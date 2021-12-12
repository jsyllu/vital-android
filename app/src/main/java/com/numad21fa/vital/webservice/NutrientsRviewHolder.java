package com.numad21fa.vital.webservice;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.numad21fa.vital.R;

public class NutrientsRviewHolder extends RecyclerView.ViewHolder {
    public TextView item_nutrientName;
    public TextView item_nutrientAmount;
    public TextView item_nutrientUnit;

    // fetch the related ItemCard instance from the view
    public NutrientsRviewHolder(View itemView) {
        super(itemView);
        item_nutrientName = itemView.findViewById(R.id.item_nutrientName);
        item_nutrientAmount = itemView.findViewById(R.id.item_nutrientAmount);
        item_nutrientUnit = itemView.findViewById(R.id.item_nutrientUnit);
    }
}