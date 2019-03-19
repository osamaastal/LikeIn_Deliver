package com.example.engosama.likein_deliver.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.engosama.likein_deliver.Classes.TermsConditions.TermsAndCondClass;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;

public class TermsAndCondAdapter extends RecyclerView.Adapter<TermsAndCondAdapter.MyViewHolder> {
    private ArrayList<TermsAndCondClass> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView title_tv,content_tv;

        public MyViewHolder(View v) {
            super(v);
            title_tv = v.findViewById(R.id.title_tv);
            content_tv = v.findViewById(R.id.content_tv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TermsAndCondAdapter(ArrayList<TermsAndCondClass> mDataset) {
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TermsAndCondAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.row_chat_msg_deliver, parent, false);
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_terms_and_conditions_main, null);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title_tv.setText(mDataset.get(position).getTitle());
        holder.content_tv.setText(mDataset.get(position).getContent());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
