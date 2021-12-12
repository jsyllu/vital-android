package com.numad21fa.vital.webservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.numad21fa.vital.R;
import com.numad21fa.vital.models.FDCFood;
import com.numad21fa.vital.models.FDCFoodNutrient;
import com.numad21fa.vital.models.FDCNutrient;

import java.util.ArrayList;
import java.util.List;

// FDC WebService TEST Activity
public class FDCWebServiceActivity extends AppCompatActivity {
    Button btn_getFDCID;
    EditText editText_dataInput;
    String entered_food_name;
    List<FDCFood> foods;
    TextView txtView_results;

    // RcycleView Foods
    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;

    // RecycleView Nutrients
    private ArrayList<ItemNutrient> itemNutrientsList = new ArrayList<>();

    private static final String instanceKey = "instanceKey";
    private static final String indexItems = "indexItems";

    FDCWebService foodWebService = new FDCWebService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdcweb_service);
        btn_getFDCID = findViewById(R.id.btn_getFDCID);
        editText_dataInput = findViewById(R.id.editText_dataInput);
        txtView_results = findViewById(R.id.textView_results);

//        // Instantiate RecycleView on current Activity
//        openViewFromPrevState(savedInstanceState);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getFDCID:
                //
                foodWebService.getFoodID(editText_dataInput.getText().toString(), new FDCWebService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(FDCWebServiceActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String fdcID) {
                        Toast.makeText(FDCWebServiceActivity.this, "Return " + fdcID, Toast.LENGTH_SHORT).show();
                        // If input field changes, webservice works
                        editText_dataInput.setText(fdcID);
                        entered_food_name = editText_dataInput.getText().toString();
                        Log.i("WebActivityResponse", entered_food_name);

                        //TODO GET FODDS LIST HERE and Update to RecycleView
                        foods = foodWebService.getFoods();
                        createRecyclerView(foods);
                    }
                });
                break;
        }

    }

