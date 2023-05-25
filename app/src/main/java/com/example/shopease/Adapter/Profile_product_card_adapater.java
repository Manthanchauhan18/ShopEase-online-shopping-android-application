package com.example.shopease.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shopease.ElegantNumberButton;
import com.example.shopease.Profile_of_user.My_wishlist;
import com.example.shopease.R;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.model.Wishlist;
import com.example.shopease.ui.Cart.CartFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Profile_product_card_adapater extends RecyclerView.Adapter<Profile_product_card_adapater.ViewHolder>{

    private Context context;
    private List<Wishlist> wishlist_List;

    ArrayList<String> wishlistPriceList;
    ArrayList<String> wishlistNameList;
    ArrayList<String> wishlistRatingList;

    private DatabaseReference mDatabase;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public Profile_product_card_adapater(List<Wishlist> wishlist_List, Context context,ArrayList<String> wishlistPriceList,ArrayList<String> wishlistNameList,ArrayList<String> wishlistRatingList ) {
        this.context = context;
        this.wishlist_List = wishlist_List;
        this.wishlistNameList = wishlistNameList;
        this.wishlistPriceList = wishlistPriceList;
        this.wishlistRatingList = wishlistRatingList;

    }


    @NonNull
    @Override
    public Profile_product_card_adapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_product_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Profile_product_card_adapater.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView profile_wishlist_product_layout_image = holder.profile_wishlist_product_layout_image;
        TextView profile_wishlist_product_layout_product_name = holder.profile_wishlist_product_layout_product_name;
        TextView profile_wishlist_product_layout_price = holder.profile_wishlist_product_layout_price;
        TextView profile_wishlist_product_layout_rating = holder.profile_wishlist_product_layout_rating;
        ImageView profile_wishlist_product_layout_delete = holder.profile_wishlist_product_layout_delete;


        profile_wishlist_product_layout_product_name.setText(wishlist_List.get(position).getWishlist_Name());
        profile_wishlist_product_layout_price.setText("₹ "+wishlist_List.get(position).getWishlist_price());
        profile_wishlist_product_layout_rating.setText(wishlist_List.get(position).getWishlist_rating());

        Glide.with(context)
                .load(wishlist_List.get(position).getWishlist_imageUrl())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profile_wishlist_product_layout_image);

        profile_wishlist_product_layout_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the Firebase Realtime Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = currentUser.getUid();
                DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
                DatabaseReference savedCartRef = userRef.child("Wishlist");

//                    Get the key of the product to delete
                String wishlistKey = wishlist_List.get(position).getWishlist_id();

//                    Delete the product from Firebase
                savedCartRef.child(wishlistKey).removeValue();

                // Remove the product from the list
                wishlist_List.remove(position);

                // Notify the adapter that the data has changed
                notifyDataSetChanged();

                // Refresh the CartFragment
                My_wishlist my_wishlist = new My_wishlist();
                FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, my_wishlist);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return wishlist_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_wishlist_product_layout_image;
        TextView profile_wishlist_product_layout_product_name,profile_wishlist_product_layout_price,profile_wishlist_product_layout_rating;
        ImageView profile_wishlist_product_layout_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_wishlist_product_layout_image = itemView.findViewById(R.id.profile_wishlist_product_layout_image);
            profile_wishlist_product_layout_product_name = itemView.findViewById(R.id.profile_wishlist_product_layout_product_name);
            profile_wishlist_product_layout_price = itemView.findViewById(R.id.profile_wishlist_product_layout_price);
            profile_wishlist_product_layout_rating = itemView.findViewById(R.id.profile_wishlist_product_layout_rating);
            profile_wishlist_product_layout_delete = itemView.findViewById(R.id.profile_wishlist_product_layout_delete);

        }
    }


}
