package com.example.shopease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    //initialize the objects
    TextView txt_splash_shopease;
    LottieAnimationView splash_screen_lottie;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));


        //connect xml file things with java file things
        txt_splash_shopease = findViewById(R.id.txt_splash_shopease);
        splash_screen_lottie = findViewById(R.id.splash_screen_lottie);


        //animation for ShopEase text on splash screen
//        Animation animation_txt = AnimationUtils.loadAnimation(SplashScreen.this , R.anim.scale);
//        txt_splash_shopease.startAnimation(animation_txt);

        // Get the extra boolean value indicating whether the SplashScreen was started from the MainActivity or not
        boolean isFromMainActivity = getIntent().getBooleanExtra("isFromMainActivity", false);

        // If the SplashScreen was started from the MainActivity, finish the MainActivity
        if (isFromMainActivity) {
            splash_screen_lottie.setSpeed(1.5f);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            },1200);
        } else {

            //initialize intent for redirect to loginscreen
            Intent intent_login_screen = new Intent(SplashScreen.this, Login_Screen.class);
            splash_screen_lottie.setSpeed(1.5f);

            //make the screen redirect to login screen automaticaly without clicking anywhere on splashscreen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent_login_screen);   //use to active the intent
                    finish();   //use for once the splash screen work done then it will distory till the application was not restart
                }
            }, 1500);   //clossing braceket for automatic redirect to login screen
        }


    }
}