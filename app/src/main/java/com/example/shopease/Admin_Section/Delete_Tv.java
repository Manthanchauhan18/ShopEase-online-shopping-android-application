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
import com.example.shopease.model.Tv;
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

public class Delete_Tv extends AppCompatActivity {

    ImageView btn_admin_delete_tv_back;
    Spinner spinner_admin_delete_tv;
    TextView txt_delete_tv_error;
    Button btn_admin_delete_tv;
    ProgressBar admin_delete_tv_progressbar;

    List<String> list_delete_tv_name = new ArrayList<String>();
    List<Tv> list_tv = new ArrayList<Tv>();

    DatabaseReference dbRef_tv;
    StorageReference storage;

    String tv_id,tv_name,tv_imageurl;

    Handler handler_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tv);

        btn_admin_delete_tv_back = findViewById(R.id.btn_admin_delete_tv_back);
        spinner_admin_delete_tv = findViewById(R.id.spinner_admin_delete_tv);

        txt_delete_tv_error = findViewById(R.id.txt_delete_tv_error);
        txt_delete_tv_error.setVisibility(View.GONE);

        btn_admin_delete_tv = findViewById(R.id.btn_admin_delete_tv);

        admin_delete_tv_progressbar = findViewById(R.id.admin_delete_tv_progressbar);
        admin_delete_tv_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();

//        connect with firebase offers section
                dbRef_tv = FirebaseDatabase.getInstance().getReference("Tv");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Tv");


//        if delete offer back button press then return to admin home
        btn_admin_delete_tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_offer_admin_home = new Intent(Delete_Tv.this , Admin_Home.class);
                startActivity(intent_delete_offer_admin_home);
                finish();

            }
        });


//        get offer name and add into offers_name list
        dbRef_tv.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                list_delete_tv_name.clear();
                list_tv.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Tv tv = shot.getValue(Tv.class);

                    list_delete_tv_name.add(tv.getTv_name());
                    list_tv.add(tv);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_tv_name);
                spinner_admin_delete_tv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        set offer_name on spinner
        spinner_admin_delete_tv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                tv_id = list_tv.get(position).getTv_id();
                tv_name = list_delete_tv_name.get(position); // listObj.get(position).getgName();
                tv_imageurl = list_tv.get(position).getImageUrl_tv();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_tv_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef_tv = FirebaseDatabase.getInstance().getReference("Tv");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Tv");

                Query delQ = dbRef_tv.orderByChild("tv_id").equalTo(tv_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(tv_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_tv_error.setText("Tv Removed Successfully");
                                    txt_delete_tv_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_tv_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_tv_progressbar.setVisibility(ProgressBar.GONE);
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