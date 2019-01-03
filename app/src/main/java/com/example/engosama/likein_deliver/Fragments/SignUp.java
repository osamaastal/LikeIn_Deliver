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

import org.w3c.dom.Text;

public class SignUp extends Fragment {
    View view;
    Button sign_up_btn;
    TextView login_tv;
    public SignUp() {
        // Required empty public constructor
    }

    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();
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
        view =  inflater.inflate(R.layout.layout_f_sign_up, container, false);
        login_tv = (TextView)view.findViewById(R.id.login_tv);
        sign_up_btn = (Button)view.findViewById(R.id.sign_up_btn);

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.fragmentManager = getFragmentManager();
                Login.fragmentTransaction = Login.fragmentManager.beginTransaction();
                Login.fragment = new Login_frag();
                Login.fragmentTransaction.replace(R.id.fragment_container,Login.fragment);
//                Login.fragmentTransaction.addToBackStack(null);
                Login.fragmentTransaction.commit();
            }
        });
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Requests.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
