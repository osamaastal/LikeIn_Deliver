package com.example.engosama.likein_deliver.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.Fragments.Req_Completed;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;
import java.util.List;

public class ReqCompletedAdapter extends RecyclerView.Adapter<ReqCompletedAdapter.MyViewHolder> {

    //    private ArrayList<ReqCompletedClass> mItems;
    private List<ReqCompletedClass> mItems = new ArrayList<>();
    private String adapter_type;
    private Context mContext;
    private Fragment mFragment;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reqNumber_tv,username_tv,hyber1_tv,req_time_tv,
                req_date_tv,address_tv,no_of_products_tv,cost_tv;
        ImageView info_icon;
//        Button completed_btn;

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
            info_icon = v.findViewById(R.id.info_icon);
//            completed_btn = v.findViewById(R.id.completed_btn);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
//    public ReqCompletedAdapter(ArrayList<ReqCompletedClass> mItems) {
//        this.mItems = mItems;
//    }
    public ReqCompletedAdapter(Context context, List<ReqCompletedClass> names, String adapter_type, Fragment mFragment) {
        mItems = names;
        mContext = context;
        this.adapter_type=adapter_type;
        this.mFragment=mFragment;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReqCompletedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_req_completed, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.reqNumber_tv.setText(mItems.get(position).get_id());
        holder.username_tv.setText(mItems.get(position).getUsername());
        holder.hyber1_tv.setText(mItems.get(position).getHyber_name());
//        holder.hyber2_tv.setText(mItems.get(position).getHyber2());
        holder.req_time_tv.setText(mItems.get(position).getReq_time());
        holder.req_date_tv.setText(mItems.get(position).getReq_date());
        holder.address_tv.setText(mItems.get(position).getAddressDetails());
        String temp1= ""+mItems.get(position).getNo_of_products();
        holder.no_of_products_tv.setText(temp1);
        String temp = ""+mItems.get(position).getTotal();
        holder.cost_tv.setText(temp);

        /************************Actions***************************/
        holder.info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp =mContext.getSharedPreferences("ReqDetails",Context.MODE_PRIVATE);
                SharedPreferences.Editor Ep = sp.edit();
                Ep.putInt("position",position);
                Ep.putInt("type",6);
                Ep.commit();
                Requests.GoTo_OtherActivity_static("f_request_details");
            }
        });

        /*************************************************************/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
