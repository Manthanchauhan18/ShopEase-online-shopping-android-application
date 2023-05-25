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
import com.example.shopease.model.Appliance;
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

public class Delete_Appliance extends AppCompatActivity {

    ImageView btn_admin_Delete_appliance_back;
    Spinner spinner_admin_delete_appliance;
    TextView txt_delete_appliance_error;
    Button btn_admin_delete_appliance;
    ProgressBar admin_delete_appliance_progressbar;

    List<String> list_delete_appliance_name = new ArrayList<String>();
    List<Appliance> list_appliance = new ArrayList<Appliance>();

    DatabaseReference dbRef;
    StorageReference storage;

    String appliance_id,appliance_name,appliance_imageurl;

    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appliance);

        btn_admin_Delete_appliance_back = findViewById(R.id.btn_admin_delete_appliance_back);
        spinner_admin_delete_appliance = findViewById(R.id.spinner_admin_delete_appliance);
        btn_admin_delete_appliance = findViewById(R.id.btn_admin_delete_appliance);

        txt_delete_appliance_error = findViewById(R.id.txt_delete_appliance_error);
        txt_delete_appliance_error.setVisibility(View.GONE);

        admin_delete_appliance_progressbar = findViewById(R.id.admin_delete_appliance_progressbar);
        admin_delete_appliance_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Appliances");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Appliances");


//        if delete offer back button press then return to admin home
        btn_admin_Delete_appliance_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_offer_admin_home = new Intent(Delete_Appliance.this , Admin_Home.class);
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
                list_delete_appliance_name.clear();
                list_appliance.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Appliance appliance = shot.getValue(Appliance.class);

                    list_delete_appliance_name.add(appliance.getAppliance_name());
                    list_appliance.add(appliance);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_appliance_name);
                spinner_admin_delete_appliance.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        set offer_name on spinner
        spinner_admin_delete_appliance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                appliance_id = list_appliance.get(position).getAppliance_id();
                appliance_name = list_delete_appliance_name.get(position); // listObj.get(position).getgName();
                appliance_imageurl = list_appliance.get(position).getImageUrl();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_appliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_appliance_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Appliances");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Appliances");

                Query delQ = dbRef.orderByChild("appliance_id").equalTo(appliance_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(appliance_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_appliance_error.setText("Appliance Removed Successfully");
                                    txt_delete_appliance_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_appliance_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_appliance_progressbar.setVisibility(ProgressBar.GONE);
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