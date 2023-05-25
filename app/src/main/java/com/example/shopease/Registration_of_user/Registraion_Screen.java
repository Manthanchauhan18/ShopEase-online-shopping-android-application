package com.example.shopease.Registration_of_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopease.Login_Screen;
import com.example.shopease.Otp_verification_page;
import com.example.shopease.R;
import com.example.shopease.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registraion_Screen extends AppCompatActivity {


    //initialize objects
    Button btn_reg_register,btn_reg_cancle;
    EditText edit_reg_name , edit_reg_email , edit_reg_password,edit_reg_confirm_password,edit_reg_mobile;
    FirebaseAuth auth;
    String uid;
    DatabaseReference dbRef;
    ProgressBar progressBar_reg;
    int flag=0;
    TextView txt_registration_error;

    //declaration for String
    String name,email,mobile,password,confirm_password;

    Handler handler_error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion_screen);

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));


        //connect xml file things with java file things
        btn_reg_register = findViewById(R.id.btn_reg_register);
        btn_reg_cancle = findViewById(R.id.btn_reg_cancle);

        edit_reg_name = findViewById(R.id.edit_reg_name);
        edit_reg_email = findViewById(R.id.edit_reg_email);
        edit_reg_password = findViewById(R.id.edit_reg_password);
        edit_reg_confirm_password = findViewById(R.id.edit_reg_confirm_password);
        edit_reg_mobile = findViewById(R.id.edit_reg_mobile);
        txt_registration_error = findViewById(R.id.txt_registration_error);
        txt_registration_error.setVisibility(View.GONE);


        //connect with xml file prograss bar and make it not visible by default for now
        progressBar_reg = findViewById(R.id.progressbar_reg);
        progressBar_reg.setVisibility(View.GONE);

        handler_error = new Handler();



// Set the onTouchListener to detect clicks on the right drawable
        edit_reg_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Check if the event is an ACTION_UP event and it occurred on the right drawable
                if (event.getAction() == MotionEvent.ACTION_UP &&
                        event.getRawX() >= (edit_reg_password.getRight() - edit_reg_password.getCompoundDrawables()[2].getBounds().width())) {
                    // Toggle the password visibility
                    if (edit_reg_password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        edit_reg_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        edit_reg_password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24), null, getResources().getDrawable(R.drawable.ic_baseline_eye_visibility_24), null);
                    } else {
                        edit_reg_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        edit_reg_password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24), null, getResources().getDrawable(R.drawable.ic_baseline_eye_visibility_off_24), null);
                    }
                    // Move the cursor to the end of the text
                    edit_reg_password.setSelection(edit_reg_password.length());
                    return true; // Consume the touch event
                }
                return false;
            }
        });


// Set the onTouchListener to detect clicks on the right drawable
        edit_reg_confirm_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Check if the event is an ACTION_UP event and it occurred on the right drawable
                if (event.getAction() == MotionEvent.ACTION_UP &&
                        event.getRawX() >= (edit_reg_confirm_password.getRight() - edit_reg_confirm_password.getCompoundDrawables()[2].getBounds().width())) {
                    // Toggle the password visibility
                    if (edit_reg_confirm_password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        edit_reg_confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        edit_reg_confirm_password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24), null, getResources().getDrawable(R.drawable.ic_baseline_eye_visibility_24), null);
                    } else {
                        edit_reg_confirm_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        edit_reg_confirm_password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24), null, getResources().getDrawable(R.drawable.ic_baseline_eye_visibility_off_24), null);
                    }
                    // Move the cursor to the end of the text
                    edit_reg_confirm_password.setSelection(edit_reg_confirm_password.length());
                    return true; // Consume the touch event
                }
                return false;
            }
        });



        View rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });



        //for name regular expresion
        edit_reg_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    flag=1;
                    name = edit_reg_name.getText().toString();


                    //apply regular expretion for name and checking for the pattern is matching or not
                    String expname="^[A-Z a-z]{3,15}$";
                    Pattern patname=Pattern.compile(expname);
                    Matcher matname=patname.matcher(name);

