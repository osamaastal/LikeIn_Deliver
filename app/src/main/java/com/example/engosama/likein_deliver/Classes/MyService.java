package com.example.engosama.likein_deliver.Classes;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

//import com.bunch.bunch.utilities.MainApplication;
//import com.bunch.bunch.utilities.TAGS;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Set;

//import static com.bunch.bunch.driver.activity.MapActivity.ORDERS;

public class MyService extends Service {
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 5000;
    private static final float LOCATION_DISTANCE = 0f;
//    private String _id;
    private class LocationListener implements android.location.LocationListener {
        Location mLastLocation;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            SharedPreferences sp = getSharedPreferences("Service",MODE_PRIVATE);
            Set<String> IDSet= sp.getStringSet("IDSet",null);
            String ID="";
            for(String i : IDSet){
                ID = i;
                Log.e(TAG, "onLocationChanged: " + location);
                mLastLocation.set(location);
                DatabaseReference DBReference = FirebaseDatabase.getInstance().getReference()
                        .child("tracking").child(ID);
                Log.i("DBReference",DBReference.toString());
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                DBReference.child("lat").setValue(lat);
                DBReference.child("lng").setValue(lng);
                Log.i("XXXXXX","lat:"+lat +"/n lng:"+lng);
            }

//            Toast.makeText(Requests.context, "lat: "+lat + "/n lng: "+lng, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");

        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }

        DatabaseReference orders_database = FirebaseDatabase.getInstance().getReference()
                .child("tracking").child("5c5d4e4756de1b001783fa53");
        Query query = orders_database.orderByChild("orderId");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e("snapshot", snapshot.toString());
//                        if (snapshot.getKey().equals(MainApplication.prefs.getString(TAGS.ORDER_ID, "0"))) {
//                        if (snapshot.getKey().equals(MainApplication.prefs.getString(TAGS.ORDER_ID, "0"))) {
                            boolean completed = Boolean.parseBoolean(String.valueOf(snapshot.child("completed").getValue()));
                            if (completed) {
                                stopTracking();
                                break;
                            }
//                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public void stopTracking() {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        reference.child(ORDERS).child(MainApplication.prefs.getString(TAGS.ORDER_ID, "0")).removeValue();
//        MainApplication.addSharedPreference(TAGS.ORDER_ID, "0");
//        stopSelf();
    }
}