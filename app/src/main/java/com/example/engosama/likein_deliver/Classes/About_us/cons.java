package com.example.engosama.likein_deliver.Classes.About_us;

/************************* Mo’min J.Abusaada *************************/
//
//	Item.java
import org.json.*;
import java.util.*;


public class cons{

    private String id;
    private String content;
    private String title;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public cons(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        id = jsonObject.opt("_id").toString();
        content = jsonObject.opt("content").toString();
        title = jsonObject.opt("title").toString();
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", id);
            jsonObject.put("content", content);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}