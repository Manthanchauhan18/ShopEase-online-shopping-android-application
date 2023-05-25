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

import com.example.shopease.Adapter.Category_tv_card_adapter;
import com.example.shopease.Adapter.category_mobile_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Tv;
import com.example.shopease.model.Upcoming_phones;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_category_Tv extends Fragment {

    ImageView btn_user_tv_category_back_image;
    RecyclerView user_tv_category_recycler_view;

    DatabaseReference dbref_tv;
    ArrayList<String> tv_name = new ArrayList<String>();
    ArrayList<String> tv_price = new ArrayList<String>();
    ArrayList<String> tv_size = new ArrayList<String>();
    ArrayList<Tv> tv_list;

    SearchView category_tv_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category__tv, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category__tv, container, false);

        user_tv_category_recycler_view = themedView.findViewById(R.id.user_tv_category_recycler_view);
        category_tv_searchview = themedView.findViewById(R.id.category_tv_searchview);


        btn_user_tv_category_back_image = themedView.findViewById(R.id.btn_user_tv_category_back_image);

        btn_user_tv_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        category_tv_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (tv_list != null) {
                    ArrayList<Tv> filteredList_tv = new ArrayList<>();
                    for (Tv tv : tv_list) {
                        if (tv.getTv_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_tv.add(tv);
                        }
                    }
                    user_tv_category_recycler_view.setAdapter(new Category_tv_card_adapter(filteredList_tv, getActivity(),tv_name,tv_price,tv_size));
                }
                return true;
            }

        });


        dbref_tv = FirebaseDatabase.getInstance().getReference("Tv");

        user_tv_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_tv_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        tv_list = new ArrayList<Tv>();
        user_tv_category_recycler_view.setHasFixedSize(true);


        // Get the arguments bundle from the fragment
        Bundle args = getArguments();

//      Check if the bundle is not null and contains the key you are looking for
        if (args != null && args.containsKey("14999")) {
            // Retrieve the value associated with the key
            String value = args.getString("14999");

            if(value=="14999"){

                dbref_tv.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Tv tv = snapshot.getValue(Tv.class);
                            String price_string = tv.getTv_price().replace(",","");
                            int price = Integer.parseInt(price_string);
                            if (price <= 14999 ) {
                                tv_list.add(tv);
                            }
                        }
                        user_tv_category_recycler_view.setAdapter(new Category_tv_card_adapter(tv_list, getActivity(),tv_name,tv_price,tv_size));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }

            // Do something with the value
            // ...
        }else if (args != null && args.containsKey("8999")) {
            // Retrieve the value associated with the key
            String value = args.getString("8999");

            if(value=="8999"){

                dbref_tv.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Tv tv = snapshot.getValue(Tv.class);
                            String price_string = tv.getTv_price().replace(",","");
                            int price = Integer.parseInt(price_string);
                            if (price <= 8999 && price > 0) {
                                tv_list.add(tv);
                            }
                        }
                        user_tv_category_recycler_view.setAdapter(new Category_tv_card_adapter(tv_list, getActivity(),tv_name,tv_price,tv_size));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }

            // Do something with the value
            // ...
        }else if (args != null && args.containsKey("24999")) {
            // Retrieve the value associated with the key
            String value = args.getString("24999");

            if(value=="24999"){

                dbref_tv.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Tv tv = snapshot.getValue(Tv.class);
                            String price_string = tv.getTv_price().replace(",","");
                            int price = Integer.parseInt(price_string);
                            if (price <= 24999 ) {
                                tv_list.add(tv);
                            }
                        }
                        user_tv_category_recycler_view.setAdapter(new Category_tv_card_adapter(tv_list, getActivity(),tv_name,tv_price,tv_size));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }

            // Do something with the value
            // ...
        }else if (args != null && args.containsKey("49999")) {
            // Retrieve the value associated with the key
            String value = args.getString("49999");

            if(value=="49999"){

                dbref_tv.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Tv tv = snapshot.getValue(Tv.class);
                            String price_string = tv.getTv_price().replace(",","");
                            int price = Integer.parseInt(price_string);
                            if (price <= 49999 ) {
                                tv_list.add(tv);
                            }
                        }
                        user_tv_category_recycler_view.setAdapter(new Category_tv_card_adapter(tv_list, getActivity(),tv_name,tv_price,tv_size));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle errors
                    }
                });


            }

            // Do something with the value
            // ...
        }else{

            dbref_tv.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Tv tv = snapshot.getValue(Tv.class);
                        tv_list.add(tv);
                    }
                    user_tv_category_recycler_view.setAdapter(new Category_tv_card_adapter(tv_list, getActivity(),tv_name,tv_price,tv_size));
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