//                    if name is valid then indicate green border otherwise indicate red border
                    if(name.equals("")){
                        edit_reg_name.setBackgroundResource(R.drawable.edit_text_background);
                        flag=1;
                    }
                    else {
                        if (matname.matches() == true) {
//                        progressBar_reg.setVisibility(View.GONE);
//                        Toast.makeText(Registraion_Screen.this, "Name is not valid or empty", Toast.LENGTH_SHORT).show();
//                        edit_reg_name.requestFocus();
                            edit_reg_name.setBackgroundResource(R.drawable.green_border);
                            flag = 0;
//                        return;
                        } else {
                            edit_reg_name.setBackgroundResource(R.drawable.red_border);
                            txt_registration_error.setText("Fill the Details Properly");
                            txt_registration_error.setVisibility(View.VISIBLE);

                            handler_error.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_registration_error.setVisibility(View.GONE);
                                }
                            },4000);

                            flag=1;
                        }
                    }


                }


            }
        });


        //for email regular expresion
        edit_reg_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    flag=1;
                    email = edit_reg_email.getText().toString();

                    //apply regular expretion for email and checking for the pattern is matching or not
                    String expemail ="^[a-zA-z0-9+_.-]+@[a-zA-Z0-9._]+$";
                    Pattern patemail = Pattern.compile(expemail);
                    Matcher matemail = patemail.matcher(email);

//                    if email is valid then indicate green border otherwise indicate red border
                    if(email.equals("")){
                        edit_reg_email.setBackgroundResource(R.drawable.edit_text_background);
                        flag=1;
                    }
                    else{
                        if(matemail.matches()==true){
//                          progressBar_reg.setVisibility(View.GONE);
//                          Toast.makeText(Registraion_Screen.this, "Email id is not valid or empty", Toast.LENGTH_SHORT).show();
//                          edit_reg_email.requestFocus();
                            edit_reg_email.setBackgroundResource(R.drawable.green_border);
                            flag=0;
//                            return;
                        }else{
                            edit_reg_email.setBackgroundResource(R.drawable.red_border);
                            txt_registration_error.setText("Fill the Details Properly");
                            txt_registration_error.setVisibility(View.VISIBLE);

                            handler_error.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_registration_error.setVisibility(View.GONE);
                                }
                            },4000);

                            flag=1;
                        }
                    }


                }


            }
        });


        //for mobile regular expresion
        edit_reg_mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    flag = 1;
                    mobile = edit_reg_mobile.getText().toString();

                    //apply regular expretion for mobile and checking for the pattern is matching or not
                    String expmobile = "^[6-9]\\d{9}$";
                    Pattern patmobile = Pattern.compile(expmobile);
                    Matcher matmobile = patmobile.matcher(mobile);

//                    if mobile no is valid then indicate green border otherwise indicate red border
                    if(mobile.equals("")){
                        edit_reg_mobile.setBackgroundResource(R.drawable.edit_text_background);
                        flag=1;
                    }else{
                        if(matmobile.matches()==true){
//                            progressBar_reg.setVisibility(View.GONE);
//                            Toast.makeText(Registraion_Screen.this, "Mobile id is not valid or empty", Toast.LENGTH_SHORT).show();
//                            edit_reg_mobile.requestFocus();
                            edit_reg_mobile.setBackgroundResource(R.drawable.green_border);
                            flag=0;
//                            return;
                        }
                        else{
                            edit_reg_mobile.setBackgroundResource(R.drawable.red_border);
                            txt_registration_error.setText("Fill the Details Properly");
                            txt_registration_error.setVisibility(View.VISIBLE);

                            handler_error.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_registration_error.setVisibility(View.GONE);
                                }
                            },4000);

                            flag=1;
                        }
                    }

                }


            }
        });



        //for password regular expresion
        edit_reg_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    flag=1;
                    password = edit_reg_password.getText().toString();

                    //apply regular expretion for password and checking for the pattern is matching or not
                    String exppass="^[A-Z]{1}[a-z0-9]{8,12}$";
                    Pattern patpass=Pattern.compile(exppass);
                    Matcher matpass=patpass.matcher(password);

