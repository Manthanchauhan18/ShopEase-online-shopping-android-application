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
import com.example.shopease.model.Fitness;
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

public class Delete_fitness extends AppCompatActivity {

    ImageView btn_admin_Delete_fitness_back;
    Spinner spinner_admin_delete_fitness;
    TextView txt_delete_fitness_error;
    Button btn_admin_delete_fitness;
    ProgressBar admin_delete_fitness_progressbar;

    List<String> list_delete_fitness_name = new ArrayList<String>();
    List<Fitness> list_fitness = new ArrayList<Fitness>();

    DatabaseReference dbRef;
    StorageReference storage;

    String fitness_id,fitness_name,fitness_imageurl;

    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_fitness);

        btn_admin_Delete_fitness_back = findViewById(R.id.btn_admin_delete_fitness_back);
        spinner_admin_delete_fitness = findViewById(R.id.spinner_admin_delete_fitness);
        btn_admin_delete_fitness = findViewById(R.id.btn_admin_delete_fitness);

        txt_delete_fitness_error = findViewById(R.id.txt_delete_fitness_error);
        txt_delete_fitness_error.setVisibility(View.GONE);

        admin_delete_fitness_progressbar = findViewById(R.id.admin_delete_fitness_progressbar);
        admin_delete_fitness_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Fitness");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Fitness");


//        if delete offer back button press then return to admin home
        btn_admin_Delete_fitness_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_offer_admin_home = new Intent(Delete_fitness.this , Admin_Home.class);
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
                list_delete_fitness_name.clear();
                list_fitness.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Fitness fitness = shot.getValue(Fitness.class);

                    list_delete_fitness_name.add(fitness.getFitness_name());
                    list_fitness.add(fitness);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_fitness_name);
                spinner_admin_delete_fitness.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        set offer_name on spinner
        spinner_admin_delete_fitness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                fitness_id = list_fitness.get(position).getFitness_id();
                fitness_name = list_delete_fitness_name.get(position); // listObj.get(position).getgName();
                fitness_imageurl = list_fitness.get(position).getFitness_imageUrl();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_fitness_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Fitness");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Fitness");

                Query delQ = dbRef.orderByChild("fitness_id").equalTo(fitness_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(fitness_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_fitness_error.setText("Fitness Product Removed Successfully");
                                    txt_delete_fitness_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_fitness_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_fitness_progressbar.setVisibility(ProgressBar.GONE);
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