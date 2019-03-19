package com.example.engosama.likein_deliver.Classes.Http;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Adapters.AlertAdapter;
import com.example.engosama.likein_deliver.Adapters.ProductAdapter;
import com.example.engosama.likein_deliver.Adapters.ReqCanceledAdapter;
import com.example.engosama.likein_deliver.Adapters.ReqCompletedAdapter;
import com.example.engosama.likein_deliver.Adapters.ReqCurrentAdapter;
import com.example.engosama.likein_deliver.Adapters.ReqNewAdapter;
import com.example.engosama.likein_deliver.Classes.Alert.show_alerts;
import com.example.engosama.likein_deliver.Classes.Client.Deliver;
import com.example.engosama.likein_deliver.Classes.Client.Deliver_No_noken;
import com.example.engosama.likein_deliver.Classes.Product_Classes.show_products;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsCanceled;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsCompleted;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsCurrent;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsNew;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class HttpRequest {

    //Declare the URL you want to send
    public static String URL = "https://likenapi.herokuapp.com/User/add";//http://example.com.in/ai/abc?var=";
    public static JSONObject response;
    //public HttpRequest(final Map<String, String> parameters, String uri, final Context mcontext){

    public HttpRequest() {
    }

    public HttpRequest(final Map<String, String> parameters, final Map<String, String> headers, String uri, final Context mcontext) {
        //volley request
        this.URL = uri;
        //here you can choose your method,GET/POST,in this case is Post
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST,
                URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //if send data success do something here
                Toast.makeText(mcontext, "do it succ", Toast.LENGTH_LONG).show();
                HttpRequest.response = response;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //if having error to make request to server do something here
                Toast.makeText(mcontext, error.getMessage() + "error", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //send your parameter here

                Map<String, String> parameters = new HashMap<>();
//                parameters.put("yourVariable", "var");
                parameters.put("phone_number", "+966669203134");
                parameters.put("lat", "10.202020");
                parameters.put("lng", "10.2020202");
                parameters.put("city", "Gaza");

                return parameters;
            }

            //adding header to authenticate
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/x-www-form-urlencoded");

                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        requestQueue.add(jsonReq);

    }
    public void GetAlerts(final Context mcontext, final RecyclerView Rv,
                          final String adapter_type, final Fragment mFragment) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/Notifications/Notification/"+_id;
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("GetAlerts", response.toString());
                        Rv.setAdapter(new AlertAdapter(mcontext, new show_alerts(response).getAlertsList(), adapter_type, mFragment));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }
    public void CheckAlerts (final Context mcontext, final CallBackResponse callBackResponse){
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url  ="https://likenapi.herokuapp.com/Notifications/Notification/"+_id;
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBackResponse.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResponse.errorResponse(error.getMessage());
            }
        });
        queue.add(request);
    }
    public void GetReqCompleted(final Context mcontext, final CallBackResponse callBackResponse) {//final RecyclerView Rv, final String adapter_type, final Fragment mFragment
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/order/getMyOrdersDriver/"+_id+"/6";
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display response
                        callBackResponse.getResponse(response);
//                        Log.d("GetReqCompleted", response.toString());
//                        Rv.setAdapter(new ReqCompletedAdapter(mcontext, new show_RequestsCompleted(response).getRequestsList()
//                                , adapter_type, mFragment));
//                        Rv.getAdapter().notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBackResponse.errorResponse(error.getMessage());
//                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }

    public void GetReqCurrent(final Context mcontext, final CallBackResponse callBackResponse) {//final RecyclerView Rv, final String adapter_type, final Fragment mFragment
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/order/getMyOrdersDriver/"+_id+"/5";
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display response
                        Log.d("GetReqCompleted", response.toString());
                        callBackResponse.getResponse(response);
//                        Rv.setAdapter(new ReqCurrentAdapter(mcontext, new show_RequestsCurrent(response).getRequestsList()
//                                , adapter_type, mFragment));
//                        Rv.getAdapter().notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBackResponse.errorResponse(error.getMessage());
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);

    }
    public void GetReqNew(final Context mcontext, final RecyclerView Rv,
                                final String adapter_type, final Fragment mFragment,final ProgressBar progressBar) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/order/getMyOrdersDriver/"+_id+"/4";
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("res", response);
                try {
                    JSONObject object = new JSONObject(response);
                    Rv.setAdapter(new ReqNewAdapter(mcontext, new show_RequestsNew(object).getRequestsList()
                            , adapter_type, mFragment));
                    Rv.getAdapter().notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("res", "error "+ error.getMessage());

            }
        });
        // prepare the Request
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // display response
//                        Log.d("GetReqCompleted", response.toString());
//                        Rv.setAdapter(new ReqNewAdapter(mcontext, new show_RequestsNew(response).getRequestsList()
//                                , adapter_type, mFragment));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Error.Response", error.getMessage());
//                        Log.d("GetReqCompleted", response.toString());
//                    }
//                }
//        );

