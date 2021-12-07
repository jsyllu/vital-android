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
//    String foodName;
    List<FDCFood> foods;
    Context context;
    String fdcID;


    public FDCWebService(Context context) {
        API_Key = "&api_key=zelNdbGKFObd921h7Za0hXvKE2HHK9GDPHhb6a4V";
        // TODO get API_KEY from KeyStore
        // https://developer.android.com/reference/java/security/KeyStore
//        try {
//            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//            char[] pwdArray = "password".toCharArray();
//            ks.load(null, pwdArray);
//
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
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
        String url = API_Search + API_Key + "&query=" + foodName;

        // get json array
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // TODO how to get correct response
                    Log.i("WebServiceResponse", response.get("totalHits").toString());
                    // Response go through, cast to models
                    // foods = (List<FDCFood>) response.getJSONArray("foods");
                    JSONArray foodsArray = response.getJSONArray("foods");
                    for (int i = 0; i < foodsArray.length(); i++) {
                        FDCFood food = new FDCFood();
                        JSONObject jfood = foodsArray.getJSONObject(i);
                        if (jfood.getString("fdcId").isEmpty()) {
                            continue;
                        }
                        food.setFdcId(jfood.get("fdcId").toString());
                        food.setDescription(jfood.get("description").toString());
                        JSONArray nutrientsArray = jfood.getJSONArray("foodNutrients");
                        List<FDCFoodNutrient> nutrients = new ArrayList<>();
                        for (int j = 0; j < nutrientsArray.length(); j++) {
                            JSONObject jnutrient = nutrientsArray.getJSONObject(i);
                            if (jnutrient.getString("value").isEmpty()) {
                                continue;
                            }
                            FDCFoodNutrient foodNutrient = new FDCFoodNutrient();
                            foodNutrient.setAmount(Double.parseDouble(jnutrient.getString("value")));
                            FDCNutrient nutrient = new FDCNutrient();
                            nutrient.setName(jnutrient.getString("nutrientName"));
                            nutrient.setUnitName(jnutrient.getString("unitName"));
                            foodNutrient.setNutrient(nutrient);
                            nutrients.add(foodNutrient);
                        }
                        food.setFoodNutrients(nutrients);
                        foods.add(food);
                    }
                    Log.i("WebServiceResponse-Foods.size", "" + foods.size());
                    Log.i("WebServiceResponse", foods.get(0).getFdcId());
                    Log.i("WebServiceResponse", foods.get(0).getDescription());
                    Log.i("WebServiceResponse", foods.get(0).getFoodNutrients().get(0).getNutrient().getName());


                } catch (Exception e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(foodName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("WebServiceResponse", "Response ERROR");
                volleyResponseListener.onResponse("ERROR");

            }
        });
//        this.foodName = foodName;
        // TODO url is correct, need to parse into models
        Log.i("WebActivity", String.valueOf(request));

        // Call Request Queue, if null then initiate it in MySingleton
        MySingleton.getInstance(context).addToRequestQueue(request);

    }
}

