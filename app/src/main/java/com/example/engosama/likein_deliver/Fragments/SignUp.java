package com.example.engosama.likein_deliver.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Login;
import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Http.HttpRequest_POST;
import com.example.engosama.likein_deliver.R;

import org.w3c.dom.Text;

public class SignUp extends Fragment {
    View view;
    Button sign_up_btn;
    TextView login_tv;
    ImageView back_btn;
    EditText full_name_et, email_et, phone_no_et, password_et, gender_et, conf_password_et;
    CheckBox checkbox;
    String fullname, email, password, confPassword, gender, phoneNo;

    public SignUp() {
        // Required empty public constructor
    }

    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();
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
        view = inflater.inflate(R.layout.layout_f_sign_up, container, false);
        login_tv = (TextView) view.findViewById(R.id.login_tv);
        sign_up_btn = (Button) view.findViewById(R.id.sign_up_btn);
        back_btn = view.findViewById(R.id.back_btn);
        full_name_et = view.findViewById(R.id.full_name_et);
        email_et = view.findViewById(R.id.email_et);
        phone_no_et = view.findViewById(R.id.phone_no_et);
        password_et = view.findViewById(R.id.password_et);
        gender_et = view.findViewById(R.id.gender_et);
        conf_password_et = view.findViewById(R.id.conf_password_et);
        checkbox = view.findViewById(R.id.checkbox);

//        login_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Login.switch_fragment(new Login_frag());
//            }
//        });
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Login.switch_fragment(new Login_frag());
//            }
//        });
//        sign_up_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fullname = full_name_et.getText().toString().trim();
//                phoneNo = phone_no_et.getText().toString().trim();
//                email = email_et.getText().toString().trim();
//                password = password_et.getText().toString();
//                confPassword = conf_password_et.getText().toString();
//                gender = gender_et.getText().toString().trim();
//                if(checkFields()){
//                    try{
//                        HttpRequest_POST request = new HttpRequest_POST();
//                        request.Post_create_user(getContext(),fullname,phoneNo,email,gender,password, SignUp.this);
////                        Toast.makeText(Login.context, "User Created Successfully!", Toast.LENGTH_SHORT).show();
//                    }catch (Exception e){
//                        Toast.makeText(Login.context, "Error: Post Request >> Go to Logcat e", Toast.LENGTH_SHORT).show();
//                        Log.e("Error: Post Request",e.getMessage());
//                    }
////                    Intent intent = new Intent(getActivity(), Requests.class);
////                    intent.putExtra("email", "0");
////                    intent.putExtra("password", "0");
////                    startActivity(intent);
//                }
//            }
//        });

        return view;
    }

//    private boolean checkFields() {
//        String emptyErrorMsg = "املأ هذا الحقل";
//        /** empty fields*/
//        if (email.equals("") || password.equals("")||fullname.equals("")
//                ||gender.equals("")||phoneNo.equals("")||confPassword.equals("")) {
//            if (email.equals("")) {
//                email_et.setError(emptyErrorMsg);
//            }
//            if (password.equals("")) {
//                password_et.setError(emptyErrorMsg);
//            }
//            if (fullname.equals("")) {
//                full_name_et.setError(emptyErrorMsg);
//            }
//            if (gender.equals("")) {
//                gender_et.setError(emptyErrorMsg);
//            }
//            if (phoneNo.equals("")) {
//                phone_no_et.setError(emptyErrorMsg);
//            }
//            if (confPassword.equals("")) {
//                conf_password_et.setError(emptyErrorMsg);
//            }
//            return false;
//        }
//        /** check phoneNo */
//        if(phone_no_et.getText().toString().length()<13)
//        {
//            phone_no_et.setError("أدخل رقم الجوال");
//            return false;
//        }
//        if(!phone_no_et.getText().toString().contains("+966"))
//        {
//            phone_no_et.setError("أدخل رمز الدولة");
//            return false;
//        }
//
//        /** check email*/
//        if(!(email.contains("@")&& email.contains("."))){
//            email_et.setError("هذا الايميل غير صالح!");
//            return false;
//        }
//        /**check  gender*/
//        if (!(gender.equals("ذكر")||gender.equals("انثى")||gender.equals("أنثى"))){
//            gender_et.setError("الخيارات: ذكر ، انثى");
//            return false;
//        }
//        /** check password */
//        if(!password.equals(confPassword)){
//            conf_password_et.setError("كلمة المرور غير متطابقة");
//            return false;
//        }
//        /**  Is Checkbox selected */
//        if(!checkbox.isChecked()){
//            checkbox.setError("يرجى الموافقة عالى الشروط!");
//            return false;
//        }
//        return true;
//    }

}
