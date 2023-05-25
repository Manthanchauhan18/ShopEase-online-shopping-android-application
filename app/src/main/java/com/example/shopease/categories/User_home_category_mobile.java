package com.example.shopease.categories;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopease.Adapter.Smartphone_card_adapter;
import com.example.shopease.Adapter.category_mobile_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Upcoming_phones;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class User_home_category_mobile extends Fragment {

    ImageView btn_user_mobile_category_back_image;
    DatabaseReference dbref;
    ArrayList<String> phone_name = new ArrayList<String>();
    ArrayList<String> phone_price = new ArrayList<String>();
    ArrayList<String> phone_storage = new ArrayList<String>();
    ArrayList<Upcoming_phones> phone_list;
    RecyclerView user_mobile_category_recycler_view;

    SearchView category_mobile_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View root = inflater.inflate(R.layout.fragment_user_home_category_mobile, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_mobile, container, false);



        user_mobile_category_recycler_view = themedView.findViewById(R.id.user_mobile_category_recycler_view);
        category_mobile_searchview = themedView.findViewById(R.id.category_mobile_searchview);

//        FirebaseApp.initializeApp(getActivity()); // If not already initialized
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Upcoming Phones");

        category_mobile_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (phone_list != null) {
                    ArrayList<Upcoming_phones> filteredList_mobile = new ArrayList<>();
                    for (Upcoming_phones phone : phone_list) {
                        if (phone.getSmartphone_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_mobile.add(phone);
                        }
                    }
                    user_mobile_category_recycler_view.setAdapter(new category_mobile_card_adapter(filteredList_mobile, getActivity(),phone_name,phone_price,phone_storage));
                }
                return true;
            }

        });



        btn_user_mobile_category_back_image = themedView.findViewById(R.id.btn_user_mobile_category_back_image);
        btn_user_mobile_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Upcoming Phones");

        user_mobile_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_mobile_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        phone_list = new ArrayList<Upcoming_phones>();
        user_mobile_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Upcoming_phones upcoming_phones = snapshot.getValue(Upcoming_phones.class);
                    phone_list.add(upcoming_phones);
                }
                user_mobile_category_recycler_view.setAdapter(new category_mobile_card_adapter(phone_list, getActivity(),phone_name,phone_price,phone_storage));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




        return themedView;
    }
}