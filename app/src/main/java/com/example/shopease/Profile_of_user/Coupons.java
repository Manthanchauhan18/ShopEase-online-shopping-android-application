package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

public class Coupons extends Fragment {

    ImageView txt_profile_coupons_back_image;
    TextView txt_profile_coupons_username;

    CardView user_product_empty_coupons_cardview;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_coupons, container, false);

        txt_profile_coupons_back_image = root.findViewById(R.id.txt_profile_coupons_back_image);
        user_product_empty_coupons_cardview = root.findViewById(R.id.user_product_empty_coupons_cardview);


        txt_profile_coupons_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });


        // Inflate the layout for this fragment
        return root;
    }
}