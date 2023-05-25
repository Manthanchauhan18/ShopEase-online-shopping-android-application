package com.example.shopease.Admin_Section;

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
import com.example.shopease.model.Sports;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_sports extends AppCompatActivity {

    ImageView btn_admin_add_sport_back;
    EditText edit_admin_add_sport_name,edit_admin_add_sport_price,edit_admin_add_sport_rating,edit_admin_add_sport_discription,edit_admin_add_sports_stock;
    Button btn_admin_add_sport_select_image,btn_admin_add_sport;
    TextView txt_add_sport_error;
    ProgressBar admin_add_sport_progressbar;

    Uri sport_imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String sport_id;
    String sport_Name,sport_price,sport_rating,sport_discription,sport_stock;

    int flag=0;
    Handler handler_error;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sports);

        btn_admin_add_sport_back = findViewById(R.id.btn_admin_add_sport_back);
        edit_admin_add_sport_name = findViewById(R.id.edit_admin_add_sport_name);
        btn_admin_add_sport_select_image = findViewById(R.id.btn_admin_add_sport_select_image);
        edit_admin_add_sports_stock = findViewById(R.id.edit_admin_add_sports_stock);
        btn_admin_add_sport = findViewById(R.id.btn_admin_add_sport);

        txt_add_sport_error = findViewById(R.id.txt_add_sport_error);
        txt_add_sport_error.setVisibility(View.GONE);

        admin_add_sport_progressbar = findViewById(R.id.admin_add_sport_progressbar);
        admin_add_sport_progressbar.setVisibility(View.GONE);

        edit_admin_add_sport_price = findViewById(R.id.edit_admin_add_sport_price);
        edit_admin_add_sport_rating = findViewById(R.id.edit_admin_add_sports_rating);
        edit_admin_add_sport_discription = findViewById(R.id.edit_admin_add_sports_discription);

        handler_error = new Handler();

        //        if click on add offer back button then return to admin home
        btn_admin_add_sport_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_sports.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });


//        if click on add offer select image then active the system gallery service and select image
        btn_admin_add_sport_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 414);
                admin_add_sport_progressbar.setVisibility(View.VISIBLE);
            }
        });



        edit_admin_add_sport_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_sport_name.getText().toString().equals("")){
                        edit_admin_add_sport_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_sport_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_sport_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_sport_price.getText().toString().equals("")){
                        edit_admin_add_sport_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_sport_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_sport_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_sport_rating.getText().toString().equals("")){
                        edit_admin_add_sport_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_sport_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_sport_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_sport_discription.getText().toString().equals("")){
                        edit_admin_add_sport_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_sport_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_sports_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_sports_stock.getText().toString().equals("")){
                        edit_admin_add_sports_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_sports_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


//        if click on add offer button then add offers details into firebase offer section
        btn_admin_add_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_add_sport_progressbar.setVisibility(ProgressBar.VISIBLE);

                sport_Name = edit_admin_add_sport_name.getText().toString();
                sport_price = edit_admin_add_sport_price.getText().toString();
                sport_rating = edit_admin_add_sport_rating.getText().toString();
                sport_discription = edit_admin_add_sport_discription.getText().toString();
                sport_stock = edit_admin_add_sports_stock.getText().toString();

                if(edit_admin_add_sport_name.getText().toString().equals("") || edit_admin_add_sport_price.getText().toString().equals("") || edit_admin_add_sport_price.getText().toString().equals("") || edit_admin_add_sport_price.getText().toString().equals("") || edit_admin_add_sports_stock.getText().toString().equals("") || sport_imagePath == null){
                    flag=1;

                    if(edit_admin_add_sport_name.getText().toString().equals("")){
                        edit_admin_add_sport_name.setBackgroundResource(R.drawable.red_border);
                    }else{edit_admin_add_sport_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_sport_price.getText().toString().equals("")){
                        edit_admin_add_sport_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_sport_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_sport_rating.getText().toString().equals("")){
                        edit_admin_add_sport_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_sport_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_sport_discription.getText().toString().equals("")){
                        edit_admin_add_sport_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_sport_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_sports_stock.getText().toString().equals("")){
                        edit_admin_add_sports_stock.setBackgroundResource(R.drawable.red_border);
                    }else{edit_admin_add_sports_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(sport_imagePath == null && !edit_admin_add_sport_name.getText().toString().equals("") && !edit_admin_add_sport_price.getText().toString().equals("")  && !edit_admin_add_sport_rating.getText().toString().equals("")  && !edit_admin_add_sport_discription.getText().toString().equals("") && !edit_admin_add_sports_stock.getText().toString().equals("")){
                        txt_add_sport_error.setText("Please select the image");
                        txt_add_sport_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_sport_progressbar.setVisibility(View.GONE);

                }



                if(flag==0){


//                connect to firebase offer section
                    dbRef= FirebaseDatabase.getInstance().getReference("Sport");
//                connect to firebase offers image storage
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/sports");

                    store=storage.child(sport_Name);
                    sport_id=dbRef.push().getKey();

                    //if image path selected then add offers successfully into firebase
                    store.putFile(sport_imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Sports sports=new Sports(sport_id,uri.toString(),sport_Name,sport_price,sport_rating,sport_discription,sport_stock);

                                            dbRef.child(sport_id).setValue(sports);

                                            txt_add_sport_error.setText("Sport Product Added Successfully");
                                            txt_add_sport_error.setVisibility(View.VISIBLE);


                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_sport_error.setVisibility(View.GONE);

                                                }
                                            },5000);

                                            admin_add_sport_progressbar.setVisibility(View.GONE);

                                            edit_admin_add_sport_name.setText("");
                                            edit_admin_add_sport_price.setText("");
                                            edit_admin_add_sport_rating.setText("");
                                            edit_admin_add_sport_discription.setText("");
                                            edit_admin_add_sports_stock.setText("");

                                        }
                                    });
                                }
                            });
                }else{
                    admin_add_sport_progressbar.setVisibility(View.GONE);
//                    txt_add_offer_error.setText("Please fill the details Properly");
//                    txt_add_offer_error.setVisibility(View.VISIBLE);

                }




            }
        });




    }


    //    select the image path from gallery and return the image path
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int reqCode,int result,Intent data)
    {
        if(result==RESULT_OK && reqCode==414)
        {
            sport_imagePath = data.getData();
            admin_add_sport_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_sport_error.setText("Image : "+sport_imagePath.toString());
            txt_add_sport_error.setVisibility(View.VISIBLE);


        }


    }


}