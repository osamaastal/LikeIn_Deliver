package com.example.engosama.likein_deliver.Fragments;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.engosama.likein_deliver.Activities.Login;
import com.example.engosama.likein_deliver.Activities.Other_Activity;
import com.example.engosama.likein_deliver.Classes.Client.Deliver;
import com.example.engosama.likein_deliver.Classes.Client.Deliver_No_noken;
import com.example.engosama.likein_deliver.Classes.Http.CallBackResponse;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.Classes.Upload_Image.VolleyMultipartRequest;
import com.example.engosama.likein_deliver.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.hdodenhof.circleimageview.CircleImageView;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class MyProfile extends Fragment implements AdapterView.OnItemSelectedListener {

    private Context mcontext = Other_Activity.context;
    private View view;
    private ImageView back_btn;
    private CircleImageView userImg;
    private EditText username_et,phoneNo_et,email_et;
    private Button upload1_btn,upload2_btn,upload3_btn,Logout_btn,save_btn;
    private Spinner spinner;
    private RequestQueue requestQueue;
    private Deliver deliver;
    private static final int GET_FROM_GALLERY = 1;
    private int UploadImgType=0;
    private byte[] byteImage_photo;
    private String Nationality;
    /**********************New Upload*************************/
    private String upload_URL = "https://likenapi.herokuapp.com/Driver/file_upload";
    private RequestQueue rQueue;
    private Map<String,Bitmap> BitmapPicsMap= new HashMap<>();

    private Map<String,String> URLPicsMap = new HashMap<>();

    private String User_pic = "User";
    private String ID_pic = "ID";
    private String Car_license_pic = "Car_license";
    private String Car_pic = "Car";

    /********************************************************/

    public MyProfile() {
        // Required empty public constructor
    }

    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
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
        view = inflater.inflate(R.layout.layout_f_my_profile, container, false);
        requestMultiplePermissions();
        BitmapPicsMap.put(User_pic,null);
        BitmapPicsMap.put(ID_pic,null);
        BitmapPicsMap.put(Car_license_pic,null);
        BitmapPicsMap.put(Car_pic,null);

        URLPicsMap.put(User_pic,null);
        URLPicsMap.put(ID_pic,null);
        URLPicsMap.put(Car_license_pic,null);
        URLPicsMap.put(Car_pic,null);

        /*********************Definition ************************/
        back_btn = view.findViewById(R.id.back_btn);
        userImg = view.findViewById(R.id.userImg);
        username_et = view.findViewById(R.id.username_et);
        phoneNo_et = view.findViewById(R.id.phoneNo_et);
        email_et = view.findViewById(R.id.email_et);
        upload1_btn = view.findViewById(R.id.upload1_btn);
        upload2_btn = view.findViewById(R.id.upload2_btn);
        upload3_btn = view.findViewById(R.id.upload3_btn);
        Logout_btn = view.findViewById(R.id.Logout_btn);
        save_btn = view.findViewById(R.id.save_btn);
        /**************************Spinner *******************************/
         spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mcontext,
                R.array.country_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        /********************************************************************/
        /************************JSON-Volley*******************************/

        HttpRequest request = new HttpRequest();
        request.GetProfileData(mcontext, new CallBackResponse() {
            @Override
            public void getResponse(JSONObject jsonObject) {

            }

            @Override
            public void getResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONObject jsonOb = jsonObject.getJSONObject("items");
                    Deliver_No_noken deliver = new Deliver_No_noken(jsonOb);
                    String imgURL = deliver.getImage();
                    if(!imgURL.equals("") && !imgURL.equals("null")){
//                        Toast.makeText(mcontext, imgURL, Toast.LENGTH_SHORT).show();
                        Picasso.get().load(imgURL).fit().centerInside().into(userImg);
                    }
                    username_et.setText(deliver.getName());
                    phoneNo_et.setText(deliver.getPhone_number());
                    email_et.setText(deliver.getEmail());
                    switch (deliver.getAddress().trim()){
                        case "":
                            spinner.setSelection(0);
                            break;
                        case "السعودية":
                            spinner.setSelection(1);
                            break;
                        case "الإمارات":
                            spinner.setSelection(2);
                            break;
                        case "البحرين":
                            spinner.setSelection(3);
                            break;
                        case "قطر":
                            spinner.setSelection(4);
                            break;
                        case "اليمن":
                            spinner.setSelection(5);
                            break;
                        case "عمان":
                            spinner.setSelection(6);
                            break;
                        case "الجزائر":
                            spinner.setSelection(7);
                            break;
                        case "فلسطين":
                            spinner.setSelection(8);
                            break;
                        case "الأردن":
                            spinner.setSelection(9);
                            break;
                        case "لبنان":
                            spinner.setSelection(10);
                            break;
                        case "سوريا":
                            spinner.setSelection(11);
                            break;
                        case "تونس":
                            spinner.setSelection(12);
                            break;
                        case "السودان":
                            spinner.setSelection(13);
                            break;
                        case "ليبيا":
                            spinner.setSelection(14);
                            break;
                        case "العراق":
                            spinner.setSelection(15);
                            break;
                        default:
                            break;

                    }


                 } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Error: ", e.getMessage());
                 }
            }

            @Override
            public void errorResponse(String error) {
                Log.i("error",error);
            }
        });
