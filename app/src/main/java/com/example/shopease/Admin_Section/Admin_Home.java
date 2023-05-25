package com.example.shopease.Admin_Section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopease.Login_Screen;
import com.example.shopease.R;

public class Admin_Home extends AppCompatActivity {

//    initialize objects
    Button btAdshow,btn_admin_home_add_offers,btn_admin_home_delete_offers;
    Button btn_admin_home_delete_day_deals , btn_admin_home_add_day_deals,btn_admin_home_add_upcoming_smartphone,btn_admin_home_delete_upcoming_smartphone_home;
    Button btn_admin_home_delete_category,btn_admin_home_add_category,btn_admin_home_delete_grocery,btn_admin_home_add_grocery;
    Button btn_admin_home_add_appliances,btn_admin_home_delete_appliances,btn_admin_home_add_fashion,btn_admin_home_delete_fashion;
    Button btn_admin_home_add_furniture,btn_admin_home_delete_furniture,btn_admin_home_add_books,btn_admin_home_delete_books,btn_admin_home_add_personal_care,btn_admin_home_delete_personal_care;
    Button btn_admin_home_add_fitness,btn_admin_home_delete_fitness,btn_admin_home_add_pharmacy,btn_admin_home_delete_pharmacy,btn_admin_home_add_sports,btn_admin_home_delete_sports;
    Button btn_admin_home_add_electronics,btn_admin_home_delete_electronics,btn_admin_home_add_tv,btn_admin_home_delete_tv;
    ImageView btn_admin_logout;

    Handler handler_logout;
    private SharedPreferences loginPreferences2;
    private SharedPreferences.Editor loginPrefsEditor2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        setTheme(R.style.Theme_Mytheme);


//        conenct objects with xml file ids
        btAdshow=findViewById(R.id.btAdshow);
        btn_admin_home_add_offers = findViewById(R.id.btn_admin_home_add_offers);
        btn_admin_home_delete_offers = findViewById(R.id.btn_admin_home_delete_offers);
        btn_admin_home_add_day_deals = findViewById(R.id.btn_admin_home_add_day_deals);
        btn_admin_home_delete_day_deals = findViewById(R.id.btn_admin_home_delete_day_deals);
        btn_admin_home_add_upcoming_smartphone = findViewById(R.id.btn_admin_home_add_upcoming_smartphone);
        btn_admin_home_delete_upcoming_smartphone_home = findViewById(R.id.btn_admin_home_delete_upcoming_smartphone_home);
        btn_admin_home_add_category = findViewById(R.id.btn_admin_home_add_category);
        btn_admin_home_delete_category = findViewById(R.id.btn_admin_home_delete_category);
        btn_admin_home_delete_grocery = findViewById(R.id.btn_admin_home_delete_grocery);
        btn_admin_home_add_grocery = findViewById(R.id.btn_admin_home_add_grocery);

        btn_admin_home_delete_appliances = findViewById(R.id.btn_admin_home_delete_appliances);
        btn_admin_home_add_appliances = findViewById(R.id.btn_admin_home_add_appliances);

        btn_admin_home_add_fashion = findViewById(R.id.btn_admin_home_add_fashion);
        btn_admin_home_delete_fashion = findViewById(R.id.btn_admin_home_delete_fashion);

        btn_admin_home_add_furniture = findViewById(R.id.btn_admin_home_add_furniture);
        btn_admin_home_delete_furniture = findViewById(R.id.btn_admin_home_delete_furniture);

        btn_admin_home_add_books = findViewById(R.id.btn_admin_home_add_books);
        btn_admin_home_delete_books = findViewById(R.id.btn_admin_home_delete_books);

        btn_admin_home_add_personal_care = findViewById(R.id.btn_admin_home_add_personal_care);
        btn_admin_home_delete_personal_care = findViewById(R.id.btn_admin_home_delete_personal_care);

        btn_admin_home_add_fitness = findViewById(R.id.btn_admin_home_add_fitness);
        btn_admin_home_delete_fitness = findViewById(R.id.btn_admin_home_delete_fitness);

        btn_admin_home_add_pharmacy = findViewById(R.id.btn_admin_home_add_pharmacy);
        btn_admin_home_delete_pharmacy = findViewById(R.id.btn_admin_home_delete_pharmacy);

        btn_admin_home_add_sports = findViewById(R.id.btn_admin_home_add_sports);
        btn_admin_home_delete_sports = findViewById(R.id.btn_admin_home_delete_sports);

        btn_admin_home_add_electronics = findViewById(R.id.btn_admin_home_add_electronics);
        btn_admin_home_delete_electronics = findViewById(R.id.btn_admin_home_delete_electronics);

