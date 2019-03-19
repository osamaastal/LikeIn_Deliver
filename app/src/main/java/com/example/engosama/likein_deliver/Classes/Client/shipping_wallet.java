package com.example.engosama.likein_deliver.Classes.Client;

import org.json.JSONException;
import org.json.JSONObject;


public class shipping_wallet{

    private User users;
    private String message;
    private boolean status;
    private int statusCode;

    public void setusers(User users){
        this.users = users;
    }
    public User getusers(){
        return this.users;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean isStatus()
    {
        return this.status;
    }
    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }
    public int getStatusCode(){
        return this.statusCode;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public shipping_wallet(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        users = new User(jsonObject.optJSONObject("users"));
        message = jsonObject.opt("message").toString();
        status = jsonObject.optBoolean("status");
        statusCode = jsonObject.optInt("status_code");
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("users", users.toJsonObject());
            jsonObject.put("message", message);
            jsonObject.put("status", status);
            jsonObject.put("status_code", statusCode);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}