package com.example.engosama.likein_deliver.Classes.Req_Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import android.util.Log;

import com.example.engosama.likein_deliver.Activities.Requests;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class show_RequestsCanceled {
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

    public show_RequestsCanceled(JSONObject jsonObject) {
        this.requestsList = new ArrayList<>();
        if (jsonObject==null)
        {return;}
        Log.d("Response", "one");
        JSONArray requestJsonArray = jsonObject.optJSONArray("items");
//        SharedPreferences sp = Requests.context.getSharedPreferences("IDS", Context.MODE_PRIVATE);
//        SharedPreferences.Editor EP = sp.edit();
//        Set<String> set = null;
//        set.add("1");
//        set.add("2");
//        set.add("3");
//        set.add("4");
//        EP.putStringSet("ids",set);
//        EP.commit();
//
//        SharedPreferences sp1 = Requests.context.getSharedPreferences("IDS", Context.MODE_PRIVATE);
//        Map<String,Object> allIDS=sp1.getAll();
        ArrayList<String> allIDS = new ArrayList<>();

        if(requestJsonArray!= null){
            int quantity = requestJsonArray.length();
            for (int i = 0; i < requestJsonArray.length(); i++) {
                JSONObject requestObject = requestJsonArray.optJSONObject(i);
                if(requestObject.optInt("StatusId") == 8 || requestObject.optInt("StatusId") == 9){
                    requestsList.add(new ReqCompletedClass(requestObject,quantity));
                }
            }
        }
        message = jsonObject.opt("message").toString();
        status = jsonObject.optBoolean("status");
        statusCode = jsonObject.optInt("status_code");
    }

}

