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
import com.example.shopease.model.Offers;
import com.example.shopease.model.Upcoming_phones;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_upcoming_smartphone extends AppCompatActivity {

    ImageView btn_admin_add_upcoming_smartphone_back;
    EditText edit_admin_add_upcoming_smartphonoe_name,edit_admin_add_upcoming_smartphonoe_price,edit_admin_add_upcoming_smartphonoe_storage,edit_admin_add_upcoming_smartphonoe_rating;
    EditText edit_admin_add_upcoming_smartphonoe_discription,edit_admin_add_upcoming_smartphonoe_summary,edit_admin_add_upcoming_smartphonoe_stock;
    Button btn_admin_add_upcoming_smartphone_select_image,btn_admin_add_upcoming_smartphone;

    TextView txt_add_upcoming_smartphone_error;
    ProgressBar admin_add_upcoming_smartphone_progressbar;


    Uri imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String smartphone_id;
    String smartphone_Name,smartphone_Price,smartphone_storage,smartphone_rating,smartphone_discription,smartphone_summary,smartphone_stock;

    int flag=0;
    Handler handler_error;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upcoming_smartphone);

        btn_admin_add_upcoming_smartphone_back = findViewById(R.id.btn_admin_add_upcoming_smartphone_back);
        edit_admin_add_upcoming_smartphonoe_name = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_name);
        btn_admin_add_upcoming_smartphone_select_image = findViewById(R.id.btn_admin_add_upcoming_smartphone_select_image);
        btn_admin_add_upcoming_smartphone = findViewById(R.id.btn_admin_add_upcoming_smartphone);

        txt_add_upcoming_smartphone_error = findViewById(R.id.txt_add_upcoming_smartphone_error);
        txt_add_upcoming_smartphone_error.setVisibility(View.GONE);

        admin_add_upcoming_smartphone_progressbar = findViewById(R.id.admin_add_upcoming_smartphone_progressbar);
        admin_add_upcoming_smartphone_progressbar.setVisibility(View.GONE);

        edit_admin_add_upcoming_smartphonoe_price = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_price);
        edit_admin_add_upcoming_smartphonoe_storage = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_storage);
        edit_admin_add_upcoming_smartphonoe_rating = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_rating);
        edit_admin_add_upcoming_smartphonoe_discription = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_discription);
        edit_admin_add_upcoming_smartphonoe_summary = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_summary);

        edit_admin_add_upcoming_smartphonoe_stock = findViewById(R.id.edit_admin_add_upcoming_smartphonoe_stock);

        handler_error = new Handler();


        btn_admin_add_upcoming_smartphone_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_admin_home = new Intent(Add_upcoming_smartphone.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();

            }
        });

        btn_admin_add_upcoming_smartphone_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 202);
                admin_add_upcoming_smartphone_progressbar.setVisibility(View.VISIBLE);

            }
        });


        edit_admin_add_upcoming_smartphonoe_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_name.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_upcoming_smartphonoe_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_upcoming_smartphonoe_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_price.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_price.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_upcoming_smartphonoe_price.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_upcoming_smartphonoe_storage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_storage.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_storage.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_upcoming_smartphonoe_storage.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_upcoming_smartphonoe_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_stock.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_stock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_upcoming_smartphonoe_stock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        edit_admin_add_upcoming_smartphonoe_rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_rating.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_rating.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_upcoming_smartphonoe_rating.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });

        edit_admin_add_upcoming_smartphonoe_discription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_discription.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_discription.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_upcoming_smartphonoe_discription.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });


        btn_admin_add_upcoming_smartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_add_upcoming_smartphone_progressbar.setVisibility(ProgressBar.VISIBLE);
                smartphone_Name = edit_admin_add_upcoming_smartphonoe_name.getText().toString();
                smartphone_Price = edit_admin_add_upcoming_smartphonoe_price.getText().toString();
                smartphone_storage = edit_admin_add_upcoming_smartphonoe_storage.getText().toString();
                smartphone_rating = edit_admin_add_upcoming_smartphonoe_rating.getText().toString();
                smartphone_discription = edit_admin_add_upcoming_smartphonoe_discription.getText().toString();
                smartphone_summary = edit_admin_add_upcoming_smartphonoe_summary.getText().toString();
                smartphone_stock = edit_admin_add_upcoming_smartphonoe_stock.getText().toString();


                if(edit_admin_add_upcoming_smartphonoe_name.getText().toString().equals("") || edit_admin_add_upcoming_smartphonoe_price.getText().toString().equals("") || edit_admin_add_upcoming_smartphonoe_storage.getText().toString().equals("") || edit_admin_add_upcoming_smartphonoe_stock.getText().toString().equals("")  || edit_admin_add_upcoming_smartphonoe_rating.getText().toString().equals("") || edit_admin_add_upcoming_smartphonoe_discription.getText().toString().equals("") || edit_admin_add_upcoming_smartphonoe_summary.getText().toString().equals("") || imagePath == null){
                    flag=1;

                    if(edit_admin_add_upcoming_smartphonoe_name.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_upcoming_smartphonoe_price.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_price.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_price.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_upcoming_smartphonoe_storage.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_storage.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_storage.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_upcoming_smartphonoe_stock.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_stock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_stock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_upcoming_smartphonoe_rating.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_rating.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_rating.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_upcoming_smartphonoe_discription.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_discription.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_discription.setBackgroundResource(R.drawable.green_border);
                    }

                    if(edit_admin_add_upcoming_smartphonoe_summary.getText().toString().equals("")){
                        edit_admin_add_upcoming_smartphonoe_summary.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_upcoming_smartphonoe_summary.setBackgroundResource(R.drawable.green_border);
                    }


                    if(imagePath == null && !edit_admin_add_upcoming_smartphonoe_name.getText().toString().equals("") && !edit_admin_add_upcoming_smartphonoe_price.getText().toString().equals("") && !edit_admin_add_upcoming_smartphonoe_storage.getText().toString().equals("") && !edit_admin_add_upcoming_smartphonoe_stock.getText().toString().equals("") && edit_admin_add_upcoming_smartphonoe_rating.getText().toString().equals("") && edit_admin_add_upcoming_smartphonoe_discription.getText().toString().equals("") && edit_admin_add_upcoming_smartphonoe_summary.getText().toString().equals("")){
                        txt_add_upcoming_smartphone_error.setText("Please select the image");
                        txt_add_upcoming_smartphone_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_upcoming_smartphone_progressbar.setVisibility(View.GONE);

                }else{
                    dbRef = FirebaseDatabase.getInstance().getReference("Upcoming Phones");

                    // Check if number of day deals is less than 4 before allowing user to add a new deal
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // If less than 4 day deals, proceed with adding new deal
                            flag = 0;

                            storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Upcoming Phones");
                            store = storage.child(smartphone_Name);
                            smartphone_id = dbRef.push().getKey();

                            store.putFile(imagePath)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    Upcoming_phones upcoming_phones = new Upcoming_phones(smartphone_id,uri.toString(), smartphone_Name , smartphone_Price , smartphone_storage , smartphone_rating , smartphone_discription , smartphone_summary , smartphone_stock);

                                                    dbRef.child(smartphone_id).setValue(upcoming_phones);

                                                    txt_add_upcoming_smartphone_error.setText("Phone Added Successfully");
                                                    txt_add_upcoming_smartphone_error.setVisibility(View.VISIBLE);

                                                    handler_error.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            txt_add_upcoming_smartphone_error.setVisibility(View.GONE);
                                                        }
                                                    }, 5000);

                                                    admin_add_upcoming_smartphone_progressbar.setVisibility(View.GONE);
                                                    edit_admin_add_upcoming_smartphonoe_name.setText("");
                                                    edit_admin_add_upcoming_smartphonoe_price.setText("");
                                                    edit_admin_add_upcoming_smartphonoe_storage.setText("");
                                                    edit_admin_add_upcoming_smartphonoe_rating.setText("");
                                                    edit_admin_add_upcoming_smartphonoe_discription.setText("");
                                                    edit_admin_add_upcoming_smartphonoe_summary.setText("");
                                                    edit_admin_add_upcoming_smartphonoe_stock.setText("");


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
        if(result==RESULT_OK && reqCode==202)
        {
            imagePath = data.getData();
            admin_add_upcoming_smartphone_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_upcoming_smartphone_error.setText("Image : "+imagePath.toString());
            txt_add_upcoming_smartphone_error.setVisibility(View.VISIBLE);


        }


    }


}