        btn_admin_home_add_tv = findViewById(R.id.btn_admin_home_add_tv);
        btn_admin_home_delete_tv = findViewById(R.id.btn_admin_home_delete_tv);



        btn_admin_logout = findViewById(R.id.btn_admin_logout);
        loginPreferences2 = this.getSharedPreferences("loginPrefs2", MODE_PRIVATE);

        //if click on logout button then go to loginpage and close the admin home page
        btn_admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a dialog box to confirm user logout
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Home.this);
                View dialogView = getLayoutInflater().inflate(R.layout.rounded_dialog, null);
                builder.setView(dialogView);
                AlertDialog alert = builder.create();

                TextView messageTextView = dialogView.findViewById(android.R.id.message);
                messageTextView.setText("Are you sure you want to Logout?");

                Button positiveButton = dialogView.findViewById(R.id.dialog_positive_button);
                positiveButton.setOnClickListener(view -> {
                    // User clicked the Yes button, perform logout
                    loginPrefsEditor2 = loginPreferences2.edit();
                    loginPrefsEditor2.putBoolean("isLoggedIn2", false);
                    loginPrefsEditor2.apply();
                    alert.dismiss();
                    showProgressBar();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent_logout = new Intent(Admin_Home.this, Login_Screen.class);
                            startActivity(intent_logout);
                            finish();
                        }
                    },2000);


                });

                Button negativeButton = dialogView.findViewById(R.id.dialog_negative_button);
                negativeButton.setOnClickListener(view -> {
                    // Dismiss the dialog and do nothing
                    alert.dismiss();
                });

                alert.show();

//                admin_home_progressbar.setVisibility(View.VISIBLE);
//                handler_logout = new Handler();
//                handler_logout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Intent intent_admin = new Intent(Admin_Home.this , Login_Screen.class);
//                        startActivity(intent_admin);
//                        finish();
//                        admin_home_progressbar.setVisibility(View.GONE);
//
//                    }
//                },2000);


            }
        });


        //if click on show user button then go to show user page
        btAdshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent admin_show_user=new Intent(getApplicationContext(), ShowUser.class);
                startActivity(admin_show_user);
            }
        });

////        if click on add product button then go to add product page
//        btAdadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Intent admin_add_grocery=new Intent(getApplicationContext(), AddProduct.class);
//                startActivity(admin_add_grocery);
//            }
//        });
//
////        if click on delete product button then go to delete product section
//        btAddelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Intent admin_delete_grocery=new Intent(getApplicationContext(), Delete_Product.class);
//                startActivity(admin_delete_grocery);
//            }
//        });

//
////        if click on update product then go to update product page
//        btAdupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Intent ii7=new Intent(getApplicationContext(), Update_Product.class);
//                startActivity(ii7);
//            }
//        });

//        if click on add offer button then go to add offer page
        btn_admin_home_add_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home_add_offers = new Intent(Admin_Home.this , Add_Offer.class);
                startActivity(intent_home_add_offers);
            }
        });

//        if click on delete offer page then go to delete offer page
        btn_admin_home_delete_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home_delete_offers = new Intent(Admin_Home.this , Delete_Offer.class);
                startActivity(intent_home_delete_offers);
            }
        });

//        if add deals of the day press then go to add day deals page
        btn_admin_home_add_day_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add_day_deals = new Intent(Admin_Home.this , Add_day_deals.class);
                startActivity(intent_add_day_deals);
            }
        });

//        if delete deals of the day press then go to delete day deals
        btn_admin_home_delete_day_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_day_deals = new Intent(Admin_Home.this , Delete_day_deals.class);
                startActivity(intent_delete_day_deals);
            }
        });

//        if add upcoming smartphone button press then go to add upcoming smartphone sectinon
        btn_admin_home_add_upcoming_smartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_upcoming_smartphone = new Intent(Admin_Home.this , Add_upcoming_smartphone.class);
                startActivity(intent_add_upcoming_smartphone);

            }
        });

//        if delete upcoming smartphone button press then go to delete upcoming smartphone section
        btn_admin_home_delete_upcoming_smartphone_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_upcoming_smartphone_home = new Intent(Admin_Home.this , Delete_Upcoming_smartphone.class);
                startActivity(intent_delete_upcoming_smartphone_home);

            }
        });

//        if add category button press then go to add category section
        btn_admin_home_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_category = new Intent(Admin_Home.this , Add_category.class);
                startActivity(intent_add_category);

            }
        });

