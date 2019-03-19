package com.example.engosama.likein_deliver.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.engosama.likein_deliver.Activities.Login;
import com.example.engosama.likein_deliver.R;

public class Password_BackUp extends Fragment {
    View view;
    ImageView back_btn;
    public Password_BackUp() {
        // Required empty public constructor
    }

    public static Password_BackUp newInstance(String param1, String param2) {
        Password_BackUp fragment = new Password_BackUp();
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
        view = inflater.inflate(R.layout.layout_f_password_back_up, container, false);
        back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.switch_fragment(new Login_frag());
            }
        });
        return view;
    }

}
