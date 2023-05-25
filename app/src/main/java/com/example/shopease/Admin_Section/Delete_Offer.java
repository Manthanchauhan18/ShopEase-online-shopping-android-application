package com.example.shopease.Admin_Section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shopease.R;
import com.example.shopease.model.Offers;
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

public class Delete_Offer extends AppCompatActivity {

//    initialize the object
    ImageView btn_admin_Delete_Offer_back;
    Spinner spinner_admin_delete_offer;
    Button btn_admin_delete_Offer;
    ProgressBar admin_delete_offer_progressbar;

    List<String> list_delete_offer_name = new ArrayList<String>();
    List<Offers> listOffers = new ArrayList<Offers>();

    DatabaseReference dbRef;
    StorageReference storage;

    String gid, gUrl, gName;

    TextView txt_delete_offer_error;
    Handler handler_error;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_offer);

//        connet object with xml file ids
        btn_admin_Delete_Offer_back = findViewById(R.id.btn_admin_Delete_Offer_back);
        spinner_admin_delete_offer = findViewById(R.id.spinner_admin_delete_offer);
        btn_admin_delete_Offer = findViewById(R.id.btn_admin_delete_Offer);


        admin_delete_offer_progressbar = findViewById(R.id.admin_delete_offer_progressbar);
        admin_delete_offer_progressbar.setVisibility(View.GONE);

        txt_delete_offer_error = findViewById(R.id.txt_delete_offer_error);
        txt_delete_offer_error.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Offers");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Offers");



//        if delete offer back button press then return to admin home
        btn_admin_Delete_Offer_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_offer_admin_home = new Intent(Delete_Offer.this , Admin_Home.class);
                startActivity(intent_delete_offer_admin_home);
                finish();
            }
        });


//        get offer name and add into offers_name list
        dbRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                list_delete_offer_name.clear();
                listOffers.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Offers offers = shot.getValue(Offers.class);

                    list_delete_offer_name.add(offers.getgName());
                    listOffers.add(offers);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_offer_name);
                spinner_admin_delete_offer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        set offer_name on spinner
        spinner_admin_delete_offer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                gid = listOffers.get(position).getGid();
                gName = list_delete_offer_name.get(position); // listObj.get(position).getgName();
                gUrl = listOffers.get(position).getImageUrl();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_Offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_offer_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Offers");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Offers");

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
                                    txt_delete_offer_error.setText("Offer Removed Successfully");
                                    txt_delete_offer_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_offer_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_offer_progressbar.setVisibility(ProgressBar.GONE);
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





    }//on create close
}//main class close