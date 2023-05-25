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

import com.example.shopease.Adapter.Category_fitness_card_adapter;
import com.example.shopease.Adapter.Category_grocery_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Fitness;
import com.example.shopease.model.Grocery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_fitness extends Fragment {

    ImageView btn_user_fitness_category_back_image;

    RecyclerView user_fitness_category_recycler_view;
    DatabaseReference dbref;
    ArrayList<String> fitness_name = new ArrayList<String>();
    ArrayList<String> fitness_price = new ArrayList<String>();
    ArrayList<Fitness> fitness_list;

    SearchView category_fitness_searchview;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_fitness, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_fitness, container, false);

        user_fitness_category_recycler_view = themedView.findViewById(R.id.user_fitness_category_recycler_view);

        btn_user_fitness_category_back_image = themedView.findViewById(R.id.btn_user_fitness_category_back_image);
        category_fitness_searchview = themedView.findViewById(R.id.category_fitness_searchview);

        btn_user_fitness_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Fitness");

        user_fitness_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_fitness_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        fitness_list = new ArrayList<Fitness>();
        user_fitness_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Fitness fitness = snapshot.getValue(Fitness.class);
                    fitness_list.add(fitness);
                }
                user_fitness_category_recycler_view.setAdapter(new Category_fitness_card_adapter(fitness_list, getActivity(),fitness_name,fitness_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        category_fitness_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (fitness_list != null) {
                    ArrayList<Fitness> filteredList_fitness = new ArrayList<>();
                    for (Fitness fitness : fitness_list) {
                        if (fitness.getFitness_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_fitness.add(fitness);
                        }
                    }
                    user_fitness_category_recycler_view.setAdapter(new Category_fitness_card_adapter(filteredList_fitness, getActivity(),fitness_name,fitness_price));
                }
                return true;
            }

        });



        return themedView;
    }
}