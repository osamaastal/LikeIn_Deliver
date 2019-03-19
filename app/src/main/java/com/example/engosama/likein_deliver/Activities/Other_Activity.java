package com.example.engosama.likein_deliver.Activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Fragments.Alerts;
import com.example.engosama.likein_deliver.Fragments.Connect_Us;
import com.example.engosama.likein_deliver.Fragments.MyProfile;
import com.example.engosama.likein_deliver.Fragments.RequestDetails;
import com.example.engosama.likein_deliver.Fragments.Terms_and_Conditions;
import com.example.engosama.likein_deliver.Fragments.Wallet;
import com.example.engosama.likein_deliver.R;

public class Other_Activity extends AppCompatActivity {

    public static Fragment f_alerts = new Alerts();
    public static Fragment f_my_profile = new MyProfile();
    public static Fragment f_connect_us = new Connect_Us();
    public static Fragment f_terms_and_conditions = new Terms_and_Conditions();
    public static Fragment f_wallet = new Wallet();
//    public static Fragment f_request_details = new RequestDetails();
    public static Fragment fragment ;
    public static FragmentManager fragmentManager;
    public static Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_other_activity);
        context =this;

        if (getIntent() !=null)
        {
            if (getIntent().getStringExtra("FRAGEMNT") !=null){
                if (getIntent().getStringExtra("FRAGEMNT").equals("f_alerts")){
                    fragment=f_alerts;
                }else if (getIntent().getStringExtra("FRAGEMNT").equals("f_my_profile")){
                    fragment=new MyProfile();
                }else if (getIntent().getStringExtra("FRAGEMNT").equals("f_connect_us")){
                    fragment=f_connect_us;
                }else if (getIntent().getStringExtra("FRAGEMNT").equals("f_terms_and_conditions")){
                    fragment=f_terms_and_conditions;
                }else if (getIntent().getStringExtra("FRAGEMNT").equals("f_wallet")){
                    fragment=f_wallet;
                }else if (getIntent().getStringExtra("FRAGEMNT").equals("f_request_details")){
                    fragment=new RequestDetails();
                }else {
                    Toast.makeText(this, "Wrong Intent!!", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            fragment = new Alerts();
            Toast.makeText(this, "Default Fragment!", Toast.LENGTH_SHORT).show();
        }
        fragmentManager = getSupportFragmentManager();
        //switch fragment
        switch_fragment(fragment);

    }
    public static void switch_fragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.otherFrag_content, fragment).commit();
    }


}
