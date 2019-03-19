package com.example.engosama.likein_deliver.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Adapters.ReqCurrentAdapter;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsCurrent;
import com.example.engosama.likein_deliver.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;


public class Req_Current extends Fragment {
    private View view;
    private ReqCurrentAdapter reqCurrentAdapter;
    private ArrayList<ReqCompletedClass> reqCompArrayList;
    public static RecyclerView req_current_RV;
    private LinearLayoutManager mLayoutManager;
    private int increment = 4;
    private Button refreshFrag_btn;
    private String TAG= "Req_Current Fragment";
    private ProgressBar progressBar;
    public Req_Current() {
        // Required empty public constructor
    }

    public static Req_Current newInstance(String param1, String param2) {
        Req_Current fragment = new Req_Current();
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
        view = inflater.inflate(R.layout.layout_f_req_current, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        /****************************UpdateLocation()*************************/
//        myLocation.getLocation(getActivity().getApplicationContext(), locationResult);
//
//        boolean r = myLocation.getLocation(getActivity().getApplicationContext(),
//                locationResult);
//        UpdateLocation();

        /******************************Adapter********************************/
        reqCompArrayList = new ArrayList<>();
        req_current_RV = view.findViewById(R.id.req_current_RV);
        mLayoutManager = new LinearLayoutManager(Other_Activity.context);
        req_current_RV.setHasFixedSize(true);
        req_current_RV.setLayoutManager(mLayoutManager);
        HttpRequest request = new HttpRequest();
        progressBar.setVisibility(View.VISIBLE);
        request.GetReqCurrent(getContext(), new CallBackResponse() {
            @Override
            public void getResponse(JSONObject jsonObject) {

            }
            @Override
            public void getResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    req_current_RV.setAdapter(new ReqCurrentAdapter(getContext(), new show_RequestsCurrent(jsonObject).getRequestsList()
                            , "details", Req_Current.this));
                    req_current_RV.getAdapter().notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void errorResponse(String error) {
                Log.i(TAG,"error: "+ error);
            }
        });// req_current_RV, "detail", Req_Current.this
        /******************************************************/
        /***************************Actions ***********************/
        refreshFrag_btn = view.findViewById(R.id.refreshFrag_btn);
        refreshFrag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Req_Current.this).attach(Req_Current.this).commit();
            }
        });
        /************************************************************/

        return view;
    }

}
