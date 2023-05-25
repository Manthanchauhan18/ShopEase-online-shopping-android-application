package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.Adapter.saved_address_card_view;
import com.example.shopease.R;
import com.example.shopease.model.Address;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Saved_addresses extends Fragment {

    TextView txt_profile_saved_address_new_address;
    ImageView btn_admin_saved_address_back;

    RecyclerView saved_address_recycler_view;

    DatabaseReference dbref_address;
    ArrayList<Address> address_list;

    ArrayList<String> address_name = new ArrayList<String>();
    ArrayList<String> address_mobile = new ArrayList<String>();
    ArrayList<String> address_address = new ArrayList<String>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_saved_addresses, container, false);

        txt_profile_saved_address_new_address = root.findViewById(R.id.txt_profile_saved_address_new_address);
        btn_admin_saved_address_back = root.findViewById(R.id.btn_admin_saved_address_back);
        saved_address_recycler_view = root.findViewById(R.id.saved_address_recycler_view);

        btn_admin_saved_address_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        txt_profile_saved_address_new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Add_new_address());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        // Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
        DatabaseReference savedAddressesRef = userRef.child("Saved_Addresses");

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        saved_address_recycler_view.setLayoutManager(layoutManager2);

        saved_address_recycler_view.setItemAnimator(new DefaultItemAnimator());

        address_list = new ArrayList<Address>();
        saved_address_recycler_view.setHasFixedSize(true);

        savedAddressesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Address address_model = snapshot.getValue(Address.class);
                    address_list.add(address_model);
                }
                saved_address_recycler_view.setAdapter(new saved_address_card_view(address_list, getActivity(),address_name,address_mobile,address_address));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        // Inflate the layout for this fragment
        return root;
    }
}