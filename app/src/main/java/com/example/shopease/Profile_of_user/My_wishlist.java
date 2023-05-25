package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.Adapter.Cart_product_layout_card_adapter;
import com.example.shopease.Adapter.Profile_product_card_adapater;
import com.example.shopease.R;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.model.Wishlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class My_wishlist extends Fragment {

    ImageView btn_profile_wishlist_back_image;
    TextView txt_profile_wishlist_username;

    CardView user_product_empty_wishlist_cardview;

    RecyclerView profile_wishlist_recycler_view;
    ArrayList<String> wishlist_name = new ArrayList<String>();
    ArrayList<String> wishlist_price = new ArrayList<String>();
    ArrayList<String> wishlist_rating = new ArrayList<String>();
    ArrayList<String> wishlist_stock = new ArrayList<String>();
    ArrayList<Wishlist> wishlist_list;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        btn_profile_wishlist_back_image = root.findViewById(R.id.btn_profile_wishlist_back_image);
        user_product_empty_wishlist_cardview = root.findViewById(R.id.user_product_empty_wishlist_cardview);
        profile_wishlist_recycler_view = root.findViewById(R.id.profile_wishlist_recycler_view);

        btn_profile_wishlist_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });


        // Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

//       Get the current user's ID and use it to create a child node for the user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = database.getReference("User").child(userId);

        // Get a reference to the "saved addresses" node under the user's node
        DatabaseReference wishlist_ref = userRef.child("Wishlist");

        LinearLayoutManager layoutManager_cart = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // Set the LayoutManager of the RecyclerView
        profile_wishlist_recycler_view.setLayoutManager(layoutManager_cart);
        profile_wishlist_recycler_view.setItemAnimator(new DefaultItemAnimator());

        wishlist_list = new ArrayList<Wishlist>();
        profile_wishlist_recycler_view.setHasFixedSize(true);

        wishlist_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot_cart : dataSnapshot.getChildren()) {
                    Wishlist wishlist = snapshot_cart.getValue(Wishlist.class);
                    wishlist_list.add(wishlist);

                    if(!wishlist_list.equals(null)){
                        user_product_empty_wishlist_cardview.setVisibility(View.GONE);
                    }

                }
                profile_wishlist_recycler_view.setAdapter(new Profile_product_card_adapater(wishlist_list, getActivity(),wishlist_name,wishlist_price,wishlist_rating));
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