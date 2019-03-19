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
import com.example.engosama.likein_deliver.Classes.Product_Classes.Product;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
//    private ArrayList<Product> mItems;
    private static final String TAG = "RecyclerViewAdapter";
    //vars
    private List<Product> mItems = new ArrayList<>();
    private String adapter_type;
    private Context mContext;
    private Fragment mFragment;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName_tv,ProductQuantity_tv;
        ImageView product_img;
        public MyViewHolder(View v) {
            super(v);
            productName_tv = v.findViewById(R.id.productName_tv);
            ProductQuantity_tv = v.findViewById(R.id.ProductQuantity_tv);
            product_img = v.findViewById(R.id.product_img);

        }
    }

    public ProductAdapter(Context context, List<Product> names, String adapter_type, Fragment mFragment) {
        mItems = names;
        mContext = context;
        this.adapter_type = adapter_type;
        this.mFragment = mFragment;
    }

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_req_detail_product_list_product, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String pruductName=mItems.get(position).getPrice_name()+ " " +mItems.get(position).getName() ;
        holder.productName_tv.setText(pruductName);
        String qty = mItems.get(position).getQty()+"";
        holder.ProductQuantity_tv.setText(qty);

        Glide.with(mContext)
                .load(mItems.get(position).getImage())
                .into(holder.product_img)
        ;



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
