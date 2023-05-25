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
import com.example.shopease.model.Grocery;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_Grocery extends AppCompatActivity {

    ImageView btn_admin_add_grocery_back;
    EditText edit_admin_add_grocery_name,edit_admin_add_grocery_price,edit_admin_add_grocery_amount,edit_admin_add_grocery_discription,edit_admin_add_grocery_rating,edit_admin_add_grocery_stock;
    Button btn_admin_add_grocery_select_image,btn_admin_add_grocery;
    TextView txt_add_grocery_error;
    ProgressBar admin_add_grocery_progressbar;

    Uri grocery_imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String grocery_id;
    String grocery_Name,grocery_price,grocery_amount,grocery_rating,grocery_discription,grocery_stock;

    int flag=0;
    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);

        btn_admin_add_grocery_back = findViewById(R.id.btn_admin_add_grocery_back);
        edit_admin_add_grocery_name = findViewById(R.id.edit_admin_add_grocery_name);
        btn_admin_add_grocery_select_image = findViewById(R.id.btn_admin_add_grocery_select_image);
        btn_admin_add_grocery = findViewById(R.id.btn_admin_add_grocery);

        txt_add_grocery_error = findViewById(R.id.txt_add_grocery_error);
        txt_add_grocery_error.setVisibility(View.GONE);

        admin_add_grocery_progressbar = findViewById(R.id.admin_add_grocery_progressbar);
        admin_add_grocery_progressbar.setVisibility(View.GONE);

        edit_admin_add_grocery_price = findViewById(R.id.edit_admin_add_grocery_price);
        edit_admin_add_grocery_amount = findViewById(R.id.edit_admin_add_grocery_amount);
        edit_admin_add_grocery_rating = findViewById(R.id.edit_admin_add_grocery_rating);
        edit_admin_add_grocery_discription = findViewById(R.id.edit_admin_add_grocery_discription);
        edit_admin_add_grocery_stock = findViewById(R.id.edit_admin_add_grocery_stock);

        handler_error = new Handler();

        //        if click on add offer back button then return to admin home
        btn_admin_add_grocery_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_Grocery.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });


//        if click on add offer select image then active the system gallery service and select image
        btn_admin_add_grocery_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 205);
                admin_add_grocery_progressbar.setVisibility(View.VISIBLE);
            }
        });



        edit_admin_add_grocery_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_grocery_name.getText().toString().equals("")){
                        edit_admin_add_grocery_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_grocery_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_grocery_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_grocery_price.getText().toString().equals("")){
                        edit_admin_add_grocery_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_grocery_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_grocery_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_grocery_amount.getText().toString().equals("")){
                        edit_admin_add_grocery_amount.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_grocery_amount.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_grocery_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_grocery_stock.getText().toString().equals("")){
                        edit_admin_add_grocery_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_grocery_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_grocery_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_grocery_rating.getText().toString().equals("")){
                        edit_admin_add_grocery_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_grocery_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_grocery_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_grocery_discription.getText().toString().equals("")){
                        edit_admin_add_grocery_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_grocery_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


//        if click on add offer button then add offers details into firebase offer section
        btn_admin_add_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_add_grocery_progressbar.setVisibility(ProgressBar.VISIBLE);

                grocery_Name = edit_admin_add_grocery_name.getText().toString();
                grocery_price = edit_admin_add_grocery_price.getText().toString();
                grocery_amount = edit_admin_add_grocery_amount.getText().toString();
                grocery_rating = edit_admin_add_grocery_rating.getText().toString();
                grocery_discription = edit_admin_add_grocery_discription.getText().toString();
                grocery_stock = edit_admin_add_grocery_stock.getText().toString();

                if(edit_admin_add_grocery_name.getText().toString().equals("") || edit_admin_add_grocery_price.getText().toString().equals("") || edit_admin_add_grocery_amount.getText().toString().equals("") || edit_admin_add_grocery_rating.getText().toString().equals("") || edit_admin_add_grocery_discription.getText().toString().equals("") || edit_admin_add_grocery_stock.getText().toString().equals("") || grocery_imagePath == null){
                    flag=1;

                    if(edit_admin_add_grocery_name.getText().toString().equals("")){
                        edit_admin_add_grocery_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_grocery_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_grocery_price.getText().toString().equals("")){
                        edit_admin_add_grocery_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_grocery_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_grocery_amount.getText().toString().equals("")){
                        edit_admin_add_grocery_amount.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_grocery_amount.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_grocery_rating.getText().toString().equals("")){
                        edit_admin_add_grocery_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_grocery_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_grocery_stock.getText().toString().equals("")){
                        edit_admin_add_grocery_stock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_grocery_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_grocery_discription.getText().toString().equals("")){
                        edit_admin_add_grocery_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_grocery_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(grocery_imagePath == null && !edit_admin_add_grocery_name.getText().toString().equals("") && !edit_admin_add_grocery_price.getText().toString().equals("") && !edit_admin_add_grocery_amount.getText().toString().equals("") && edit_admin_add_grocery_rating.getText().toString().equals("") && edit_admin_add_grocery_discription.getText().toString().equals("") && edit_admin_add_grocery_stock.getText().toString().equals("")){
                        txt_add_grocery_error.setText("Please select the image");
                        txt_add_grocery_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_grocery_progressbar.setVisibility(View.GONE);

                }



                if(flag==0){


//                connect to firebase offer section
                    dbRef= FirebaseDatabase.getInstance().getReference("Grocery");
//                connect to firebase offers image storage
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Grocery");

                    store=storage.child(grocery_Name);
                    grocery_id=dbRef.push().getKey();

                    //if image path selected then add offers successfully into firebase
                    store.putFile(grocery_imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Grocery grocery=new Grocery(grocery_id,uri.toString(),grocery_Name,grocery_price,grocery_amount,grocery_rating,grocery_discription,grocery_stock);

                                            dbRef.child(grocery_id).setValue(grocery);

                                            txt_add_grocery_error.setText("Grocery Added Successfully");
                                            txt_add_grocery_error.setVisibility(View.VISIBLE);


                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_grocery_error.setVisibility(View.GONE);

                                                }
                                            },5000);

                                            admin_add_grocery_progressbar.setVisibility(View.GONE);

                                            edit_admin_add_grocery_name.setText("");
                                            edit_admin_add_grocery_price.setText("");
                                            edit_admin_add_grocery_amount.setText("");
                                            edit_admin_add_grocery_rating.setText("");
                                            edit_admin_add_grocery_discription.setText("");
                                            edit_admin_add_grocery_stock.setText("");

                                        }
                                    });
                                }
                            });
                }else{
                    admin_add_grocery_progressbar.setVisibility(View.GONE);
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
        if(result==RESULT_OK && reqCode==205)
        {
            grocery_imagePath = data.getData();
            admin_add_grocery_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_grocery_error.setText("Image : "+grocery_imagePath.toString());
            txt_add_grocery_error.setVisibility(View.VISIBLE);


        }


    }


}