//        parseJSON();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mcontext, Nationality, Toast.LENGTH_SHORT).show();
//                ChangeData();
                checkFields();
                /******* get fcmToken *******/
                SharedPreferences sp1 = mcontext.getSharedPreferences("Login",MODE_PRIVATE);
                String fcmToken = sp1.getString("fcmToken",null);
                /***************************/
                SharedPreferences sp = mcontext.getSharedPreferences("Deliver", MODE_PRIVATE);
                SharedPreferences.Editor Ed=sp.edit();
                Ed.putString("email",email_et.getText().toString());
                Ed.putString("fcmToken",fcmToken);

//                if(BitmapPicsMap.get(ID_pic)!= null)
                    Ed.putString("images[0]",URLPicsMap.get(ID_pic));

//                if(BitmapPicsMap.get(Car_license_pic)!= null)
                    Ed.putString("images[1]",URLPicsMap.get(Car_license_pic));

//                if(BitmapPicsMap.get(Car_pic)!= null)
                    Ed.putString("images[2]",URLPicsMap.get(Car_pic));

//                if(BitmapPicsMap.get(User_pic)!= null)
                    Ed.putString("image",URLPicsMap.get(User_pic));

//                if(!username_et.getText().toString().equals(""))
                    Ed.putString("name",username_et.getText().toString());

