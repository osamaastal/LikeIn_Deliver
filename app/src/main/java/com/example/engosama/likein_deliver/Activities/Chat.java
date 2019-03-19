package com.example.engosama.likein_deliver.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Adapters.ChatAdapter;
import com.example.engosama.likein_deliver.Classes.NewMessage;
import com.example.engosama.likein_deliver.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chat extends AppCompatActivity {

    Map<String,Object> map2;
    HashMap<String,String>  user_details;
    ImageView send_btn,back_btn;
    EditText newMsg_et;
    RecyclerView chat_RV;

    public static Context context;
    ChatAdapter chatAdapter;
    ArrayList<NewMessage> msg_arraylist;
    LinearLayoutManager mLayoutManager;
    DatabaseReference reference;

    String Username, new_msg_key;
    boolean isRead= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_chat);
        /************************Definitions***********************************/
        context = this;
//        Username= Login.Username;
//        String  intent = getIntent().getExtras().getString("");
        send_btn = findViewById(R.id.send_btn);
        back_btn = findViewById(R.id.back_btn);
        newMsg_et = findViewById(R.id.newMsg_et);
        chat_RV = findViewById(R.id.conversation_RV);
        msg_arraylist= new ArrayList<>();
        /**********************************************************************/
        /***********************SharedPreference******************************/
        SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
        Username=sp.getString("userName",null);
        String Diver_id = sp.getString("_id",null);
        final String userImg = sp.getString("userImg",null);

        /******************************Adapter*********************************/
        chatAdapter = new ChatAdapter(msg_arraylist);
        chat_RV.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        chat_RV.setLayoutManager(mLayoutManager);
        chat_RV.setAdapter(chatAdapter);

        /************************DatabaseReference******************************/

        Log.i("Diver_id",Diver_id);
        reference = FirebaseDatabase.getInstance().getReference().child("messages").child(Diver_id);//"NewBranchForTesting"
        /***********************************************************************/

        /*******************************Actions*********************************/
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMsg_et.getText().toString().trim().equals("")){
                    Toast.makeText(Chat.this, "OK!", Toast.LENGTH_SHORT).show();
                    Map<String,Object> map1 = new HashMap<>();
                    new_msg_key =  reference.push().getKey();
                    reference.updateChildren(map1);

                    user_details = new HashMap<>();
                    DatabaseReference reference1 = reference.child(new_msg_key);
                    map2 = new HashMap<>();
//                    map2.put("isRead",isRead);
                    map2.put("reply",true);
                    map2.put("text", newMsg_et.getText().toString().trim());
                    map2.put("type","text");
                    if(!userImg.equals("") && !userImg.equals("null"))
                        user_details.put("avatar",userImg);
                    else
                        user_details.put("avatar","No image for this user");
                    user_details.put("name",Username);
                    map2.put("user", user_details);


                    reference1.updateChildren(map2);
                    newMsg_et.setText("");
                }
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                updateConversation( dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                updateConversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /************************************************************************/
    }

    private void updateConversation(DataSnapshot dataSnapshot){
        String  msg= "";
        int type;
        NewMessage newMsg = new NewMessage();
        Iterator iterator = dataSnapshot.getChildren().iterator();

//        ArrayList<Object> xx = new ArrayList<>();
//
//        while (iterator.hasNext()){
//            xx.add(((DataSnapshot)iterator.next()).getValue());
//        }

        msg = dataSnapshot.child("text").getValue().toString();
        try{
            if(dataSnapshot.child("user").child("name").getValue().toString().equals("Admin")||
                    dataSnapshot.child("user").child("name").getValue().toString().equals("admin"))
                type = 1;
            else
                type = 0;
        }catch(Exception e){
            if(dataSnapshot.child("User").child("name").getValue().toString().equals("Admin")||
                    dataSnapshot.child("user").child("name").getValue().toString().equals("admin"))
                type = 1 ;
            else
                type = 0;
        }

//        if(xx.size()==4){
//            msg = xx.get(1).toString();
//        }

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        newMsg.setMsg_str(msg);
        newMsg.setMsg_time_str(today.format("%k:%M"));
        newMsg.setType(type);
        msg_arraylist.add(newMsg);
        chatAdapter.notifyDataSetChanged();

        /*********************************/
//        while(i.hasNext()){
//            msg = (String)((DataSnapshot)i.next()).getValue();
//            User = (String)((DataSnapshot)i.next()).getValue();
//            newMsg.setMsg_str(msg);
//            newMsg.setMsg_time_str(Calendar.HOUR+":"+Calendar.MINUTE+" "+Calendar.AM_PM);
//
////            if(User.equals(Username) ){
//                msg_arraylist.add(newMsg);
//                chatAdapter.notifyDataSetChanged();
////            }else{
////                Toast.makeText(this, User+ " != "+Username, Toast.LENGTH_SHORT).show();
////            }
//
//
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRead=true;
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        isRead=false;
//    }
}
