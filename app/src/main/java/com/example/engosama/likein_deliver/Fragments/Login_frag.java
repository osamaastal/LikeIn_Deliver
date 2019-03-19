package com.example.engosama.likein_deliver.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Login;
//import com.example.engosama.likein_deliver.Classes.Client.User;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.R;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class Login_frag extends Fragment {

    View view;
    TextView sign_up_tv,forgot_pass_tv;
    EditText email_et,password_et;
    Button login_btn;
    private String fcmToken;
//    public static User _user;
    public Login_frag() {
        // Required empty public constructor
    }

    public static Login_frag newInstance(String param1, String param2) {
        Login_frag fragment = new Login_frag();
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
        view = inflater.inflate(R.layout.layout_f_login_frag, container, false);
        login_btn =  view.findViewById(R.id.login_btn);
//        sign_up_tv =  view.findViewById(R.id.sign_up_tv);
        forgot_pass_tv =  view.findViewById(R.id.forgot_pass_tv);
        email_et =  view.findViewById(R.id.email_et);
        password_et =  view.findViewById(R.id.password_et);

//        sign_up_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Login.switch_fragment(new SignUp());
//            }
//        });
        forgot_pass_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.switch_fragment(new Password_BackUp());
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Login.context, "Test Button", Toast.LENGTH_SHORT).show();
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                String emptyErrorMsg = "املأ هذا الحقل!";
                if(email.equals("")|| password.equals("")){
                    if(email.equals("")){
                        email_et.setError(emptyErrorMsg);
                    }
                    if(password.equals("")){
                        password_et.setError(emptyErrorMsg);
                    }
                }else {
                        try{
                            fcmToken = FirebaseInstanceId.getInstance().getToken();
                            SharedPreferences sp=getActivity().getSharedPreferences("Login", MODE_PRIVATE);
                            SharedPreferences.Editor Ed=sp.edit();
                            Ed.putString("fcmToken",fcmToken );
                            Ed.commit();
                        }catch (Exception e){
                            Toast.makeText(Login.context, "Error fcmtoken: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        HttpRequest_POST post=new HttpRequest_POST();
                        post.Post_Login(Login.context,email,password);
//
                    }

            }
        });

        return view;
    }

    public void sendPost() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("https://likenapi.herokuapp.com/Driver/login");
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//                    conn.setRequestProperty("Accept","application/json");
//                    conn.setDoOutput(true);
//                    conn.setDoInput(true);
//
//                    JSONObject jsonParam = new JSONObject();
////                    jsonParam.put("_id", "is this important?");
//                    jsonParam.put("email", email_et.getText().toString());
//                    jsonParam.put("password", password_et.getText().toString());
//
//                    Log.i("JSON", jsonParam.toString());
//                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
//                    os.writeBytes(jsonParam.toString());
//
//                    os.flush();
//                    os.close();
//
//                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
//                    Log.i("MSG" , conn.getResponseMessage());
//
//                    conn.disconnect();
//                } catch (Exception e) {
//                    Log.i("Error: ",e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
    }
    private void Firebase_Token(){
//        if (_user.getVerifyCode().equals(conf_code_et.getText().toString())) {
//            final String token= FirebaseInstanceId.getInstance().getToken();
//            HttpRequest_POST post=new HttpRequest_POST();
//            post.Post_ActvitPhone_user(getContext(), _user.getId()
//                    , token, new HttpRequest_POST.Onusercreater() {
//                        @Override
//                        public void onSuccess(User user_, String msg) {
//                            Toast.makeText(getContext(),msg+"Token"+user_.getToken(),Toast.LENGTH_SHORT).show();
//
//                            /**************Save Login Inf*********/
//                            SharedPreferences sp=getActivity().getSharedPreferences("Login", MODE_PRIVATE);
//                            SharedPreferences.Editor Ed=sp.edit();
//                            Ed.putString("phone",user_.getPhoneNumber() );
//                            Ed.putString("id",user_.getId());
//                            Ed.putString("token",user_.getToken());
//                            Ed.commit();
//
//                            Intent intent = new Intent(Login.context, Requests.class);
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onStart() {
//
//                        }
//
//                        @Override
//                        public void onFailure(String msg) {
//                            Toast.makeText(getContext(),token+msg,Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
    }
//    public static void setUser(User user){
//        _user = user;
//    }
//    public void postData() {
//        // Create a new HttpClient and Post Header
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");
//
//        try {
//            // Add your data
//            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//            nameValuePairs.add(new BasicNameValuePair("id", "12345"));
//            nameValuePairs.add(new BasicNameValuePair("stringdata", "Hi"));
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//            // Execute HTTP Post Request
//            HttpResponse response = httpclient.execute(httppost);
//
//        } catch (ClientProtocolException e) {
//            // TODO Auto-generated catch block
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//        }
//    }

}
