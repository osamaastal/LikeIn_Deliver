package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Adapters.ReqNewAdapter;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;

public class Req_Canceled_User extends Fragment {
//    private ReqNewAdapter reqNewAdapter;
    private ArrayList<ReqCompletedClass> reqCompArrayList;
    private RecyclerView req_canceled_RV;
    private LinearLayoutManager mLayoutManager;
    private Button refreshFrag_btn;
    private ProgressBar progressBar;
    public Req_Canceled_User() {
        // Required empty public constructor
    }

    public static Req_Canceled_User newInstance(String param1, String param2) {
        Req_Canceled_User fragment = new Req_Canceled_User();
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
        View view=  inflater.inflate(R.layout.layout_f_req_canceled_user, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        /******************************Adapter********************************/
        reqCompArrayList = new ArrayList<>();
        req_canceled_RV = view.findViewById(R.id.req_canceled_RV);
        mLayoutManager = new LinearLayoutManager(Requests.context);
        req_canceled_RV.setHasFixedSize(true);
        req_canceled_RV.setLayoutManager(mLayoutManager);
        HttpRequest request=new HttpRequest();
        progressBar.setVisibility(View.VISIBLE);
//        request.GetReqCanceled(getContext(),8,req_canceled_RV,"detail",Req_Canceled_User.this);
        request.GetReqCanceled(getContext(),9,req_canceled_RV,"detail",Req_Canceled_User.this,progressBar);
        /******************************************************/

        /***************************Actions ***********************/
        refreshFrag_btn = view.findViewById(R.id.refreshFrag_btn);
        refreshFrag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Req_Canceled_User.this).attach(Req_Canceled_User.this).commit();
            }
        });
        /************************************************************/
        return view;

    }

}
