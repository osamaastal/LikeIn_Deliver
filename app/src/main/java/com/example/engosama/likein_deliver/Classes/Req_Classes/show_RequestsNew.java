package com.example.engosama.likein_deliver.Classes.Req_Classes;

import android.util.Log;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class show_RequestsNew {
    private List<ReqCompletedClass> requestsList;
    private boolean status;
    private String message;
    private int statusCode;

    public List<ReqCompletedClass> getRequestsList() {
        return requestsList;
    }

    public void setRequestsList(List<ReqCompletedClass> requestsList) {
        this.requestsList = requestsList;
    }

    public show_RequestsNew(JSONObject jsonObject) {
        this.requestsList = new ArrayList<>();
        if (jsonObject==null)
        {return;}
        Log.d("Response", "one");
        JSONArray requestJsonArray = jsonObject.optJSONArray("items");
        if(requestJsonArray != null){
            int quantity = requestJsonArray.length();
            for (int i = 0; i < requestJsonArray.length(); i++) {

                JSONObject requestObject = requestJsonArray.optJSONObject(i);
                if(requestObject.optInt("StatusId") == 4){
                    requestsList.add(new ReqCompletedClass(requestObject,quantity));
                    Log.d("Response", "two");
                }

            }

        }
        message = jsonObject.opt("message").toString();
        status = jsonObject.optBoolean("status");
        statusCode = jsonObject.optInt("status_code");
    }
//    public static void RemoveThisItem(ReqCompletedClass reqItem){
//        requestsList.remove(reqItem);
//    }

}

