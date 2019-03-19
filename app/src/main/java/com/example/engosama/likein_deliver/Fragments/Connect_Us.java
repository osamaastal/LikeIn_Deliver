package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Adapters.ContactUsAdapter;
import com.example.engosama.likein_deliver.Classes.ContactUsClass;
import com.example.engosama.likein_deliver.R;



import java.util.ArrayList;

public class Connect_Us extends Fragment {
    View view;
    ImageView cancel_ban;
    ContactUsAdapter contactUsAdapter;
    ArrayList<ContactUsClass> conClassArrayList;
    RecyclerView contact_us_RV;
    LinearLayoutManager mLayoutManager;
    private RequestQueue mRequestQueue;
    public Connect_Us() {
        // Required empty public constructor
    }

    public static Connect_Us newInstance(String param1, String param2) {
        Connect_Us fragment = new Connect_Us();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.layout_f_connect_us, container, false);
        /***********************Definitions*******************************/
        cancel_ban = view.findViewById(R.id.cancel_ban);
        contact_us_RV = view.findViewById(R.id.contact_us_RV);
        conClassArrayList = new ArrayList<>();
//        conClassArrayList.add(new ContactUsClass("البريد الالكتروني: ","osamayastal@gmail.com"));
//        conClassArrayList.add(new ContactUsClass("رقم الجوال: ","+966000000000"));
//        conClassArrayList.add(new ContactUsClass("رقم الهاتف: ","+9662060004"));
//        conClassArrayList.add(new ContactUsClass("رقم الهاتف: ","+9662060004"));
//        conClassArrayList.add(new ContactUsClass("رقم الهاتف: ","+9662060004"));
//        conClassArrayList.add(new ContactUsClass("رقم الهاتف: ","+9662060004"));
        /******************************Adapter*********************************/
//        contactUsAdapter = new ContactUsAdapter(conClassArrayList);
        contact_us_RV.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(Other_Activity.context);
        contact_us_RV.setLayoutManager(mLayoutManager);
//        contact_us_RV.setAdapter(contactUsAdapter);
        /****************************Actions**************************/
        cancel_ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        /*************************JSON-OKHttp3*****************************/
        mRequestQueue = Volley.newRequestQueue(Other_Activity.context);
        parseJSON();
        /*******************************************************************/

        return view;
    }
    private void parseJSON() {
        String url = "https://likenapi.herokuapp.com/constant/ContactOption";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
//                            JSONArray jsonArray = response.getJSONArray("تواصل معنا");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject headerData = jsonArray.getJSONObject(i);
                                String name = headerData.getString("name")+ " :   ";
                                String data = headerData.getString("data");
                                conClassArrayList.add(new ContactUsClass(name,data));
                            }
                            contactUsAdapter = new ContactUsAdapter(conClassArrayList);
                            contact_us_RV.setAdapter(contactUsAdapter);
                            contactUsAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Error: ",e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("Error: ",error.getMessage());
            }
        });

        mRequestQueue.add(request);
    }

}
