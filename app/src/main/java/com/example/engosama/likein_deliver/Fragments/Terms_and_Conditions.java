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
import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Classes.TermsConditions.TermsAndCondClass;
import com.example.engosama.likein_deliver.Adapters.TermsAndCondAdapter;
import com.example.engosama.likein_deliver.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Terms_and_Conditions extends Fragment {

    private View view;
    private ImageView cancel_btn;
    private ArrayList<TermsAndCondClass> termsArrayList;
    private TermsAndCondAdapter termsAndCondAdapter;
    private RecyclerView TermsAndConditions_RV;
    private LinearLayoutManager linearLayoutManager;
    private RequestQueue requestQueue;
    public Terms_and_Conditions() {
        // Required empty public constructor
    }

    public static Terms_and_Conditions newInstance(String param1, String param2) {
        Terms_and_Conditions fragment = new Terms_and_Conditions();
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
        view = inflater.inflate(R.layout.layout_f_terms_and_conditions, container, false);
        /************************Definitions**************************/
        cancel_btn = view.findViewById(R.id.cancel_ban);
        TermsAndConditions_RV = view.findViewById(R.id.TermsAndConditions_RV);
        termsArrayList = new ArrayList<>();
        /****************************Adapter**************************/
        TermsAndConditions_RV.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(Other_Activity.context);
        TermsAndConditions_RV.setLayoutManager(linearLayoutManager);
        /*************************Actions*****************************/
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        /*************************JSON-OKHttp3*****************************/
        requestQueue =Volley.newRequestQueue(Other_Activity.context);
        parseJSON();
        /*******************************************************************/


        return view;
    }
    private void parseJSON(){
        String url = "https://likenapi.herokuapp.com/constant/staticpage";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject headerData = jsonArray.getJSONObject(i);
                                String title = headerData.getString("title");
                                String content = headerData.getString("content");
                                termsArrayList.add(new TermsAndCondClass(title,content));
                            }
                            termsAndCondAdapter = new TermsAndCondAdapter(termsArrayList);
                            TermsAndConditions_RV.setAdapter(termsAndCondAdapter);
                            termsAndCondAdapter.notifyDataSetChanged();

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

        requestQueue.add(request);
    }
}
