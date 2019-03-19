package com.example.engosama.likein_deliver.Classes.Http;

import org.json.JSONObject;

import java.util.Map;

public interface CallBackResponse {
    public void getResponse(JSONObject jsonObject);
    public void getResponse(String response);
    public void errorResponse(String error);
//    Map<String, String> getParams_interface();
//    Map<String, String> getHeaders_interface();
}
