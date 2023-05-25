package com.example.shopease.Admin_Section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.shopease.Adapter.Category_card_adapter;
import com.example.shopease.Adapter.Show_user_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Category;
import com.example.shopease.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowUser extends AppCompatActivity {

    RecyclerView show_user_recycler_view;
    ImageView btn_admin_show_user_back;

    ArrayList<User> user_list;
    DatabaseReference dbref_show_user;

    ArrayList<String> user_email = new ArrayList<String>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        dbref_show_user = FirebaseDatabase.getInstance().getReference("User");

        show_user_recycler_view = findViewById(R.id.show_user_recycler_view);
        btn_admin_show_user_back = findViewById(R.id.btn_admin_show_user_back);

        show_user_recycler_view.setLayoutManager(new GridLayoutManager(this, 1));
        show_user_recycler_view.setItemAnimator(new DefaultItemAnimator());

        user_list = new ArrayList<>();
        show_user_recycler_view.setHasFixedSize(true);

        dbref_show_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user_model = snapshot.getValue(User.class);
                    user_list.add(user_model);
                }
                show_user_recycler_view.setAdapter(new Show_user_card_adapter(user_list, ShowUser.this,user_email));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        //if click on show users back button then return to admin home page
        btn_admin_show_user_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_admin_show_user = new Intent(ShowUser.this , Admin_Home.class);
                startActivity(intent_admin_show_user);
                finish();
            }
        });


    }
}