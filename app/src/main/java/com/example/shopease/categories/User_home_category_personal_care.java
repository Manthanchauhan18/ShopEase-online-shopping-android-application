package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.app.Person;
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
import com.example.shopease.Adapter.Category_personal_care_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Personal_Care;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_personal_care extends Fragment {

    ImageView btn_user_personal_care_category_back_image;

    RecyclerView user_personal_care_category_recycler_view;
    DatabaseReference dbref;
    ArrayList<String> personal_care_name = new ArrayList<String>();
    ArrayList<String> personal_care_price = new ArrayList<String>();
    ArrayList<String> personal_care_weight = new ArrayList<String>();
    ArrayList<Personal_Care> personal_care_list;

    SearchView category_personal_care_searchview;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_personal_care, container, false);


        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_personal_care, container, false);

        user_personal_care_category_recycler_view = themedView.findViewById(R.id.user_personal_care_category_recycler_view);
        category_personal_care_searchview = themedView.findViewById(R.id.category_personal_care_searchview);


        btn_user_personal_care_category_back_image = themedView.findViewById(R.id.btn_user_personal_care_category_back_image);

        btn_user_personal_care_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Personal Care");

        user_personal_care_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_personal_care_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        personal_care_list = new ArrayList<Personal_Care>();
        user_personal_care_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Personal_Care personal_care = snapshot.getValue(Personal_Care.class);
                    personal_care_list.add(personal_care);
                }
                user_personal_care_category_recycler_view.setAdapter(new Category_personal_care_adapter(personal_care_list, getActivity(),personal_care_name,personal_care_price,personal_care_weight));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        category_personal_care_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (personal_care_list != null) {
                    ArrayList<Personal_Care> filteredList_personal_care = new ArrayList<>();
                    for (Personal_Care personal_care : personal_care_list) {
                        if (personal_care.getPersonal_care_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_personal_care.add(personal_care);
                        }
                    }
                    user_personal_care_category_recycler_view.setAdapter(new Category_personal_care_adapter(filteredList_personal_care, getActivity(),personal_care_name,personal_care_price,personal_care_weight));
                }
                return true;
            }

        });

        return themedView;
    }
}