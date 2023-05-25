package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class Saved_wallet_and_cards extends Fragment {

    ImageView txt_profile_saved_cards_back_image;
    TextView txt_profile_saved_cards_discription,txt_profile_saved_cards_upi_discription;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_saved_wallet_and_cards, container, false);

        txt_profile_saved_cards_back_image = root.findViewById(R.id.txt_profile_saved_cards_back_image);
        txt_profile_saved_cards_discription = root.findViewById(R.id.txt_profile_saved_cards_discription);
        txt_profile_saved_cards_upi_discription = root.findViewById(R.id.txt_profile_saved_cards_upi_discription);


        txt_profile_saved_cards_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });

        txt_profile_saved_cards_discription.setText("Save your Credit/Debit Cards for faster payments.\nYour Cards are Secure with us");
        txt_profile_saved_cards_upi_discription.setText("No UPIs saved to be shown");



        // Inflate the layout for this fragment
        return root;
    }
}