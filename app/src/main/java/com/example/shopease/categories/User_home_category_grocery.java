package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopease.Adapter.Category_grocery_card_adapter;
import com.example.shopease.Adapter.Grocery_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Upcoming_phones;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_grocery extends Fragment {

    ImageView btn_user_grocery_category_back_image;

    RecyclerView user_grocery_category_recycler_view;
    DatabaseReference dbref;
    ArrayList<String> grocery_name = new ArrayList<String>();
    ArrayList<String> grocery_price = new ArrayList<String>();
    ArrayList<String> grocery_amount = new ArrayList<String>();
    ArrayList<Grocery> grocery_list;

    SearchView category_grocery_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_grocery, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_grocery, container, false);

        user_grocery_category_recycler_view = themedView.findViewById(R.id.user_grocery_category_recycler_view);
        category_grocery_searchview = themedView.findViewById(R.id.category_grocery_searchview);


        btn_user_grocery_category_back_image = themedView.findViewById(R.id.btn_user_grocery_category_back_image);

        btn_user_grocery_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Grocery");

        user_grocery_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_grocery_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        grocery_list = new ArrayList<Grocery>();
        user_grocery_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grocery grocery = snapshot.getValue(Grocery.class);
                    grocery_list.add(grocery);
                }
                user_grocery_category_recycler_view.setAdapter(new Category_grocery_card_adapter(grocery_list, getActivity(),grocery_name,grocery_price,grocery_amount));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        category_grocery_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (grocery_list != null) {
                    ArrayList<Grocery> filteredList_grocery = new ArrayList<>();
                    for (Grocery grocery : grocery_list) {
                        if (grocery.getGrocery_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_grocery.add(grocery);
                        }
                    }
                    user_grocery_category_recycler_view.setAdapter(new Category_grocery_card_adapter(filteredList_grocery, getActivity(),grocery_name,grocery_price,grocery_amount));
                }
                return true;
            }

        });


        return themedView;
    }
}