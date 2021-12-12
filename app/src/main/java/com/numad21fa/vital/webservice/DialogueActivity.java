package com.numad21fa.vital.webservice;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.numad21fa.vital.R;

import java.util.ArrayList;

public class DialogueActivity extends AppCompatActivity {
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> amountList = new ArrayList<>();
    private ArrayList<String> unitList = new ArrayList<>();
    private RecyclerView nutrientsRview;
    private NutrientsRviewAdapter nutrientsRviewAdapter;
    private RecyclerView.LayoutManager nutrientsrLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String foodName = extras.getString("foodName");
        nameList = extras.getStringArrayList("nameList");
        amountList = extras.getStringArrayList("amountList");
        unitList = extras.getStringArrayList("unitList");
//        Log.i("ItemCard - nutrientsList", amountList.get(0));
        setContentView(R.layout.activity_food_nutrients_dialog);
        TextView header = findViewById(R.id.header);
        header.setText("View Nutrients of " + foodName);

        nutrientsrLayoutManger = new LinearLayoutManager(this);
        nutrientsRview = findViewById(R.id.nutrients_recycler_view);
        nutrientsRview.setHasFixedSize(true);

        nutrientsRviewAdapter = new NutrientsRviewAdapter(nameList, amountList, unitList);

        nutrientsRview.setAdapter(nutrientsRviewAdapter);
        nutrientsRview.setLayoutManager(nutrientsrLayoutManger);

    }
}