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
import com.example.shopease.model.Offers;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_Offer extends AppCompatActivity {

//    initialize the object
    ImageView btn_admin_add_offers_back;
    EditText edit_admin_add_offer_name;
    Button btn_admin_add_offers_select_image , btn_admin_add_offers;
    ProgressBar admin_add_offers_progressbar;

    Uri imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String gid;
    String gName;

    int flag=0;
    TextView txt_add_offer_error;
    Handler handler_error;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

//        connect the objects with xml file ids
        btn_admin_add_offers_back = findViewById(R.id.btn_admin_add_offers_back);
        edit_admin_add_offer_name = findViewById(R.id.edit_admin_add_offer_name);
        btn_admin_add_offers_select_image = findViewById(R.id.btn_admin_add_offers_select_image);
        btn_admin_add_offers = findViewById(R.id.btn_admin_add_offers);

        admin_add_offers_progressbar = findViewById(R.id.admin_add_offers_progressbar);
        admin_add_offers_progressbar.setVisibility(View.GONE);

        txt_add_offer_error = findViewById(R.id.txt_add_offer_error);
        txt_add_offer_error.setVisibility(View.GONE);
        handler_error = new Handler();


//        if click on add offer back button then return to admin home
        btn_admin_add_offers_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_admin_home = new Intent(Add_Offer.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();
            }
        });

//        if click on add offer select image then active the system gallery service and select image
        btn_admin_add_offers_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 201);
                admin_add_offers_progressbar.setVisibility(View.VISIBLE);
            }
        });


        edit_admin_add_offer_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(edit_admin_add_offer_name.getText().toString().equals("")){
                        edit_admin_add_offer_name.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        edit_admin_add_offer_name.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });




//        if click on add offer button then add offers details into firebase offer section
        btn_admin_add_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_add_offers_progressbar.setVisibility(ProgressBar.VISIBLE);

                gName = edit_admin_add_offer_name.getText().toString();

                if(edit_admin_add_offer_name.getText().toString().equals("") || imagePath == null){
                    flag=1;

                    if(edit_admin_add_offer_name.getText().toString().equals("")){
                        edit_admin_add_offer_name.setBackgroundResource(R.drawable.red_border);
                    }else{
                        edit_admin_add_offer_name.setBackgroundResource(R.drawable.green_border);
                    }

                    if(imagePath == null && !edit_admin_add_offer_name.getText().toString().equals("")){
                        txt_add_offer_error.setText("Please select the image");
                        txt_add_offer_error.setVisibility(View.VISIBLE);
                    }
                    admin_add_offers_progressbar.setVisibility(View.GONE);

                }



                if(flag==0){


//                connect to firebase offer section
                    dbRef= FirebaseDatabase.getInstance().getReference("Offers");
//                connect to firebase offers image storage
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Offers");

                    store=storage.child(gName);
                    gid=dbRef.push().getKey();

                    //if image path selected then add offers successfully into firebase
                    store.putFile(imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Offers offers=new Offers(gid,uri.toString(),gName);

                                            dbRef.child(gid).setValue(offers);

                                            txt_add_offer_error.setText("Offer Added Successfully");
                                            txt_add_offer_error.setVisibility(View.VISIBLE);


                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_offer_error.setVisibility(View.GONE);

                                                }
                                            },5000);

                                            admin_add_offers_progressbar.setVisibility(View.GONE);

                                            edit_admin_add_offer_name.setText("");

                                        }
                                    });
                                }
                            });
                }else{
                    admin_add_offers_progressbar.setVisibility(View.GONE);
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
        if(result==RESULT_OK && reqCode==201)
        {
            imagePath = data.getData();
            admin_add_offers_progressbar.setVisibility(View.GONE);

            flag=0;
            txt_add_offer_error.setText("Image : "+imagePath.toString());
            txt_add_offer_error.setVisibility(View.VISIBLE);


        }


    }

}