package com.example.shopease.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shopease.product_details.Product_details_mobile_page;
import com.example.shopease.R;
import com.example.shopease.model.Upcoming_phones;

import java.util.ArrayList;
import java.util.List;

public class Smartphone_card_adapter extends RecyclerView.Adapter<com.example.shopease.Adapter.Smartphone_card_adapter.ViewHolder>{

        private Context context;
        private List<Upcoming_phones> cart_phone_list;

        ArrayList<String> phone_name_list;
        ArrayList<String> phone_price_list;
        ArrayList<String> phone_storage_list;

    public Smartphone_card_adapter(ArrayList<Upcoming_phones> phone_list, FragmentActivity activity, ArrayList<String> phone_name, ArrayList<String> phone_price, ArrayList<String> phone_storage) {
        this.context = activity;
        this.cart_phone_list = phone_list;
        this.phone_name_list = phone_name;
        this.phone_price_list = phone_price;
        this.phone_storage_list = phone_storage;


    }


    @NonNull
    @Override
    public Smartphone_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_fragment_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Smartphone_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_cart_mobile_image = holder.user_cart_mobile_image;
        TextView user_cart_txt_mobile_name = holder.user_cart_txt_mobile_name;
        TextView user_cart_txt_mobile_price = holder.user_cart_txt_mobile_price;
        TextView user_cart_mobile_storage = holder.user_cart_mobile_storage;


        user_cart_txt_mobile_name.setText(cart_phone_list.get(position).getSmartphone_name());
        user_cart_txt_mobile_price.setText("₹"+cart_phone_list.get(position).getSmartphone_price());
        user_cart_mobile_storage.setText(cart_phone_list.get(position).getSmartphone_storage()+" GB");

        Glide.with(context)
                .load(cart_phone_list.get(position).getImageUrl_phone())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(user_cart_mobile_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_mobile_page fragment = new Product_details_mobile_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", cart_phone_list.get(position).getImageUrl_phone());
                bundle.putString("name", cart_phone_list.get(position).getSmartphone_name());
                bundle.putString("price", cart_phone_list.get(position).getSmartphone_price());
                bundle.putString("storage", cart_phone_list.get(position).getSmartphone_storage());
                bundle.putString("rating" , cart_phone_list.get(position).getSmartphone_rating());
                bundle.putString("discription" , cart_phone_list.get(position).getSmartphone_discription());
                bundle.putString("summary" , cart_phone_list.get(position).getSmartphone_summary());
                bundle.putString("stock" , cart_phone_list.get(position).getSmartphone_stock());

                // Attach the bundle to the fragment
                fragment.setArguments(bundle);

                // Start a new fragment transaction to display the fragment
                FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return Math.min(cart_phone_list.size(), 9);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_cart_mobile_image;
        TextView user_cart_txt_mobile_name,user_cart_txt_mobile_price,user_cart_mobile_storage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_cart_mobile_image = itemView.findViewById(R.id.user_cart_mobile_image);
            user_cart_txt_mobile_name = itemView.findViewById(R.id.user_cart_txt_mobile_name);
            user_cart_txt_mobile_price = itemView.findViewById(R.id.user_cart_txt_mobile_price);
            user_cart_mobile_storage = itemView.findViewById(R.id.user_cart_mobile_storage);

        }
    }


}
