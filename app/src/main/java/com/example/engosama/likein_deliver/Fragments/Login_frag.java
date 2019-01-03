package com.example.engosama.likein_deliver.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.engosama.likein_deliver.Activities.Login;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.R;

public class Login_frag extends Fragment {

    View view;
    TextView sign_up_tv;
    Button login_btn;

    public Login_frag() {
        // Required empty public constructor
    }

    public static Login_frag newInstance(String param1, String param2) {
        Login_frag fragment = new Login_frag();
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
        view = inflater.inflate(R.layout.layout_f_login_frag, container, false);
        login_btn = (Button) view.findViewById(R.id.login_btn);
        sign_up_tv = (TextView) view.findViewById(R.id.sign_up_tv);

        sign_up_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.fragmentManager = getFragmentManager();
                Login.fragmentTransaction = Login.fragmentManager.beginTransaction();
                Login.fragment = new SignUp();
                Login.fragmentTransaction.replace(R.id.fragment_container,Login.fragment);
//                Login.fragmentTransaction.addToBackStack(null);
                Login.fragmentTransaction.commit();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Requests.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
