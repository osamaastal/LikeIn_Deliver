package com.example.engosama.likein_deliver.Fragments;


import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;

import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Adapters.ReqNewAdapter;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.R;
import com.marozzi.roundbutton.RoundButton;

import java.util.ArrayList;


public class Req_Canceled extends Fragment {


    private View view;
    private ViewPager mViewPager;
//    private ReqNewAdapter reqNewAdapter;
//    private ArrayList<ReqCompletedClass> reqCompArrayList;
//    private RecyclerView req_canceled_RV;
//    private LinearLayoutManager mLayoutManager;
    Button refreshFrag_btn;
    private final String[] PAGE_TITLES = new String[]{
            "ملغية من الإدارة",
            "ملغية من العميل"
    };

    private final Fragment[] PAGES = new Fragment[]{
            new Req_Canceled_Admin(),
            new Req_Canceled_User()
    };

    public Req_Canceled() {
        // Required empty public constructor
    }

    public static Req_Canceled newInstance(String param1, String param2) {
        Req_Canceled fragment = new Req_Canceled();
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
        view = inflater.inflate(R.layout.layout_f_req_canceled, container, false);
        /******************************Pager Tabs*****************************/
        mViewPager =  view.findViewById(R.id.viewpager1);
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout1);
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#56ce8b"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#56ce8b"));
        tabLayout.setupWithViewPager(mViewPager);
        /**********************************************************************/
        /******************************Adapter********************************/
//        reqCompArrayList = new ArrayList<>();
//        req_canceled_RV = view.findViewById(R.id.req_canceled_RV);
//        mLayoutManager = new LinearLayoutManager(Requests.context);
//        req_canceled_RV.setHasFixedSize(true);
//        req_canceled_RV.setLayoutManager(mLayoutManager);
//        HttpRequest request=new HttpRequest();
//        request.GetReqCanceled(getContext(),8,req_canceled_RV,"detail",Req_Canceled.this);
//        request.GetReqCanceled(getContext(),9,req_canceled_RV,"detail",Req_Canceled.this);
        /******************************************************/

        /***************************Actions ***********************/
//        refreshFrag_btn = view.findViewById(R.id.refreshFrag_btn);
//        refreshFrag_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.detach(Req_Canceled.this).attach(Req_Canceled.this).commit();
//            }
//        });
        /************************************************************/

        return view;
    }
    private void  animation(){
        final RoundButton bt = (RoundButton) view;
        bt.startAnimation();
        bt.postDelayed(new Runnable() {
            @Override
            public void run() {
                bt.revertAnimation();
            }
        }, 3000);
    }


    void onClickBt(View view) {
        final RoundButton bt = (RoundButton) view;
        bt.startAnimation();
        bt.postDelayed(new Runnable() {
            @Override
            public void run() {
                bt.revertAnimation();
            }
        }, 3000);
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

}
