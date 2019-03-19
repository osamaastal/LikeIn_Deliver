package com.example.engosama.likein_deliver.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.engosama.likein_deliver.Classes.Alert.Alert;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {
//    private ArrayList<Alert> mItems;
    private static final String TAG = "RecyclerViewAdapter";
    //vars
    private List<Alert> mItems = new ArrayList<>();
    private String adapter_type;
    private Context mContext;
    private Fragment mFragment;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView alert_title_tv,alert_content_tv,alert_date_tv;
//        ImageView alert_img;

        public MyViewHolder(View v) {
            super(v);
            alert_title_tv = v.findViewById(R.id.alert_title_tv);
            alert_content_tv = v.findViewById(R.id.alert_content_tv);
            alert_date_tv = v.findViewById(R.id.alert_date_tv);
//            alert_img = v.findViewById(R.id.alert_img);
        }
    }

    public AlertAdapter(Context context, List<Alert> names, String adapter_type, Fragment mFragment) {
        mItems = names;
        mContext = context;
        this.adapter_type=adapter_type;
        this.mFragment=mFragment;
    }

    @Override
    public AlertAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_alerts_main, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.alert_title_tv.setText(mItems.get(position).getTitle());
        holder.alert_content_tv.setText(mItems.get(position).getMsg());
        String DateAndTime = mItems.get(position).getDate()+" at "+mItems.get(position).getTime();
        holder.alert_date_tv.setText(DateAndTime);
//        holder.alert_img.setImageBitmap(mItems.get(position).getAlertImg());/** Bitmap*/

//        Glide.with(mContext)
//                .load(mItems.get(position).getAlertImg())
//                .into(holder.alert_img)
//        ;


//        holder.alert_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
