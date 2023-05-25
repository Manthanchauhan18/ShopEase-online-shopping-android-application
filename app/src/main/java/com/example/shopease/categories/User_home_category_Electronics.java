package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.media.Image;
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

import com.example.shopease.Adapter.Category_books_card_adapter;
import com.example.shopease.Adapter.Category_electronics_card_adapter;
import com.example.shopease.Adapter.Category_tv_card_adapter;
import com.example.shopease.Adapter.category_mobile_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Books;
import com.example.shopease.model.Electronics;
import com.example.shopease.model.Tv;
import com.example.shopease.model.Upcoming_phones;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class User_home_category_Electronics extends Fragment {

    ImageView btn_user_electronics_category_back_image;
    DatabaseReference dbref;
    ArrayList<String> electronics_name = new ArrayList<String>();
    ArrayList<String> electronics_price = new ArrayList<String>();
    ArrayList<String> electronics_storage = new ArrayList<String>();
    ArrayList<Electronics> electronics_list;
    RecyclerView user_electronics_category_recycler_view;

    SearchView category_electronics_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category__electronics, container, false);


        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category__electronics, container, false);

        user_electronics_category_recycler_view = themedView.findViewById(R.id.user_electronics_category_recycler_view);
        category_electronics_searchview = themedView.findViewById(R.id.category_electronics_searchview);


        btn_user_electronics_category_back_image = themedView.findViewById(R.id.btn_user_electronics_category_back_image);
        btn_user_electronics_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Electronics");

        user_electronics_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_electronics_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        electronics_list = new ArrayList<Electronics>();
        user_electronics_category_recycler_view.setHasFixedSize(true);



        category_electronics_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (electronics_list != null) {
                    ArrayList<Electronics> filteredList_electronics = new ArrayList<>();
                    for (Electronics electronics : electronics_list) {
                        if (electronics.getElectronics_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_electronics.add(electronics);
                        }
                    }
                    user_electronics_category_recycler_view.setAdapter(new Category_electronics_card_adapter(filteredList_electronics, getActivity(),electronics_name,electronics_price));
                }
                return true;
            }

        });



        dbref = FirebaseDatabase.getInstance().getReference("Electronics");

        user_electronics_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_electronics_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        electronics_list = new ArrayList<Electronics>();
        user_electronics_category_recycler_view.setHasFixedSize(true);


        // Get the arguments bundle from the fragment
        Bundle args = getArguments();


//      Check if the bundle is not null and contains the key you are looking for
        if (args != null && args.containsKey("jbl")) {
            // Retrieve the value associated with the key
            String value = args.getString("jbl");

            if (value == "jbl") {

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Electronics electronics = snapshot.getValue(Electronics.class);
                            String electronic_name = electronics.getElectronics_name().toLowerCase();
                            if (electronics.getElectronics_name().toLowerCase().contains("jbl")) {
                                electronics_list.add(electronics);
                            }
                        }
                        user_electronics_category_recycler_view.setAdapter(new Category_electronics_card_adapter(electronics_list, getActivity(), electronics_name, electronics_price));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });

            }
        }else if (args != null && args.containsKey("sony")) {
            // Retrieve the value associated with the key
            String value = args.getString("sony");

            if (value == "sony") {

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Electronics electronics = snapshot.getValue(Electronics.class);
                            String electronic_name = electronics.getElectronics_name().toLowerCase();
                            if (electronics.getElectronics_name().toLowerCase().contains("sony")) {
                                electronics_list.add(electronics);
                            }
                        }
                        user_electronics_category_recycler_view.setAdapter(new Category_electronics_card_adapter(electronics_list, getActivity(), electronics_name, electronics_price));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }
        }else if (args != null && args.containsKey("boat")) {
            // Retrieve the value associated with the key
            String value = args.getString("boat");

            if (value == "boat") {

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Electronics electronics = snapshot.getValue(Electronics.class);
                            String electronic_name = electronics.getElectronics_name().toLowerCase();
                            if (electronics.getElectronics_name().toLowerCase().contains("boat")) {
                                electronics_list.add(electronics);
                            }
                        }
                        user_electronics_category_recycler_view.setAdapter(new Category_electronics_card_adapter(electronics_list, getActivity(), electronics_name, electronics_price));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }
        }else if (args != null && args.containsKey("boult")) {
                // Retrieve the value associated with the key
                String value = args.getString("boult");

                if (value == "boult") {

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Electronics electronics = snapshot.getValue(Electronics.class);
                            String electronic_name = electronics.getElectronics_name().toLowerCase();
                            if (electronics.getElectronics_name().toLowerCase().contains("boult")) {
                                electronics_list.add(electronics);
                            }
                        }
                        user_electronics_category_recycler_view.setAdapter(new Category_electronics_card_adapter(electronics_list, getActivity(), electronics_name, electronics_price));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }
        }else{

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Electronics electronics = snapshot.getValue(Electronics.class);
                            electronics_list.add(electronics);
                        }
                        user_electronics_category_recycler_view.setAdapter(new Category_electronics_card_adapter(electronics_list, getActivity(),electronics_name,electronics_price));
//                recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }




            return themedView;
    }
}