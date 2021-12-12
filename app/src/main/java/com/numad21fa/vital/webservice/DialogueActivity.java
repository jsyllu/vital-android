package com.numad21fa.vital.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.numad21fa.vital.R;

import java.util.ArrayList;

public class DialogueActivity extends AppCompatActivity {
    private ArrayList<String> nutrientsList = new ArrayList<>();
    private RecyclerView nutrientsRview;
    private NutrientsRviewAdapter nutrientsRviewAdapter;
    private RecyclerView.LayoutManager nutrientsrLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        nutrientsList = extras.getStringArrayList("itemList");
        Log.i("ItemCard - nutrientsList", nutrientsList.get(0));

        nutrientsrLayoutManger = new LinearLayoutManager(this);
        nutrientsRview = findViewById(R.id.nutrients_recycler_view);
//        nutrientsRview.setHasFixedSize(true);

        nutrientsRviewAdapter = new NutrientsRviewAdapter(nutrientsList);

        nutrientsRview.setAdapter(nutrientsRviewAdapter);
        nutrientsRview.setLayoutManager(nutrientsrLayoutManger);

        setContentView(R.layout.activity_food_nutrients_dialog);
    }
}