//                if(!phoneNo_et.getText().toString().equals(""))
                    Ed.putString("phone_number",phoneNo_et.getText().toString());

                Ed.putString("address",Nationality);
                Ed.commit();
                /**************************************************************/
                try{
                    HttpRequest_POST post=new HttpRequest_POST();
                    post.Put_update_prof(mcontext, new CallBackResponse() {
                        @Override
                        public void getResponse(JSONObject jsonObject) {

                        }
                        /**   requestQueue.getCache().clear(); */
                        @Override
                        public void getResponse(String response) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                JSONObject jsonOb = jsonObject.getJSONObject("items");
                                String msg = jsonObject.getString("message");
                                if(msg.equals("")){
                                    Deliver deliver = new Deliver(jsonOb);
//                                    /******* get fcmToken *******/
//                                    SharedPreferences sp1 = mcontext.getSharedPreferences("Login",MODE_PRIVATE);
//                                    String fcmToken = sp1.getString("fcmToken",null);
                                }else{
                                    Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
//                                Toast.makeText(mcontext, "ErrorXX go to Log", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void errorResponse(String error) {

                        }
                    });
                }catch (Exception e){
//                    Toast.makeText(mcontext, "ErrorXX go to Log.i ", Toast.LENGTH_SHORT).show();
                    Log.i("ErrorXX",e.getMessage());
                }

                /*********************************************************************/


            }
        });
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImgType = 1;
                CatchFrom_GALLERY();
            }
        });
        upload1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImgType = 2;
                CatchFrom_GALLERY();
            }
        });
        upload2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImgType = 3;
                CatchFrom_GALLERY();
            }
        });
        upload3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImgType = 4;
                CatchFrom_GALLERY();
            }
        });
        return view;
    }

    private boolean checkFields(){
        if(username_et.getText().toString().equals("")){
            username_et.setError("أدخل الاسم");
            return false;
        }
        if (phoneNo_et.getText().toString().equals("")){
            phoneNo_et.setError("ادخل رقم الهاتف");
            return false;
        }
        if (email_et.getText().toString().equals("")){
            phoneNo_et.setError("ادخل البريد الالكتروني");
            return false;
        }
        if (spinner.getSelectedItem()==null || spinner.getSelectedItem().toString().equals("اختر الدولة")){
            Toast.makeText(mcontext, "أدخل الجنسية", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(mcontext, spinner.getSelectedItem()+"", Toast.LENGTH_SHORT).show();
        return true;
    }

    /********************************************************************************/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /***new */
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        /******** UploadImgType  ====  2 , 3 , 4   *************/
         if (requestCode==GET_FROM_GALLERY && resultCode == RESULT_OK ) {
            if (data != null) {
//                Toast.makeText(mcontext, "requestCode = " + requestCode, Toast.LENGTH_SHORT).show();
//                Log.i("requestCode=",requestCode+"");
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(mcontext.getContentResolver(), contentURI);
                    switch (UploadImgType){
                        case 1 :
                            BitmapPicsMap.put(User_pic,bitmap);
                            userImg.setImageBitmap(bitmap);
//                            Toast.makeText(mcontext, "UploadImgType = "+ UploadImgType, Toast.LENGTH_SHORT).show();
                            break;
                        case 2 :
                            BitmapPicsMap.put(ID_pic,bitmap);
//                            Toast.makeText(mcontext, "UploadImgType = "+ UploadImgType, Toast.LENGTH_SHORT).show();
                            break;
                        case 3 :
                            BitmapPicsMap.put(Car_license_pic,bitmap);
//                            Toast.makeText(mcontext, "UploadImgType = "+ UploadImgType, Toast.LENGTH_SHORT).show();
                            break;
                        case 4 :
                            BitmapPicsMap.put(Car_pic,bitmap);
//                            Toast.makeText(mcontext, "UploadImgType = "+ UploadImgType, Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }

                    uploadImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mcontext, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        /** code 1 */
//        //Detects request codes
//        if(requestCode==GET_FROM_GALLERY && resultCode == RESULT_OK && UploadImgType==1) {//
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(mcontext.getContentResolver(), selectedImage);
//                userImg.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_LONG).show();
//                e.printStackTrace();
//            } catch (IOException e) {
//                Toast.makeText(mcontext, e.getMessage(), Toast.LENGTH_LONG).show();
//                e.printStackTrace();
//            }
//        }
//
//        if(requestCode==GET_FROM_GALLERY && resultCode == RESULT_OK && UploadImgType==2) {//
//
//
//        }
//

    }


    public void CatchFrom_GALLERY(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(intent,GET_FROM_GALLERY);
    }

    /*******************************MultiPart Upload *****************************/
    ArrayList<HashMap<String, String>> arraylist;

    private void uploadImage(final Bitmap bitmap){
//        final String[] ImageURL = new String[1];
        final ProgressDialog dialog=new ProgressDialog(getContext());

        dialog.setTitle("رفع صورة");
        dialog.setMessage("يتم الآن رفع الصورة...\n قد يستغرق ذلك بعض الوقت😘");
        dialog.show();


        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, upload_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        String responseData = new String(response.data);
                        Log.i("ressssssoo",responseData);
                        rQueue.getCache().clear();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);
                            Log.i("responseUploadImage",responseData);
                            jsonObject.toString().replace("\\\\","");
                            JSONObject jobject = jsonObject.getJSONObject("result");
                            String ImageURL = jobject.getString("url");

                            switch (UploadImgType){
                                case 1:
                                    URLPicsMap.put(User_pic,ImageURL);
                                    break;
                                case 2:
                                    URLPicsMap.put(ID_pic,ImageURL);
                                    break;
                                case 3:
                                    URLPicsMap.put(Car_license_pic,ImageURL);
                                    break;
                                case 4:
                                    URLPicsMap.put(Car_pic,ImageURL);
                                    break;
                                default:
                                    break;
                            }
//                            URLPicsMap.put("",ImageURL);

                            Log.i("ImageURL", ImageURL);
                            Toast.makeText(mcontext, "تم رفع الصورة بنجاح", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
//                            if (jsonObject.getString("status").equals("true")) {
//
//                                arraylist = new ArrayList<HashMap<String, String>>();
//                                JSONArray dataArray = jsonObject.getJSONArray("data");
//
//                                String url = "";
//                                for (int i = 0; i < dataArray.length(); i++) {
//                                    JSONObject dataobj = dataArray.getJSONObject(i);
//                                    url = dataobj.optString("pathToFile");
//                                }
//                                dialog.dismiss();
////                                Glide.with(getContext()).load(url).into(user_img);
//                            }
//                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences sp = mcontext.getSharedPreferences("Login",MODE_PRIVATE);
                String token = sp.getString("token",null);
                 params.put("token", token);  //add string parameters
                return params;
            }

            /*
             *pass files using below method
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("filename", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };


        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rQueue = Volley.newRequestQueue(mcontext);
        rQueue.add(volleyMultipartRequest);
//        Log.i("ImageURLXXX",ImageURL[0]+"");
//        return ImageURL[0];
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(getActivity())
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getActivity(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    /*******************************************************************************/

    /****************************************************************************/
    /**    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^   */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Nationality = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Nationality = "السعودية";
    }
    /*************************************************************************/

    /*******************************************MultiPart - Ahmed ***********************************/
//    RequestQueue queue = MyVolley.getRequestQueue();
//    SimpleMultiPartRequest request = new SimpleMultiPartRequest(
//            Request.Method.POST,
//            APIConfig.EDIT_USER,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    MainApplication.hidepDialog();
//                    Log.e("response", response);
//                    MainApplication.hidepDialog();
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        boolean status = jsonObject.optBoolean("status");
//                        String message = jsonObject.optString("message");
//                        MainApplication.ToastMessage(context, message);
//                        if (status) {
//                            String items = jsonObject.optString("items");
//                            UserItem UserItem = MainApplication.gson.fromJson(items, UserItem.class);
//                            realmDatabase.addUser(UserItem);
//                            ((EditProfileActivity) context).finish();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    MainApplication.hidepDialog();
//                    Log.e("error", error.getMessage() + "/");
//                    NetworkResponse response = error.networkResponse;
//                    try {
//                        if (response != null) {
//                            String res = new String(response.data,
//                                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//                            Log.e("error response", res);
//                        }
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }) {
//        @Override
//        public Map<String, String> getHeaders() {
//            return MainApplication.Header(true);
//        }
//    };
//        if (!TextUtils.isEmpty(file))
//            request.addFile(TAGS.PROFILE_IMAGE, file);
//        request.addMultipartParam(TAGS.NAME, "text/plain", name);
////        request.addMultipartParam(TAGS.EMAIL, "text/plain", email);
//        request.addMultipartParam(TAGS.MOBILE, "text/plain", mobile);
//        request.addMultipartParam(TAGS.LOCATION, "text/plain", location);
//        request.addMultipartParam(TAGS.LATITUDE, "text/plain", lat);
//        request.addMultipartParam(TAGS.LONGITUDE, "text/plain", lng);
//        request.setFixedStreamingMode(true);
//        request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                                      DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        request.setShouldCache(false);
//        queue.add(request);
    /***************************************************************************************************/
//    public String makeServiceCall(String url, int method,
//                                  List<NameValuePair> params) {
//        try {
//            // http client
//            HttpParams httpParameters = new BasicHttpParams();
//            // Set the timeout in milliseconds until a connection is
//            // established.
//            int timeoutConnection = 3000;
//            HttpConnectionParams.setConnectionTimeout(httpParameters,
//                    timeoutConnection);
//            // Set the default socket timeout (SO_TIMEOUT)
//            // in milliseconds which is the timeout for waiting for data.
//            int timeoutSocket = 3000;
//            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpEntity httpEntity = null;
//            HttpResponse httpResponse = null;
//
//            // Checking http request method type
//            if (method == POST) {
//                HttpPost httpPost = new HttpPost(url);
//                // adding post params
//                if (params != null) {
//                    httpPost.setEntity(new UrlEncodedFormEntity(params));
//                }
//                httpResponse = httpClient.execute(httpPost);
//            } else if (method == GET) {
//                // appending params to url
//                if (params != null) {
//                    String paramString = URLEncodedUtils
//                            .format(params, "utf-8");
//                    url += "?" + paramString;
//                }
//                HttpGet httpGet = new HttpGet(url);
//                httpResponse = httpClient.execute(httpGet);
//            } else if (method == PUT) {
//                HttpPut httpPut = new HttpPut(url);
//                // adding put params
//                if (params != null) {
//                    httpPut.setEntity(new UrlEncodedFormEntity(params));
//                }
//                httpResponse = httpClient.execute(httpPut);
//            }
//            httpEntity = httpResponse.getEntity();
//            response = EntityUtils.toString(httpEntity);
//        } catch (ConnectTimeoutException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return response;
//    }

//    protected String doInBackground(String... params) {
//        InputStream inputStream = null;
//        String result = "";
//
//        try {
//            // 1. create HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
//            // 2. make POST request to the given URL
//            HttpPut httpPUT = new
//                    HttpPut("http://xxx.xx.x.xxx:xxxx/xxxxxxxy/webresources/net.xxxxxx.users/3");
//            String json = "";
//            // 3. build jsonObject
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("idUser","3");
//            jsonObject.put("name","Mark");
//            jsonObject.put("pass","1234");
//            jsonObject.put("rol","554");
//            jsonObject.put("usuario","mark");
//
//
//
//
//            // 4. convert JSONObject to JSON to String
//            json = jsonObject.toString();
//
//            // 5. set json to StringEntity
//            StringEntity se = new StringEntity(json);
//            // 6. set httpPost Entity
//            httpPUT.setEntity(se);
//            // 7. Set some headers to inform server about the type of the content
//            httpPUT.setHeader("Accept", "application/json");
//            httpPUT.setHeader("Content-type", "application/json");
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.execute(httpPUT);
//            // 9. receive response as inputStream
//            //                  inputStream = httpResponse.getEntity().getContent();
//            //                  // 10. convert inputstream to string
//            //                  if(inputStream != null)
//            //                      result = convertInputStreamToString(inputStream);
//            //                  else
//            //                      result = "Did not work!";
//        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
//        }
//
//        return "EXITO!";
//    }
}