//                    if password is valid then indicate green border otherwise indicate red border
                    if(password.equals("")){
                        edit_reg_password.setBackgroundResource(R.drawable.edit_text_background);
                        flag=1;
                    }else{
                        if(matpass.matches()==true ){
//                            progressBar_reg.setVisibility(View.GONE);
//                            Toast.makeText(Registraion_Screen.this, "Password is not valid or empty", Toast.LENGTH_SHORT).show();
//                            edit_reg_password.requestFocus();
                            edit_reg_password.setBackgroundResource(R.drawable.green_border);
                            flag=0;
//                            return;
                        }else{
                            edit_reg_password.setBackgroundResource(R.drawable.red_border);
                            txt_registration_error.setText("Fill the Details Properly");
                            txt_registration_error.setVisibility(View.VISIBLE);

                            handler_error.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_registration_error.setVisibility(View.GONE);
                                }
                            },4000);

                            flag=1;
                        }

                    }


                }


            }
        });


        //for confirm password regular expresion
        edit_reg_confirm_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    flag=1;
                    confirm_password = edit_reg_confirm_password.getText().toString();

//                    if confirm password is match with password then indicate green border otherwise indicate red border
                    if(confirm_password.equals("")){
                        edit_reg_confirm_password.setBackgroundResource(R.drawable.edit_text_background);
                        flag=1;
                    }else{
                        //checking for the password and confirm password is matching or not
                        if(!password.equals(confirm_password)){
//                            progressBar_reg.setVisibility(View.GONE);
                            edit_reg_confirm_password.setText("");
//                            edit_reg_confirm_password.requestFocus();
                            edit_reg_confirm_password.setBackgroundResource(R.drawable.red_border);
                            txt_registration_error.setText("Fill the Details Properly");
                            txt_registration_error.setVisibility(View.VISIBLE);


                            handler_error.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_registration_error.setVisibility(View.GONE);
                                }
                            },4000);

                            flag=1;
                        }else{
                            edit_reg_confirm_password.setBackgroundResource(R.drawable.green_border);
                            flag=0;
                        }
                    }


                }


            }
        });



        //click on register button in registration page
        btn_reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //the block is check for all the input filled by user and if all the feilds are correct then user,
                //is registerd successfully other wise it is focus on that things which is incorrect and not register user till the things are correct

                progressBar_reg.setVisibility(View.VISIBLE);


                //make a string object of all the objects for checking the regular expretion
                name = edit_reg_name.getText().toString();
                email = edit_reg_email.getText().toString();
                mobile = edit_reg_mobile.getText().toString();
                password = edit_reg_password.getText().toString();
                confirm_password = edit_reg_confirm_password.getText().toString();

//                check all the fields if any feild blank then flag=1 and target which feild is blank and make that feild border red
                if(name.equals("") || email.equals("") || mobile.equals("") || password.equals("") || confirm_password.equals("")){

                    flag=1;

                    if(name.equals("")){
                        edit_reg_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_reg_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(email.equals("")){
                        edit_reg_email.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_reg_email.setBackgroundResource(R.drawable.green_border);
                    }

                    if(mobile.equals("")){
                        edit_reg_mobile.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_reg_mobile.setBackgroundResource(R.drawable.green_border);
                    }

                    if(password.equals("")){
                        edit_reg_password.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_reg_password.setBackgroundResource(R.drawable.green_border);
                    }

                    if(confirm_password.equals("")){
                        edit_reg_confirm_password.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_reg_confirm_password.setBackgroundResource(R.drawable.green_border);
                    }

                    txt_registration_error.setText("Fill the Details Properly");
                    txt_registration_error.setVisibility(View.VISIBLE);

                    handler_error.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt_registration_error.setVisibility(View.GONE);
                        }
                    },4000);

                }

