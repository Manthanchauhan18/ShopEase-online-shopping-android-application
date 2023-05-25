package com.example.shopease.Admin_Section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Delete_Product extends AppCompatActivity {

//    initialize object
    EditText etAdDelname, etAdDelprice;
    Button btAdDelProduct;
    ProgressBar pbAdDel;
    Spinner spAdDel;

    List<String> listName = new ArrayList<String>();
    List<Products> listObj = new ArrayList<Products>();

    ImageView btn_admin_Delete_product_back;

    DatabaseReference dbRef;
    StorageReference storage;

    String gid, gUrl, gName, measure;
    int price, stock;

    TextView txt_delete_product_error;
    Handler handler_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);


//        connect objects with xml file ids
        etAdDelname = findViewById(R.id.etAdDelname);
        etAdDelprice = findViewById(R.id.etAdDelprice);

        btAdDelProduct= findViewById(R.id.btAdDelProduct);
        btn_admin_Delete_product_back = findViewById(R.id.btn_admin_Delete_product_back);

        pbAdDel = findViewById(R.id.PbAdDel);
        pbAdDel.setVisibility(ProgressBar.GONE);

        spAdDel = findViewById(R.id.SpAdDel);

        txt_delete_product_error = findViewById(R.id.txt_delete_product_error);
        txt_delete_product_error.setVisibility(View.GONE);

        handler_error = new Handler();


//        if click on back button then return to admin home
        btn_admin_Delete_product_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_product = new Intent(Delete_Product.this , Admin_Home.class);
                startActivity(intent_delete_product);
                finish();
            }
        });

//        connect to firebase product section
        dbRef = FirebaseDatabase.getInstance().getReference("Products");
//        connect to firebase storage for image storing
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com");

        //get name and product object from firebase and add into according list
        dbRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                listName.clear();
                listObj.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Products products = shot.getValue(Products.class);

                    listName.add(products.getgName());
                    listObj.add(products);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listName);
                spAdDel.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        add name of the product into spinner and set text to
        spAdDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                gid = listObj.get(position).getGid();
                gName = listName.get(position); // listObj.get(position).getgName();
                gUrl = listObj.get(position).getImageUrl();
                price = listObj.get(position).getPrice();


                etAdDelname.setText("" + listName.get(position));
                etAdDelprice.setText("" + price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if click on delete product then the details of the product delete from the firebase also
        btAdDelProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pbAdDel.setVisibility(ProgressBar.VISIBLE);
                dbRef = FirebaseDatabase.getInstance().getReference("Products");
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com");

//                if id of the product and the product id at firebase match
                Query delQ = dbRef.orderByChild("gid").equalTo(gid);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {
                            Products g1 = snap.getValue(Products.class);

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(gName);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_product_error.setText("Product Removed Successfully");
                                    txt_delete_product_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_product_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    pbAdDel.setVisibility(ProgressBar.GONE);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });

            }
        });





    }
}