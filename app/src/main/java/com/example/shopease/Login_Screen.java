package com.example.shopease;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopease.Admin_Section.Admin_Home;
import com.example.shopease.Registration_of_user.Registraion_Screen;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Screen extends AppCompatActivity {

    //initialize objects
    TextView txt_log_signUp,txt_log_forgot_password;
    Button btn_log_login;
    EditText edit_log_email,edit_log_password;
    FirebaseAuth auth;
    ProgressBar progressBar;
    LinearLayout btn_google_sign_in,login_screen_linear_layout;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    private SharedPreferences loginPreferences2;
    private SharedPreferences.Editor loginPrefsEditor2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        //connect xml file things with this file things
        txt_log_signUp = findViewById(R.id.txt_log_signUp);
        btn_log_login = findViewById(R.id.btn_log_login);
        edit_log_email = findViewById(R.id.edit_log_email);
        edit_log_password = findViewById(R.id.edit_log_password);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        txt_log_forgot_password = findViewById(R.id.txt_log_forgot_password);
        btn_google_sign_in = findViewById(R.id.btn_google_sign_in);
        login_screen_linear_layout = findViewById(R.id.login_screen_linear_layout);

        auth = FirebaseAuth.getInstance();


        //if user login before in app than it will redirect to home page otherwise it ask for user name and password
//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(Login_Screen.this, MainActivity.class));
//            finish();
//        }

// Set the onTouchListener to detect clicks on the right drawable
        edit_log_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Check if the event is an ACTION_UP event and it occurred on the right drawable
                if (event.getAction() == MotionEvent.ACTION_UP &&
                        event.getRawX() >= (edit_log_password.getRight() - edit_log_password.getCompoundDrawables()[2].getBounds().width())) {
                    // Toggle the password visibility
                    if (edit_log_password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        edit_log_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        edit_log_password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24), null, getResources().getDrawable(R.drawable.ic_baseline_eye_visibility_24), null);
                    } else {
                        edit_log_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        edit_log_password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24), null, getResources().getDrawable(R.drawable.ic_baseline_eye_visibility_off_24), null);
                    }
                    // Move the cursor to the end of the text
                    edit_log_password.setSelection(edit_log_password.length());
                    return true; // Consume the touch event
                }
                return false;
            }
        });

        login_screen_linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_log_email.clearFocus();
                edit_log_password.clearFocus();

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
        });



        //if click on singup than it will redirect to the registration page
        txt_log_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_log_email.setText("");
                edit_log_email.clearFocus();
                edit_log_password.setText("");
                edit_log_password.clearFocus();
                Intent intent_registration_screen = new Intent(Login_Screen.this , Registraion_Screen.class);
                startActivity(intent_registration_screen);
            }
        });    //signup onclick closed


        txt_log_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(edit_log_email.getText().toString().equals("")){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login_Screen.this, "Please enter username to reset password", Toast.LENGTH_SHORT).show();
                }else{
                    String email = edit_log_email.getText().toString();
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Login_Screen.this, "Password reset mail sended", Toast.LENGTH_SHORT).show();
                                    }else{
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Login_Screen.this, "Password reset mail failed to send", Toast.LENGTH_SHORT).show();
                                    }
                                    
                                    
                                }
                                
                            });
                }
            }
        });


        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this , googleSignInOptions);

        btn_google_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent google_sign_in = googleSignInClient.getSignInIntent();
                startActivityForResult(google_sign_in , 100);
            }
        });



        // Check if the user is already logged in
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean isLoggedIn = loginPreferences.getBoolean("isLoggedIn", false);

        // Check if the user is already logged in
        loginPreferences2 = getSharedPreferences("loginPrefs2", MODE_PRIVATE);
        boolean isLoggedIn2 = loginPreferences2.getBoolean("isLoggedIn2", false);

        // If the user is already logged in, redirect to the home screen
        if (isLoggedIn) {
            Intent homeIntent = new Intent(Login_Screen.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }else if(isLoggedIn2){
            Intent homeIntent = new Intent(Login_Screen.this, Admin_Home.class);
            startActivity(homeIntent);
            finish();
        }else {
            //if click of login screen than check the fields and if correct then redirect to the home screen
            btn_log_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    String email = edit_log_email.getText().toString();
                    String password = edit_log_password.getText().toString();


                    //check for if the username and password is for admin or not
                    if (email.equals("admin") && password.equals("admin")) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                progressBar.setVisibility(View.GONE);
                                Intent intent_admin_home = new Intent(Login_Screen.this, Admin_Home.class);
                                intent_admin_home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent_admin_home);
                                finish();

                                // Save the login status to shared preferences when the user logs in
                                loginPrefsEditor2 = loginPreferences2.edit();
                                loginPrefsEditor2.putBoolean("isLoggedIn2", true);
                                loginPrefsEditor2.apply();

                            }
                        },2000);



                    }    //admin check block close

                    //if username and password is not for admin and developer than it will check for registerd user
                    else {

                        //checking username field is empty or not
                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(Login_Screen.this, "Email id is empty", Toast.LENGTH_SHORT).show();
                            edit_log_email.requestFocus();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }   // username empty checking block close

                        //checking password feild is empty or not
                        if (TextUtils.isEmpty(password)) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Login_Screen.this, "Password is empty", Toast.LENGTH_SHORT).show();
                            edit_log_password.requestFocus();
                            return;
                        }   //password empty checking block close


                        //check for authentication for username and password in firebase
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {    // if username and password is correct then go to home page and not get back to login screen again
                                            progressBar.setVisibility(View.GONE);

                                            // Save the login status to shared preferences when the user logs in
                                            loginPrefsEditor = loginPreferences.edit();
                                            loginPrefsEditor.putBoolean("isLoggedIn", true);
                                            loginPrefsEditor.apply();


                                            Intent intent_main_activity = new Intent(Login_Screen.this, MainActivity.class);
                                            intent_main_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent_main_activity);
                                            finish();
                                        }    //if block close
                                        else {    //if user name and password is incorrect than popup some messege
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(Login_Screen.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                        }     //else block close
                                    }
                                });    //auth block close


                    }     //checking username and password on firebase authentiction block close

                }
            });    //btn login block close

        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();

            }catch(ApiException e){

            }
        }
    }

    private void HomeActivity() {

        // Save the login status to shared preferences when the user logs in
        loginPrefsEditor = loginPreferences.edit();
        loginPrefsEditor.putBoolean("isLoggedIn", true);
        loginPrefsEditor.apply();


        finish();
        Intent admin_home_intent = new Intent(Login_Screen.this , MainActivity.class);
        startActivity(admin_home_intent);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.rounded_dialog, null);
        builder.setView(dialogView);
        AlertDialog alert = builder.create();

        TextView messageTextView = dialogView.findViewById(android.R.id.message);
        messageTextView.setText("Are you sure you want to exit?");

        Button positiveButton = dialogView.findViewById(R.id.dialog_positive_button);
        positiveButton.setOnClickListener(view -> {
            Intent intent_splash_screen = new Intent(Login_Screen.this , SplashScreen.class);
            intent_splash_screen.putExtra("isFromMainActivity" , true);
            startActivity(intent_splash_screen);
            // Close the app
            finish();
        });

        Button negativeButton = dialogView.findViewById(R.id.dialog_negative_button);
        negativeButton.setOnClickListener(view -> {
            // Dismiss the dialog and do nothing
            alert.dismiss();
        });

        alert.show();
    }

}