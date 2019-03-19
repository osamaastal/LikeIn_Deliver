package com.example.engosama.likein_deliver.Classes.About_us;

import org.json.JSONException;
import org.json.JSONObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.*;
import java.util.*;


public class Conditions{

    private List<item> items;
    private String message;
    private boolean status;
    private int statusCode;

    public void setItems(List<item> items){
        this.items = items;
    }
    public List<item> getItems(){
        return this.items;
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
    public Conditions(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        JSONArray itemsJsonArray = jsonObject.optJSONArray("items");
        if(itemsJsonArray != null){
            items = new ArrayList<>();
            for (int i = 0; i < itemsJsonArray.length(); i++) {
                JSONObject itemsObject = itemsJsonArray.optJSONObject(i);
                items.add(new item(itemsObject));
            }

        }
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
            if(items != null && items.size() > 0){
                JSONArray itemsJsonArray = new JSONArray();
                for(item itemsElement : items){
                    itemsJsonArray.put(itemsElement.toJsonObject());
                }
                jsonObject.put("items", itemsJsonArray);
            }
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

class item{

   private String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String content,title;


   public void setId(String id){
       this.id = id;
   }
   public String getId(){
       return this.id;
   }


   /**
    * Instantiate the instance using the passed jsonObject to set the properties values
    */
   public item(JSONObject jsonObject){
       if(jsonObject == null){
           return;
       }
       id = jsonObject.opt("_id").toString();
       title = jsonObject.opt("title").toString();
       content = jsonObject.opt("content").toString();

   }

   /**
    * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
    */
   public JSONObject toJsonObject()
   {
       JSONObject jsonObject = new JSONObject();
       try {
           jsonObject.put("_id", id);
           jsonObject.put("title", title);
           jsonObject.put("content", content);

       } catch (JSONException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return jsonObject;
   }

}