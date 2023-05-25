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
import com.example.shopease.model.Personal_Care;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_Personal_care extends AppCompatActivity {

    ImageView btn_admin_add_personal_care_back;
    EditText edit_admin_add_personal_care_name,edit_admin_add_personal_care_price,edit_admin_add_personal_care_weight,edit_admin_add_personal_care_rating,edit_admin_add_personal_care_discription,edit_admin_add_personal_care_stock;
    Button btn_admin_add_personal_care_select_image,btn_admin_add_personal_care;
    TextView txt_add_personal_care_error;
    ProgressBar admin_add_personal_care_progressbar;

    Uri personal_care_imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String personal_care_id;
    String personal_care_Name,personal_care_price,personal_care_weight,personal_care_rating,personal_care_discription,personal_care_stock;

    int flag=0;
    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_care);

        btn_admin_add_personal_care_back = findViewById(R.id.btn_admin_add_personal_care_back);
        edit_admin_add_personal_care_name = findViewById(R.id.edit_admin_add_personal_care_name);
        btn_admin_add_personal_care_select_image = findViewById(R.id.btn_admin_add_personal_care_select_image);
        edit_admin_add_personal_care_stock = findViewById(R.id.edit_admin_add_personal_care_stock);
        btn_admin_add_personal_care = findViewById(R.id.btn_admin_add_personal_care);

        txt_add_personal_care_error = findViewById(R.id.txt_add_personal_care_error);
        txt_add_personal_care_error.setVisibility(View.GONE);

        admin_add_personal_care_progressbar = findViewById(R.id.admin_add_personal_care_progressbar);
        admin_add_personal_care_progressbar.setVisibility(View.GONE);

        edit_admin_add_personal_care_price = findViewById(R.id.edit_admin_add_personal_care_price);
        edit_admin_add_personal_care_weight = findViewById(R.id.edit_admin_add_personal_care_weight);
        edit_admin_add_personal_care_rating = findViewById(R.id.edit_admin_add_personal_care_rating);
        edit_admin_add_personal_care_discription = findViewById(R.id.edit_admin_add_personal_care_discription);

        handler_error = new Handler();

        //        if click on add offer back button then return to admin home
        btn_admin_add_personal_care_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_Personal_care.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });


//        if click on add offer select image then active the system gallery service and select image
        btn_admin_add_personal_care_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 411);
                admin_add_personal_care_progressbar.setVisibility(View.VISIBLE);
            }
        });

        edit_admin_add_personal_care_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_personal_care_name.getText().toString().equals("")){
                        edit_admin_add_personal_care_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_personal_care_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_personal_care_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_personal_care_price.getText().toString().equals("")){
                        edit_admin_add_personal_care_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_personal_care_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_personal_care_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_personal_care_weight.getText().toString().equals("")){
                        edit_admin_add_personal_care_weight.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_personal_care_weight.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_personal_care_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_personal_care_rating.getText().toString().equals("")){
                        edit_admin_add_personal_care_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_personal_care_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_personal_care_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_personal_care_discription.getText().toString().equals("")){
                        edit_admin_add_personal_care_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_personal_care_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_personal_care_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_personal_care_stock.getText().toString().equals("")){
                        edit_admin_add_personal_care_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_personal_care_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });



//        if click on add offer button then add offers details into firebase offer section
        btn_admin_add_personal_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_add_personal_care_progressbar.setVisibility(ProgressBar.VISIBLE);

                personal_care_Name = edit_admin_add_personal_care_name.getText().toString();
                personal_care_price = edit_admin_add_personal_care_price.getText().toString();
                personal_care_weight = edit_admin_add_personal_care_weight.getText().toString();
                personal_care_rating = edit_admin_add_personal_care_rating.getText().toString();
                personal_care_discription = edit_admin_add_personal_care_discription.getText().toString();
                personal_care_stock = edit_admin_add_personal_care_stock.getText().toString();

                if(edit_admin_add_personal_care_name.getText().toString().equals("") || edit_admin_add_personal_care_price.getText().toString().equals("") || edit_admin_add_personal_care_weight.getText().toString().equals("") || edit_admin_add_personal_care_rating.getText().toString().equals("") || edit_admin_add_personal_care_discription.getText().toString().equals("") || edit_admin_add_personal_care_stock.getText().toString().equals("") || personal_care_imagePath == null){
                    flag=1;

                    if(edit_admin_add_personal_care_name.getText().toString().equals("")){
                        edit_admin_add_personal_care_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_personal_care_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_personal_care_price.getText().toString().equals("")){
                        edit_admin_add_personal_care_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_personal_care_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_personal_care_weight.getText().toString().equals("")){
                        edit_admin_add_personal_care_weight.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_personal_care_weight.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_personal_care_rating.getText().toString().equals("")){
                        edit_admin_add_personal_care_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_personal_care_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_personal_care_discription.getText().toString().equals("")){
                        edit_admin_add_personal_care_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_personal_care_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_personal_care_stock.getText().toString().equals("")){
                        edit_admin_add_personal_care_stock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_personal_care_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(personal_care_imagePath == null && !edit_admin_add_personal_care_name.getText().toString().equals("") && !edit_admin_add_personal_care_price.getText().toString().equals("") && !edit_admin_add_personal_care_weight.getText().toString().equals("") && !edit_admin_add_personal_care_rating.getText().toString().equals("") && !edit_admin_add_personal_care_discription.getText().toString().equals("") && !edit_admin_add_personal_care_stock.getText().toString().equals("")){
                        txt_add_personal_care_error.setText("Please select the image");
                        txt_add_personal_care_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_personal_care_progressbar.setVisibility(View.GONE);

                }



                if(flag==0){


//                connect to firebase offer section
                    dbRef= FirebaseDatabase.getInstance().getReference("Personal Care");
//                connect to firebase offers image storage
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Personal_care");

                    store=storage.child(personal_care_Name);
                    personal_care_id=dbRef.push().getKey();

                    //if image path selected then add offers successfully into firebase
                    store.putFile(personal_care_imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Personal_Care personal_care=new Personal_Care(personal_care_id,uri.toString(),personal_care_Name,personal_care_price,personal_care_weight,personal_care_rating,personal_care_discription,personal_care_stock);

                                            dbRef.child(personal_care_id).setValue(personal_care);

                                            txt_add_personal_care_error.setText("Personal Care Product Added Successfully");
                                            txt_add_personal_care_error.setVisibility(View.VISIBLE);


                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_personal_care_error.setVisibility(View.GONE);

                                                }
                                            },5000);

                                            admin_add_personal_care_progressbar.setVisibility(View.GONE);

                                            edit_admin_add_personal_care_name.setText("");
                                            edit_admin_add_personal_care_price.setText("");
                                            edit_admin_add_personal_care_weight.setText("");
                                            edit_admin_add_personal_care_rating.setText("");
                                            edit_admin_add_personal_care_discription.setText("");
                                            edit_admin_add_personal_care_stock.setText("");

                                        }
                                    });
                                }
                            });
                }else{
                    admin_add_personal_care_progressbar.setVisibility(View.GONE);
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
        if(result==RESULT_OK && reqCode==411)
        {
            personal_care_imagePath = data.getData();
            admin_add_personal_care_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_personal_care_error.setText("Image : "+personal_care_imagePath.toString());
            txt_add_personal_care_error.setVisibility(View.VISIBLE);


        }


    }


}