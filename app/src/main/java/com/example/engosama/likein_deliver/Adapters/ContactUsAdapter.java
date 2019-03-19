package com.example.engosama.likein_deliver.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.engosama.likein_deliver.Classes.ContactUsClass;
import com.example.engosama.likein_deliver.R;

import java.util.ArrayList;

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.MyViewHolder> {
    private ArrayList<ContactUsClass> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView name_tv,data_tv;

        public MyViewHolder(View v) {
            super(v);
            name_tv = v.findViewById(R.id.name_tv);
            data_tv = v.findViewById(R.id.data_tv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ContactUsAdapter(ArrayList<ContactUsClass> mDataset) {
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ContactUsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.row_chat_msg_deliver, parent, false);
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contact_us_main, null);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name_tv.setText(mDataset.get(position).getName());
        holder.data_tv.setText(mDataset.get(position).getData());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
