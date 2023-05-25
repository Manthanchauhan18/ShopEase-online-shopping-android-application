package com.example.shopease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopease.ui.Cart.CartFragment;
import com.example.shopease.ui.Home.HomeFragment;
import com.example.shopease.ui.Profile.ProfileFragment;
import com.example.shopease.ui.category.CategoriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    private BottomNavigationView bottomNavigationView;
    private boolean isFragmentTransactionInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Set the Home fragment as the default
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new HomeFragment())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (!isFragmentTransactionInProgress) {
            isFragmentTransactionInProgress = true;

            switch (item.getItemId()) {
                case R.id.menu_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new HomeFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.menu_categories:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new CategoriesFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.menu_cart:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new CartFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.menu_profile:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new ProfileFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
            }

            // Disable bottom navigation view items while transaction is in progress
            bottomNavigationView.getMenu().setGroupEnabled(0, false);

            getSupportFragmentManager().addOnBackStackChangedListener(() -> {
                isFragmentTransactionInProgress = false;

                // Re-enable bottom navigation view items when transaction is complete
                bottomNavigationView.getMenu().setGroupEnabled(0, true);
            });

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        List<String> dialogFragments = Arrays.asList(
                "com.example.shopease.ui.Home.HomeFragment",
                "com.example.shopease.ui.Profile.ProfileFragment",
                "com.example.shopease.ui.Cart.CartFragment",
                "com.example.shopease.ui.category.CategoriesFragment"
        );
        String currentFragmentName = currentFragment.getClass().getName();

        if (dialogFragments.contains(currentFragmentName)) {
            // Show the dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.rounded_dialog, null);
            builder.setView(dialogView);
            AlertDialog alert = builder.create();

            TextView messageTextView = dialogView.findViewById(android.R.id.message);
            messageTextView.setText("Are you sure you want to exit?");

            Button positiveButton = dialogView.findViewById(R.id.dialog_positive_button);
            positiveButton.setOnClickListener(view -> {
                Intent intent_splash_screen = new Intent(MainActivity.this , SplashScreen.class);
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
        } else {
            // Go back to the previous screen
            super.onBackPressed();
        }
    }



}