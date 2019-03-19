package com.example.engosama.likein_deliver.Fragments;

import android.content.Context;
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
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Adapters.ReqCompletedAdapter;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsCompleted;
import com.example.engosama.likein_deliver.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Req_Completed extends Fragment {
    private View view;
    private ReqCompletedAdapter reqCompletedAdapter;
    private ArrayList<ReqCompletedClass> reqCompArrayList;
    public static RecyclerView req_completed_RV;
    private LinearLayoutManager mLayoutManager;
    private Button refreshFrag_btn;
    private ProgressBar progressBar;
    public  FragmentTransaction ft;
    public Req_Completed() {
        // Required empty public constructor
    }

    public static Req_Completed newInstance(String param1, String param2) {
        Req_Completed fragment = new Req_Completed();
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
        view = inflater.inflate(R.layout.layout_f_req_completed, container, false);
        refreshFrag_btn = view.findViewById(R.id.refreshFrag_btn);
        progressBar = view.findViewById(R.id.progressBar);

        /******************************Adapter********************************/
        reqCompArrayList = new ArrayList<>();
        req_completed_RV = view.findViewById(R.id.req_completed_RV);
        mLayoutManager = new LinearLayoutManager(Requests.context);
        req_completed_RV.setHasFixedSize(true);
        req_completed_RV.setLayoutManager(mLayoutManager);
        HttpRequest request=new HttpRequest();
        progressBar.setVisibility(View.VISIBLE);
        request.GetReqCompleted(getContext(), new CallBackResponse() {
            @Override
            public void getResponse(JSONObject jsonObject) {

            }

            @Override
            public void getResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    req_completed_RV.setAdapter(new ReqCompletedAdapter(getContext(), new show_RequestsCompleted(jsonObject).getRequestsList()
                            , "details", Req_Completed.this));
                    req_completed_RV.getAdapter().notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void errorResponse(String error) {

            }
        });//req_completed_RV,"detail",Req_Completed.this
//        reqCompletedAdapter = new ReqCompletedAdapter(reqCompArrayList);
//        req_completed_RV.setAdapter(reqCompletedAdapter);
        /******************************************************/

        /********************************Actions*******************************/
        refreshFrag_btn = view.findViewById(R.id.refreshFrag_btn);
        refreshFrag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Req_Completed.this).attach(Req_Completed.this).commit();
            }
        });
        //anothr code :

//                Fragment frg = null;
//                frg = getFragmentManager().findFragmentByTag("Your_Fragment_TAG");
//                final FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.detach(frg);
//                ft.attach(frg);
//                ft.commit();

        /****************************************************************/

        return view;
    }
    public void refreshFrag(){
         ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
