package com.example.engosama.likein_deliver.Classes.Http;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.engosama.likein_deliver.Classes.Client.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest_put {

//    public void Put_update_prof(final Context mcontext, final String phone_nb, final Fragment mfragment) {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/User/updateprofile";//"http://httpbin.org/post";
//
//        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                        String jsonData = response;
//                        JSONObject Jobject = null;
//                        try {
//                            Jobject = new JSONObject(jsonData);
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//                        try {
//
//                            String mssg = Jobject.getString("message");
//                            Log.d("Response_mssg", mssg);
//                            if (mssg.equals("return succssfully")) {
//                                User _user = new User(Jobject.getJSONObject("items"));
//
//                                Toast.makeText(mcontext, "id" + _user.getId(),
//                                        Toast.LENGTH_LONG).show();
//
//                            } else {
//                                Toast.makeText(mcontext, mssg,
//                                        Toast.LENGTH_SHORT).show();
//
//                            }
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parameters = new HashMap<String, String>();
////
//                parameters.put("_id", "5bfaa7c71850eb001614cf08");
//                parameters.put("name", "");
//                parameters.put("dt_dob", "");
//                parameters.put("email", "Gaza");
//                parameters.put("image", null);
//                parameters.put("address", xx);
//                parameters.put("phone_number", xx);
//                parameters.put("password", xx);
//                parameters.put("supplier_id", xx);
//                parameters.put("isBlock", xx);
//                parameters.put("fcmToken", xx);
//                parameters.put("token", xx);
//
//
//                return parameters;
//            }
//        };
//        queue.add(postRequest);
//
////        // prepare the Request
//
//    }



    /********************************************************************************/
//    public void Put_change_point(final Context mcontext, final String id, final Onpoint_listenner listener) throws JSONException {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        final User_Info userInfo_ = new User_Info(mcontext);
//        String url = "https://likenapi.herokuapp.com/UserPoint/UserPoint/" + userInfo_.getId();
//
//
//        listener.onStart();
//
////        Request a json response from the provided URL
//
//        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                        String jsonData = response;
//                        JSONObject Jobject = null;
//                        try {
//                            Jobject = new JSONObject(jsonData);
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//                        try {
////                            change_point change_point_ = new change_point(Jobject);
//                            listener.onSuccess(change_point_);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                        listener.onFailure(String.valueOf(error.getMessage()));
//                    }
//                }
//        ) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> Headers = new HashMap<String, String>();
//                Headers.put("token", userInfo_.getToken());
//                Headers.put("_id", id);
//// "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
////                        ".eyJfaWQiOiI1YmZhYTdjNzE4NTBlYjAwMTYxNGNmMDgiLCJpYXQiOjE1" +
////                        "NDYxOTQxOTksImV4cCI6MTU3NzczMDE5OX0." +
////                        "OPYRYbXwR2uKD2BW4mpbFcXJT9G85aaB2q8zvJFlGBY");
////                Headers.put("Accept","application/json");
////                Headers.put("Content-Type","application/json");
//                return Headers;
//            }
//        };
//        queue.add(postRequest);
//
//// Add the request to the RequestQueue.
//
//
//    }

    /****************************************************************************/
//    public void Put_change_point(final Context mcontext, final String id, final Onpoint_listenner listener) throws JSONException {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        final User_Info user_info_ = new User_Info(mcontext);
//        String url = "https://likenapi.herokuapp.com/UserPoint/UserPoint/" + user_info_.getId();
//
//
//        listener.onStart();
//
////        Request a json response from the provided URL
//
//        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                        String jsonData = response;
//                        JSONObject Jobject = null;
//                        try {
//                            Jobject = new JSONObject(jsonData);
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//                        try {
//                            change_point change_point_ = new change_point(Jobject);
//                            listener.onSuccess(change_point_);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                        listener.onFailure(String.valueOf(error.getMessage()));
//                    }
//                }
//        ) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> Headers = new HashMap<String, String>();
//                Headers.put("token", user_info_.getToken());
//                Headers.put("_id", id);
//// "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
////                        ".eyJfaWQiOiI1YmZhYTdjNzE4NTBlYjAwMTYxNGNmMDgiLCJpYXQiOjE1" +
////                        "NDYxOTQxOTksImV4cCI6MTU3NzczMDE5OX0." +
////                        "OPYRYbXwR2uKD2BW4mpbFcXJT9G85aaB2q8zvJFlGBY");
////                Headers.put("Accept","application/json");
////                Headers.put("Content-Type","application/json");
//                return Headers;
//            }
//        };
//        queue.add(postRequest);
//
//// Add the request to the RequestQueue.
//
//
//    }
    public interface Onpoint_listenner{
        void onSuccess(User  user);
//        void onSuccess(get_user_points get_user_points_);
        void onStart();
        void onFailure(String msg);
    }


}
