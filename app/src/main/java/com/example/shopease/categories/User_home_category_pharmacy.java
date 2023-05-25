package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopease.Adapter.Category_grocery_card_adapter;
import com.example.shopease.Adapter.Category_pharmacy_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Pharmacy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_pharmacy extends Fragment {

    ImageView btn_user_pharmacy_category_back_image;

    RecyclerView user_pharmacy_category_recycler_view;
    DatabaseReference dbref;
    ArrayList<String> pharmacy_name = new ArrayList<String>();
    ArrayList<String> pharmacy_price = new ArrayList<String>();
    ArrayList<Pharmacy> pharmacy_list;

    SearchView category_pharmacy_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_pharmacy, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_pharmacy, container, false);

        user_pharmacy_category_recycler_view = themedView.findViewById(R.id.user_pharmacy_category_recycler_view);

        btn_user_pharmacy_category_back_image = themedView.findViewById(R.id.btn_user_pharmacy_category_back_image);
        category_pharmacy_searchview = themedView.findViewById(R.id.category_pharmacy_searchview);

        btn_user_pharmacy_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Pharmacy");

        user_pharmacy_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_pharmacy_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        pharmacy_list = new ArrayList<Pharmacy>();
        user_pharmacy_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Pharmacy pharmacy = snapshot.getValue(Pharmacy.class);
                    pharmacy_list.add(pharmacy);
                }
                user_pharmacy_category_recycler_view.setAdapter(new Category_pharmacy_card_adapter(pharmacy_list, getActivity(),pharmacy_name,pharmacy_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        category_pharmacy_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (pharmacy_list != null) {
                    ArrayList<Pharmacy> filteredList_pharmacy = new ArrayList<>();
                    for (Pharmacy pharmacy: pharmacy_list) {
                        if (pharmacy.getPharmacy_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_pharmacy.add(pharmacy);
                        }
                    }
                    user_pharmacy_category_recycler_view.setAdapter(new Category_pharmacy_card_adapter(filteredList_pharmacy, getActivity(),pharmacy_name,pharmacy_price));
                }
                return true;
            }

        });


        return themedView;
    }
}