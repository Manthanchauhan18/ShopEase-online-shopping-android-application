package com.example.shopease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopease.Admin_Section.Admin_Home;
import com.example.shopease.model.User;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Otp_verification_page extends AppCompatActivity {

    Button otp_verification_btn_verify_otp;
    TextView otp_verification_text_resend_otp,otp_verification_text_mobile_number,otp_verification_text_error;
    EditText otp_verification_input_1,otp_verification_input_2,otp_verification_input_3,otp_verification_input_4,otp_verification_input_5,otp_verification_input_6;
    LinearLayout otp_verification_linear_layout;
    ProgressBar progressbar;

    FirebaseAuth auth;
    String uid;
    DatabaseReference dbRef;
    Handler handler_error;
    int flag=0;

    private DatabaseReference mDatabase;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_page);

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));


        otp_verification_btn_verify_otp = findViewById(R.id.otp_verification_btn_verify_otp);

        otp_verification_text_resend_otp = findViewById(R.id.otp_verification_text_resend_otp);
        otp_verification_text_mobile_number = findViewById(R.id.otp_verification_text_mobile_number);
        progressbar = findViewById(R.id.progressbar);
        progressbar.setVisibility(View.GONE);

        otp_verification_text_error = findViewById(R.id.otp_verification_text_error);
        otp_verification_text_error.setVisibility(View.GONE);


        otp_verification_input_1 = findViewById(R.id.otp_verification_input_1);
        otp_verification_input_2 = findViewById(R.id.otp_verification_input_2);
        otp_verification_input_3 = findViewById(R.id.otp_verification_input_3);
        otp_verification_input_4 = findViewById(R.id.otp_verification_input_4);
        otp_verification_input_5 = findViewById(R.id.otp_verification_input_5);
        otp_verification_input_6 = findViewById(R.id.otp_verification_input_6);

        otp_verification_linear_layout = findViewById(R.id.otp_verification_linear_layout);
        otp_verification_linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp_verification_input_1.clearFocus();
                otp_verification_input_2.clearFocus();
                otp_verification_input_3.clearFocus();
                otp_verification_input_4.clearFocus();
                otp_verification_input_5.clearFocus();
                otp_verification_input_6.clearFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(otp_verification_input_1.getWindowToken(), 0);

            }
        });

        numberOtpMove();

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String mobile = getIntent().getStringExtra("mobile");
        String password = getIntent().getStringExtra("password");
        String otp_backend = getIntent().getStringExtra("otp_backend");

        otp_verification_text_mobile_number.setText("+91 "+mobile);


        otp_verification_btn_verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(otp_verification_input_1.getText().toString().equals("") && otp_verification_input_2.getText().toString().equals("") && otp_verification_input_3.getText().toString().equals("") && otp_verification_input_4.getText().toString().equals("") && otp_verification_input_5.getText().toString().equals("") && otp_verification_input_6.getText().toString().equals("")){
                    flag = 1;

                    if(otp_verification_input_1.getText().toString().equals("")){
                        otp_verification_input_1.setBackgroundResource(R.drawable.red_border_otp);
                    }else{
                        otp_verification_input_1.setBackgroundResource(R.drawable.green_border_otp);
                        flag=0;
                    }

                    if(otp_verification_input_2.getText().toString().equals("")){
                        otp_verification_input_2.setBackgroundResource(R.drawable.red_border_otp);
                    }else{
                        otp_verification_input_2.setBackgroundResource(R.drawable.green_border_otp);
                        flag=0;
                    }

                    if(otp_verification_input_3.getText().toString().equals("")){
                        otp_verification_input_3.setBackgroundResource(R.drawable.red_border_otp);
                    }else{
                        otp_verification_input_3.setBackgroundResource(R.drawable.green_border_otp);
                        flag=0;
                    }

                    if(otp_verification_input_4.getText().toString().equals("")){
                        otp_verification_input_4.setBackgroundResource(R.drawable.red_border_otp);
                    }else{
                        otp_verification_input_4.setBackgroundResource(R.drawable.green_border_otp);
                        flag=0;
                    }

                    if(otp_verification_input_5.getText().toString().equals("")){
                        otp_verification_input_5.setBackgroundResource(R.drawable.red_border_otp);
                    }else{
                        otp_verification_input_5.setBackgroundResource(R.drawable.green_border_otp);
                        flag=0;
                    }

                    if(otp_verification_input_6.getText().toString().equals("")){
                        otp_verification_input_6.setBackgroundResource(R.drawable.red_border_otp);
                    }else{
                        otp_verification_input_6.setBackgroundResource(R.drawable.green_border_otp);
                        flag=0;
                    }

                }else{
                    flag = 0;
                    progressbar.setVisibility(View.VISIBLE);

                    String otp_input = otp_verification_input_1.getText().toString() +
                            otp_verification_input_2.getText().toString() +
                            otp_verification_input_3.getText().toString() +
                            otp_verification_input_4.getText().toString() +
                            otp_verification_input_5.getText().toString() +
                            otp_verification_input_6.getText().toString();

                    if(otp_backend != null){
                        otp_verification_text_error.setText(otp_backend);
                        otp_verification_text_error.setVisibility(View.VISIBLE);

                        // Get the user's email and password from the intent
                        String email = getIntent().getStringExtra("email");
                        String password = getIntent().getStringExtra("password");


//                      Verify the OTP
                        if (otp_backend.equals(otp_input)) {
                            // If OTP is correct, create a new Firebase user with the email and password
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            // If user is created successfully, store the user information in the Firebase database
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("User").child(user.getUid()).child("id").setValue(user.getUid());
                                            mDatabase.child("User").child(user.getUid()).child("name").setValue(name);
                                            mDatabase.child("User").child(user.getUid()).child("email").setValue(email);
                                            mDatabase.child("User").child(user.getUid()).child("mobile").setValue(mobile);
                                            mDatabase.child("User").child(user.getUid()).child("password").setValue(password);

                                            // Redirect the user to the registration success page
                                            Toast.makeText(Otp_verification_page.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Otp_verification_page.this, Login_Screen.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // If user creation fails, show an error message
                                            Toast.makeText(Otp_verification_page.this, "User creation failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // If OTP is incorrect, show an error message
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(Otp_verification_page.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Otp_verification_page.this, "Please check your internet", Toast.LENGTH_SHORT).show();
                    }


                }


//                if(flag == 0){
//
//                    otp_verification_text_error.setText("");
//                    otp_verification_text_error.setVisibility(View.GONE);
//                    //checking for firebase and store the values
//                    auth = FirebaseAuth.getInstance();
//                    auth.createUserWithEmailAndPassword(email,password)
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if(task.isSuccessful()){     //cheking firebase and all things are correct then it will store the details in firebase in "User" field
////                                    progressBar_reg.setVisibility(View.GONE);
//
//                                        dbRef = FirebaseDatabase.getInstance().getReference("User");
//                                        User user = new User(name,email,mobile,password);
//                                        dbRef.child(auth.getUid()).setValue(user)
//                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                        Toast.makeText(Otp_verification_page.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                                        Intent intent_register = new Intent(Otp_verification_page.this, Login_Screen.class);
//                                                        startActivity(intent_register);
//                                                        finish();
//                                                    }
//                                                });
//
//
//
//                                    }else{  //if user not be able to store the details then go in to else block
////                                    progressBar_reg.setVisibility(View.GONE);
//                                        otp_verification_text_error.setText("Unable to Register Please try again latter");
//                                        otp_verification_text_error.setVisibility(View.VISIBLE);
//
//                                        handler_error.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                otp_verification_text_error.setVisibility(View.GONE);
//                                            }
//                                        },4000);
//
//                                    }
//                                }
//                            });   //auth block close
//
//
//
//                }


            }
        });





    }

    private void numberOtpMove() {
        otp_verification_input_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_verification_input_2.requestFocus();
                } else if (s.toString().trim().isEmpty() && before == 1) {
                    otp_verification_input_1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        otp_verification_input_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_verification_input_3.requestFocus();
                } else if (s.toString().trim().isEmpty() && before == 1) {
                    otp_verification_input_1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        otp_verification_input_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_verification_input_4.requestFocus();
                } else{
                    otp_verification_input_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        otp_verification_input_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_verification_input_5.requestFocus();
                } else {
                    otp_verification_input_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        otp_verification_input_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_verification_input_6.requestFocus();
                } else{
                    otp_verification_input_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        otp_verification_input_6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp_verification_input_1.clearFocus();
                    otp_verification_input_2.clearFocus();
                    otp_verification_input_3.clearFocus();
                    otp_verification_input_4.clearFocus();
                    otp_verification_input_5.clearFocus();
                    otp_verification_input_6.clearFocus();

                } else{
                    otp_verification_input_5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

}