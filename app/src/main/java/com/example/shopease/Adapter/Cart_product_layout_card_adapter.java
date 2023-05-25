package com.example.shopease.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shopease.ElegantNumberButton;
import com.example.shopease.R;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.ui.Cart.CartFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Cart_product_layout_card_adapter extends RecyclerView.Adapter<Cart_product_layout_card_adapter.ViewHolder>{

    private Context context;
    private List<Cart_product_layout> cartList;

    ArrayList<String> cartPriceList;
    ArrayList<String> cartNameList;
    ArrayList<String> cartRatingList;
    ArrayList<String> cartStockList;

    private ElegantNumberButton elegantNumberButton;
    private Button decrementButton;
    private Button incrementButton;

    int product_stock;

    private DatabaseReference mDatabase;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    // Declare count as a member variable of the class
    private int count;



    public Cart_product_layout_card_adapter(List<Cart_product_layout> cartList, Context context,ArrayList<String> cartPriceList,ArrayList<String> cartNameList,ArrayList<String> cartRatingList,ArrayList<String> carStockList) {
        this.context = context;
        this.cartList = cartList;
        this.cartNameList = cartNameList;
        this.cartPriceList = cartPriceList;
        this.cartRatingList = cartRatingList;
        this.cartStockList = carStockList;

    }

    @NonNull
    @Override
    public Cart_product_layout_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_product_layout_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        ImageView cart_product_layout_image = holder.cart_product_layout_image;
        TextView cart_product_layout_product_name = holder.cart_product_layout_product_name;
        TextView cart_product_layout_price = holder.cart_product_layout_price;
        TextView cart_product_layout_rating = holder.cart_product_layout_rating;
        ImageView cart_product_layout_delete = holder.cart_product_layout_delete;

        TextView elegantNumberButton= holder.elegantNumberButton;
        Button decrementButton = holder.decrementButton;
        Button incrementButton = holder.incrementButton;


// Initialize count using Integer.parseInt((String) elegantNumberButton.getText())
        count = Integer.parseInt((String) elegantNumberButton.getText());

        String s = cartList.get(position) != null ? cartList.get(position).getCart_stock().toString().trim() : null;
        int stock;
        if (s != null) {
            try {
                stock = Integer.parseInt(s);

                incrementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(count < stock){
                            count++;
                            elegantNumberButton.setText(String.valueOf(count));
                        }

                    }
                });

            } catch (NumberFormatException e) {
                Toast.makeText(context, "product is out of stock", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "product having null stock", Toast.LENGTH_SHORT).show();
        }





        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0){
                    count--;
                    elegantNumberButton.setText(String.valueOf(count));
                }

                int count = Integer.parseInt((String) elegantNumberButton.getText());
                if (count == 0) {

                    // Get a reference to the Firebase Realtime Database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String userId = currentUser.getUid();
                    DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
                    DatabaseReference savedCartRef = userRef.child("Cart_Product");

//                    Get the key of the product to delete
                    String cartKey = cartList.get(position).getCart_id();

//                    Delete the product from Firebase
                    savedCartRef.child(cartKey).removeValue();

//                    Remove the product from the list
                    cartList.remove(position);

//                    Notify the adapter that the data has changed
                    notifyDataSetChanged();

//                    Refresh the CartFragment
                    CartFragment cartFragment = new CartFragment();
                    FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, cartFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

            }
        });



        cart_product_layout_product_name.setText(cartList.get(position).getCart_Name());
        cart_product_layout_price.setText("₹ "+cartList.get(position).getCart_price());
        cart_product_layout_rating.setText(cartList.get(position).getCart_rating());

        Glide.with(context)
                .load(cartList.get(position).getCart_imageUrl())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cart_product_layout_image);

        cart_product_layout_delete.setOnClickListener(new View.OnClickListener() {
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

//                    Get the key of the product to delete
                String cartKey = cartList.get(position).getCart_id();

//                    Delete the product from Firebase
                savedCartRef.child(cartKey).removeValue();

                // Remove the product from the list
                cartList.remove(position);

                // Notify the adapter that the data has changed
                notifyDataSetChanged();

                // Refresh the CartFragment
                CartFragment cartFragment = new CartFragment();
                FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, cartFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cart_product_layout_image;
        TextView cart_product_layout_product_name,cart_product_layout_price,cart_product_layout_rating;
        private TextView elegantNumberButton;
        private Button decrementButton;
        private Button incrementButton;
        ImageView cart_product_layout_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_product_layout_image = itemView.findViewById(R.id.cart_product_layout_image);
            cart_product_layout_product_name = itemView.findViewById(R.id.cart_product_layout_product_name);
            cart_product_layout_price = itemView.findViewById(R.id.cart_product_layout_price);
            cart_product_layout_rating = itemView.findViewById(R.id.cart_product_layout_rating);
            cart_product_layout_delete = itemView.findViewById(R.id.cart_product_layout_delete);

            elegantNumberButton = itemView.findViewById(R.id.elegant_number_button);
            decrementButton = itemView.findViewById(R.id.card_decrement_button);
            incrementButton = itemView.findViewById(R.id.card_increment_button);

        }
    }
}