// add it to the RequestQueue
        queue.add(request);
    }
    public void GetReqCanceled(final Context mcontext, int xx, final RecyclerView Rv,
                               final String adapter_type, final Fragment mFragment, final ProgressBar progressBar) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/order/getMyOrdersDriver/"+_id+"/"+xx;
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("res", response);
                try {
                    JSONObject object = new JSONObject(response);

                    Rv.setAdapter(new ReqCanceledAdapter(mcontext, new show_RequestsCanceled(object).getRequestsList()
                            , adapter_type, mFragment));
//                    Rv.getAdapter().notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("res", "error "+ error.getMessage());

            }
        });

        // add it to the RequestQueue
        queue.add(request);
    }

    public void GetProducts(final Context mcontext,int type, final RecyclerView Rv,
                          final String adapter_type, final Fragment mFragment, final ProgressBar progressBar) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);

        String url = "https://likenapi.herokuapp.com/order/getMyOrdersDriver/"+_id+"/"+type;
        Log.i("URLProducts",url);
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display response
                        Log.i("getRequest", response);
                        try {
                            JSONObject object = new JSONObject(response);
                            Rv.setAdapter(new ProductAdapter(mcontext, new show_products(object).getProductsList(), adapter_type, mFragment));
                            Rv.getAdapter().notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error.Response", error.getMessage());
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }

    public void GetProfileData(final Context mcontext, final CallBackResponse callBackResponse) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/Driver/userprofile/"+_id;
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        StringRequest response = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
                Map<String, String> headers = new HashMap<>();
                SharedPreferences sp2=Other_Activity.context.getSharedPreferences("Login", MODE_PRIVATE);
                String token =sp2.getString("token","null");
                headers.put("Content-Type","application/x-www-form-urlencoded");
                headers.put("token",token);
                return headers;
            }

//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parameters = new HashMap<String, String>();
//                SharedPreferences sp2=Other_Activity.context.getSharedPreferences("Login", MODE_PRIVATE);
//                String token =sp2.getString("token","null");
//                parameters.put("token", token);
//                return parameters;
//            }
        };
        queue.add(response);

    }
    public void GetReqDetails(final Context mcontext,int type, final CallBackResponse callBackResponse) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);

//        SharedPreferences sp1 = mcontext.getSharedPreferences("ReqDetails",MODE_PRIVATE);
//        int type = sp1.getInt("type",1);

        String url = "https://likenapi.herokuapp.com/order/getMyOrdersDriver/"+_id+"/"+type;
        Log.i("URLGetReqDetails",url);
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        StringRequest response = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBackResponse.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackResponse.errorResponse(error.getMessage());
            }
        }) ;
        queue.add(response);

    }

    public void GetUserMainData(final Context mcontext, final CallBackResponse callBackResponse) {
        SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        String _id = sp2.getString("_id",null);
        String url = "https://likenapi.herokuapp.com/Driver/userprofile/"+_id;
            RequestQueue queue = Volley.newRequestQueue(mcontext);
            StringRequest response = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
                    Log.i("GETUserData","Passed!!");
                    Map<String, String> parameters = new HashMap<String, String>();
                    SharedPreferences sp2=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
                    String token =sp2.getString("token","null");

//                    parameters.put("Content-Type", "application/x-www-form-urlencoded");
                    parameters.put("token", token);
                    Log.i("token_GETUserData",token);
                    return parameters;
                }

//                @Override
//                protected Map<String, String> getParams() {
////                    return  callBackResponse.getParams_interface();
//
//                }
            };

//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject jsonObject) {
//                            callBackResponse.getResponse(jsonObject);
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    callBackResponse.errorResponse(error.getMessage());
//                    Log.d("Error: ",error.getMessage()+"");
//                }
//            })
//            {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> parameters = new HashMap<String, String>();
//                    SharedPreferences sp2=Other_Activity.context.getSharedPreferences("Login", MODE_PRIVATE);
//                    String token =sp2.getString("token","null");
//                    parameters.put("token", token);
//                    Log.i("tokenxxx",token);
//                    return parameters;
//                }
//            };

            queue.add(response);
        }




}