//        if delete category button press then go to delete category section
        btn_admin_home_delete_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_category = new Intent(Admin_Home.this , Delete_category.class);
                startActivity(intent_delete_category);

            }
        });


//        if add grocery button press then go to add grocery section
        btn_admin_home_add_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_grocery = new Intent(Admin_Home.this , Add_Grocery.class);
                startActivity(intent_add_grocery);

            }
        });

//        if delete grocery button press then go to delete grocery section
        btn_admin_home_delete_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_grocery = new Intent(Admin_Home.this , Delete_grocery.class);
                startActivity(intent_delete_grocery);

            }
        });



        btn_admin_home_add_appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_appliance = new Intent(Admin_Home.this , Add_Appliances.class);
                startActivity(intent_add_appliance);

            }
        });


        btn_admin_home_delete_appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_appliance = new Intent(Admin_Home.this , Delete_Appliance.class);
                startActivity(intent_delete_appliance);


            }
        });


        btn_admin_home_add_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_appliance = new Intent(Admin_Home.this , Add_Fashion.class);
                startActivity(intent_add_appliance);

            }
        });


        btn_admin_home_delete_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_fashion = new Intent(Admin_Home.this , Delete_fashion.class);
                startActivity(intent_delete_fashion);


            }
        });

        btn_admin_home_add_furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_furniture = new Intent(Admin_Home.this , Add_Furniture.class);
                startActivity(intent_add_furniture);

            }
        });


        btn_admin_home_delete_furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_furniture = new Intent(Admin_Home.this , Delete_Furniture.class);
                startActivity(intent_delete_furniture);


            }
        });

        btn_admin_home_add_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_books = new Intent(Admin_Home.this , Add_Books.class);
                startActivity(intent_add_books);

            }
        });


        btn_admin_home_delete_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_books = new Intent(Admin_Home.this , Delete_Books.class);
                startActivity(intent_delete_books);


            }
        });

        btn_admin_home_add_personal_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_personal_care = new Intent(Admin_Home.this , Add_Personal_care.class);
                startActivity(intent_add_personal_care);

            }
        });


        btn_admin_home_delete_personal_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_personal_care = new Intent(Admin_Home.this , Delete_Personal_care.class);
                startActivity(intent_delete_personal_care);


            }
        });

        btn_admin_home_add_fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_fitness = new Intent(Admin_Home.this , Add_fitness.class);
                startActivity(intent_add_fitness);

            }
        });


        btn_admin_home_delete_fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_fitness = new Intent(Admin_Home.this , Delete_fitness.class);
                startActivity(intent_delete_fitness);


            }
        });

        btn_admin_home_add_pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_pharmacy = new Intent(Admin_Home.this , Add_pharmacy.class);
                startActivity(intent_add_pharmacy);

            }
        });


        btn_admin_home_delete_pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_pharmacy = new Intent(Admin_Home.this , Delete_pharmacy.class);
                startActivity(intent_delete_pharmacy);


            }
        });

        btn_admin_home_add_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_sports = new Intent(Admin_Home.this , Add_sports.class);
                startActivity(intent_add_sports);

            }
        });


        btn_admin_home_delete_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_sports = new Intent(Admin_Home.this , Delete_sports.class);
                startActivity(intent_delete_sports);


            }
        });

        btn_admin_home_add_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_electronic = new Intent(Admin_Home.this , Add_electronic.class);
                startActivity(intent_add_electronic);

            }
        });


        btn_admin_home_delete_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_electronic = new Intent(Admin_Home.this , Delete_electronic.class);
                startActivity(intent_delete_electronic);


            }
        });

        btn_admin_home_add_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add_tv = new Intent(Admin_Home.this , Add_tv.class);
                startActivity(intent_add_tv);

            }
        });


        btn_admin_home_delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_tv = new Intent(Admin_Home.this , Delete_Tv.class);
                startActivity(intent_delete_tv);


            }
        });





    }//on create close

    private void showProgressBar() {
        // Inflate the layout file for the progress bar
        View progressView = getLayoutInflater().inflate(R.layout.progress_bar_layout, null);

        // Add the progress bar to the root view of the activity
        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.addView(progressView);

        // Set the progress bar's visibility to VISIBLE
        ProgressBar progressBar = progressView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        // Set the tint color
        progressBar.getIndeterminateDrawable().setColorFilter(
                ContextCompat.getColor(this, R.color.white),
                PorterDuff.Mode.SRC_IN
        );

        // Apply a blur effect to the background view
        View blurView = progressView.findViewById(R.id.blur_view);
        Paint paint = new Paint();
        paint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
        blurView.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        blurView.setBackgroundColor(Color.parseColor("#80000000"));
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


}//class close