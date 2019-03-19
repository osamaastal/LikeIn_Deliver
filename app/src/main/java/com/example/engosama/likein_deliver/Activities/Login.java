package com.example.engosama.likein_deliver.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Fragments.Login_frag;
import com.example.engosama.likein_deliver.Fragments.SignUp;
import com.example.engosama.likein_deliver.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    public static Fragment fragment;
    public static Context context;
    public static String Username;
    private LocationManager locationManager = null;
    private LocationListener locationListener = null;
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_login);
        context = this;
//        Username = "osama";

        /******************************JSON**********************************/
        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);

        String id = sp.getString("_id", null);
//        id = null ;
        String token = sp.getString("token", null);
        if (id != null && token != null) {
//            Toast.makeText(context, "OOO", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context,Requests.class));
            finish();
        }
            /******************************fragment*******************************/
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragment = (Fragment) fragmentManager.findFragmentById(R.id.fragment_container);
            if (fragment == null) {
                fragment = new Login_frag();
                fragmentTransaction.add(R.id.fragment_container, fragment).commit();
                fragmentTransaction.addToBackStack(null);
            }
            /*****************************************************************/
        }



    public static void switch_fragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

}
