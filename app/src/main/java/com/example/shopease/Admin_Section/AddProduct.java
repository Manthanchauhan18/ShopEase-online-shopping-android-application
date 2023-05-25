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
import com.example.shopease.model.Products;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddProduct extends AppCompatActivity {

//    initailize object
    EditText etAdaddname,etAdaddprice,etAdaddstock;
    Button btAdaddimage,btAdadditem;
    ProgressBar pbAdadd;
    Uri imagePath;
    DatabaseReference dbRef;
    StorageReference storage,store;
    String gName;
    int price,stock;
    String name , price_check , stock_check;
    String gid;
    ImageView btn_admin_add_product_back;
    TextView txt_add_product_error;

    Handler handler_error;
    int flag=0;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


//        connect object with xml file ids
        etAdaddname = findViewById(R.id.etAdaddname);
        etAdaddprice = findViewById(R.id.etAdaddprice);
        etAdaddstock = findViewById(R.id.etAdaddstock);

        btAdaddimage = findViewById(R.id.btAdaddimage);
        btAdadditem = findViewById(R.id.btAdadditem);

        pbAdadd = findViewById(R.id.pbAdadd);
        pbAdadd.setVisibility(ProgressBar.GONE);

        txt_add_product_error = findViewById(R.id.txt_add_product_error);
        txt_add_product_error.setVisibility(View.GONE);

//        handler declaration for error message
        handler_error = new Handler();

        btn_admin_add_product_back = findViewById(R.id.btn_admin_add_product_back);

        //if click on add image then go to system service of sleect image from gallery of the phone
        btAdaddimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);//for calling gallery
                startActivityForResult(gallery, 201);
                pbAdadd.setVisibility(View.VISIBLE);
            }
        });

//        if click on add product back button then return admin home page
        btn_admin_add_product_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add_product = new Intent(AddProduct.this , Admin_Home.class);
                startActivity(intent_add_product);
                finish();
            }
        });


//        check if name focus change then the input is valid or not
        etAdaddname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                    flag=1;

                    name = etAdaddname.getText().toString();

                    if(name.equals("")){
                        etAdaddname.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        etAdaddname.setBackgroundResource(R.drawable.green_border);
                        flag=0;

                    }

                }


            }

        });

//        check if price focus change then the input is valid or not
        etAdaddprice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(etAdaddprice.getText().toString().equals("")){
                        etAdaddprice.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        etAdaddprice.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }



                }

            }
        });

//        check if stock focus change then the input is valid or not
        etAdaddstock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    flag=1;

                    if(etAdaddstock.getText().toString().equals("")){
                        etAdaddstock.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        etAdaddstock.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }

                }
            }
        });




//        if click on add product button then go to firebase product section and save the details of the product
        btAdadditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pbAdadd.setVisibility(ProgressBar.VISIBLE);

                name = etAdaddname.getText().toString();

//                if add button press then check the all feilds are filled or not and flag valur change
                if(name.equals("") || etAdaddprice.getText().toString().equals("") || etAdaddstock.getText().toString().equals("") || imagePath == null){
                    flag=1;
                    txt_add_product_error.setText("Plase fil the details");
                    txt_add_product_error.setVisibility(View.VISIBLE);

                    if(name.equals("")){
                        etAdaddname.setBackgroundResource(R.drawable.red_border);
                    }else{
                        etAdaddname.setBackgroundResource(R.drawable.green_border);
                    }

                    if(etAdaddprice.getText().toString().equals("")){
                        etAdaddprice.setBackgroundResource(R.drawable.red_border);
                    }else{
                        etAdaddprice.setBackgroundResource(R.drawable.green_border);
                    }

                    if(etAdaddstock.getText().toString().equals("")){
                        etAdaddstock.setBackgroundResource(R.drawable.red_border);
                    }else{
                        etAdaddstock.setBackgroundResource(R.drawable.green_border);
                    }

                    if(imagePath == null){
                        txt_add_product_error.setText("Please Select Image");
                        txt_add_product_error.setVisibility(View.VISIBLE);
                    }
                    pbAdadd.setVisibility(View.GONE);

                }


//                if there is no correctin then flag value change to zero and all the data add into firebase
                if(flag==0){


//                get inputs from name price and stock edittext
                    gName=etAdaddname.getText().toString();
                    price=Integer.parseInt(etAdaddprice.getText().toString());
                    stock=Integer.parseInt(etAdaddstock.getText().toString());

//                connect to firebase product section and the image is store in to firebase storage section
                    dbRef= FirebaseDatabase.getInstance().getReference("Products");
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com");


                    store=storage.child(gName);
                    gid=dbRef.push().getKey();

                    //if image selected then add all the details of the product to firebase product sectiion
                    store.putFile(imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Products p=new Products(gid,uri.toString(),gName,price,stock);

                                            dbRef.child(gid).setValue(p);

                                            txt_add_product_error.setText("Product Added Successfully");
                                            txt_add_product_error.setVisibility(View.VISIBLE);

//                                            hold the text for 5 second after it it will be not visible
                                            handler_error.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    txt_add_product_error.setVisibility(View.GONE);

                                                }
                                            },5000);


                                            pbAdadd.setVisibility(View.GONE);


                                            etAdaddprice.setText("");
                                            etAdaddname.setText("");
                                            etAdaddstock.setText("");

                                        }
                                    });
                                }
                            });



                }else{
                    pbAdadd.setVisibility(View.GONE);
//                    txt_add_product_error.setText("Plase fill the details");
//                    txt_add_product_error.setVisibility(View.VISIBLE);
                }



            }
        });



    }

    //for opening the system service for gallery to select the photo
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int reqCode,int result,Intent data)
    {
        if(result==RESULT_OK && reqCode==201)
        {
            imagePath = data.getData();
            pbAdadd.setVisibility(View.GONE);

//            if image selcted then flag value change to zero
            flag=0;
            txt_add_product_error.setText("Image : "+imagePath.toString());
            txt_add_product_error.setVisibility(View.VISIBLE);

        }


    }




}