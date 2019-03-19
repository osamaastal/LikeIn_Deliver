package com.example.engosama.likein_deliver.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
//import com.example.engosama.likein_deliver.Classes.Upload_Image.MultipartRequest;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.Classes.Req_Classes.RequestDetailsClass.RequestDetailsClass;
import com.example.engosama.likein_deliver.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class RequestDetails extends Fragment {

    private View view;
    private ImageView back_btn;
    private TextView req_id_tv,req_state_tv,req_date_tv,username_tv,userNumber_tv,userAddress_tv
    ,notes_tv,payWay_tv,deliver_time_tv,deliver_date_tv,deliveryChoice_tv,deliveryWay_tv,replaysChoice_tv
            ,cobonCode_tv,subTotal_tv,delivery_cost_tv,TotalCost_tv;
//    private Dialog cancelReqDialog;
//    private Button sellDone_btn;
    private String _id;
    public static Map<String,Object> DetailsDataMap = new HashMap<>();
    private RecyclerView productList_RV;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progressBar;

    public RequestDetails() {
        // Required empty public constructor
    }

    public static RequestDetails newInstance(String param1, String param2) {
        RequestDetails fragment = new RequestDetails();
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
        view = inflater.inflate(R.layout.layout_f_request_details, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        back_btn = view.findViewById(R.id.back_btn);
//        cancelReq_tv = view.findViewById(R.id.cancelReq_tv);
        productList_RV = view.findViewById(R.id.productList_RV);
        mLayoutManager = new LinearLayoutManager(Other_Activity.context);
        productList_RV.setHasFixedSize(true);
        productList_RV.setLayoutManager(mLayoutManager);
//        sellDone_btn = view.findViewById(R.id.sellDone_btn);
//        setData();
        SharedPreferences sp1 = Requests.context.getSharedPreferences("ReqDetails",MODE_PRIVATE);
        int type = sp1.getInt("type",1);

        HttpRequest httpRequest1 = new HttpRequest();
        progressBar.setVisibility(View.VISIBLE);
        httpRequest1.GetProducts(Other_Activity.context,type,productList_RV,"detail",RequestDetails.this,progressBar);

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.GetReqDetails(Other_Activity.context,type , new CallBackResponse() {
            @Override
            public void getResponse(JSONObject jsonObject) {

            }

            @Override
            public void getResponse(String response) {
                Log.i("responseReqDetails", response);
                JSONObject jsonObject = null;
                try {
                    //JSONObject requestObject = requestJsonArray.optJSONObject(i);
                    jsonObject = new JSONObject(response);
                    JSONArray jArray = jsonObject.getJSONArray("items");
                    SharedPreferences sp = Other_Activity.context.getSharedPreferences("ReqDetails",Context.MODE_PRIVATE);
                    int position = sp.getInt("position",0);

                    JSONObject jsonOb = jArray.optJSONObject(position);
//                    Log.i("jsonOb",jsonOb.toString());
                    RequestDetailsClass reqDetail = new RequestDetailsClass(jsonOb);
                    Log.i("responseReqDetails1", response);
                    req_id_tv = view.findViewById(R.id.req_id_tv);
                    req_state_tv = view.findViewById(R.id.req_state_tv);
                    req_date_tv = view.findViewById(R.id.req_date_tv);
                    username_tv = view.findViewById(R.id.username_tv);
                    userNumber_tv = view.findViewById(R.id.userNumber_tv);
                    userAddress_tv = view.findViewById(R.id.userAddress_tv);
                    notes_tv = view.findViewById(R.id.notes_tv);
                    payWay_tv = view.findViewById(R.id.payWay_tv);
                    deliver_time_tv = view.findViewById(R.id.deliver_time_tv);
                    deliver_date_tv = view.findViewById(R.id.deliver_date_tv);
                    deliveryChoice_tv = view.findViewById(R.id.deliveryChoice_tv);
                    deliveryWay_tv = view.findViewById(R.id.deliveryWay_tv);
                    replaysChoice_tv = view.findViewById(R.id.replaysChoice_tv);
                    cobonCode_tv = view.findViewById(R.id.cobonCode_tv);
                    subTotal_tv = view.findViewById(R.id.subTotal_tv);
                    delivery_cost_tv = view.findViewById(R.id.delivery_cost_tv);
                    TotalCost_tv = view.findViewById(R.id.TotalCost_tv);
                    /************************************/
                    req_id_tv.setText(reqDetail.get_id());
                    _id =reqDetail.get_id();
                    String state_st="";
                    switch (reqDetail.getStatusId()){
                    case 4 :
                        state_st = "بانتظار استلام السائق";
                        break;
                    case 5 :
                        state_st = "قيد التوصيل";
                        break;
                    case 6 :
                        state_st = "تم الوصيل";
                        break;
                    case 8 :
                        state_st = "ملغي من قبل الادارة";
                        break;
                    case 9 :
                        state_st = "ملغي من قبل العميل";
                        break;
                    default:
                        break;
                    }
                    req_state_tv.setText(state_st);
                    req_date_tv.setText(reqDetail.getReq_date());
                    username_tv.setText(reqDetail.getSeller_full_name());
                    userNumber_tv.setText(reqDetail.getSeller_phone_number());
                    userAddress_tv.setText(reqDetail.getSeller_address());
                    notes_tv.setText(reqDetail.getNotes());
                    String payType_st = "";
                    switch (reqDetail.getPaymentType()) {
                    case 1:
                        payType_st = "البطاقة الإئتمانية";
                        break;
                    case 2:
                        payType_st = "فيزا";
                        break;
                    case 3:
                        payType_st = "الرصيد";
                        break;
                    default:
                        break;
                    }
                    payWay_tv.setText(payType_st);
                    deliver_time_tv.setText(reqDetail.getDeliv_time());
                    deliver_date_tv.setText(reqDetail.getDeliv_date());
                    deliveryChoice_tv.setText(reqDetail.getDeliv_option_name());
                    deliveryWay_tv.setText(reqDetail.getDelev_name());
                    String replace="";
                    switch (reqDetail.getIsNotFound()) {
                        case 1:
                            replace = "استبدال";
                            break;
                        case 2:
                            replace = "لا تستبدل";
                            break;
                        case 3:
                            replace = "لا شيء";
                            break;
                        default:
                            break;
                    }
                    replaysChoice_tv.setText(replace);
                    cobonCode_tv.setText(reqDetail.getCoupon());
                    String subTotal_st = reqDetail.getSubTotal()+"";
                    subTotal_tv.setText(subTotal_st);
                    String deliveryCost_st=reqDetail.getDeliveryCost() +"";
                    delivery_cost_tv.setText(deliveryCost_st);
                    String totalCost_st= reqDetail.getTotal()+"";
                    TotalCost_tv.setText(totalCost_st);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void errorResponse(String error) {

            }
        });
//        HttpRequest httpRequest1 = new HttpRequest();
//        httpRequest1.GetProducts(getContext(),type,productList_RV,"detail",RequestDetails.this);
        /************************Actions**************************/
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

//        cancelReq_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopUp();
//            }
//        });
        /******************************************************/
        return view;
    }
//    public void showPopUp(){
//        cancelReqDialog= new Dialog(Other_Activity.context);
//        cancelReqDialog.setContentView(R.layout.popup_cancle_request);
//        cancelReqDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        cancelReqDialog.show();
//        final EditText cancelReq_et = cancelReqDialog.findViewById(R.id.cancelReq_et);
//        Button cancelReq_btn =cancelReqDialog.findViewById(R.id.cancelReq_btn);
//        Button back_btn =cancelReqDialog.findViewById(R.id.back_btn);
//
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancelReqDialog.dismiss();
//            }
//        });
//
//        cancelReq_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /** here we have to but the code needed for canceling*/
//                if(!cancelReq_et.getText().toString().trim().equals("")){
//                    cancelReqDialog.dismiss();
//                    HttpRequest_POST put = new HttpRequest_POST();
//                    put.Put_edit_requestCase(Other_Activity.context,_id, new CallBackResponse() {
//                        @Override
//                        public void getResponse(JSONObject jsonObject) {
//
//                        }
////chatAdapter.notifyDataSetChanged();
//                        @Override
//                        public void getResponse(String response) {
//                            JSONObject jsonObject = null;
//                            String msg ;
//                            try {
//                                jsonObject = new JSONObject(response);
//                                Log.i("TEST1","Passed!");
////                                RemoveThisItem(position);
//                                msg = jsonObject.getString("message");
//                                Toast.makeText(Other_Activity.context, msg, Toast.LENGTH_LONG).show();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Log.i("Error",e.getMessage());
//                            }
//                        }
//
//                        @Override
//                        public void errorResponse(String error) {
//                            Toast.makeText(Other_Activity.context, error+"", Toast.LENGTH_LONG).show();
//                        }
//                    },9);
//                }else {
//                    cancelReq_et.setError("اكتب السبب رجاءً");
//                }
//
//            }
//        });
//    }
}
