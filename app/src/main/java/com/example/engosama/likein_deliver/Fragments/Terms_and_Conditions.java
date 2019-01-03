package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.engosama.likein_deliver.R;

public class Terms_and_Conditions extends Fragment {

    View view;
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
        view = inflater.inflate(R.layout.layout_terms_and_conditions, container, false);



        return view;
    }

}
