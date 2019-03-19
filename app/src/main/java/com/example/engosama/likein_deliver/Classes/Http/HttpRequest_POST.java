package com.example.engosama.likein_deliver.Classes.Http;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Client.Deliver;
import com.example.engosama.likein_deliver.Classes.Client.User;
import com.example.engosama.likein_deliver.Classes.Client.User_Info;
import com.example.engosama.likein_deliver.Classes.Image_Folder.IMG;
//import com.example.engosama.likein_deliver.Classes.Upload_Image.MultipartRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class HttpRequest_POST {
    public static User_Info user_info;
    public void Post_Login(final Context mcontext, final String email,
                           final String password){//, final String fcmtoken
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        String url = "https://likenapi.herokuapp.com/Driver/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject Jobject = null;
                try {
                    Jobject = new JSONObject(response);
                    Log.i("response_Login",response);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                try {
                    String msg=Jobject.getString("message");
                    Log.i("message",msg);
                    if (msg.equals("تم تسجيل الدخول بنجاح")) {

                        Deliver deliver = new Deliver(Jobject.getJSONObject("items"));

                        /***********************************/
//                        SharedPreferences sp2 = mcontext.getSharedPreferences("Login", MODE_PRIVATE);
//                        String fcmToken =sp2.getString("fcmToken","null");
                        /** For token and fcmtoken*/
                        try{
                            SharedPreferences sp = mcontext.getSharedPreferences("Login", MODE_PRIVATE);
                            SharedPreferences.Editor Ed=sp.edit();
//                            Ed.putString("fcmToken",fcmToken);
                            Ed.putString("token",deliver.getToken());
                            Ed.putString("_id",deliver.get_id());
                            Log.i("_id",deliver.get_id());
                            Log.i("token_Login",deliver.getToken());
                            Ed.commit();
                        }catch (Exception e){
                            Toast.makeText(mcontext, "Error in SharedPreference!", Toast.LENGTH_SHORT).show();
                            Log.i("SharedPreference",e.getMessage());
                        }
                        Intent intent = new Intent(mcontext,Requests.class);
//                        intent.putExtra("email",email);
//                        intent.putExtra("password",password);
//                        intent.putExtra("firebaseToken",firebaseToken);

                        mcontext.startActivity(intent);
//

                    }else {
                        Toast.makeText(mcontext, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                    Log.i("Jobject:Error",e1.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mcontext, "Error: " + "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("password",password);
                Log.i("Email:",email);
                Log.i("Password:",password);

                SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
                String fcmToken =sp2.getString("fcmToken","null");
//                String token =sp2.getString("token","null");
                param.put("fcmtoken",fcmToken);
//                param.put("token",token);
                Log.i("fcmtoken:",fcmToken);
//                Log.i("token",token);
                return param;
            }

//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String,String> headers = new HashMap<>();
//                SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
//                String token =sp2.getString("token","null");
//                headers.put("token",token);
//                headers.put("Content-Type","application/x-www-form-urlencoded");
//                Log.i("token",token);
//                return headers;
//            }
        };
        queue.add(stringRequest);
    }

    public void Post_Logout(final Context mcontext){//, final String fcmtoken
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        String url = "https://likenapi.herokuapp.com/Driver/logout";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(mcontext, "response: " + response, Toast.LENGTH_LONG).show();
                JSONObject Jobject = null;
                try {
                    Jobject = new JSONObject(response);
                    Log.i("response:",response);
                } catch (JSONException e1) {
//                    e1.printStackTrace();
                    Log.i("Error:",e1.getMessage());
                }
                try {
                    String msg=Jobject.getString("message");
                    Log.i("message",msg);
                    if (msg.equals("تم تسجيل الخروج بنجاح")) {
                        Deliver deliver = new Deliver(Jobject.getJSONObject("items"));
                         Log.i("Jobject-data:",Jobject.toString());
                         Log.i("User-token",deliver.getToken());
                        /***********************************/

//                        Intent intent = new Intent(mcontext, Login.class);
//                        mcontext.startActivity(intent);

                    }else {
                        Toast.makeText(mcontext, msg,
                                Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                    Log.i("Jobject:Error",e1.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mcontext, "Error: " + "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
                String fcmToken =sp2.getString("fcmToken","null");
                String token =sp2.getString("token","null");

                param.put("token",token);
                param.put("fcmToken",fcmToken);
                Log.i("token-logout",token);
                Log.i("fcmToken-logout",fcmToken);
                return param;
            }
        };
        queue.add(stringRequest);
    }
    /**
//    public void RequestMultiPart(File file, String filename, String boundary, String url, String fileField, Map<String,String> params, final ApiResponse<String> completion ) {
//
//        final String reqUrl ="https://likenapi.herokuapp.com/Driver/file_upload";
//        MultipartRequest imageUploadReq = new MultipartRequest(reqUrl,params,file,filename,fileField,
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Multipart Request Url: ", reqUrl);
//                        Log.d("Multipart ERROR", "error => " + error.toString());
//                        completion.onCompletion(error.toString());
//                        displayVolleyResponseError(error);
//                    }
//                },
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("MediaSent Response", response);
//                        completion.onCompletion(response);
//
//                    }
//                }
//        ) {
//
//            // The following method sets the cookies in the header, I needed it for my server
//            // but you might want to remove it if it is not useful in your case
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                CookieManager manager = AppController.getInstance().getCookieManager();
//                List<HttpCookie> cookies = manager.getCookieStore().getCookies();
//                String cookie = "";
//                for (HttpCookie eachCookie : cookies) {
//                    String cookieName = eachCookie.getName().toString();
//                    String cookieValue = eachCookie.getValue().toString();
//                    cookie += cookieName + "=" + cookieValue + "; ";
//                }
//                headers.put("Cookie", cookie);
//                return headers;
//            }
//
//        };
//
//        imageUploadReq.setRetryPolicy(new DefaultRetryPolicy(1000 * 60, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        AppController.getInstance().addToRequestQueue(imageUploadReq);
//    }*/

/**########################## PUT *************************************************/
    public void Put_edit_requestCase(final Context mcontext,final String _id, final CallBackResponse callBackResponse, final int StatusId) {

        String url = "https://likenapi.herokuapp.com/order/updateMyOrder/"+_id;
        final RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        StringRequest response = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBackResponse.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResponse.errorResponse(error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> parameters = new HashMap<>();
                SharedPreferences sp2= mcontext.getSharedPreferences("Login", MODE_PRIVATE);
                String token =sp2.getString("token","null");
                parameters.put("token", token);
                parameters.put("Content-Type", "application/x-www-form-urlencoded");
                Log.i("getHeaders","Passed!!");
                return parameters;
            }

            @Override
            protected Map<String, String> getParams() {
                Log.i("getParams", "PASSED!!!!!");
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("StatusId", StatusId+"");

                return parameters;
            }


        };
        queue.add(response);
    }
    public void Put_update_prof(final Context mcontext, final CallBackResponse callBackResponse) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/Driver/updateprofile/"+_id;    //5c4b46e9b514e40017bd6c81
        final RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context


        StringRequest response = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBackResponse.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResponse.errorResponse(error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Log.i("getHeaders","Passed!!!");
                Map<String, String> parameters = new HashMap<String, String>();
                SharedPreferences sp2= Other_Activity.context.getSharedPreferences("Login", MODE_PRIVATE);
                String token =sp2.getString("token","null");
//                parameters.put("token", token);
                parameters.put("token", token);
//                parameters.put("Content-Type", "application/x-www-form-urlencoded");
                Log.i("getHeaders2","Passed too!!");

                return parameters;
            }

            @Override
            protected Map<String, String> getParams() {
//            return  callBackResponse.getParams_interface();
                Log.i("getParams", "PASSED!!!!!");
                Map<String, String> parameters = new HashMap<String, String>();
                SharedPreferences sp2= Other_Activity.context.getSharedPreferences("Deliver", MODE_PRIVATE);
                String email =sp2.getString("email",null);
                SharedPreferences sp1= mcontext.getSharedPreferences("Login",MODE_PRIVATE);
                String fcmToken =sp1.getString("fcmToken",null);
                String image0 =sp2.getString("images[0]",null);
                String image1 =sp2.getString("images[1]",null);
                String image2 =sp2.getString("images[2]",null);
                String name =sp2.getString("name",null);
                String image =sp2.getString("image",null);
                String address =sp2.getString("address",null);
                String phone_number =sp2.getString("phone_number",null);


                if(fcmToken != null)
                    parameters.put("fcmToken", fcmToken);
                if(image0 != null)
                    parameters.put("images[0]", image0);
                if(image1 != null)
                    parameters.put("images[1]", image1);
                if(image2 != null)
                    parameters.put("images[2]", image2);
                if(image != null)
                    parameters.put("image", image);

                parameters.put("email", email);
                parameters.put("name", name);
                parameters.put("address", address);
                parameters.put("phone_number", phone_number);

                Log.i("emailXXX", email+"0");
                Log.i("fcmTokenXXX", fcmToken+"0");
                Log.i("images[0]XXX", image0+"0");
                Log.i("images[1]XXX", image1+"0");
                Log.i("images[2]XXX", image2+"0");
                Log.i("UserNamePUTXXX", name+"0");
                Log.i("imageXXX", image+"0");
                Log.i("addressXXX", address+"0");
                Log.i("phone_numberXXX", phone_number+"0");

                return parameters;
            }


        };

        queue.add(response);

    }

    public void Put_refrish_fcmToken(final Context mcontext) {
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        String url = "https://likenapi.herokuapp.com/Driver/refreshtoken";//"http://httpbin.org/post";

        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
//                        Log.d("Response", response);
                        JSONObject Jobject = null;
                        try {
                            Jobject = new JSONObject(response);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            String msg = Jobject.getString("message");
                            Log.d("Response_mssg", msg);
//                            if (msg.equals("")) {
                            Deliver deliver = new Deliver(Jobject.getJSONObject("items"));
//                            Toast.makeText(mcontext, "Saved!"+"id" + deliver.get_id(), Toast.LENGTH_SHORT).show();
                            Log.i("PUT-Profile","Succsess");
//                            } else {
//                                Toast.makeText(mcontext, msg,
//                                        Toast.LENGTH_SHORT).show();
//                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            Log.i("Error_PutProfile",e1.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.i("Error.Response", String.valueOf(error.getMessage()));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
                String fcmToken =sp2.getString("fcmToken","null");
//                String token =sp2.getString("token","null");
                parameters.put("fcmToken", fcmToken);
                Log.i("fcmToken:",fcmToken);
                return parameters;
            }
        };
        queue.add(postRequest);

//        // prepare the Request

    }
    /**####################################################################################*/
//
//    public void Post_create_user(final Context mcontext,final String email,
//                                 final String password,
//                                 final Fragment mfragment,String fcmtoken) {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/Driver/login";
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                        String jsonData = response;
//                        Toast.makeText(mcontext, "jsonData= "+jsonData, Toast.LENGTH_LONG).show();
//                        JSONObject Jobject = null;
//                        try {
//                            Jobject = new JSONObject(jsonData);
//                        } catch (JSONException e1) {
//                            Toast.makeText(mcontext, e1.getMessage(), Toast.LENGTH_SHORT).show();
//                            e1.printStackTrace();
//                        }
//                        try {
//
//                            String msg = Jobject.getString("message");
//                            Log.d("Response_mssg", msg);
//                            if (msg.equals("return succssfully")) {
//                                User _user = new User(Jobject.getJSONObject("items"));
//                                Toast.makeText(mcontext, "id" + _user.getId() + "verfy code " + _user.getVerifyCode(),
//                                        Toast.LENGTH_LONG).show();
//                                    mcontext.startActivity(new Intent(mcontext, Requests.class));
////                                Login.fragmentManager = mfragment.getFragmentManager();
////                                Login.fragmentTransaction = Login.fragmentManager.beginTransaction();
////                                /**     phone_no_et.getText().toString()    */
////                                Login.fragment = new Conf_Login(_user);
////                                Login.fragmentTransaction.replace(R.id.fragment_container, Login.fragment);
////                                Login.fragmentTransaction.commit();
//                                Intent intent = new Intent(mcontext,Requests.class);
//                                intent.putExtra("email", "0");
//                                intent.putExtra("password", "0");
//                                mcontext.startActivity(intent);
//                            } else {
////                                Toast.makeText(mcontext, msg,
////                                        Toast.LENGTH_SHORT).show();
////                                Toast.makeText(mcontext, "WWW", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            Toast.makeText(mcontext, "FFFFF", Toast.LENGTH_SHORT).show();
//                        }
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
//
//                parameters.put("phone_number", phone_nb);
//                parameters.put("full_name", Fullname);
//                parameters.put("email", email);
//
//                SharedPreferences sp2 = mcontext.getSharedPreferences("Location", MODE_PRIVATE);
//
//                parameters.put("lat", Double.toString(sp2.getLong("lat", 0)));
//                parameters.put("lng", Double.toString(sp2.getLong("lng", 0)));
//                parameters.put("city", sp2.getString("address", ""));
//                return parameters;
//            }
//        };
//        queue.add(postRequest);
//
//        // prepare the Request
//
//    }

//    public void Post_send_Order(final Context mcontext,final AddOrder Order_,final String token ,final Onaddorder listener) throws JSONException {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/order/addOrder";//"http://httpbin.org/post";
//
//        // POST parameters
//        String param="{" +
//                "user_id:5bfaa7c71850eb001614cf08," +
//                "deliveryCost:30," +
//                "subTotal:120," +
//                "paymentType:2," +
//                "coupon:S9WPCb," +
//                "isBasket:false," +
//                "BasketId:5c0d41931bd7a277ab1feb52," +
//                "delivery_date:01-12-2019," +
//                "delivery_id:5bf96eed3015c60016e3cc49," +
//                "addressDetails:address Details  here," +
//                "lat:36.5459684," +
//                "lng:3.3568953," +
//                "Notes:Notes Notes Notes," +
//                "StatusId:1," +
//                "deliveryOption_id:5bf96f1c3015c60016e3cc4d," +
//                "items:[{" +
//                "supplier_id:5bfa59301504700016c0cd20," +
//                "product_id:5bfb04d43794590016055590," +
//                "qty: 12," +
//                "price_name:حبة," +
//                "price:10," +
//                "isOffer:false," +
//                "isOfferQuota:false" +
//                "}]" +
//                "}";
//        JSONObject cart=Order_.toJsonObject2();
//
//// Request a json response from the provided URL
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.POST, url, cart,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        try {
//                            listener.onSuccess(new root(jsonObject).getMessage());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener (){
//
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                listener.onFailure(volleyError.toString());
//            }
//        }
//        ){
//            @Override
//            public Map<String, String> getHeaders()  {
//                Map<String, String>  Headers = new HashMap<String, String>();
//                Headers.put("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
//                        ".eyJfaWQiOiI1YmZhYTdjNzE4NTBlYjAwMTYxNGNmMDgiLCJpYXQiOjE1" +
//                        "NDYxOTQxOTksImV4cCI6MTU3NzczMDE5OX0." +
//                        "OPYRYbXwR2uKD2BW4mpbFcXJT9G85aaB2q8zvJFlGBY");
//                Headers.put("Accept","application/json");
//                Headers.put("Content-Type","application/json");
//return Headers;
//            }
//        };
//
//
//// Add the request to the RequestQueue.
//        queue.add(jsonObjectRequest);
//        // prepare the Request
//
//    }
//    public void Post_send_Order(final Context mcontext,final AddOrder Order_,final String token ,final Onaddorder listener) throws JSONException {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/order/addOrder";//"http://httpbin.org/post";
//
//        // POST parameters
//        String param="{" +
//                "user_id:5bfaa7c71850eb001614cf08," +
//                "deliveryCost:30," +
//                "subTotal:120," +
//                "paymentType:2," +
//                "coupon:S9WPCb," +
//                "isBasket:false," +
//                "BasketId:5c0d41931bd7a277ab1feb52," +
//                "delivery_date:01-12-2019," +
//                "delivery_id:5bf96eed3015c60016e3cc49," +
//                "addressDetails:address Details  here," +
//                "lat:36.5459684," +
//                "lng:3.3568953," +
//                "Notes:Notes Notes Notes," +
//                "StatusId:1," +
//                "deliveryOption_id:5bf96f1c3015c60016e3cc4d," +
//                "items:[{" +
//                "supplier_id:5bfa59301504700016c0cd20," +
//                "product_id:5bfb04d43794590016055590," +
//                "qty: 12," +
//                "price_name:حبة," +
//                "price:10," +
//                "isOffer:false," +
//                "isOfferQuota:false" +
//                "}]" +
//                "}";
//        JSONObject cart=Order_.toJsonObject2();
//
//// Request a json response from the provided URL
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.POST, url, cart,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        try {
//                            listener.onSuccess(new root(jsonObject).getMessage());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener (){
//
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                listener.onFailure(volleyError.toString());
//            }
//        }
//        ){
//            @Override
//            public Map<String, String> getHeaders()  {
//                Map<String, String>  Headers = new HashMap<String, String>();
//                Headers.put("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
//                        ".eyJfaWQiOiI1YmZhYTdjNzE4NTBlYjAwMTYxNGNmMDgiLCJpYXQiOjE1" +
//                        "NDYxOTQxOTksImV4cCI6MTU3NzczMDE5OX0." +
//                        "OPYRYbXwR2uKD2BW4mpbFcXJT9G85aaB2q8zvJFlGBY");
//                Headers.put("Accept","application/json");
//                Headers.put("Content-Type","application/json");
//return Headers;
//            }
//        };
//
//
//// Add the request to the RequestQueue.
//        queue.add(jsonObjectRequest);
//        // prepare the Request
//
//    }

//    public void Post_create_user(final Context mcontext, final String Fullname,
//                                 final String phone_nb,final String email,
//                                 final String gender,final String password,
//                                 final Fragment mfragment) {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/User/add";//"http://httpbin.org/post";
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                        String jsonData = response;
//                        Toast.makeText(mcontext, jsonData, Toast.LENGTH_LONG).show();
//                        JSONObject Jobject = null;
//                        try {
//                            Jobject = new JSONObject(jsonData);
//                        } catch (JSONException e1) {
//                            Toast.makeText(mcontext, "ZZZZZ", Toast.LENGTH_SHORT).show();
//                            e1.printStackTrace();
//                        }
//                        try {
//
//                            String msg = Jobject.getString("message");
//                            Log.d("Response_mssg", msg);
//                            if (msg.equals("return succssfully")) {
//                                User _user = new User(Jobject.getJSONObject("items"));
//                                Toast.makeText(mcontext, "id" + _user.getId() + "verfy code " + _user.getVerifyCode(),
//                                        Toast.LENGTH_LONG).show();
//                                    mcontext.startActivity(new Intent(mcontext, Requests.class));
////                                Login.fragmentManager = mfragment.getFragmentManager();
////                                Login.fragmentTransaction = Login.fragmentManager.beginTransaction();
////                                /**     phone_no_et.getText().toString()    */
////                                Login.fragment = new Conf_Login(_user);
////                                Login.fragmentTransaction.replace(R.id.fragment_container, Login.fragment);
////                                Login.fragmentTransaction.commit();
//                                Intent intent = new Intent(mcontext,Requests.class);
//                                intent.putExtra("email", "0");
//                                intent.putExtra("password", "0");
//                                mcontext.startActivity(intent);
//                            } else {
////                                Toast.makeText(mcontext, msg,
////                                        Toast.LENGTH_SHORT).show();
////                                Toast.makeText(mcontext, "WWW", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            Toast.makeText(mcontext, "FFFFF", Toast.LENGTH_SHORT).show();
//                        }
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
//
//                parameters.put("phone_number", phone_nb);
//                parameters.put("full_name", Fullname);
//                parameters.put("email", email);
//
//                SharedPreferences sp2 = mcontext.getSharedPreferences("Location", MODE_PRIVATE);
//
//                parameters.put("lat", Double.toString(sp2.getLong("lat", 0)));
//                parameters.put("lng", Double.toString(sp2.getLong("lng", 0)));
//                parameters.put("city", sp2.getString("address", ""));
//                return parameters;
//            }
//        };
//        queue.add(postRequest);
//
//        // prepare the Request
//
//    }

//    public void Post_ActvitPhone_user(final Context mcontext, final String id,
//                                       final String token, final Onusercreater listenner) {//final String cod,
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/user/verify";//"http://httpbin.org/post";
//        listenner.onStart();
//        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                        String jsonData = response;
//                        JSONObject Jobject = null;
//                        try {
//                            activate_user activate_user_ = new activate_user(new JSONObject(jsonData));
//                            listenner.onSuccess(activate_user_.getusers(), activate_user_.getMessage());
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                        listenner.onFailure(error.getMessage());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parameters = new HashMap<String, String>();
////
//                parameters.put("id", id);
////                parameters.put("verify_code", cod);
////                parameters.put("fcmToken", token);
//
//                return parameters;
//            }
//        };
//        queue.add(postRequest);
//
//        // prepare the Request
//
//    }


    public void Post_test_coupon(final Context mcontext, final String coupon_txt, final OnGetDataListener listener) {
//        listener.onStart();
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "http://likenapi.herokuapp.com/coupon/checkCoupon";//"http://httpbin.org/post";
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
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
//                            test_coupon test_coupon_ = new test_coupon(Jobject);
//
//                            if (test_coupon_.getStatusCode() == 200) {
//
//                                listener.onSuccess(test_coupon_);
//
//                            } else {
//
//                                listener.onFailure(test_coupon_.getMessage());
//
//                            }
////                            mOrder_.setmCoupon(test_coupon_.getItems());
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            listener.onFailure(e1.getMessage());
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                        listener.onFailure("Error.Response");
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parameters = new HashMap<String, String>();
////
//                parameters.put("coupon", coupon_txt);
//
//                return parameters;
//            }
//        };
//        queue.add(postRequest);

        // prepare the Request

    }

    public void Post_Offer_discount(final Context mcontext, final OnGetDataListener listener) {
//        listener.onStart();
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        ;//"http://httpbin.org/post";
//        final String url = "https://likenapi.herokuapp.com/Supplier/supplierOfferProducts?page=0&limit=10";
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
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
//                            listener.onFailure(e1.getMessage());
//                        }
//                        try {
//                            show_offers show_offers_ = new show_offers(Jobject);
//                            listener.onSuccess(show_offers_);
//
//
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                            listener.onFailure(e1.getMessage());
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                        listener.onFailure("Error.Response");
//                    }
//                }
//        );
//        queue.add(postRequest);

        // prepare the Request

    }

    public void Post_GetlikeProduct(final Context mcontext, final String category_id, final String product_id, final OnGetProductListener listener) {
//        listener.onStart();
//        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
//        String url = "https://likenapi.herokuapp.com/Supplier/relatedPoductsInOtherSupplier?page=1&limit=10";//"http://httpbin.org/post";
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
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
//                        product_from_catigories Prod = new product_from_catigories(Jobject);
//                        listener.onSuccess(Prod.getproducts());
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", String.valueOf(error.getMessage()));
//                        listener.onFailure("Error.Response");
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parameters = new HashMap<String, String>();
////
//                parameters.put("product_id", product_id);
//                parameters.put("category_id", category_id);
//
//                return parameters;
//            }
//        };
//        queue.add(postRequest);

        // prepare the Request

    }

//    public void Post_Liken(final Context mcontext, List<liken_param> params, final OnLikenListener listener) {
////        listener.onStart();
////        RequestQueue queue = Volley.newRequestQueue(mcontext);
////        String url = "https://likenapi.herokuapp.com/Supplier/liken";
////
////        // POST parameters
////        JSONObject cart = new JSONObject();
////        if (params != null && params.size() > 0) {
////            JSONArray itemsJsonArray = new JSONArray();
////            for (liken_param itemsElement : params) {
////                itemsJsonArray.put(itemsElement.toJsonObject());
////            }
////            try {
////                cart.put("cart", itemsJsonArray);
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////        }
////// Request a json response from the provided URL
////        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
////                Request.Method.POST, url, cart,
////                new Response.Listener<JSONObject>() {
////                    @Override
////                    public void onResponse(JSONObject jsonObject) {
////                        Liken liken_ = new Liken(jsonObject);
////                        listener.onSuccess(liken_.getItems());
////                        listener.onSuccess(liken_.getMessage());
////                    }
////                }, new Response.ErrorListener() {
////
////            @Override
////            public void onErrorResponse(VolleyError volleyError) {
////                listener.onFailure(volleyError.toString());
////            }
////        }
////        );
////
////
////// Add the request to the RequestQueue.
////        queue.add(jsonObjectRequest);
//
//    }


    public interface Onusercreater {
        void onSuccess(Deliver deliver, String msg);

        void onStart();

        void onFailure(String msg);
    }

    public interface OnGetProductListener {
//        //this is for callbacks
//        void onSuccess(List<product> product);
//
//        void onStart();
//
//        void onFailure(String msg);
    }

    public interface OnLikenListener {
//        //this is for callbacks
//        void onSuccess(List<liken_haybers> liken_haybers);
//
//        void onSuccess(String msg);
//
//        void onStart();
//
//        void onFailure(String msg);
    }

    public interface OnGetDataListener {
//        //this is for callbacks
//        void onSuccess(test_coupon mTest_coupon);
//
//        void onSuccess(show_offers Offers);
//
//        void onStart();
//
//        void onFailure(String msg);
    }

    public interface Onaddorder {
//        void onSuccess(String msg);
//
//        void onStart();
//
//        void onFailure(String msg);
    }
}
