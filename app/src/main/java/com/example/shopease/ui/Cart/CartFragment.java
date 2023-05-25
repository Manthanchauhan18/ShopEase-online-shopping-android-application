package com.example.shopease.ui.Cart;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.shopease.Adapter.Cart_product_layout_card_adapter;
import com.example.shopease.Adapter.Grocery_card_adapter;
import com.example.shopease.Adapter.Smartphone_card_adapter;
import com.example.shopease.Payment.Razor_pay;
import com.example.shopease.R;
import com.example.shopease.categories.User_home_category_grocery;
import com.example.shopease.categories.User_home_category_mobile;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Upcoming_phones;
import com.example.shopease.ui.category.CategoriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    Button user_cart_button_shop_now,cart_button_buy_products;
    CardView user_product_empty_card_cardview;
    RecyclerView user_cart_mobile_recyclerview,user_cart_grocery_recyclerview,cart_product_layout_recycler_view;

    DatabaseReference dbref,dbref2,dbref_cart;
    ArrayList<String> phone_name = new ArrayList<String>();
    ArrayList<String> phone_price = new ArrayList<String>();
    ArrayList<String> phone_storage = new ArrayList<String>();
    ArrayList<Upcoming_phones> phone_list;

    ArrayList<String> grocery_name = new ArrayList<String>();
    ArrayList<String> grocery_price = new ArrayList<String>();
    ArrayList<String> grocery_amount = new ArrayList<String>();
    ArrayList<Grocery> grocery_list;

    ArrayList<String> cart_name = new ArrayList<String>();
    ArrayList<String> cart_price = new ArrayList<String>();
    ArrayList<String> cart_rating = new ArrayList<String>();
    ArrayList<String> cart_stock = new ArrayList<String>();
    ArrayList<Cart_product_layout> cart_list;

    Cart_product_layout_card_adapter cart_product_layout_card_adapter;

    FirebaseFirestore db;
    FirebaseAuth auth;

    TextView user_cart_txt_smartphone_view_more,user_cart_txt_grocery_view_more,cart_fragment_txt_total_price;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        cart_button_buy_products = root.findViewById(R.id.cart_button_buy_products);
        cart_button_buy_products.setVisibility(View.GONE);

        user_product_empty_card_cardview = root.findViewById(R.id.user_product_empty_card_cardview);
        cart_fragment_txt_total_price = root.findViewById(R.id.cart_fragment_txt_total_price);
        cart_fragment_txt_total_price.setVisibility(View.GONE);




        cart_product_layout_recycler_view = root.findViewById(R.id.cart_product_layout_recycler_view);

        // Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
        DatabaseReference savedCartRef = userRef.child("Cart_Product");

        LinearLayoutManager layoutManager_cart = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // Set the LayoutManager of the RecyclerView
        cart_product_layout_recycler_view.setLayoutManager(layoutManager_cart);
        cart_product_layout_recycler_view.setItemAnimator(new DefaultItemAnimator());

        cart_list = new ArrayList<Cart_product_layout>();
        cart_product_layout_recycler_view.setHasFixedSize(true);

        savedCartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot_cart : dataSnapshot.getChildren()) {
                    Cart_product_layout cart_product_layout = snapshot_cart.getValue(Cart_product_layout.class);
                    cart_list.add(cart_product_layout);

                    if(!cart_list.equals(null)){
                        cart_button_buy_products.setVisibility(View.VISIBLE);
                        user_product_empty_card_cardview.setVisibility(View.GONE);
                    }

                }
                cart_product_layout_recycler_view.setAdapter(new Cart_product_layout_card_adapter(cart_list, getActivity(),cart_name,cart_price,cart_rating,cart_stock));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        cart_button_buy_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_razor_pay = new Intent(getActivity(), Razor_pay.class);
//                intent_razor_pay.putExtra("price" , price);
                startActivity(intent_razor_pay);

//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, new Razor_payment());
//                transaction.addToBackStack(null);
//                transaction.commit();


            }
        });


        user_cart_button_shop_now = root.findViewById(R.id.user_cart_button_shop_now);

        user_cart_button_shop_now.setOnClickListener(new View.OnClickListener() {
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



        user_cart_txt_grocery_view_more = root.findViewById(R.id.user_cart_txt_grocery_view_more);
        user_cart_txt_smartphone_view_more = root.findViewById(R.id.user_cart_txt_smartphone_view_more);

        user_cart_txt_smartphone_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new User_home_category_mobile());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        user_cart_txt_grocery_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new User_home_category_grocery());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        user_cart_mobile_recyclerview = root.findViewById(R.id.user_cart_mobile_recyclerview);
        user_cart_grocery_recyclerview = root.findViewById(R.id.user_cart_grocery_recyclerview);

        dbref = FirebaseDatabase.getInstance().getReference("Upcoming Phones");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        // Set the LayoutManager of the RecyclerView
        user_cart_mobile_recyclerview.setLayoutManager(layoutManager);
        user_cart_mobile_recyclerview.setItemAnimator(new DefaultItemAnimator());

        phone_list = new ArrayList<Upcoming_phones>();
        user_cart_mobile_recyclerview.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Upcoming_phones upcoming_phones = snapshot.getValue(Upcoming_phones.class);
                    phone_list.add(upcoming_phones);
                }
                user_cart_mobile_recyclerview.setAdapter(new Smartphone_card_adapter(phone_list, getActivity(),phone_name,phone_price,phone_storage));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        dbref2 = FirebaseDatabase.getInstance().getReference("Grocery");

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        // Set the LayoutManager of the RecyclerView
        user_cart_grocery_recyclerview.setLayoutManager(layoutManager2);
        user_cart_grocery_recyclerview.setItemAnimator(new DefaultItemAnimator());

        grocery_list = new ArrayList<Grocery>();
        user_cart_grocery_recyclerview.setHasFixedSize(true);

        dbref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grocery grocery = snapshot.getValue(Grocery.class);
                    grocery_list.add(grocery);
                }
                user_cart_grocery_recyclerview.setAdapter(new Grocery_card_adapter(grocery_list, getActivity(),grocery_name,grocery_price,grocery_amount));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




        return root;
    }
}