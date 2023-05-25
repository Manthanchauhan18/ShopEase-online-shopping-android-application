package com.example.shopease.Admin_Section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shopease.R;
import com.example.shopease.model.Products;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Update_Product extends AppCompatActivity {

//    initialize the object
    EditText etAdUpname, etAdUpprice, etAdUpstock;
    Button btAdUpUpgrocey, btAdUpUpimage;
    ProgressBar pbAdUp;
    Spinner spAdUp;
    ImageView btn_admin_update_product_back;
    TextView txt_update_product_error;

    List<String> listName = new ArrayList<String>();
    List<Products> listObj = new ArrayList<Products>();

    DatabaseReference dbRef;
    StorageReference storage,store;

    String gid, gUrl, gName;
    int price, stock;

    Uri imagePath;

    Handler handler_error_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

//        connect the object with the xml file ids
        etAdUpname = findViewById(R.id.etAdUpname);
        etAdUpprice = findViewById(R.id.etAdUpprice);
        etAdUpstock = findViewById(R.id.etAdUpstock);

        btAdUpUpimage = findViewById(R.id.btAdUpUpimage);
        btAdUpUpgrocey = findViewById(R.id.btAdUpUpgrocery);

        btn_admin_update_product_back = findViewById(R.id.btn_admin_update_product_back);
        txt_update_product_error = findViewById(R.id.txt_update_product_error);
        txt_update_product_error.setVisibility(View.GONE);


        pbAdUp = findViewById(R.id.pbAdUp);
        pbAdUp.setVisibility(ProgressBar.GONE);

        spAdUp = findViewById(R.id.spAdUp);

//        handler declaration for error
        handler_error_message = new Handler();


//        connect with the firebase product section
        dbRef = FirebaseDatabase.getInstance().getReference("Products");
//        connect with firebase storage for product images
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com");


//        get name and the product object from database and add it into list
        dbRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                listName.clear();
                listObj.clear();

                for (DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Products products = shot.getValue(Products.class);

                    listName.add(products.getgName());
                    listObj.add(products);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listName);
                spAdUp.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

//        if back button press then return to admin home
        btn_admin_update_product_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home_screen = new Intent(Update_Product.this , Admin_Home.class);
                startActivity(intent_home_screen);
                finish();
            }
        });


//        display the name of the products on the spinner and the price and the stock of the product on price and stock editbox
        spAdUp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                gUrl = listObj.get(position).getImageUrl();
                gid = listObj.get(position).getGid();
                gName = listObj.get(position).getgName();
                price = listObj.get(position).getPrice();
                stock = listObj.get(position).getStock();

                etAdUpname.setText(gName);
                etAdUpprice.setText("" + price);
                etAdUpstock.setText("" + stock);

                pbAdUp.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if press on select image button then use system service and select photo from gallery
        btAdUpUpimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pbAdUp.setVisibility(View.VISIBLE);
                Intent image = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(image, 201);

//                Intent image = new Intent(Intent.ACTION_GET_CONTENT);
//                image.setType("image/*");
//                startActivityForResult(Intent.createChooser(image, "Select Image"), 201);
            }
        });

//        if update grocery button press then update the product details in firebase product section
        btAdUpUpgrocey.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pbAdUp.setVisibility(View.VISIBLE);

//                check for imagepath if it is null then it will updat only price and stock values
                if(imagePath == null){
                    dbRef = FirebaseDatabase.getInstance().getReference("Products");

                    price = Integer.parseInt(etAdUpprice.getText().toString());
                    stock = Integer.parseInt(etAdUpstock.getText().toString());

                    Products gr = new Products(gid, gUrl, gName, price, stock);

                    Handler handler_update = new Handler();
                    handler_update.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            dbRef.child(gid).setValue(gr);

                            txt_update_product_error.setText("Product Details Updated");
                            txt_update_product_error.setVisibility(View.VISIBLE);
                            txt_update_product_error.setTextColor(Color.BLACK);

                            handler_error_message.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_update_product_error.setVisibility(View.GONE);


                                }
                            },5000);

                            pbAdUp.setVisibility(ProgressBar.GONE);

                        }
                    },1000);


                }else{
//                    if the imagepath is not null then the image price and stock values are changed

                    dbRef = FirebaseDatabase.getInstance().getReference("Products");
                    storage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com");

                    price = Integer.parseInt(etAdUpprice.getText().toString());
                    stock = Integer.parseInt(etAdUpstock.getText().toString());

                    store=storage.child(gName);

//                    handler for hold give some delay into the proccess of updateing values
                    Handler handler_update_image = new Handler();
                    handler_update_image.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            store.putFile(imagePath)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            store.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    Products update=new Products(gid,uri.toString(),gName,price,stock);

                                                    dbRef.child(gid).setValue(update);

                                                    pbAdUp.setVisibility(View.GONE);
                                                    txt_update_product_error.setText("Product Details Updated");
                                                    txt_update_product_error.setVisibility(View.VISIBLE);
                                                    txt_update_product_error.setTextColor(Color.BLACK);

                                                    handler_error_message.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            txt_update_product_error.setVisibility(View.GONE);


                                                        }
                                                    },5000);


                                                }
                                            });

                                        }
                                    });


                        }
                    },500);


                }


            }
        });





    }

//    in result of the select image the image path return
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int reqCode, int res, Intent data)
    {
        if (res == RESULT_OK && reqCode == 201)
        {
            imagePath = data.getData();
            pbAdUp.setVisibility(View.GONE);

            txt_update_product_error.setText("Image : "+imagePath.toString());
            txt_update_product_error.setTextColor(Color.BLACK);
            txt_update_product_error.setVisibility(View.VISIBLE);



        }
    }

}