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
import com.example.shopease.model.Appliance;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_Appliances extends AppCompatActivity {

    ImageView btn_admin_add_appliance_back;
    EditText edit_admin_add_appliance_name,edit_admin_add_appliance_price,edit_admin_add_appliance_weight,edit_admin_add_appliance_rating,edit_admin_add_appliance_discription,edit_admin_add_appliance_stock;
    Button btn_admin_add_appliance_select_image,btn_admin_add_appliance;
    TextView txt_add_appliance_error;
    ProgressBar admin_add_appliance_progressbar;

    Uri appliance_imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String appliance_id;
    String appliance_Name,appliance_price,appliance_weight,appliance_rating,appliance_discription,appliance_stock;

    int flag=0;
    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appliances);

        btn_admin_add_appliance_back = findViewById(R.id.btn_admin_add_appliance_back);
        edit_admin_add_appliance_name = findViewById(R.id.edit_admin_add_appliance_name);
        btn_admin_add_appliance_select_image = findViewById(R.id.btn_admin_add_appliance_select_image);
        btn_admin_add_appliance = findViewById(R.id.btn_admin_add_appliance);

        txt_add_appliance_error = findViewById(R.id.txt_add_appliance_error);
        txt_add_appliance_error.setVisibility(View.GONE);

        admin_add_appliance_progressbar = findViewById(R.id.admin_add_appliance_progressbar);
        admin_add_appliance_progressbar.setVisibility(View.GONE);

        edit_admin_add_appliance_price = findViewById(R.id.edit_admin_add_appliance_price);
        edit_admin_add_appliance_weight = findViewById(R.id.edit_admin_add_appliance_weight);
        edit_admin_add_appliance_rating = findViewById(R.id.edit_admin_add_appliance_rating);
        edit_admin_add_appliance_discription = findViewById(R.id.edit_admin_add_appliance_discription);
        edit_admin_add_appliance_stock = findViewById(R.id.edit_admin_add_appliance_stock);

        handler_error = new Handler();

        //        if click on add offer back button then return to admin home
        btn_admin_add_appliance_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_Appliances.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });


//        if click on add offer select image then active the system gallery service and select image
        btn_admin_add_appliance_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 206);
                admin_add_appliance_progressbar.setVisibility(View.VISIBLE);
            }
        });



        edit_admin_add_appliance_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_appliance_name.getText().toString().equals("")){
                        edit_admin_add_appliance_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_appliance_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_appliance_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_appliance_price.getText().toString().equals("")){
                        edit_admin_add_appliance_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_appliance_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_appliance_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_appliance_weight.getText().toString().equals("")){
                        edit_admin_add_appliance_weight.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_appliance_weight.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_appliance_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_appliance_stock.getText().toString().equals("")){
                        edit_admin_add_appliance_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_appliance_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_appliance_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_appliance_rating.getText().toString().equals("")){
                        edit_admin_add_appliance_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_appliance_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_appliance_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_appliance_discription.getText().toString().equals("")){
                        edit_admin_add_appliance_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_appliance_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });



//        if click on add offer button then add offers details into firebase offer section
        btn_admin_add_appliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_add_appliance_progressbar.setVisibility(ProgressBar.VISIBLE);

                appliance_Name = edit_admin_add_appliance_name.getText().toString();
                appliance_price = edit_admin_add_appliance_price.getText().toString();
                appliance_weight = edit_admin_add_appliance_weight.getText().toString();
                appliance_rating = edit_admin_add_appliance_rating.getText().toString();
                appliance_discription = edit_admin_add_appliance_discription.getText().toString();
                appliance_stock = edit_admin_add_appliance_stock.getText().toString();

                if(edit_admin_add_appliance_name.getText().toString().equals("") || edit_admin_add_appliance_price.getText().toString().equals("") || edit_admin_add_appliance_weight.getText().toString().equals("") || edit_admin_add_appliance_rating.getText().toString().equals("") || edit_admin_add_appliance_discription.getText().toString().equals("") || edit_admin_add_appliance_stock.getText().toString().equals("") || appliance_imagePath == null){
                    flag=1;

                    if(edit_admin_add_appliance_name.getText().toString().equals("")){
                        edit_admin_add_appliance_name.setBackgroundResource(R.drawable.red_border);
                    }else{edit_admin_add_appliance_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_appliance_price.getText().toString().equals("")){
                        edit_admin_add_appliance_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_appliance_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_appliance_weight.getText().toString().equals("")){
                        edit_admin_add_appliance_weight.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_appliance_weight.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_appliance_stock.getText().toString().equals("")){
                        edit_admin_add_appliance_stock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_appliance_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_appliance_rating.getText().toString().equals("")){
                        edit_admin_add_appliance_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_appliance_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_appliance_discription.getText().toString().equals("")){
                        edit_admin_add_appliance_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_appliance_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(appliance_imagePath == null && !edit_admin_add_appliance_name.getText().toString().equals("") && !edit_admin_add_appliance_price.getText().toString().equals("") && !edit_admin_add_appliance_stock.getText().toString().equals("") && !edit_admin_add_appliance_weight.getText().toString().equals("") && !edit_admin_add_appliance_rating.getText().toString().equals("") && !edit_admin_add_appliance_discription.getText().toString().equals("")){
                        txt_add_appliance_error.setText("Please select the image");
                        txt_add_appliance_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_appliance_progressbar.setVisibility(View.GONE);

                }



                if(flag==0){


//                connect to firebase offer section
                    dbRef= FirebaseDatabase.getInstance().getReference("Appliances");
//                connect to firebase offers image storage
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Appliances");

                    store=storage.child(appliance_Name);
                    appliance_id=dbRef.push().getKey();

                    //if image path selected then add offers successfully into firebase
                    store.putFile(appliance_imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Appliance appliance=new Appliance(appliance_id,uri.toString(),appliance_Name,appliance_price,appliance_weight,appliance_rating,appliance_discription,appliance_stock);

                                            dbRef.child(appliance_id).setValue(appliance);

                                            txt_add_appliance_error.setText("Appliance Product Added Successfully");
                                            txt_add_appliance_error.setVisibility(View.VISIBLE);


                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_appliance_error.setVisibility(View.GONE);

                                                }
                                            },5000);

                                            admin_add_appliance_progressbar.setVisibility(View.GONE);

                                            edit_admin_add_appliance_name.setText("");
                                            edit_admin_add_appliance_price.setText("");
                                            edit_admin_add_appliance_weight.setText("");
                                            edit_admin_add_appliance_rating.setText("");
                                            edit_admin_add_appliance_discription.setText("");
                                            edit_admin_add_appliance_stock.setText("");

                                        }
                                    });
                                }
                            });
                }else{
                    admin_add_appliance_progressbar.setVisibility(View.GONE);
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
        if(result==RESULT_OK && reqCode==206)
        {
            appliance_imagePath = data.getData();
            admin_add_appliance_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_appliance_error.setText("Image : "+appliance_imagePath.toString());
            txt_add_appliance_error.setVisibility(View.VISIBLE);


        }


    }


}