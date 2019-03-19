package com.example.engosama.likein_deliver.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.Classes.Req_Classes.show_RequestsNew;
import com.example.engosama.likein_deliver.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReqNewAdapter extends RecyclerView.Adapter<ReqNewAdapter.MyViewHolder> {
//    private ArrayList<ReqCompletedClass> mItems;
    private List<ReqCompletedClass> mItems ;
    private String adapter_type;
    private Context mContext;
    private Fragment mFragment;
    private int position;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView reqNumber_tv,username_tv,hyber1_tv,req_time_tv,
                req_date_tv,address_tv,no_of_products_tv,cost_tv;
        private Button new_btn;


        public MyViewHolder(View v) {
            super(v);
            reqNumber_tv = v.findViewById(R.id.reqNumber_tv);
            username_tv = v.findViewById(R.id.username_tv);
            hyber1_tv = v.findViewById(R.id.hyber1_tv);
            req_time_tv = v.findViewById(R.id.req_time_tv);
            req_date_tv = v.findViewById(R.id.req_date_tv);
            address_tv = v.findViewById(R.id.address_tv);
            no_of_products_tv = v.findViewById(R.id.no_of_products_tv);
            cost_tv = v.findViewById(R.id.cost_tv);
            new_btn = v.findViewById(R.id.new_btn);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReqNewAdapter(Context context, List<ReqCompletedClass> names, String adapter_type, Fragment mFragment) {
        mItems = names;
        mContext = context;
        this.adapter_type=adapter_type;
        this.mFragment=mFragment;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReqNewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_req_new, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        this.position =position;
        holder.reqNumber_tv.setText(mItems.get(position).get_id());
        holder.username_tv.setText(mItems.get(position).getUsername());
        holder.hyber1_tv.setText(mItems.get(position).getHyber_name());
        holder.req_time_tv.setText(mItems.get(position).getReq_time());
        holder.req_date_tv.setText(mItems.get(position).getReq_date());
        holder.address_tv.setText(mItems.get(position).getAddressDetails());
        String temp1= ""+mItems.get(position).getNo_of_products();
        holder.no_of_products_tv.setText(temp1);
        String temp = ""+mItems.get(position).getTotal();
        holder.cost_tv.setText(temp);
        final String _id = mItems.get(position).get_id();
        holder.new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest_POST requestPost = new HttpRequest_POST();
                requestPost.Put_edit_requestCase(mContext,_id, new CallBackResponse() {
                    @Override
                    public void getResponse(JSONObject jsonObject) {
                    }

                    @Override
                    public void getResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            Log.i("TEST1","Passed!");
//                            RemoveThisItem(position);

                            Toast.makeText(mContext, "تم التحويل الى الطلبات الحالية", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Error",e.getMessage());
                        }
                        mItems.remove(mItems.get(position));
                        notifyDataSetChanged();

                    }

                    @Override
                    public void errorResponse(String error) {
                        Toast.makeText(mContext, "خطأ اثناء التحويل", Toast.LENGTH_SHORT).show();
                        Log.i("ErrorResponse",error+"1");
                    }
                }, 5);
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void RemoveThisItem(int position){
        mItems.remove(position);
    }
}
