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
import com.example.shopease.model.Deals;
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

public class Delete_day_deals extends AppCompatActivity {

    Button btn_admin_delete_day_deals;
    ImageView btn_admin_Delete_day_deals_back;
    Spinner spinner_admin_delete_day_deals;
    TextView txt_delete_day_deals_error;
    ProgressBar admin_delete_day_deals_progressbar;

    List<String> list_delete_day_deals_name = new ArrayList<String>();
    List<Deals> list_deals = new ArrayList<Deals>();

    DatabaseReference dbRef;
    StorageReference storage;

    String deals_id, deals_Url, deals_Name;

    Handler handler_error;



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_day_deals);

        btn_admin_Delete_day_deals_back = findViewById(R.id.btn_admin_Delete_day_deals_back);
        btn_admin_delete_day_deals = findViewById(R.id.btn_admin_delete_day_deals);
        spinner_admin_delete_day_deals = findViewById(R.id.spinner_admin_delete_day_deals);

        txt_delete_day_deals_error = findViewById(R.id.txt_delete_day_deals_error);
        txt_delete_day_deals_error.setVisibility(View.GONE);

        admin_delete_day_deals_progressbar = findViewById(R.id.admin_delete_day_deals_progressbar);
        admin_delete_day_deals_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


        dbRef = FirebaseDatabase.getInstance().getReference("Day Deals");
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Day Deals");


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list_delete_day_deals_name.clear();
                list_deals.clear();

                for(DataSnapshot shot : snapshot.getChildren())
                {
                    Deals deals = shot.getValue(Deals.class);

                    list_delete_day_deals_name.add(deals.getDeals_name());
                    list_deals.add(deals);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_day_deals_name);
                spinner_admin_delete_day_deals.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_admin_Delete_day_deals_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_admin_home = new Intent(Delete_day_deals.this , Admin_Home.class);
                startActivity(intent_admin_home);
                finish();
            }
        });



        spinner_admin_delete_day_deals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                deals_id = list_deals.get(position).getDeals_id();
                deals_Name = list_delete_day_deals_name.get(position); // listObj.get(position).getgName();
                deals_Url = list_deals.get(position).getImageUrl();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btn_admin_delete_day_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_delete_day_deals_progressbar.setVisibility(View.VISIBLE);

                Query delQ = dbRef.orderByChild("deals_id").equalTo(deals_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot snap : snapshot.getChildren()){


                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(deals_Name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_day_deals_error.setText("Deal Removed Successfully");
                                    txt_delete_day_deals_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_day_deals_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_day_deals_progressbar.setVisibility(ProgressBar.GONE);
                                }
                            });

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });



    }
}