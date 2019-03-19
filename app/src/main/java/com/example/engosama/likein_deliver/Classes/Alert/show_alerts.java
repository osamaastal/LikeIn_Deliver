package com.example.engosama.likein_deliver.Classes.Alert;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class show_alerts {
    private List<Alert> alertsList;
private boolean status;
private String message;
private int statusCode;
    public List<Alert> getAlertsList() {
        return alertsList;
    }

    public void setAlertsList(List<Alert> alertsList) {
        this.alertsList = alertsList;
    }

    public show_alerts(JSONObject jsonObject) {
        this.alertsList = new ArrayList<>();
        if (jsonObject==null)
        {return;}
        Log.d("Respons", "one");
        JSONArray alertJsonArray = jsonObject.optJSONArray("items");
        if(alertJsonArray != null){
            for (int i = 0; i < alertJsonArray.length(); i++) {
                JSONObject alertObject = alertJsonArray.optJSONObject(i);
                alertsList.add(new Alert(alertObject));
                Log.d("Respons", "tow");
            }
        }
        message = jsonObject.opt("message").toString();
        status = jsonObject.optBoolean("status");
        statusCode = jsonObject.optInt("status_code");
    }

}

