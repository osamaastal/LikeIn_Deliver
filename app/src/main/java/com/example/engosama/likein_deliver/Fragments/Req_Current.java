package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.engosama.likein_deliver.R;


public class Req_Current extends Fragment {
    View view;

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
        return view;
    }

}
