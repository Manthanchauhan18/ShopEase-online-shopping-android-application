package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.Adapter.Cart_product_layout_card_adapter;
import com.example.shopease.Adapter.Order_product_layout_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.model.Orders;
import com.example.shopease.ui.category.CategoriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;

public class My_Order extends Fragment {

    ImageView btn_profile_orders_back_image;
    Button user_profile_orders_button_shop_now;
    CardView user_product_empty_order_cardview;

    RecyclerView order_product_layout_recycler_view;

    ArrayList<String> product_name = new ArrayList<String>();
    ArrayList<String> product_price = new ArrayList<String>();
    ArrayList<String> user_address = new ArrayList<String>();
    ArrayList<String> product_quantity = new ArrayList<String>();
    ArrayList<String> transaction_id = new ArrayList<String>();
    ArrayList<String> order_id = new ArrayList<String>();
    ArrayList<Orders> order_list;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my__order, container, false);

        btn_profile_orders_back_image = root.findViewById(R.id.btn_profile_orders_back_image);
        user_profile_orders_button_shop_now = root.findViewById(R.id.user_profile_orders_button_shop_now);
        user_product_empty_order_cardview = root.findViewById(R.id.user_product_empty_order_cardview);
        order_product_layout_recycler_view = root.findViewById(R.id.order_product_layout_recycler_view);


        btn_profile_orders_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });

        user_profile_orders_button_shop_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new CategoriesFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setSelectedItemId(R.id.menu_categories);

            }
        });

        // Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
        DatabaseReference savedCartRef = userRef.child("Orders");

        LinearLayoutManager layoutManager_cart = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // Set the LayoutManager of the RecyclerView
        order_product_layout_recycler_view.setLayoutManager(layoutManager_cart);
        order_product_layout_recycler_view.setItemAnimator(new DefaultItemAnimator());

        order_list = new ArrayList<Orders>();
        order_product_layout_recycler_view.setHasFixedSize(true);

        savedCartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot_cart : dataSnapshot.getChildren()) {
                    Orders orders = snapshot_cart.getValue(Orders.class);
                    order_list.add(orders);

                    if(!order_list.equals(null)){
                        user_product_empty_order_cardview.setVisibility(View.GONE);
                    }

                }
                order_product_layout_recycler_view.setAdapter(new Order_product_layout_card_adapter(order_list, getActivity(),product_price,product_name,product_quantity,user_address));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




        // Inflate the layout for this fragment
        return root;
    }
}