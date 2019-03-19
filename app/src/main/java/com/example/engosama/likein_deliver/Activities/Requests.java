package com.example.engosama.likein_deliver.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import com.example.engosama.likein_deliver.Adapters.ReqCurrentAdapter;
import com.example.engosama.likein_deliver.BuildConfig;
import com.example.engosama.likein_deliver.Classes.Client.Deliver_No_noken;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.Classes.MyService;
import com.example.engosama.likein_deliver.Fragments.Req_Canceled;
import com.example.engosama.likein_deliver.Fragments.Req_Completed;
import com.example.engosama.likein_deliver.Fragments.Req_Current;
import com.example.engosama.likein_deliver.Fragments.Req_New;
import com.example.engosama.likein_deliver.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class Requests extends AppCompatActivity {
    private final String LOG_TAG = Requests.class.getSimpleName();
//    public static String orderID="5c5d4e4756de1b001783fa53";

    private final String[] PAGE_TITLES = new String[]{
            "المكتملة",
            "الحالية",
            "الجديدة",
            "الملغية"
    };

    private final Fragment[] PAGES = new Fragment[]{
            new Req_Completed(),
            new Req_Current(),
            new Req_New(),
            new Req_Canceled()
    };

    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    public static Context context;
    private RequestQueue requestQueue;
    private ViewPager mViewPager;
    private LinearLayout drawer;
    private boolean IsAlerts;
    private TextView my_profile_tv, conditions_tv, connection_tv, rate_app_tv,
            share_app_tv, logout_tv, user_name_tv, user_phone_no_tv;//wallet_money_tv, my_wallet_tab_tv
    private ImageView alerts_icon,userImg;//menu_icon

    private String email, password, firebaseToken, logoutMessage;

    private LocationManager locationManager = null;
//    private LocationListener locationListener = null;
    private Boolean flag;
    public static int ReqType;

    private PendingIntent pendingIntent;
    private AlarmManager manager;
    public static ArrayList<String> allIDS = new ArrayList<>();
    @Override
    protected void onResume() {
        super.onResume();
        /**********************************Request GetUserMainData*********************************/
        try {
            HttpRequest request = new HttpRequest();
            request.GetUserMainData(Requests.this, new CallBackResponse() {
                @Override
                public void getResponse(JSONObject jsonObject) {

                }

                @Override
                public void getResponse(String response) {
                    Log.i("response", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonOb = jsonObject.getJSONObject("items");
                        final Deliver_No_noken deliver = new Deliver_No_noken(jsonOb);
                        final String imgURL = deliver.getImage();
                        my_profile_tv = drawer.findViewById(R.id.my_profile_tv);
                        user_name_tv = drawer.findViewById(R.id.user_name_tv);
                        user_phone_no_tv = drawer.findViewById(R.id.user_phone_no_tv);
                        if (!imgURL.equals("") && !imgURL.equals("null")) {
//                            Toast.makeText(Requests.this, imgURL, Toast.LENGTH_SHORT).show();
                            Picasso.get().load(imgURL).fit().centerInside().into(userImg);
                        }
                        user_name_tv.setText(deliver.getName());

                        user_phone_no_tv.setText(deliver.getPhone_number());
//                                email_et.setText(deliver.getEmail());
                        my_profile_tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                GoTo_OtherActivity("f_my_profile");
                            }
                        });
                        SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
                        SharedPreferences.Editor Ep = sp.edit();
                        Ep.putString("userName",deliver.getName());
                        Ep.putString("userImg",imgURL);
                        Ep.commit();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Error: ", e.getMessage());
                    }
                }

                @Override
                public void errorResponse(String error) {

                }

            });
        } catch (Exception e) {
//            Toast.makeText(context, "HttpRequests error go to Log.i", Toast.LENGTH_SHORT).show();
            Log.i("HttpRequests", e.getMessage());
        }
        /******************************************************************************************/
        try {
            HttpRequest request =new HttpRequest();
            request.CheckAlerts(Requests.this, new CallBackResponse() {
                @Override
                public void getResponse(JSONObject jsonObject) {
                }

                @Override
                public void getResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int checkItems  = jsonObject.getJSONArray("items").length();
                        if(checkItems == 0){
                            alerts_icon.setImageResource(R.drawable.alert_off);
//                            IsAlerts = false;

                        }else {
                            alerts_icon.setImageResource(R.drawable.alert_on);
//                            IsAlerts = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void errorResponse(String error) {
                    Log.i("Requests","Error: "+error);
                }
            });
        }catch (Exception e){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_requests);
        context = this;

//        menu_icon = findViewById(R.id.menu_icon);
        /** TODO ***************************DRAWER new Library********************************/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
//        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_menu));
        new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withMenuLayout(R.layout.layout_side_drawer)
                .withGravity(SlideGravity.RIGHT)
                .inject();
        /** TODO ************************XXX**********************************/
        /*
        الحالية: قيد التوصيل - 5
        الجديدة: باتظار استلام السائق - 4
        مكتملة: تم الوصيل - 6
        // فيها حقل اسمو Notes   ملغية: ملغي من الادارة  - 8
        // فيها حقل اسمو Notes   ملغية :ملغي من العميل - 9
        */
        /************************************************************/
        /***************************for refresh fcmtoken****************************/
        /**&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
        HttpRequest_POST fcmtokenPost = new HttpRequest_POST();
        fcmtokenPost.Put_refrish_fcmToken(this);
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        String fcmToken = sp.getString("fcmToken", null);
        Log.i("fcmToken:", fcmToken);

        /***************************************************************/

        /**########################################################*/
        /***********************Get location***************/
        Getpermissin();
        /**********************New Location Ahmed*********************/
        RunableForLocation();
        /****************************definitions*****************************/
