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
import com.example.shopease.model.Electronics;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_electronic extends AppCompatActivity {


    ImageView btn_admin_add_electronics_back;
    EditText edit_admin_add_electronics_name,edit_admin_add_electronics_price,edit_admin_add_electronics_rating,edit_admin_add_electronics_discription,edit_admin_add_electronics_stock;
    Button btn_admin_add_electronics_select_image,btn_admin_add_electronics;

    TextView txt_add_electronics_error;
    ProgressBar admin_add_electronics_progressbar;


    Uri electronics_imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String electronics_id;
    String electronics_Name,electronics_Price,electronics_rating,electronics_discription,electronics_stock;

    int flag=0;
    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_electronic);

        btn_admin_add_electronics_back = findViewById(R.id.btn_admin_add_electronics_back);
        edit_admin_add_electronics_name = findViewById(R.id.edit_admin_add_electronics_name);
        btn_admin_add_electronics_select_image = findViewById(R.id.btn_admin_add_electronics_select_image);
        btn_admin_add_electronics = findViewById(R.id.btn_admin_add_electronics);

        txt_add_electronics_error = findViewById(R.id.txt_add_electronics_error);
        txt_add_electronics_error.setVisibility(View.GONE);

        admin_add_electronics_progressbar = findViewById(R.id.admin_add_electronics_progressbar);
        admin_add_electronics_progressbar.setVisibility(View.GONE);

        edit_admin_add_electronics_price = findViewById(R.id.edit_admin_add_electronics_price);
        edit_admin_add_electronics_rating = findViewById(R.id.edit_admin_add_electronics_rating);
        edit_admin_add_electronics_discription = findViewById(R.id.edit_admin_add_electronics_discription);
        edit_admin_add_electronics_stock = findViewById(R.id.edit_admin_add_electronics_stock);

        handler_error = new Handler();


        btn_admin_add_electronics_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_electronic.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });

        btn_admin_add_electronics_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 415);
                admin_add_electronics_progressbar.setVisibility(View.VISIBLE);

            }
        });


        edit_admin_add_electronics_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_electronics_name.getText().toString().equals("")){
                        edit_admin_add_electronics_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_electronics_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_electronics_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_electronics_price.getText().toString().equals("")){
                        edit_admin_add_electronics_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_electronics_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_electronics_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_electronics_stock.getText().toString().equals("")){
                        edit_admin_add_electronics_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_electronics_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_electronics_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_electronics_rating.getText().toString().equals("")){
                        edit_admin_add_electronics_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_electronics_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_electronics_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_electronics_discription.getText().toString().equals("")){
                        edit_admin_add_electronics_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_electronics_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        btn_admin_add_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_add_electronics_progressbar.setVisibility(ProgressBar.VISIBLE);
                electronics_Name = edit_admin_add_electronics_name.getText().toString();
                electronics_Price = edit_admin_add_electronics_price.getText().toString();
                electronics_rating = edit_admin_add_electronics_rating.getText().toString();
                electronics_discription = edit_admin_add_electronics_discription.getText().toString();
                electronics_stock = edit_admin_add_electronics_stock.getText().toString();


                if(edit_admin_add_electronics_name.getText().toString().equals("") || edit_admin_add_electronics_price.getText().toString().equals("") || edit_admin_add_electronics_stock.getText().toString().equals("") || edit_admin_add_electronics_rating.getText().toString().equals("") || edit_admin_add_electronics_discription.getText().toString().equals("") || electronics_imagePath == null){
                    flag=1;

                    if(edit_admin_add_electronics_name.getText().toString().equals("")){
                        edit_admin_add_electronics_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_electronics_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_electronics_price.getText().toString().equals("")){
                        edit_admin_add_electronics_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_electronics_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_electronics_stock.getText().toString().equals("")){
                        edit_admin_add_electronics_stock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_electronics_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_electronics_rating.getText().toString().equals("")){
                        edit_admin_add_electronics_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_electronics_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_electronics_discription.getText().toString().equals("")){
                        edit_admin_add_electronics_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_electronics_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(electronics_imagePath == null && !edit_admin_add_electronics_name.getText().toString().equals("") && !edit_admin_add_electronics_stock.getText().toString().equals("") && !edit_admin_add_electronics_price.getText().toString().equals("") && edit_admin_add_electronics_rating.getText().toString().equals("") && edit_admin_add_electronics_discription.getText().toString().equals("")){
                        txt_add_electronics_error.setText("Please select the image");
                        txt_add_electronics_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_electronics_progressbar.setVisibility(View.GONE);

                }else{
                    dbRef = FirebaseDatabase.getInstance().getReference("Electronics");

                    // Check if number of day deals is less than 4 before allowing user to add a new deal
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // If less than 4 day deals, proceed with adding new deal
                            flag = 0;

                            storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Electronics");
                            store = storage.child(electronics_Name);
                            electronics_id = dbRef.push().getKey();

                            store.putFile(electronics_imagePath)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    Electronics electronics = new Electronics(electronics_id,uri.toString(),electronics_Name , electronics_Price , electronics_rating , electronics_discription , electronics_stock);

                                                    dbRef.child(electronics_id).setValue(electronics);

                                                    txt_add_electronics_error.setText("Electronic Product Added Successfully");
                                                    txt_add_electronics_error.setVisibility(View.VISIBLE);

                                                    handler_error.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            txt_add_electronics_error.setVisibility(View.GONE);
                                                        }
                                                    }, 5000);

                                                    admin_add_electronics_progressbar.setVisibility(View.GONE);
                                                    edit_admin_add_electronics_name.setText("");
                                                    edit_admin_add_electronics_price.setText("");
                                                    edit_admin_add_electronics_rating.setText("");
                                                    edit_admin_add_electronics_discription.setText("");
                                                    edit_admin_add_electronics_stock.setText("");

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
        if(result==RESULT_OK && reqCode==415)
        {
            electronics_imagePath = data.getData();
            admin_add_electronics_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_electronics_error.setText("Image : "+electronics_imagePath.toString());
            txt_add_electronics_error.setVisibility(View.VISIBLE);


        }


    }
}