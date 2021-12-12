package com.numad21fa.vital.webservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.numad21fa.vital.R;
import com.numad21fa.vital.models.FDCFoodNutrient;

import java.util.ArrayList;

//public class NutrientsAdapter extends ArrayAdapter<FDCFoodNutrient> {
//    public NutrientsAdapter(Context context, ArrayList<FDCFoodNutrient> nutrients) {
//        super(context, 0, nutrients);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        FDCFoodNutrient nutrient = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nutrients, parent, false);
//        }
//
//        // Lookup view for data population
//        TextView item_nutrientName = (TextView) convertView.findViewById(R.id.item_nutrientName);
//        TextView item_nutrientAmount = (TextView) convertView.findViewById(R.id.item_nutrientAmount);
//        TextView item_nutrientUnit = (TextView) convertView.findViewById(R.id.item_nutrientUnit);
//
//        // Populate the data into the template view using the data object
//        item_nutrientName.setText(nutrient.getNutrient().getName());
//        item_nutrientAmount.setText(nutrient.getAmount().toString());
//        item_nutrientUnit.setText(nutrient.getNutrient().getUnitName());
//
//        // Return the completed view to render on screen
//        return convertView;
//    }
//}

public class NutrientsRviewAdapter extends RecyclerView.Adapter<NutrientsRviewHolder> {

//    private ArrayList<ItemNutrient> itemNutrientsList;
//    private ItemCard.ItemClickListener listener;
    private ArrayList<String> nameList;
    private ArrayList<String> amountList;
    private ArrayList<String> unitList;

    public NutrientsRviewAdapter(ArrayList<String> nameList, ArrayList<String> amountList, ArrayList<String> unitList) {
        this.nameList = nameList;
        this.amountList = amountList;
        this.unitList = unitList;
    }
//
//    public void setOnItemClickListener(ItemCard.ItemClickListener listener) {
//        this.listener = listener;
//    }

    @Override
    public NutrientsRviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nutrients, parent, false);
        return new NutrientsRviewHolder(view);
    }

    @Override
    public void onBindViewHolder(NutrientsRviewHolder holder, int position) {
        String currentName = nameList.get(position);
        holder.item_nutrientName.setText(currentName);
        String currentAmount = amountList.get(position);
        holder.item_nutrientAmount.setText(currentAmount);
        String currentUnit = unitList.get(position);
        holder.item_nutrientUnit.setText(currentUnit);
//        holder.item_nutrientAmount.setText(currentItem.getNutrientAmount().toString());
//        holder.item_nutrientUnit.setText(currentItem.getNutrientUnit());
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}