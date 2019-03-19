package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Adapters.AlertAdapter;
import com.example.engosama.likein_deliver.Classes.Alert.Alert;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;

public class Alerts extends Fragment {
    View view;
    ImageView cancel_ban;
//    AlertAdapter alertAdapter;
    ArrayList<Alert> alertArrayList;
    RecyclerView Alert_RV;
    LinearLayoutManager mLayoutManager;
    public Alerts() {
        // Required empty public constructor
    }

    public static Alerts newInstance(String param1, String param2) {
        Alerts fragment = new Alerts();
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
        view = inflater.inflate(R.layout.layout_f_alerts, container, false);
        /***********************Definitions*******************************/
        cancel_ban = view.findViewById(R.id.cancel_ban);



//        alertArrayList.add(new Alert("عنوان التنبيه1","هذا هو التنبيه الأول",null,"15/12/2018"));
//        alertArrayList.add(new Alert("عنوان التنبيه2","هذا هو التنبيه الثاني",null,"2/1/2019"));
//        alertArrayList.add(new Alert("عنوان التنبيه3","هذا هو التنبيه الثالث",null,"27/12/2018"));
//        alertArrayList.add(new Alert("عنوان التنبيه4","هذا هو التنبيه الرابع",null,"5/5/2018"));
//        alertArrayList.add(new Alert("عنوان التنبيه5","هذا هو التنبيه الخامس",null,"7/9/2018"));
//        alertArrayList.add(new Alert("عنوان التنبيه6","هذا هو التنبيه السادس",null,"1/1/2020"));


        /****************************************************************/
        /******************************Adapter*********************************/
        alertArrayList = new ArrayList<>();
        Alert_RV= view.findViewById(R.id.Alert_RV);
        mLayoutManager = new LinearLayoutManager(Other_Activity.context);
        Alert_RV.setLayoutManager(mLayoutManager);
        HttpRequest request=new HttpRequest();
        request.GetAlerts(getContext(),Alert_RV,"detail",Alerts.this);

//        alertAdapter = new AlertAdapter(alertArrayList);
//        Alert_RV.setHasFixedSize(true);
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(Other_Activity.context);
//        Alert_RV.setLayoutManager(mLayoutManager);
//        Alert_RV.setAdapter(alertAdapter);
        /******************************************************/

        /************************Actions*************************/
        cancel_ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return  view;
    }

}
