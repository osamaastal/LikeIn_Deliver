package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.engosama.likein_deliver.R;

public class MyProfile extends Fragment {
    View view;

    public MyProfile() {
        // Required empty public constructor
    }

    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
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
        view = inflater.inflate(R.layout.layout_f_my_profile, container, false);
        return view;
    }

}
