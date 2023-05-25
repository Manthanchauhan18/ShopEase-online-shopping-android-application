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
import com.example.shopease.Adapter.Category_sports_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Sports;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_sports extends Fragment {

    ImageView btn_user_sports_category_back_image;

    RecyclerView user_sports_category_recycler_view;
    DatabaseReference dbref;
    ArrayList<String> sports_name = new ArrayList<String>();
    ArrayList<String> sports_price = new ArrayList<String>();
    ArrayList<Sports> sports_list;

    SearchView category_sports_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_sports, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_sports, container, false);

        user_sports_category_recycler_view = themedView.findViewById(R.id.user_sports_category_recycler_view);
        category_sports_searchview = themedView.findViewById(R.id.category_sports_searchview);


        btn_user_sports_category_back_image = themedView.findViewById(R.id.btn_user_sports_category_back_image);

        btn_user_sports_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Sport");

        user_sports_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_sports_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        sports_list = new ArrayList<Sports>();
        user_sports_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Sports sports = snapshot.getValue(Sports.class);
                    sports_list.add(sports);
                }
                user_sports_category_recycler_view.setAdapter(new Category_sports_card_adapter(sports_list, getActivity(),sports_name,sports_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        category_sports_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (sports_list != null) {
                    ArrayList<Sports> filteredList_sports = new ArrayList<>();
                    for (Sports sports : sports_list) {
                        if (sports.getSports_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_sports.add(sports);
                        }
                    }
                    user_sports_category_recycler_view.setAdapter(new Category_sports_card_adapter(filteredList_sports, getActivity(),sports_name,sports_price));
                }
                return true;
            }

        });



        return themedView;
    }
}