//        wallet_money_tv = findViewById(R.id.wallet_money_tv);
//        my_wallet_tab_tv = (TextView) findViewById(R.id.my_wallet_tab_tv);
        conditions_tv = (TextView) findViewById(R.id.conditions_tv);
        connection_tv = (TextView) findViewById(R.id.connection_tv);
        rate_app_tv = (TextView) findViewById(R.id.rate_app_tv);
        share_app_tv = (TextView) findViewById(R.id.share_app_tv);
        logout_tv = (TextView) findViewById(R.id.logout_tv);
        drawer = findViewById(R.id.drawer);
        alerts_icon = (ImageView) findViewById(R.id.alerts_icon);
//        menu_icon = (ImageView) findViewById(R.id.menu_icon);
        userImg = (ImageView) findViewById(R.id.userImg);
        logoutMessage = "تم تسجيل الخروج بنجاح";
        fragmentManager = getSupportFragmentManager();
        /***************************TabLayout***********************/
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#56ce8b"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#56ce8b"));
        tabLayout.setupWithViewPager(mViewPager);

        /*****************************Drawer Actions*******************************/
        conditions_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoTo_OtherActivity("f_terms_and_conditions");
            }
        });
        connection_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoTo_OtherActivity("f_connect_us");
            }
        });
        alerts_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoTo_OtherActivity("f_alerts", firebaseToken);
            }
        });
        rate_app_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
            }
        });
        logout_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor Ep = sp.edit();
                Ep.putString("_id",null);
                Ep.commit();

                HttpRequest_POST post = new HttpRequest_POST();
                post.Post_Logout(Login.context);
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        share_app_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Choose one..."));
                } catch (Exception e) {
                    Log.i("Error sharing: ", e.getMessage());
                }
            }
        });
        /****************************************************************************/
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

    public void GoTo_OtherActivity(String frag) {
        Intent intent = new Intent(Requests.this, Other_Activity.class);
        intent.putExtra("FRAGEMNT", frag);
        startActivity(intent);
    }

    public void GoTo_OtherActivity(String frag, String firebaseToken) {
        Intent intent = new Intent(Requests.this, Other_Activity.class);
        intent.putExtra("FRAGEMNT", frag);
        startActivity(intent);
    }

    public static void GoTo_Chat() {
        Intent intent = new Intent(Requests.context, Chat.class);
        context.startActivity(intent, null);
    }

    public static void GoTo_OtherActivity_static(String frag) {
        Intent intent = new Intent(Requests.context, Other_Activity.class);
        intent.putExtra("FRAGEMNT", frag);
        context.startActivity(intent, null);
    }


    private void Getpermissin() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 15);
                locationManager = (LocationManager)
                        //scale
                        this.getSystemService(Context.LOCATION_SERVICE);

                //if you want to lock screen for always Portrait mode
                this.setRequestedOrientation(ActivityInfo
                        .SCREEN_ORIENTATION_PORTRAIT);
//                get_location();

            } else {
                locationManager = (LocationManager)
                        this.getSystemService(Context.LOCATION_SERVICE);
                //if you want to lock screen for always Portrait mode
                this.setRequestedOrientation(ActivityInfo
                        .SCREEN_ORIENTATION_PORTRAIT);
//                get_location();
            }
        }
    }


    /**********************************New Location for lat and lng **************************/
    Runnable r;
    Location location;
    Handler handler= new Handler();
    int delay = 10000;
    private void RunableForLocation() {
        r = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void run() {
                SharedPreferences sp1 = getSharedPreferences("Service",MODE_PRIVATE);
                SharedPreferences.Editor Ep = sp1.edit();
                Set<String> IDSet = new ArraySet<>();
                ArrayList<String> allIdOfCurrntAdapter = ReqCurrentAdapter.getCountIDs();
                if (allIdOfCurrntAdapter.size() > 0) { /** this code is new - test it */
//
                location = getLastKnownLocation(context);
                for (int i = 0; i < allIdOfCurrntAdapter.size(); i++) {
                    String ID = allIdOfCurrntAdapter.get(i);
//                    Toast.makeText(Requests.this, ID, Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReferencexxx = FirebaseDatabase.getInstance().getReference().child("tracking").child(ID);//ORDERS
//                DatabaseReference databaseReferencexxx = orders_database.child(String.valueOf(driverItem.getId()));
//                MainApplication.addSharedPreference(TAGS.ORDER_ID, String.valueOf(driverItem.getId()));
                    if (location != null) {
                        double lat = location.getLatitude();
                        double lng = location.getLongitude();
                        databaseReferencexxx.child("lat").setValue(lat);
                        databaseReferencexxx.child("lng").setValue(lng);
//                    Toast.makeText(Requests.this, "lat: "+lat + "\nlng: "+lng, Toast.LENGTH_SHORT).show();
                        IDSet.add(ID);
                    }
                }
                    Ep.putStringSet("IDSet",IDSet);
                    Ep.commit();
                    Intent intent = new Intent(Requests.this, MyService.class);
//                    intent.putExtra("id", ID);
                    startService(intent);
                    handler.postDelayed(this, delay);
            }
            }
        };
        handler.postDelayed(r, delay);
    }

    public Location getLastKnownLocation(Context context) {
        LocationManager lm = (LocationManager) context.getApplicationContext().getSystemService(context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {

            if (ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return null;
            }
            Location l = lm.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }

        return bestLocation;
    }

    /*****************************************************************************************/

}
