package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.AppLaunchChecker;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopease.Adapter.Category_appliance_card_adapter;
import com.example.shopease.Adapter.category_mobile_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Appliance;
import com.example.shopease.model.Upcoming_phones;
import com.example.shopease.ui.Home.HomeFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_Appliance extends Fragment {

    ImageView btn_user_appliance_category_back_image;
    DatabaseReference dbref;
    ArrayList<String> appliance_name = new ArrayList<String>();
    ArrayList<String> appliance_price = new ArrayList<String>();
    ArrayList<Appliance> appliance_list;
    RecyclerView user_appliance_category_recycler_view;

    SearchView category_appliance_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category__appliance, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category__appliance, container, false);

        user_appliance_category_recycler_view = themedView.findViewById(R.id.user_applicance_category_recycler_view);

        btn_user_appliance_category_back_image = themedView.findViewById(R.id.btn_user_appliance_category_back_image);
        category_appliance_searchview = themedView.findViewById(R.id.category_appliance_searchview);

        btn_user_appliance_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Appliances");

        user_appliance_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_appliance_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        appliance_list = new ArrayList<Appliance>();
        user_appliance_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appliance appliance = snapshot.getValue(Appliance.class);
                    appliance_list.add(appliance);
                }
                user_appliance_category_recycler_view.setAdapter(new Category_appliance_card_adapter(appliance_list, getActivity(),appliance_name,appliance_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

//        FirebaseApp.initializeApp(getActivity()); // If not already initialized
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Appliances");

        category_appliance_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (appliance_list != null) {
                    ArrayList<Appliance> filteredList_appliance = new ArrayList<>();
                    for (Appliance appliance : appliance_list) {
                        if (appliance.getAppliance_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_appliance.add(appliance);
                        }
                    }
                    user_appliance_category_recycler_view.setAdapter(new Category_appliance_card_adapter(filteredList_appliance, getActivity(),appliance_name,appliance_price));
                }
                return true;
            }

        });




        return themedView;
    }
}