//    // store the current instance into outState
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        int size = itemList == null ? 0 : itemList.size();
//        outState.putInt(indexItems, size);
//        for (int i = 0; i < size; i++) {
//            outState.putString(instanceKey + i + "1", itemList.get(i).getDescription());
//        }
//        super.onSaveInstanceState(outState);
//    }
//
//    private void openViewFromPrevState(Bundle savedInstanceState) {
//        getPrevState(savedInstanceState);
//        createRecyclerView();
//    }
//
//    // Load previous instance if previous state is not null
//    private void getPrevState(Bundle savedInstanceState) {
//        if (savedInstanceState != null && savedInstanceState.containsKey(indexItems)) {
//            if (itemList == null || itemList.size() == 0) {
//                int size = savedInstanceState.getInt(indexItems);
//                for (int i = 0; i < size; i++) {
//                    String itemDescription = savedInstanceState.getString(instanceKey + i + "1");
//                    ItemCard itemCard = new ItemCard(itemDescription);
//                    itemList.add(itemCard);
//                }
//            }
//        }
//    }

    // Tapping link launches the URL in web browser
    private void createRecyclerView(List<FDCFood> foods) {
        rLayoutManger = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // Instantiate a new itemList
        itemList = new ArrayList<ItemCard>();

        for (int i = 0; i < foods.size(); i++) {
            FDCFood food = foods.get(i);
            // Create a new ItemCard for this food
            itemList.add(new ItemCard(food.getDescription()));
            Log.i("WebActivityFoodList - Size", String.valueOf(foods.size()));
            Log.i("WebActivityFoodList - Description", food.getDescription());
            Log.i("WebActivityItemList - Size", String.valueOf(itemList.size()));
        }

        reviewAdapter = new ReviewAdapter(itemList);
        ItemCard.ItemClickListener itemClickListener = new ItemCard.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                // TODO Add Item to USER
//                Uri url = Uri.parse("https://google.ca");
//                Intent intent = new Intent(Intent.ACTION_VIEW, url);
//                startActivity(intent);

                // TODO: Open Dialogue here
                Log.i("ItemCard - position", String.valueOf(position));
                Log.i("ItemCard - Nutrients", String.valueOf(foods.get(position).getFoodNutrients()));
                ArrayList<FDCFoodNutrient> foodNutrientsList = new ArrayList<>();
                ArrayList<String> foodNutrientsInfoList = new ArrayList<>();

                // Cast nutrients into one String
                foodNutrientsList = (ArrayList<FDCFoodNutrient>) foods.get(position).getFoodNutrients();

                Log.i("ItemCard - foodNutrientsListSize", String.valueOf(foodNutrientsList.size()));
//                for (int j=0; j < foodNutrientsList.size(); j++) {
//                    Log.i("ItemCard - NutrientName", String.valueOf(foodNutrientsList.get(j).getNutrient().getName()));
//                    Log.i("ItemCard - NutrientAmount", String.valueOf(foodNutrientsList.get(j).getAmount()));
//                    Log.i("ItemCard - NutrientUnit", String.valueOf(foodNutrientsList.get(j).getNutrient().getUnitName()));
//                    String nutrientName = foodNutrientsList.get(j).getNutrient().getName();
//                    Double nutrientAmount = foodNutrientsList.get(j).getAmount();
//                    String nutrientUnit = foodNutrientsList.get(j).getNutrient().getUnitName();
//                    String nutrientInfo = nutrientName + ' ' + nutrientAmount + nutrientUnit;
//                    foodNutrientsInfoList.add(nutrientInfo);
//                }

                createNutrientsRecyclerView();
                openNutrientsDialog(position, foodNutrientsList);


            }
        };
        reviewAdapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);

        // Set TextView for result
        txtView_results.setText("~ Showed " + itemList.size() + " of " + foods.size() + " items ~");

        //Lecture code, Remove card by Moving Gesture
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(FDCWebServiceActivity.this, "This item has been deleted", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);
                reviewAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void createNutrientsRecyclerView(){
//        nutrientsrLayoutManger = new LinearLayoutManager(this);
//        nutrientsRview = findViewById(R.id.nutrients_recycler_view);
//        nutrientsRview.setHasFixedSize(true);

        // Instantiate a new itemList
        itemNutrientsList = new ArrayList<ItemNutrient>();
    }

    // New dialogue window prompts up for Name and Link inputs
    private void openNutrientsDialog(int position, ArrayList<FDCFoodNutrient> foodNutrientsList) {
//        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
//        newDialog.setTitle("Display Nutrients");
//
//        View newNutrientsView = LayoutInflater.from(this).inflate(R.layout.activity_food_nutrients_dialog,
//                findViewById(R.id.content), false);

//        ListView mNutrientsList = newNutrientsView.findViewById(R.id.list_view_nutrients);
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, position, stringList);
//        mNutrientsList.setAdapter(adapter);


        ArrayList<String> foodNutrientsInfoList = new ArrayList<>();
        for (int j = 0; j < foodNutrientsList.size(); j++) {
            FDCFoodNutrient nutrient = foodNutrientsList.get(j);
            // Create a new ItemNutrient for this food
            String nutrientName = foodNutrientsList.get(j).getNutrient().getName();
            Double nutrientAmount = foodNutrientsList.get(j).getAmount();
            String nutrientUnit = foodNutrientsList.get(j).getNutrient().getUnitName();

            itemNutrientsList.add(new ItemNutrient(nutrientName, nutrientAmount, nutrientUnit));
            Log.i("ItemCard - NutrientName", String.valueOf(foodNutrientsList.get(j).getNutrient().getName()));
            Log.i("ItemCard - NutrientAmount", String.valueOf(foodNutrientsList.get(j).getAmount()));
            Log.i("ItemCard - NutrientUnit", String.valueOf(foodNutrientsList.get(j).getNutrient().getUnitName()));
            String nutrientInfo = nutrientName + ' ' + nutrientAmount + nutrientUnit;
            foodNutrientsInfoList.add(nutrientInfo);
        }

        Intent intent = new Intent(this, DialogueActivity.class);
        intent.putExtra("itemList", foodNutrientsInfoList);
        startActivity(intent);

//        nutrientsRviewAdapter = new NutrientsRviewAdapter(itemNutrientsList);
//        nutrientsRview.setAdapter(nutrientsRviewAdapter);
//        nutrientsRview.setLayoutManager(nutrientsrLayoutManger);

//        newDialog.setView(newNutrientsView);
//
//        newDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        newDialog.show();
//    }
    }

}