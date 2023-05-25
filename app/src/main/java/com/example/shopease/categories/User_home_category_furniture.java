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
import com.example.shopease.Adapter.Category_furniture_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Appliance;
import com.example.shopease.model.Furniture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_furniture extends Fragment {

    ImageView btn_user_furniture_category_back_image;
    DatabaseReference dbref;
    ArrayList<String> furniture_name = new ArrayList<String>();
    ArrayList<String> furniture_price = new ArrayList<String>();
    ArrayList<Furniture> furniture_list;
    RecyclerView user_furniture_category_recycler_view;

    SearchView category_furniture_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_furniture, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_furniture, container, false);

        user_furniture_category_recycler_view = themedView.findViewById(R.id.user_furniture_category_recycler_view);

        btn_user_furniture_category_back_image = themedView.findViewById(R.id.btn_user_furniture_category_back_image);
        category_furniture_searchview = themedView.findViewById(R.id.category_furniture_searchview);

        btn_user_furniture_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });


        dbref = FirebaseDatabase.getInstance().getReference("Furniture");

        user_furniture_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_furniture_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        furniture_list = new ArrayList<Furniture>();
        user_furniture_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Furniture furniture = snapshot.getValue(Furniture.class);
                    furniture_list.add(furniture);
                }
                user_furniture_category_recycler_view.setAdapter(new Category_furniture_card_adapter(furniture_list, getActivity(),furniture_name,furniture_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        category_furniture_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (furniture_list != null) {
                    ArrayList<Furniture> filteredList_furniture = new ArrayList<>();
                    for (Furniture furniture : furniture_list) {
                        if (furniture.getFurniture_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_furniture.add(furniture);
                        }
                    }
                    user_furniture_category_recycler_view.setAdapter(new Category_furniture_card_adapter(filteredList_furniture, getActivity(),furniture_name,furniture_price));
                }
                return true;
            }

        });



        return themedView;
    }
}