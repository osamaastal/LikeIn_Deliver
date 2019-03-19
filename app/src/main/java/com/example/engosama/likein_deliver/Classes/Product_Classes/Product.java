package com.example.engosama.likein_deliver.Classes.Product_Classes;

import android.util.Log;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Other_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Product {

    String _id;
    String price_name;
    double price;
    int qty;
    boolean isOffer;
    boolean isOfferQuota;
    /****************/
    String name;
    String image;
    /********************/

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getPrice_name() {
        return price_name;
    }
    public void setPrice_name(String price_name) {
        this.price_name = price_name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public boolean isOfferQuota() {
        return isOfferQuota;
    }
    public void setOfferQuota(boolean offerQuota) {
        isOfferQuota = offerQuota;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isOffer() {
        return isOffer;
    }
    public void setOffer(boolean offer) {
        isOffer = offer;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Product(){}
    public Product(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        Log.i("responseProduct",jsonObject.toString());
//        _id = jsonObject.optString("_id");
        try {
            price_name = jsonObject.getString("price_name");
        } catch (JSONException e) {
            e.printStackTrace();
            price_name="";
        }
//        Log.i("_idXX",_id);
//        Log.i("price_name",price_name);
        /****************/
        if (jsonObject.optJSONObject("product_id") != null
                && !jsonObject.optJSONObject("product_id").toString().equals("null")){
            try {
                image = jsonObject.getJSONObject("product_id").getString("image");
//                image = "https://unixtitan.net/images/pump-clipart-tubewell-5.png";
                name = jsonObject.getJSONObject("product_id").getString("name");
                _id = jsonObject.getJSONObject("product_id").getString("_id");
                Log.i("image",image+"");
                Log.i("name",name+"");
                Log.i("name",_id+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /****************/
        try{
            qty = jsonObject.getInt("qty");
            try{
                price = jsonObject.getDouble("price");
            }catch (Exception e ){
                price = 0;
            }

            isOfferQuota = jsonObject.getBoolean("isOfferQuota");
            isOffer = jsonObject.getBoolean("isOffer");
            Log.i("gty",qty+"");
            Log.i("price",price+"");
            Log.i("isOfferQuota",isOfferQuota+"");
            Log.i("isOffer",isOffer+"");
        }catch (Exception e){
            Toast.makeText(Other_Activity.context, "ERROR...OXO", Toast.LENGTH_SHORT).show();
            Log.i("OXO",e.getMessage());
        }

    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", _id);
            jsonObject.put("price_name", price_name);
            jsonObject.put("qty", qty);
            jsonObject.put("price", price);
            jsonObject.put("isOfferQuota", isOfferQuota);
            jsonObject.put("isOffer", isOffer);
            jsonObject.getJSONObject("product_id").put("name",name);
            jsonObject.getJSONObject("product_id").put("image",image);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }




}
