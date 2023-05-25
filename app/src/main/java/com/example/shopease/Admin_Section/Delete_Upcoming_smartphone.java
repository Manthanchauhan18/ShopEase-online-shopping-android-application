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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shopease.R;
import com.example.shopease.model.Upcoming_phones;
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

public class Delete_Upcoming_smartphone extends AppCompatActivity {

    ImageView btn_admin_Delete_upcoming_smartphone_back;
    Spinner spinner_admin_delete_upcomming_smartphone;
    Button btn_admin_delete_upcoming_smartphone;
    ProgressBar admin_delete_upcoming_smartphone_progressbar;
    TextView txt_delete_upcoming_smartphone_error;


    List<String> list_delete_phone_name = new ArrayList<String>();
    List<Upcoming_phones> list_phones = new ArrayList<Upcoming_phones>();

    DatabaseReference dbRef;
    StorageReference storage;

    String phone_id,phone_imageUrl , phone_name;

    TextView txt_delete_offer_error;
    Handler handler_error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_upcoming_smartphone);

        btn_admin_Delete_upcoming_smartphone_back = findViewById(R.id.btn_admin_Delete_upcoming_smartphone_back);
        spinner_admin_delete_upcomming_smartphone = findViewById(R.id.spinner_admin_delete_upcomming_smartphone);
        btn_admin_delete_upcoming_smartphone = findViewById(R.id.btn_admin_delete_upcoming_smartphone);

        admin_delete_upcoming_smartphone_progressbar = findViewById(R.id.admin_delete_upcoming_smartphone_progressbar);
        admin_delete_upcoming_smartphone_progressbar.setVisibility(View.GONE);

        txt_delete_upcoming_smartphone_error = findViewById(R.id.txt_delete_upcoming_smartphone_error);
        txt_delete_upcoming_smartphone_error.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Upcoming Phones");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Upcoming Phones");



//        if delete offer back button press then return to admin home
        btn_admin_Delete_upcoming_smartphone_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete_offer_admin_home = new Intent(Delete_Upcoming_smartphone.this , Admin_Home.class);
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
                list_delete_phone_name.clear();
                list_phones.clear();


                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Upcoming_phones phones = shot.getValue(Upcoming_phones.class);

                    list_delete_phone_name.add(phones.getSmartphone_name());
                    list_phones.add(phones);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_phone_name);
                spinner_admin_delete_upcomming_smartphone.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        set offer_name on spinner
        spinner_admin_delete_upcomming_smartphone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                phone_id = list_phones.get(position).getPhone_id();
                phone_name = list_delete_phone_name.get(position); // listObj.get(position).getgName();
                phone_imageUrl = list_phones.get(position).getImageUrl_phone();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_upcoming_smartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_upcoming_smartphone_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Upcoming Phones");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Upcoming Phones");

                Query delQ = dbRef.orderByChild("phone_id").equalTo(phone_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {
                            Upcoming_phones g1 = snap.getValue(Upcoming_phones.class);

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(phone_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_upcoming_smartphone_error.setText("Phone Removed Successfully");
                                    txt_delete_upcoming_smartphone_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_upcoming_smartphone_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_upcoming_smartphone_progressbar.setVisibility(ProgressBar.GONE);
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