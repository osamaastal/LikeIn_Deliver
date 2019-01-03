package com.example.engosama.likein_deliver.Activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.engosama.likein_deliver.Fragments.Alerts;
import com.example.engosama.likein_deliver.Fragments.Connect_Us;
import com.example.engosama.likein_deliver.Fragments.MyProfile;
import com.example.engosama.likein_deliver.Fragments.Req_Completed;
import com.example.engosama.likein_deliver.Fragments.Req_Current;
import com.example.engosama.likein_deliver.Fragments.Req_New;
import com.example.engosama.likein_deliver.Fragments.Terms_and_Conditions;
import com.example.engosama.likein_deliver.R;

public class Requests extends AppCompatActivity {
    private final String LOG_TAG = Requests.class.getSimpleName();

    // Titles of the individual pages (displayed in tabs)
    private final String[] PAGE_TITLES = new String[] {
            "المكتملة",
            "الجديدة",
            "الحالية"
    };

    // The fragments that are used as the individual pages
    private final Fragment[] PAGES = new Fragment[] {
            new Req_Completed(),
            new Req_Current(),
            new Req_New()
    };

    // The ViewPager is responsible for sliding pages (fragments) in and out upon user input
    private ViewPager mViewPager;
    TextView my_profile_tv,wallet_money_tv,conditions_tv,connection_tv,rate_app_tv,
            share_app_tv,logout_tv,user_name_tv,user_phone_no_tv,SR;
    public static Typeface questv1_bold,tajawal_bold,tajawal_extra_bold,
            tajawal_medium,tajawal_regular,saudi;
    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    public static Context context;
    public static Fragment fragment,f_alerts,f_my_profile,f_connect_us,f_terms_and_conditions;
    ImageView alerts_icon, menu_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_requests);
        /****************************definitions*****************************/
        my_profile_tv= (TextView)findViewById(R.id.my_profile_tv);
        wallet_money_tv= (TextView)findViewById(R.id.wallet_money_tv);
        conditions_tv= (TextView)findViewById(R.id.conditions_tv);
        connection_tv= (TextView)findViewById(R.id.connection_tv);
        rate_app_tv= (TextView)findViewById(R.id.rate_app_tv);
        share_app_tv= (TextView)findViewById(R.id.share_app_tv);
        logout_tv= (TextView)findViewById(R.id.logout_tv);
        user_name_tv= (TextView)findViewById(R.id.user_name_tv);
        user_phone_no_tv= (TextView)findViewById(R.id.user_phone_no_tv);
        SR= (TextView)findViewById(R.id.SR);
        alerts_icon = (ImageView)findViewById(R.id.alerts_icon);
        menu_icon = (ImageView)findViewById(R.id.menu_icon);

        f_my_profile = new MyProfile();
        f_alerts= new Alerts();
        f_connect_us = new Connect_Us();
        f_terms_and_conditions = new Terms_and_Conditions();
        /********************** Fonts**************************************/
        questv1_bold = Typeface.createFromAsset(getAssets(), "fonts/questv1_bold.ttf");
        tajawal_bold = Typeface.createFromAsset(getAssets(), "fonts/tajawal_bold.ttf");
        tajawal_extra_bold = Typeface.createFromAsset(getAssets(), "fonts/tajawal_extra_bold.ttf");
        tajawal_medium = Typeface.createFromAsset(getAssets(), "fonts/tajawal_medium.ttf");
        tajawal_regular = Typeface.createFromAsset(getAssets(), "fonts/tajawal_regular.ttf");
        saudi = Typeface.createFromAsset(getAssets(), "fonts/saudi.ttf");
//        ****************
        user_name_tv.setTypeface(tajawal_medium);
        user_phone_no_tv.setTypeface(tajawal_medium);
        my_profile_tv.setTypeface(tajawal_medium);
        conditions_tv.setTypeface(tajawal_medium);
        connection_tv.setTypeface(tajawal_medium);
        rate_app_tv.setTypeface(tajawal_medium);
        share_app_tv.setTypeface(tajawal_medium);
        logout_tv.setTypeface(tajawal_medium);
        wallet_money_tv.setTypeface(tajawal_medium);
        SR.setTypeface(tajawal_medium);
        /******************************************************************/
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#56ce8b"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#56ce8b"));
        tabLayout.setupWithViewPager(mViewPager);
//        ************************change typeface*******************
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            //noinspection ConstantConditions
//            TextView tv = (TextView)LayoutInflater.from(this).inflate(R.layout.custom_tab,null)
//            tv.setTypeface(Typeface);
//            tabLayout.getTabAt(i).setCustomView(tv);
//        }
//        *************************************************************
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        final LinearLayout content = (LinearLayout) findViewById(R.id.content);
//        drawerLayout.setScrimColor(Color.TRANSPARENT);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, R.string.open, R.string.close) {
            private float scaleFactor = 6f;
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset * -1;
                content.setTranslationX( slideX);
                content.setScaleX(1 - (slideOffset / scaleFactor));
                content.setScaleY(1 - (slideOffset / scaleFactor));
            }
        };
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0f);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        /*****************************Drawer Actions*******************************/
        my_profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = Requests.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content,f_my_profile);
                Requests.fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        conditions_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = Requests.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content,f_terms_and_conditions);
                Requests.fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        connection_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = Requests.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content,f_connect_us);
                Requests.fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        alerts_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = Requests.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content,f_alerts);
                Requests.fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        /****************************************************************************/
    }
    /* PagerAdapter for supplying the ViewPager with the pages (fragments) to display. */
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
}
