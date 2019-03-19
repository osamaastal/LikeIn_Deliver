package com.example.engosama.likein_deliver.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.engosama.likein_deliver.Classes.NewMessage;
import com.example.engosama.likein_deliver.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private ArrayList<NewMessage> mDataset;
    public static final int MSG_LEFT = 0;
    public static final int MSG_RIGHT = 1;
    DatabaseReference reference;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView msg,msg_time;

        public MyViewHolder(View v) {
            super(v);
            msg = v.findViewById(R.id.msg_tv);
            msg_time = v.findViewById(R.id.msg_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(ArrayList<NewMessage> mDataset) {
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v;
        if(viewType == MSG_LEFT){
             v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_chat_msg_deliver, null);
        }else{
             v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_chat_msg_admin, null);
        }
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.msg.setText(mDataset.get(position).getMsg_str());
        holder.msg_time.setText(mDataset.get(position).getMsg_time_str());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mDataset.get(position).getType() == 0 )
           return MSG_LEFT;
        else
            return MSG_RIGHT;
//        SharedPreferences sp = Chat.context.getSharedPreferences("Login", Context.MODE_PRIVATE);
//        String _id = sp.getString("_id",null);
//        if(_id != null ){
//            reference= FirebaseDatabase.getInstance().getReference().child("messages").child(_id);
//        }
    }
}
