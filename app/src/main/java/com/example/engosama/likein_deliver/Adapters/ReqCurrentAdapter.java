package com.example.engosama.likein_deliver.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.Classes.Req_Classes.ReqCompletedClass;
import com.example.engosama.likein_deliver.Fragments.Req_Completed;
import com.example.engosama.likein_deliver.Fragments.Req_Current;
import com.example.engosama.likein_deliver.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ReqCurrentAdapter extends RecyclerView.Adapter<ReqCurrentAdapter.MyViewHolder> {

//    private ArrayList<ReqCompletedClass> mItems;
    private static List<ReqCompletedClass> mItems;
    private String adapter_type;
    private Context mContext;
    private Fragment mFragment;
    public static int countIDs=0;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reqNumber_tv,username_tv,hyber1_tv,req_time_tv,
                req_date_tv,address_tv,no_of_products_tv,cost_tv;
        ImageView info_icon,msg_icon,call_icon;
        Button current_btn;

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
            msg_icon = v.findViewById(R.id.msg_icon);
            call_icon = v.findViewById(R.id.call_icon);
            current_btn = v.findViewById(R.id.current_btn);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReqCurrentAdapter(Context context, List<ReqCompletedClass> names, String adapter_type, Fragment mFragment) {
        mItems = names;
        mContext = context;
        this.adapter_type=adapter_type;
        this.mFragment=mFragment;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReqCurrentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_req_current, parent, false);


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
                SharedPreferences sp =mContext.getSharedPreferences("ReqDetails",MODE_PRIVATE);
                SharedPreferences.Editor Ep = sp.edit();
                Ep.putInt("position",position);
                Ep.putInt("type",5);
                Ep.commit();
                Requests.GoTo_OtherActivity_static("f_request_details");
            }
        });
        holder.msg_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Requests.GoTo_Chat();
            }
        });
        holder.call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNO = mItems.get(position).getUserSeller().getPhoneNumber();
                Requests.context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNO, null)));
            }
        });
        final String _id= mItems.get(position).get_id();
        holder.current_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
                if(_id!=null){
                    Requests.allIDS.remove(_id);
                    Log.i("allIDS_sizs",Requests.allIDS.size()+"");
                }
                /**$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
                HttpRequest_POST requestPost = new HttpRequest_POST();
                requestPost.Put_edit_requestCase(mContext,_id, new CallBackResponse() {
                    @Override
                    public void getResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void getResponse(String response) {
                        JSONObject jsonObject = null;
                        String msg ;
                        try {
                            jsonObject = new JSONObject(response);
                            Log.i("TEST1","Passed!");
//                          RemoveThisItem(position);
                            msg = jsonObject.getString("message");
                            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Error",e.getMessage());
                        }
                        mItems.remove(mItems.get(position));
                        notifyDataSetChanged();
//                        FragmentTransaction ft = Req_Current.getFragmentManager().beginTransaction();
//                        ft.detach(this).attach(this).commit();
                    }

                    @Override
                    public void errorResponse(String error) {
                        Toast.makeText(mContext, "خطأ اثناء التحويل", Toast.LENGTH_SHORT).show();
                        Log.i("ErrorResponse",error+"1");
                    }
                }, 6);

            }
        });
        /*************************************************************/
        /**$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
//        if(_id!=null ){
//            Requests.allIDS.add(_id);
//        }
        /**$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
    }

    // Return the size of your dataset (invoked by the layout manager)

    @Override
    public int getItemCount() {
        return mItems.size();
    }
//    public void RemoveThisItem(int position){
//        mItems.remove(position);
//    }
    public static ArrayList<String> getCountIDs(){
        ArrayList<String> allIdOfCurrntAdapter = new ArrayList<>();
        for (int i=0; i < mItems.size();i++){
            String newID = mItems.get(i).get_id();
            allIdOfCurrntAdapter.add(newID);
        }
        return allIdOfCurrntAdapter;
    }
}
