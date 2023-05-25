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

import com.example.shopease.Adapter.Category_appliance_card_adapter;
import com.example.shopease.Adapter.Deals_of_the_day_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Appliance;
import com.example.shopease.model.Deals;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_deals_of_the_day extends Fragment {

    ImageView btn_user_deals_of_the_day_back_image;
    DatabaseReference dbref;
    ArrayList<String> deals_of_the_day_name = new ArrayList<String>();
    ArrayList<String> deals_of_the_day_price = new ArrayList<String>();
    ArrayList<Deals> deals_of_the_day_list;
    RecyclerView user_deals_of_the_day_recycler_view;

    SearchView deals_of_the_day_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_deals_of_the_day, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_deals_of_the_day, container, false);


        user_deals_of_the_day_recycler_view = themedView.findViewById(R.id.user_deals_of_the_day_recycler_view);

        btn_user_deals_of_the_day_back_image = themedView.findViewById(R.id.btn_user_deals_of_the_day_back_image);
        deals_of_the_day_searchview = themedView.findViewById(R.id.deals_of_the_day_searchview);

        btn_user_deals_of_the_day_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Day Deals");

        user_deals_of_the_day_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_deals_of_the_day_recycler_view.setItemAnimator(new DefaultItemAnimator());

        deals_of_the_day_list = new ArrayList<Deals>();
        user_deals_of_the_day_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Deals deals = snapshot.getValue(Deals.class);
                    deals_of_the_day_list.add(deals);
                }
                user_deals_of_the_day_recycler_view.setAdapter(new Deals_of_the_day_card_adapter(deals_of_the_day_list, getActivity(),deals_of_the_day_name,deals_of_the_day_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        deals_of_the_day_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (deals_of_the_day_list != null) {
                    ArrayList<Deals> filteredList_deals = new ArrayList<>();
                    for (Deals deals : deals_of_the_day_list) {
                        if (deals.getDeals_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_deals.add(deals);
                        }
                    }
                    user_deals_of_the_day_recycler_view.setAdapter(new Deals_of_the_day_card_adapter(filteredList_deals, getActivity(),deals_of_the_day_name,deals_of_the_day_price));

                }
                return true;
            }

        });



        // Inflate the layout for this fragment
        return themedView;
    }
}