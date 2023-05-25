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
import com.example.shopease.model.Electronics;
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

public class Delete_electronic extends AppCompatActivity {

    ImageView btn_admin_Delete_electronics_back;
    Spinner spinner_admin_delete_electronics;
    Button btn_admin_delete_electronics;
    ProgressBar admin_delete_electronics_progressbar;
    TextView txt_delete_electronics_error;


    List<String> list_electronics_name = new ArrayList<String>();
    List<Electronics> list_electronics = new ArrayList<Electronics>();

    DatabaseReference dbRef;
    StorageReference storage;

    String electronics_id,electronics_imageUrl , electronics_name;

    TextView txt_electronics_error;
    Handler handler_error;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_electronic);

        btn_admin_Delete_electronics_back = findViewById(R.id.btn_admin_Delete_electronics_back);
        spinner_admin_delete_electronics = findViewById(R.id.spinner_admin_delete_electronics);
        btn_admin_delete_electronics = findViewById(R.id.btn_admin_delete_electronics);

        admin_delete_electronics_progressbar = findViewById(R.id.admin_delete_electronics_progressbar);
        admin_delete_electronics_progressbar.setVisibility(View.GONE);

        txt_delete_electronics_error = findViewById(R.id.txt_delete_electronics_error);
        txt_delete_electronics_error.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Electronics");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Electronics");



//        if delete offer back button press then return to admin home
        btn_admin_Delete_electronics_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_offer_admin_home = new Intent(Delete_electronic.this , Admin_Home.class);
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
                list_electronics_name.clear();
                list_electronics.clear();


                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Electronics electronics = shot.getValue(Electronics.class);

                    list_electronics_name.add(electronics.getElectronics_name());
                    list_electronics.add(electronics);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_electronics_name);
                spinner_admin_delete_electronics.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        set offer_name on spinner
        spinner_admin_delete_electronics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                electronics_id = list_electronics.get(position).getElectronics_id();
                electronics_name = list_electronics_name.get(position); // listObj.get(position).getgName();
                electronics_imageUrl = list_electronics.get(position).getImageUrl_electronics();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_electronics_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Electronics");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Electronics");

                Query delQ = dbRef.orderByChild("electronics_id").equalTo(electronics_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {
                            Electronics g1 = snap.getValue(Electronics.class);

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(electronics_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_electronics_error.setText("Electronic Product Removed Successfully");
                                    txt_delete_electronics_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_electronics_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_electronics_progressbar.setVisibility(ProgressBar.GONE);
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