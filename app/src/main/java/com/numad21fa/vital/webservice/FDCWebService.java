package com.numad21fa.vital.webservice;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.numad21fa.vital.models.FDCFood;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.List;

public class FDCWebService {
    final String API_Search = "https://api.nal.usda.gov/fdc/v1/foods/search?dataType=Foundation";
    final String API_Key;
    String foodName;
    List<FDCFood> foods;


    public FDCWebService() {
        API_Key = "&api_key=";
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
    }


    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String foodName);
    }

    public List<FDCFood> searchFood(String foodName, VolleyResponseListener volleyResponseListener) {

        if (this.foodName.equals(foodName) && this.foods != null) {
            return foods;
        }
        String url = API_Search + API_Key + "&query=" + foodName;
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=zelNdbGKFObd921h7Za0hXvKE2HHK9GDPHhb6a4V&query=spinach&dataType=Foundation")
//                .method("GET", null)
//                .build();
//        Response response = client.newCall(request).execute();

        // get json array
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject foodInfo = response.getJSONObject(0);
//                    foodName = foodInfo.getString("woeid");
                    foods = (List<FDCFood>) foodInfo.getJSONArray("foods");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(foodName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onResponse("ERROR");

            }
        });
        this.foodName = foodName;
        return foods;
    }
}

