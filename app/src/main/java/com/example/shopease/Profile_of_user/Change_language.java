package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Change_language extends Fragment {

    ImageView txt_profile_change_language_back_image;
    TextView txt_profile_change_language_username;

    String uid;
    FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_user;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_language, container, false);

        txt_profile_change_language_back_image = root.findViewById(R.id.txt_profile_change_language_back_image);
        txt_profile_change_language_username = root.findViewById(R.id.txt_profile_change_language_username);



//        get refrence of the user from firebase storage
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference_user = firebaseDatabase.getReference("User");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        //get name of the user from firebase user section
        databaseReference_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child(uid).child("name").getValue(String.class);
                txt_profile_change_language_username.setText("Hello, "+name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txt_profile_change_language_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });


        // Inflate the layout for this fragment
        return root;
    }
}