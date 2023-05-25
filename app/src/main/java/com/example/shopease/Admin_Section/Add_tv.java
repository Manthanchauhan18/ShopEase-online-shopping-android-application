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
import com.example.shopease.model.Tv;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_tv extends AppCompatActivity {

    ImageView btn_admin_add_tv_back;
    EditText edit_admin_add_tv_name,edit_admin_add_tv_price,edit_admin_add_tv_size,edit_admin_add_tv_stock,edit_admin_add_tv_rating,edit_admin_add_tv_discription;
    Button btn_admin_add_tv_select_image,btn_admin_add_tv;
    TextView txt_add_tv_error;
    ProgressBar admin_add_tv_progressbar;


    Uri imagePath;
    DatabaseReference dbRef_tv;
    StorageReference storage,store;
    String tvid;
    String tv_Name,tv_Price,tv_size,tv_rating,tv_discription,tv_summary,tv_stock;

    int flag=0;
    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tv);

        btn_admin_add_tv_back = findViewById(R.id.btn_admin_add_tv_back);
        edit_admin_add_tv_name = findViewById(R.id.edit_admin_add_tv_name);
        edit_admin_add_tv_price = findViewById(R.id.edit_admin_add_tv_price);
        edit_admin_add_tv_size = findViewById(R.id.edit_admin_add_tv_size);
        edit_admin_add_tv_stock = findViewById(R.id.edit_admin_add_tv_stock);
        edit_admin_add_tv_rating = findViewById(R.id.edit_admin_add_tv_rating);
        edit_admin_add_tv_discription = findViewById(R.id.edit_admin_add_tv_discription);
        btn_admin_add_tv_select_image = findViewById(R.id.btn_admin_add_tv_select_image);
        btn_admin_add_tv = findViewById(R.id.btn_admin_add_tv);

        txt_add_tv_error = findViewById(R.id.txt_add_tv_error);
        txt_add_tv_error.setVisibility(View.GONE);

        admin_add_tv_progressbar = findViewById(R.id.admin_add_tv_progressbar);
        admin_add_tv_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


        btn_admin_add_tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_tv.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });

        btn_admin_add_tv_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 252);
                admin_add_tv_progressbar.setVisibility(View.VISIBLE);

            }
        });

        edit_admin_add_tv_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_tv_name.getText().toString().equals("")){
                        edit_admin_add_tv_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_tv_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_tv_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_tv_price.getText().toString().equals("")){
                        edit_admin_add_tv_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_tv_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_tv_size.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_tv_size.getText().toString().equals("")){
                        edit_admin_add_tv_size.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_tv_size.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_tv_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_tv_stock.getText().toString().equals("")){
                        edit_admin_add_tv_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_tv_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        edit_admin_add_tv_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_tv_rating.getText().toString().equals("")){
                        edit_admin_add_tv_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_tv_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_tv_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_tv_discription.getText().toString().equals("")){
                        edit_admin_add_tv_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_tv_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        btn_admin_add_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_add_tv_progressbar.setVisibility(ProgressBar.VISIBLE);
                tv_Name = edit_admin_add_tv_name.getText().toString();
                tv_Price = edit_admin_add_tv_price.getText().toString();
                tv_size = edit_admin_add_tv_size.getText().toString();
                tv_rating = edit_admin_add_tv_rating.getText().toString();
                tv_discription = edit_admin_add_tv_discription.getText().toString();
                tv_stock = edit_admin_add_tv_stock.getText().toString();


                if(edit_admin_add_tv_name.getText().toString().equals("") || edit_admin_add_tv_price.getText().toString().equals("") || edit_admin_add_tv_size.getText().toString().equals("") || edit_admin_add_tv_stock.getText().toString().equals("")  || edit_admin_add_tv_rating.getText().toString().equals("") || edit_admin_add_tv_discription.getText().toString().equals("") || imagePath == null){
                    flag=1;

                    if(edit_admin_add_tv_name.getText().toString().equals("")){
                        edit_admin_add_tv_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_tv_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_tv_price.getText().toString().equals("")){
                        edit_admin_add_tv_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_tv_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_tv_size.getText().toString().equals("")){
                        edit_admin_add_tv_size.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_tv_size.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_tv_stock.getText().toString().equals("")){
                        edit_admin_add_tv_stock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_tv_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_tv_rating.getText().toString().equals("")){
                        edit_admin_add_tv_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_tv_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_tv_discription.getText().toString().equals("")){
                        edit_admin_add_tv_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_tv_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(imagePath == null && !edit_admin_add_tv_name.getText().toString().equals("") && !edit_admin_add_tv_price.getText().toString().equals("") && !edit_admin_add_tv_size.getText().toString().equals("") && !edit_admin_add_tv_stock.getText().toString().equals("") && edit_admin_add_tv_rating.getText().toString().equals("") && edit_admin_add_tv_discription.getText().toString().equals("") ){
                        txt_add_tv_error.setText("Please select the image");
                        txt_add_tv_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_tv_progressbar.setVisibility(View.GONE);

                }else{
                    dbRef_tv = FirebaseDatabase.getInstance().getReference("Tv");

                    // Check if number of day deals is less than 4 before allowing user to add a new deal
                    dbRef_tv.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // If less than 4 day deals, proceed with adding new deal
                            flag = 0;

                            storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Tv");
                            store = storage.child(tv_Name);
                            tvid = dbRef_tv.push().getKey();

                            store.putFile(imagePath)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    Tv tv = new Tv(tvid,uri.toString(), tv_Name , tv_Price , tv_size , tv_rating , tv_discription , tv_stock);

                                                    dbRef_tv.child(tvid).setValue(tv);

                                                    txt_add_tv_error.setText("Tv Added Successfully");
                                                    txt_add_tv_error.setVisibility(View.VISIBLE);

                                                    handler_error.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            txt_add_tv_error.setVisibility(View.GONE);
                                                        }
                                                    }, 5000);

                                                    admin_add_tv_progressbar
                                                            .setVisibility(View.GONE);
                                                    edit_admin_add_tv_name.setText("");
                                                    edit_admin_add_tv_price.setText("");
                                                    edit_admin_add_tv_size.setText("");
                                                    edit_admin_add_tv_rating.setText("");
                                                    edit_admin_add_tv_discription.setText("");
                                                    edit_admin_add_tv_stock.setText("");


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
        if(result==RESULT_OK && reqCode==252)
        {
            imagePath = data.getData();
            admin_add_tv_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_tv_error.setText("Image : "+imagePath.toString());
            txt_add_tv_error.setVisibility(View.VISIBLE);


        }


    }



}