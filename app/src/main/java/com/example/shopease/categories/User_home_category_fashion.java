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

import com.example.shopease.Adapter.Category_fashion_card_adapter;
import com.example.shopease.Adapter.category_mobile_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Fashion;
import com.example.shopease.model.Upcoming_phones;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_fashion extends Fragment {

    ImageView btn_user_fashion_category_back_image;
    DatabaseReference dbref;
    ArrayList<String> fashion_name = new ArrayList<String>();
    ArrayList<String> fashion_price = new ArrayList<String>();
    ArrayList<String> fashion_storage = new ArrayList<String>();
    ArrayList<Fashion> fashion_list;
    RecyclerView user_fashion_category_recycler_view;

    SearchView category_fashion_searchview;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_fashion, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_fashion, container, false);

        user_fashion_category_recycler_view = themedView.findViewById(R.id.user_fashion_category_recycler_view);
        category_fashion_searchview = themedView.findViewById(R.id.category_fashion_searchview);


        btn_user_fashion_category_back_image = themedView.findViewById(R.id.btn_user_fashion_category_back_image);

        btn_user_fashion_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Fashion");

        user_fashion_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_fashion_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        fashion_list = new ArrayList<Fashion>();
        user_fashion_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Fashion fashion = snapshot.getValue(Fashion.class);
                    fashion_list.add(fashion);
                }
                user_fashion_category_recycler_view.setAdapter(new Category_fashion_card_adapter(fashion_list, getActivity(),fashion_name,fashion_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        category_fashion_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (fashion_list != null) {
                    ArrayList<Fashion> filteredList_fashion = new ArrayList<>();
                    for (Fashion fashion : fashion_list) {
                        if (fashion.getFashion_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_fashion.add(fashion);
                        }
                    }
                    user_fashion_category_recycler_view.setAdapter(new Category_fashion_card_adapter(filteredList_fashion, getActivity(),fashion_name,fashion_price));
                }
                return true;
            }

        });


        return themedView;
    }
}