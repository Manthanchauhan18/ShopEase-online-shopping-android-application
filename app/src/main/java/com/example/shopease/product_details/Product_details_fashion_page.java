package com.example.shopease.product_details;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopease.Payment.Razor_pay;
import com.example.shopease.R;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.model.Wishlist;
import com.example.shopease.ui.Cart.CartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Product_details_fashion_page extends Fragment {

    Button btn_user_product_details_fashion_product_add_to_cart,btn_user_product_details_fashion_product_buy_product;
    TextView user_product_details_fashion_product_discription,user_product_details_fashion_product_price,user_product_details_fashion_product_name,user_product_details_fashion_product_size,user_product_details_fashion_product_rating;
    ImageView user_product_details_fashion_product_image;
    ImageView btn_user_product_details_fashion_cart,btn_user_Product_details_fashion_back,btn_profile_details_wishlist;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_product_details_fashion_page, container, false);



        btn_user_Product_details_fashion_back = root.findViewById(R.id.btn_user_Product_details_fashion_back);
        btn_user_product_details_fashion_cart = root.findViewById(R.id.btn_user_product_details_fashion_cart);
        user_product_details_fashion_product_image = root.findViewById(R.id.user_product_details_fashion_product_image);
        user_product_details_fashion_product_discription = root.findViewById(R.id.user_product_details_fashion_product_discription);
        user_product_details_fashion_product_price = root.findViewById(R.id.user_product_details_fashion_product_price);
        user_product_details_fashion_product_name = root.findViewById(R.id.user_product_details_fashion_product_name);
        user_product_details_fashion_product_size = root.findViewById(R.id.user_product_details_fashion_product_size);
        user_product_details_fashion_product_rating = root.findViewById(R.id.product_details_fashion_rating);
        btn_profile_details_wishlist = root.findViewById(R.id.btn_profile_details_wishlist);

        btn_user_product_details_fashion_product_add_to_cart = root.findViewById(R.id.btn_user_product_details_fashion_product_add_to_cart);
        btn_user_product_details_fashion_product_buy_product = root.findViewById(R.id.btn_user_product_details_fashion_product_buy_product);



        btn_user_Product_details_fashion_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_user_product_details_fashion_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new CartFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setSelectedItemId(R.id.menu_cart);
            }
        });

//          Get the data passed from the previous fragment
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        String price = bundle.getString("price");
        String size = bundle.getString("size");
        String imageUrl = bundle.getString("image");
        String rating = bundle.getString("rating");
        String discription = bundle.getString("discription");
        String stock = bundle.getString("stock");



// Get a reference to the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "Wishlist" node under the user's node
        DatabaseReference wishlistRef = userRef.child("Wishlist");

// Query to check if the product is already in the wishlist
        Query query = wishlistRef.orderByChild("wishlist_Name").equalTo(name);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Product already exists in the wishlist
                    btn_profile_details_wishlist.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else {
                    // Product not in the wishlist
                    btn_profile_details_wishlist.setImageResource(R.drawable.heart_white_32);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error querying wishlist: " + error.getMessage());
            }
        });


        btn_profile_details_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get a reference to the Firebase Realtime Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = currentUser.getUid();
                DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
                DatabaseReference wishlist_ref = userRef.child("Wishlist");

                // Query to check if the product is already in the cart
                Query query = wishlist_ref.orderByChild("wishlist_Name").equalTo(name);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Product already exists in the wishlist
                            DataSnapshot snapshotChild = snapshot.getChildren().iterator().next(); // get the first child
                            String wishlistKey = snapshotChild.getKey(); // get the wishlist key
                            wishlist_ref.child(wishlistKey).removeValue(); // remove the value from Firebase
// Set the drawable of the button to the normal heart
                            btn_profile_details_wishlist.setImageResource(R.drawable.heart_white_32);

                        } else {

//                                      Generate a new key for the address node under the user's "saved addresses" node
                            String wishlist_key = userRef.child("Wishlist").push().getKey();


                            // Create a new cart item object with the product details
                            Wishlist wishlist = new Wishlist(wishlist_key, imageUrl, name, price, rating);

//                                      Create a new child node for the address under the user's "saved addresses" node
                            DatabaseReference cart_ref = userRef.child("Wishlist").child(wishlist_key);

//                           Save the address information to Firebase
                            cart_ref.setValue(wishlist);

                            btn_profile_details_wishlist.setImageResource(R.drawable.ic_baseline_favorite_24);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Error querying wishlist: " + error.getMessage());
                    }
                });

            }
        });




        double emi = 0;

//        // Get the price as a string from some input
//        String priceStr = "10000.0";
//
//// Convert the price string to a double
//        double price_double = Double.parseDouble(priceStr);
//
//// Calculate the EMI based on the price, rate of interest, and loan term
//        double rateOfInterest = 10.0; // Assuming 10% rate of interest
//        int loanTerm = 12; // Assuming 12 months loan term
//        double monthlyInterest = rateOfInterest / (12 * 100);
//        double emi = (price_double * monthlyInterest * Math.pow(1 + monthlyInterest, loanTerm)) / (Math.pow(1 + monthlyInterest, loanTerm) - 1);



//          Set the data to the views in this fragment
        user_product_details_fashion_product_name.setText(name);
        user_product_details_fashion_product_price.setText("₹" + price);
        user_product_details_fashion_product_size.setText(size);
        user_product_details_fashion_product_rating.setText(rating);
        user_product_details_fashion_product_discription.setText(discription);
        Picasso.get().load(imageUrl).into(user_product_details_fashion_product_image);



        btn_user_product_details_fashion_product_buy_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_razor_pay = new Intent(getActivity() , Razor_pay.class);
                intent_razor_pay.putExtra("price" , price);
                intent_razor_pay.putExtra("name" , name);
                intent_razor_pay.putExtra("image" , imageUrl);
                intent_razor_pay.putExtra("rating" , rating);
                intent_razor_pay.putExtra("discription" , discription);
                intent_razor_pay.putExtra("quantity" , "1");
                startActivity(intent_razor_pay);
            }
        });

        btn_user_product_details_fashion_product_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the Firebase Realtime Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = currentUser.getUid();
                DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
                DatabaseReference savedCartRef = userRef.child("Cart_Product");

                // Query to check if the product is already in the cart
                Query query = savedCartRef.orderByChild("cart_Name").equalTo(name);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Product already exists in the cart
                            Toast.makeText(getActivity(), "Product already in cart", Toast.LENGTH_SHORT).show();
                        } else {

//                                      Generate a new key for the address node under the user's "saved addresses" node
                            String cart_product_key = userRef.child("Cart_Product").push().getKey();


                            // Create a new cart item object with the product details
                            Cart_product_layout cart_product_layout = new Cart_product_layout(cart_product_key, imageUrl, name, price, rating,stock);

//                                      Create a new child node for the address under the user's "saved addresses" node
                            DatabaseReference cart_ref = userRef.child("Cart_Product").child(cart_product_key);

//                           Save the address information to Firebase
                            cart_ref.setValue(cart_product_layout);

                            // Product added to the cart
                            Toast.makeText(getActivity(), "Product added to cart", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Error querying cart products: " + error.getMessage());
                    }
                });

            }
        });





        // Inflate the layout for this fragment
        return root;
    }
}