package com.numad21fa.vital;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.numad21fa.vital.models.FDCFood;
import com.numad21fa.vital.models.FDCFoodNutrient;
import com.numad21fa.vital.webservice.DialogueActivity;
import com.numad21fa.vital.webservice.FDCWebService;
import com.numad21fa.vital.webservice.FDCWebServiceActivity;
import com.numad21fa.vital.webservice.ItemCard;
import com.numad21fa.vital.webservice.ItemNutrient;
import com.numad21fa.vital.webservice.ReviewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass. Use the {@link HomeSearchFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class HomeSearchFragment extends Fragment implements OnClickListener{

  Context context;
  ImageButton btn_search;
  EditText edit_search_input;
  String food_name;
  List<FDCFood> foods;
  TextView num_of_results;
  private ArrayList<ItemCard> itemList = new ArrayList<>();
  private RecyclerView recyclerView;
  private ReviewAdapter reviewAdapter;
  private RecyclerView.LayoutManager rLayoutManger;

  FDCWebService foodWebService;


  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public HomeSearchFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment SearchFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static HomeSearchFragment newInstance(String param1, String param2) {
    HomeSearchFragment fragment = new HomeSearchFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    context = container.getContext();
    View view = inflater.inflate(R.layout.fragment_search, container, false);
    edit_search_input = view.findViewById(R.id.edit_search_input);
    btn_search = view.findViewById(R.id.btn_search);
    num_of_results = view.findViewById(R.id.num_of_results);
    btn_search.setOnClickListener(this);
    foodWebService = new FDCWebService(context);
    rLayoutManger = new LinearLayoutManager(getContext());
    recyclerView = view.findViewById(R.id.recycler_list_view);
    recyclerView.setHasFixedSize(true);
    return view;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_search:
        food_name = edit_search_input.getText().toString();
        Toast.makeText(getActivity(), "Hit Search: " + food_name, Toast.LENGTH_SHORT).show();
        // send get http request
        foodWebService.getFoodID(food_name, new FDCWebService.VolleyResponseListener() {
          @Override
          public void onError(String message) {
            Toast.makeText(getActivity(), "ERROR: " + message, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onResponse(String fdcID) {
            // If input field changes, webservice works
            Log.i("WebActivityResponse", food_name);

            //TODO GET FOODS LIST HERE and Update to RecycleView
            foods = foodWebService.getFoods();
            Toast.makeText(getActivity(), "Food size: " + foods.size(), Toast.LENGTH_SHORT).show();
            createRecyclerView(foods);
          }
        });
        break;
    }
  }

  // Tapping link launches the URL in web browser
  private void createRecyclerView(List<FDCFood> foods) {
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
//                Uri url = Uri.parse(itemList.get(position).getItemLink());
        // TODO Add Item to USER
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

        openNutrientsDialog(position, foodNutrientsList);
      }
    };
    reviewAdapter.setOnItemClickListener(itemClickListener);
    recyclerView.setAdapter(reviewAdapter);
    recyclerView.setLayoutManager(rLayoutManger);

    // Set TextView for result
    num_of_results.setText("~ Showed " + itemList.size() + " of " + foods.size() + " items ~");

    //Lecture code, Remove card by Moving Gesture
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
      @Override
      public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Toast.makeText(getActivity(), "This item has been deleted", Toast.LENGTH_SHORT).show();
        int position = viewHolder.getLayoutPosition();
        itemList.remove(position);
        reviewAdapter.notifyItemRemoved(position);
      }
    });
    itemTouchHelper.attachToRecyclerView(recyclerView);
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

      Log.i("ItemCard - NutrientName", String.valueOf(foodNutrientsList.get(j).getNutrient().getName()));
      Log.i("ItemCard - NutrientAmount", String.valueOf(foodNutrientsList.get(j).getAmount()));
      Log.i("ItemCard - NutrientUnit", String.valueOf(foodNutrientsList.get(j).getNutrient().getUnitName()));
      String nutrientInfo = nutrientName + ' ' + nutrientAmount + nutrientUnit;
      foodNutrientsInfoList.add(nutrientInfo);
    }

    Intent intent = new Intent(getActivity(), DialogueActivity.class);
    intent.putExtra("itemList", foodNutrientsInfoList);
    startActivity(intent);

  }
}