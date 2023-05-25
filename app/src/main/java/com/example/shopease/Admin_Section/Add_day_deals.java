package com.example.shopease.Admin_Section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopease.R;
import com.example.shopease.model.Deals;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_day_deals extends AppCompatActivity {

    Button btn_admin_add_day_deals_select_image,btn_admin_add_day_deals;
    ImageView btn_admin_add_day_deals_back;
    EditText edit_admin_add_day_deals_name,edit_admin_add_day_deals_price,edit_admin_add_day_deals_rating,edit_admin_add_day_deals_stock,edit_admin_add_deals_of_the_day_discription,edit_admin_add_day_deals_percentage;
    TextView txt_add_day_deals_error;
    ProgressBar admin_add_day_deals_progressbar;


    Uri imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String deals_id;
    String deals_Name,deals_price,deals_rating,deals_stock,deals_discription,deals_discount;

    int flag=0;
    Handler handler_error;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day_deals);

        btn_admin_add_day_deals_back = findViewById(R.id.btn_admin_add_day_deals_back);
        btn_admin_add_day_deals_select_image = findViewById(R.id.btn_admin_add_day_deals_select_image);
        btn_admin_add_day_deals = findViewById(R.id.btn_admin_add_day_deals);
        edit_admin_add_day_deals_name = findViewById(R.id.edit_admin_add_day_deals_name);
        edit_admin_add_day_deals_price = findViewById(R.id.edit_admin_add_day_deals_price);
        edit_admin_add_day_deals_rating = findViewById(R.id.edit_admin_add_day_deals_rating);
        edit_admin_add_day_deals_stock = findViewById(R.id.edit_admin_add_day_deals_stock);
        edit_admin_add_deals_of_the_day_discription = findViewById(R.id.edit_admin_add_deals_of_the_day_discription);
        edit_admin_add_day_deals_percentage = findViewById(R.id.edit_admin_add_day_deals_percentage);

        txt_add_day_deals_error = findViewById(R.id.txt_add_day_deals_error);
        txt_add_day_deals_error.setVisibility(View.GONE);

        admin_add_day_deals_progressbar = findViewById(R.id.admin_add_day_deals_progressbar);
        admin_add_day_deals_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


        btn_admin_add_day_deals_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_admin_home = new Intent(Add_day_deals.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();
            }
        });

        btn_admin_add_day_deals_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 301);
                admin_add_day_deals_progressbar.setVisibility(View.VISIBLE);
            }
        });

        edit_admin_add_day_deals_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_day_deals_name.getText().toString().equals("")){
                        edit_admin_add_day_deals_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_day_deals_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_day_deals_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_day_deals_price.getText().toString().equals("")){
                        edit_admin_add_day_deals_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_day_deals_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_day_deals_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_day_deals_rating.getText().toString().equals("")){
                        edit_admin_add_day_deals_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_day_deals_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_deals_of_the_day_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_deals_of_the_day_discription.getText().toString().equals("")){
                        edit_admin_add_deals_of_the_day_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_deals_of_the_day_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_day_deals_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_day_deals_stock.getText().toString().equals("")){
                        edit_admin_add_day_deals_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_day_deals_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_day_deals_percentage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_day_deals_percentage.getText().toString().equals("")){
                        edit_admin_add_day_deals_percentage.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_day_deals_percentage.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        btn_admin_add_day_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_add_day_deals_progressbar.setVisibility(View.VISIBLE);
                deals_Name = edit_admin_add_day_deals_name.getText().toString();
                deals_price = edit_admin_add_day_deals_price.getText().toString();
                deals_rating = edit_admin_add_day_deals_rating.getText().toString();
                deals_stock = edit_admin_add_day_deals_stock.getText().toString();
                deals_discription = edit_admin_add_deals_of_the_day_discription.getText().toString();
                deals_discount = edit_admin_add_day_deals_percentage.getText().toString();

                if (edit_admin_add_day_deals_name.getText().toString().equals("") || edit_admin_add_day_deals_price.getText().toString().equals("") || edit_admin_add_day_deals_rating.getText().toString().equals("") || edit_admin_add_day_deals_stock.getText().toString().equals("") || edit_admin_add_deals_of_the_day_discription.getText().toString().equals("") || edit_admin_add_day_deals_percentage.getText().toString().equals("") || imagePath == null) {
                    flag = 1;

                    if (edit_admin_add_day_deals_name.getText().toString().equals("")) {
                        edit_admin_add_day_deals_name.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_day_deals_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_day_deals_price.getText().toString().equals("")) {
                        edit_admin_add_day_deals_price.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_day_deals_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_day_deals_rating.getText().toString().equals("")) {
                        edit_admin_add_day_deals_rating.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_day_deals_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_day_deals_stock.getText().toString().equals("")) {
                        edit_admin_add_day_deals_stock.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_day_deals_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_deals_of_the_day_discription.getText().toString().equals("")) {
                        edit_admin_add_deals_of_the_day_discription.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_deals_of_the_day_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_day_deals_percentage.getText().toString().equals("")) {
                        edit_admin_add_day_deals_percentage.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_day_deals_percentage.setBackgroundResource(R.drawable.green_border);
                    }

                    if (imagePath == null && !edit_admin_add_day_deals_name.getText().toString().equals("") && !edit_admin_add_day_deals_rating.getText().toString().equals("") && !edit_admin_add_day_deals_price.getText().toString().equals("") && !edit_admin_add_day_deals_stock.getText().toString().equals("") && !edit_admin_add_deals_of_the_day_discription.getText().toString().equals("") && !edit_admin_add_day_deals_percentage.getText().toString().equals("")) {
                        txt_add_day_deals_error.setText("Please select the image");
                        txt_add_day_deals_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_day_deals_progressbar.setVisibility(View.GONE);

                } else {
                    dbRef = FirebaseDatabase.getInstance().getReference("Day Deals");

                    // Check if number of day deals is less than 4 before allowing user to add a new deal
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // If less than 4 day deals, proceed with adding new deal
                            flag = 0;

                            storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Day Deals");
                            store = storage.child(deals_Name);
                            deals_id = dbRef.push().getKey();

                            store.putFile(imagePath)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    Deals deals = new Deals(deals_id, uri.toString(), deals_Name,deals_price,deals_rating,deals_discription,deals_stock,deals_discount);

                                                    dbRef.child(deals_id).setValue(deals);

                                                    txt_add_day_deals_error.setText("Deal Added Successfully");
                                                    txt_add_day_deals_error.setVisibility(View.VISIBLE);

                                                    handler_error.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            txt_add_day_deals_error.setVisibility(View.GONE);
                                                        }
                                                    }, 5000);

                                                    admin_add_day_deals_progressbar.setVisibility(View.GONE);
                                                    edit_admin_add_day_deals_name.setText("");
                                                    edit_admin_add_day_deals_price.setText("");
                                                    edit_admin_add_day_deals_rating.setText("");
                                                    edit_admin_add_deals_of_the_day_discription.setText("");
                                                    edit_admin_add_day_deals_stock.setText("");
                                                    edit_admin_add_day_deals_percentage.setText("");

                                                }
                                            });

                                        }
                                    });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });







    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int reqCode,int result,Intent data)
    {
        if(result==RESULT_OK && reqCode==301)
        {
            imagePath = data.getData();
            admin_add_day_deals_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_day_deals_error.setText("Image : "+imagePath.toString());
            txt_add_day_deals_error.setVisibility(View.VISIBLE);


        }


    }



}