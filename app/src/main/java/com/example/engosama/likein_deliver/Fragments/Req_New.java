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

import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Adapters.ReqNewAdapter;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;


public class Req_New extends Fragment {
    private View view;
    private ReqNewAdapter reqNewAdapter;
    private ArrayList<ReqCompletedClass> reqCompArrayList;
    private RecyclerView req_new_RV;
    private LinearLayoutManager mLayoutManager;
    private Button refreshFrag_btn;
    private ProgressBar progressBar;

    public Req_New() {
        // Required empty public constructor
    }

    public static Req_New newInstance(String param1, String param2) {
        Req_New fragment = new Req_New();
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
        view = inflater.inflate(R.layout.layout_f_req_new, container, false);
        progressBar = view.findViewById(R.id.progressBar);

        /******************************Adapter********************************/
        reqCompArrayList = new ArrayList<>();
        req_new_RV = view.findViewById(R.id.req_new_RV);
        mLayoutManager = new LinearLayoutManager(Other_Activity.context);
        req_new_RV.setHasFixedSize(true);
        req_new_RV.setLayoutManager(mLayoutManager);
        HttpRequest request=new HttpRequest();
        progressBar.setVisibility(View.VISIBLE);
        request.GetReqNew(getContext(),req_new_RV,"detail",Req_New.this,progressBar);
        /******************************************************/

        /********************************Actions*******************************/
        refreshFrag_btn = view.findViewById(R.id.refreshFrag_btn);
        refreshFrag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Req_New.this).attach(Req_New.this).commit();
            }
        });
        /************************************************************/
        return view;
    }

}
