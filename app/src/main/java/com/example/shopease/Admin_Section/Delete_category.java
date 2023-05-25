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
import com.example.shopease.model.Category;
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

public class Delete_category extends AppCompatActivity {

    ImageView btn_admin_Delete_cetegory_back;
    Spinner spinner_admin_delete_category;
    TextView txt_delete_category_error;
    Button btn_admin_delete_category;
    ProgressBar admin_delete_category_progressbar;

    List<String> list_delete_category_name = new ArrayList<String>();
    List<Category> list_category = new ArrayList<Category>();

    DatabaseReference dbRef;
    StorageReference storage;

    String category_id,category_name,category_imageurl;

    Handler handler_error;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        btn_admin_Delete_cetegory_back = findViewById(R.id.btn_admin_Delete_cetegory_back);
        spinner_admin_delete_category = findViewById(R.id.spinner_admin_delete_category);
        btn_admin_delete_category = findViewById(R.id.btn_admin_delete_category);

        txt_delete_category_error = findViewById(R.id.txt_delete_category_error);
        txt_delete_category_error.setVisibility(View.GONE);

        admin_delete_category_progressbar = findViewById(R.id.admin_delete_category_progressbar);
        admin_delete_category_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Category");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Category");



//        if delete offer back button press then return to admin home
        btn_admin_Delete_cetegory_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_offer_admin_home = new Intent(Delete_category.this , Admin_Home.class);
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
                list_delete_category_name.clear();
                list_category.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Category category = shot.getValue(Category.class);

                    list_delete_category_name.add(category.getCategory_name());
                    list_category.add(category);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_category_name);
                spinner_admin_delete_category.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        set offer_name on spinner
        spinner_admin_delete_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                category_id = list_category.get(position).getCategory_id();
                category_name = list_delete_category_name.get(position); // listObj.get(position).getgName();
                category_imageurl = list_category.get(position).getImageUrl();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_category_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Category");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Category");

                Query delQ = dbRef.orderByChild("category_id").equalTo(category_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(category_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_category_error.setText("Category Removed Successfully");
                                    txt_delete_category_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_category_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_category_progressbar.setVisibility(ProgressBar.GONE);
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