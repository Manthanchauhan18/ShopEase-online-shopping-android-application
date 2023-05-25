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
import com.example.shopease.model.Furniture;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_Furniture extends AppCompatActivity {

    ImageView btn_admin_add_furniture_back;
    EditText edit_admin_add_furniture_name,edit_admin_add_furniture_price,edit_admin_add_furniture_rating,edit_admin_add_furniture_discription,edit_admin_add_furniture_stock;
    Button btn_admin_add_furniture_select_image,btn_admin_add_furniture;
    TextView txt_add_furniture_error;
    ProgressBar admin_add_furniture_progressbar;

    Uri furniture_imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String furniture_id;
    String furniture_Name,furniture_price,furniture_rating,furniture_discription,furniture_stock;

    int flag=0;
    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_furniture);


        btn_admin_add_furniture_back = findViewById(R.id.btn_admin_add_furniture_back);
        edit_admin_add_furniture_name = findViewById(R.id.edit_admin_add_furniture_name);
        btn_admin_add_furniture_select_image = findViewById(R.id.btn_admin_add_furniture_select_image);
        edit_admin_add_furniture_stock = findViewById(R.id.edit_admin_add_furniture_stock);
        btn_admin_add_furniture = findViewById(R.id.btn_admin_add_furniture);

        txt_add_furniture_error = findViewById(R.id.txt_add_furniture_error);
        txt_add_furniture_error.setVisibility(View.GONE);

        admin_add_furniture_progressbar = findViewById(R.id.admin_add_furniture_progressbar);
        admin_add_furniture_progressbar.setVisibility(View.GONE);

        edit_admin_add_furniture_price = findViewById(R.id.edit_admin_add_furniture_price);
        edit_admin_add_furniture_rating = findViewById(R.id.edit_admin_add_furniture_rating);
        edit_admin_add_furniture_discription = findViewById(R.id.edit_admin_add_furniture_discription);

        handler_error = new Handler();


        //        if click on add offer back button then return to admin home
        btn_admin_add_furniture_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_Furniture.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });


//        if click on add offer select image then active the system gallery service and select image
        btn_admin_add_furniture_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 231);
                admin_add_furniture_progressbar.setVisibility(View.VISIBLE);
            }
        });

        edit_admin_add_furniture_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_furniture_name.getText().toString().equals("")){
                        edit_admin_add_furniture_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_furniture_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_furniture_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_furniture_price.getText().toString().equals("")){
                        edit_admin_add_furniture_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_furniture_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        edit_admin_add_furniture_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_furniture_rating.getText().toString().equals("")){
                        edit_admin_add_furniture_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_furniture_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_furniture_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_furniture_discription.getText().toString().equals("")){
                        edit_admin_add_furniture_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_furniture_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_furniture_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_furniture_stock.getText().toString().equals("")){
                        edit_admin_add_furniture_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_furniture_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


//        if click on add offer button then add offers details into firebase offer section
        btn_admin_add_furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_add_furniture_progressbar.setVisibility(ProgressBar.VISIBLE);

                furniture_Name = edit_admin_add_furniture_name.getText().toString();
                furniture_price = edit_admin_add_furniture_price.getText().toString();
                furniture_rating = edit_admin_add_furniture_rating.getText().toString();
                furniture_discription = edit_admin_add_furniture_discription.getText().toString();
                furniture_stock = edit_admin_add_furniture_stock.getText().toString();

                if (edit_admin_add_furniture_name.getText().toString().equals("") || edit_admin_add_furniture_price.getText().toString().equals("") || edit_admin_add_furniture_rating.getText().toString().equals("") || edit_admin_add_furniture_discription.getText().toString().equals("") || edit_admin_add_furniture_stock.getText().toString().equals("") || furniture_imagePath == null) {
                    flag = 1;

                    if (edit_admin_add_furniture_name.getText().toString().equals("")) {
                        edit_admin_add_furniture_name.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_furniture_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_furniture_price.getText().toString().equals("")) {
                        edit_admin_add_furniture_price.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_furniture_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_furniture_rating.getText().toString().equals("")) {
                        edit_admin_add_furniture_rating.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_furniture_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_furniture_discription.getText().toString().equals("")) {
                        edit_admin_add_furniture_discription.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_furniture_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if (edit_admin_add_furniture_stock.getText().toString().equals("")) {
                        edit_admin_add_furniture_stock.setBackgroundResource(R.drawable.red_border);
                    } else {
                        edit_admin_add_furniture_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if (furniture_imagePath == null && !edit_admin_add_furniture_name.getText().toString().equals("") && !edit_admin_add_furniture_price.getText().toString().equals("") && !edit_admin_add_furniture_rating.getText().toString().equals("") && !edit_admin_add_furniture_discription.getText().toString().equals("") && !edit_admin_add_furniture_stock.getText().toString().equals("")) {
                        txt_add_furniture_error.setText("Please select the image");
                        txt_add_furniture_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_furniture_progressbar.setVisibility(View.GONE);

                }


                if (flag == 0) {


//                connect to firebase offer section
                    dbRef = FirebaseDatabase.getInstance().getReference("Furniture");
//                connect to firebase offers image storage
                    storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Furniture");

                    store = storage.child(furniture_Name);
                    furniture_id = dbRef.push().getKey();

                    //if image path selected then add offers successfully into firebase
                    store.putFile(furniture_imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Furniture furniture = new Furniture(furniture_id, uri.toString(), furniture_Name, furniture_price,furniture_rating,furniture_discription,furniture_stock);

                                            dbRef.child(furniture_id).setValue(furniture);

                                            txt_add_furniture_error.setText("Furniture added Successfully");
                                            txt_add_furniture_error.setVisibility(View.VISIBLE);


                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_furniture_error.setVisibility(View.GONE);

                                                }
                                            }, 5000);

                                            admin_add_furniture_progressbar.setVisibility(View.GONE);

                                            edit_admin_add_furniture_name.setText("");
                                            edit_admin_add_furniture_price.setText("");
                                            edit_admin_add_furniture_rating.setText("");
                                            edit_admin_add_furniture_discription.setText("");
                                            edit_admin_add_furniture_stock.setText("");

                                        }
                                    });
                                }
                            });
                } else {
                    admin_add_furniture_progressbar.setVisibility(View.GONE);
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
        if(result==RESULT_OK && reqCode==231)
        {
            furniture_imagePath = data.getData();
            admin_add_furniture_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_furniture_error.setText("Image : "+furniture_imagePath.toString());
            txt_add_furniture_error.setVisibility(View.VISIBLE);


        }


    }

}