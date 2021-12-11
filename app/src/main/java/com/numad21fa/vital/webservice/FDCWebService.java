package com.numad21fa.vital.webservice;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.numad21fa.vital.models.FDCFood;
import com.numad21fa.vital.models.FDCFoodNutrient;
import com.numad21fa.vital.models.FDCNutrient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class FDCWebService {
    final String API_Search = "https://api.nal.usda.gov/fdc/v1/foods/search?dataType=Foundation";
    final String API_Key;
    List<FDCFood> foods;
    Context context;
    String fdcID;


    // Constructor
    public FDCWebService(Context context) {
        API_Key = "&api_key=zelNdbGKFObd921h7Za0hXvKE2HHK9GDPHhb6a4V";
        this.context = context;
        this.foods = new ArrayList<>();
    }


    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String fdcID);
    }

    public void getFoodID(String foodName, VolleyResponseListener volleyResponseListener) {
        // if no change on search input, simply return previous result
//        if (this.foodName.equals(foodName) && this.foods != null) {
//            return foods;
//        }

        // Search Query
        String url = API_Search + API_Key + "&query=" + foodName;

        // Format into a volley GET Request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Each search will create a new foods list
                    foods = new ArrayList<FDCFood>();

                    Log.i("WebServiceResponse-totalHits", response.get("totalHits").toString());

                    // Parse the JSONArray from response
                    JSONArray foodsArray = response.getJSONArray("foods");

                    // Iterate through the foods list from API response
                    for (int i = 0; i < foodsArray.length(); i++) {
                        FDCFood food = new FDCFood();
                        JSONObject jfood = foodsArray.getJSONObject(i);
                        if (jfood.getString("fdcId").isEmpty()) {
                            continue;
                        }
                        // Set values into Food fields
                        food.setFdcId(jfood.get("fdcId").toString());
                        food.setDescription(jfood.get("description").toString());
                        JSONArray nutrientsArray = jfood.getJSONArray("foodNutrients");
                        List<FDCFoodNutrient> nutrients = new ArrayList<>();

                        // Iterate through the nutrients list from API response
                        for (int j = 0; j < nutrientsArray.length(); j++) {
                            JSONObject jnutrient = nutrientsArray.getJSONObject(j);
                            if (jnutrient.getString("value").isEmpty()) {
                                continue;
                            }
                            // Set values into FDCFoodNutrient fields
                            FDCFoodNutrient foodNutrient = new FDCFoodNutrient();
                            foodNutrient.setAmount(Double.parseDouble(jnutrient.getString("value")));
                            FDCNutrient nutrient = new FDCNutrient();
                            // Set values into FDCNutrient fields
                            nutrient.setName(jnutrient.getString("nutrientName"));
                            nutrient.setUnitName(jnutrient.getString("unitName"));
                            foodNutrient.setNutrient(nutrient);
                            nutrients.add(foodNutrient);
                        }
                        food.setFoodNutrients(nutrients);
                        foods.add(food);
                    }
                    Log.i("WebServiceResponse-Foods.size", "" + foods.size());
                    Log.i("WebServiceResponse-FdcID", foods.get(0).getFdcId());
                    Log.i("WebServiceResponse-Description", foods.get(0).getDescription());
                    Log.i("WebServiceResponse-1stNutrients", foods.get(0).getFoodNutrients().get(0).getNutrient().getName());
                    Log.i("WebServiceResponse-10thNutrients", foods.get(0).getFoodNutrients().get(9).getNutrient().getName());
                    fdcID = foods.get(0).getFdcId();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Return response as the first fdcID from foods list
                volleyResponseListener.onResponse(fdcID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("WebServiceResponse", "Response ERROR");
                volleyResponseListener.onResponse("ERROR");

            }
        });

        // Call Request Queue, if null then initiate it in MySingleton
        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    public List<FDCFood> getFoods() {
        return foods;
    }
}

