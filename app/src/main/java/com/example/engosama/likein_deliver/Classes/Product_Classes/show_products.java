package com.example.engosama.likein_deliver.Classes.Product_Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Classes.Product_Classes.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class show_products {
    private List<Product> productsList;
    private boolean status;
    private String message;
    private int statusCode;

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public show_products(JSONObject mainJObject) {
        this.productsList = new ArrayList<>();

        if (mainJObject == null) {
            return;
        }

//        Log.d("Respons", "one");
//        JSONObject mainJObject = null;
        JSONArray productJsonArray = null;
        try {
            /**get JSONArray "items" to show products**/
//             mainJObject = new JSONObject(response);
            SharedPreferences sp = Other_Activity.context.getSharedPreferences("ReqDetails", Context.MODE_PRIVATE);
            int position = sp.getInt("position", 0);
            productJsonArray = mainJObject.getJSONArray("items").getJSONObject(position).getJSONArray("items");

            Log.i("responseProduct", productJsonArray.get(0).toString());
        } catch (JSONException e) {
//            Log.i("responseProductError",response);
            Log.i("Error-ProductJSONArray", e.getMessage());
            e.printStackTrace();
        }

//        int quantity = productJsonArray.length();
//        Log.i("quntity_length", " = " + quantity);

        for (int i = 0; i < productJsonArray.length(); i++) {
            JSONObject productObject = productJsonArray.optJSONObject(i);
            Log.i("productObjectOOO",productObject.toString());
            productsList.add(new Product(productObject));
            Log.d("Respons", "new product " + i);
        }

        message = mainJObject.opt("message").toString();
        status = mainJObject.optBoolean("status");
        statusCode = mainJObject.optInt("status_code");
    }

}

