package com.example.engosama.likein_deliver.Classes.Client;

import android.util.Log;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Other_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Deliver {
    private String _id;
    private String address;
    private String createAt;
    private String email;
    private String name;
    private String dt_dob;
    private String image;
    private boolean isBlock;
    private ArrayList<String> images = new ArrayList<>();
    private String phone_number;
    private String supplier_id;
    private String token;
    private String password;
    private String fcmToken;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDt_dob() {
        return dt_dob;
    }

    public void setDt_dob(String dt_dob) {
        this.dt_dob = dt_dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public Deliver(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        _id = (String) jsonObject.opt("_id");
        address = jsonObject.opt("address").toString();
        createAt = jsonObject.opt("createAt").toString();
        email = jsonObject.opt("email").toString();
//        password = jsonObject.opt("password").toString();
        name = jsonObject.opt("name").toString();
        image = jsonObject.opt("image").toString();
        isBlock = jsonObject.optBoolean("isBlock");
        phone_number = jsonObject.opt("phone_number").toString();
        try {
            token = jsonObject.opt("token").toString();
        }catch (Exception e){
            Log.i("TokenError",e.getMessage());
        }
        fcmToken = jsonObject.opt("fcmToken").toString();
        dt_dob = jsonObject.opt("dt_dob").toString();
        supplier_id = jsonObject.opt("supplier_id").toString();
        try{
            JSONArray images_JSONArray = jsonObject.optJSONArray("images");
            for(int i=0 ;i< images_JSONArray.length();i++){
                try {
                    images.add(images_JSONArray.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
//            Toast.makeText(Other_Activity.context, "Error:"+e.getMessage(), Toast.LENGTH_LONG).show();

        }


    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", _id);
            jsonObject.put("address", address);
            jsonObject.put("createAt", createAt);
            jsonObject.put("email", email);
            jsonObject.put("name", name);
            jsonObject.put("image", image);
            jsonObject.put("isBlock", isBlock);
            jsonObject.put("phone_number", phone_number);
            jsonObject.put("token", token);
//            jsonObject.put("password", password);
            jsonObject.put("dt_dob", dt_dob);
//            jsonObject.put("images", images);
            jsonObject.put("supplier_id", supplier_id);
            jsonObject.put("fcmToken", fcmToken);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
