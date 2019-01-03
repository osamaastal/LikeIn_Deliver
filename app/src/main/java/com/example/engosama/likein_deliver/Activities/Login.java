package com.example.engosama.likein_deliver.Activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.engosama.likein_deliver.Fragments.Login_frag;
import com.example.engosama.likein_deliver.Fragments.SignUp;
import com.example.engosama.likein_deliver.R;

public class Login extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    public static Fragment fragment;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_login);
        context=this;
        /******************************fragment*******************************/
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = (Fragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            fragment = new SignUp();
            fragmentTransaction.add(R.id.fragment_container,fragment).commit();
            fragmentTransaction.addToBackStack(null);
        }
        /*****************************************************************/
    }
}