//                if all the feilds are correct than register user successfully
                if(flag==0){

//                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                            "+91 " + mobile.toString(),
//                            60,
//                            TimeUnit.SECONDS,
//                            Registraion_Screen.this,
//                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                @Override
//                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
////
////                                    DatabaseReference mDatabase;
////
////
////                                    mDatabase = FirebaseDatabase.getInstance().getReference();
////
////                                    // Store the user information in the Firebase database
////                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////                                    mDatabase.child("User").child(user.getUid()).child("name").setValue(name);
////                                    mDatabase.child("User").child(user.getUid()).child("email").setValue(email);
////                                    mDatabase.child("User").child(user.getUid()).child("mobile").setValue(mobile);
////                                    mDatabase.child("User").child(user.getUid()).child("password").setValue(password);
////
////                                    Toast.makeText(Registraion_Screen.this, "Registration successfull", Toast.LENGTH_SHORT).show();
////                                    Intent intent_login = new Intent(Registraion_Screen.this , Login_Screen.class);
////                                    startActivity(intent_login);
////                                    finish();
////
//                                    Toast.makeText(Registraion_Screen.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
//                                    Intent intent_login = new Intent(Registraion_Screen.this , Login_Screen.class);
//                                    startActivity(intent_login);
//
//                                }
//
//                                @Override
//                                public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                    Toast.makeText(Registraion_Screen.this, "Verification Failed", Toast.LENGTH_SHORT).show();
//
//                                }
//
//                                @Override
//                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                    super.onCodeSent(s, forceResendingToken);
//
//
//                                    Intent intet_otp_verification = new Intent(Registraion_Screen.this , Otp_verification_page.class);
//                                    intet_otp_verification.putExtra("name",name);
//                                    intet_otp_verification.putExtra("mobile",mobile);
//                                    intet_otp_verification.putExtra("email",email);
//                                    intet_otp_verification.putExtra("password",password);
//                                    intet_otp_verification.putExtra("otp_backend",s);
//                                    startActivity(intet_otp_verification);
//
//
//                                }
//                            }
//                    );


                    txt_registration_error.setText("");
                    txt_registration_error.setVisibility(View.GONE);
                    //checking for firebase and store the values
                    auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){     //cheking firebase and all things are correct then it will store the details in firebase in "User" field
                                        progressBar_reg.setVisibility(View.GONE);


                                        dbRef = FirebaseDatabase.getInstance().getReference("User");
                                        String user_id;
                                        user_id=auth.getUid();

                                        User user = new User(user_id,name,email,mobile,password);
                                        dbRef.child(auth.getUid()).setValue(user)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(Registraion_Screen.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                        Intent intent_register = new Intent(Registraion_Screen.this,Login_Screen.class);
                                                        startActivity(intent_register);
                                                        finish();
                                                    }
                                                });



                                    }else{  //if user not be able to store the details then go in to else block
                                        progressBar_reg.setVisibility(View.GONE);
                                        txt_registration_error.setText("Unable to Register Please try again latter");
                                        txt_registration_error.setVisibility(View.VISIBLE);

                                        handler_error.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                txt_registration_error.setVisibility(View.GONE);
                                            }
                                        },4000);

                                    }
                                }
                            });   //auth block close




//                    FirebaseAuth auth = FirebaseAuth.getInstance();
//                    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                        @Override
//                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                            // Auto verification of OTP, if the SMS code has been detected by Google Play services.
//                        }
//
//                        @Override
//                        public void onVerificationFailed(FirebaseException e) {
//                            // Handle the failure
//                        }
//
//                        @Override
//                        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                            // Save the verification ID and the token so we can use them later
//                            // Prompt the user to enter the OTP
//                        }
//                    };
//
//                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                            mobile,        // Phone number to verify
//                            30,                 // Timeout duration
//                            TimeUnit.SECONDS,   // Unit of timeout
//                            Registraion_Screen.this,               // Activity (for callback binding)
//                            mCallbacks);        // OnVerificationStateChangedCallbacks



                }
                else{
                    progressBar_reg.setVisibility(view.GONE);
                }
                
                
            }
        });    //register button block close



//        if cancel button press then return to login page
        btn_reg_cancle.setOnClickListener(new View.OnClickListener() {   //cancel button block
            @Override
            public void onClick(View view) {

                Intent intent_cancle = new Intent(Registraion_Screen.this , Login_Screen.class);
                startActivity(intent_cancle);
                finish();
            }
        });   //cancle button block close



    }

    private void validateName() {
    }
}