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
import com.example.shopease.R;
import com.example.shopease.model.Pharmacy;
import com.example.shopease.product_details.Product_details_mobile_page;
import com.example.shopease.product_details.Product_details_pharmacy_page;

import java.util.ArrayList;
import java.util.List;

public class Category_pharmacy_card_adapter extends RecyclerView.Adapter<Category_pharmacy_card_adapter.ViewHolder>{

    private Context context;
    private List<Pharmacy> pharmacy_list;

    ArrayList<String> pharmacy_name_list;
    ArrayList<String> pharmacy_price_list;

    public Category_pharmacy_card_adapter(List<Pharmacy> pharmacy_List, Context context, ArrayList<String> pharmacy_name_list,ArrayList<String> pharmacy_price_list){
        this.context = context;
        this.pharmacy_list = pharmacy_List;
        this.pharmacy_name_list = pharmacy_name_list;
        this.pharmacy_price_list = pharmacy_price_list;

    }


    @NonNull
    @Override
    public Category_pharmacy_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_pharmacy_card_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_pharmacy_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_grocery_category_imageview = holder.user_home_grocery_category_imageview;
        TextView user_home_grocery_category_textview = holder.user_home_grocery_category_textview;
        TextView user_home_grocery_category_price = holder.user_home_grocery_category_price;


        user_home_grocery_category_textview.setText(pharmacy_list.get(position).getPharmacy_name());
        user_home_grocery_category_price.setText("₹"+pharmacy_list.get(position).getPharmacy_price());

        Glide.with(context)
                .load(pharmacy_list.get(position).getPharmacy_imageUrl())
                .into(user_home_grocery_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_pharmacy_page fragment = new Product_details_pharmacy_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", pharmacy_list.get(position).getPharmacy_imageUrl());
                bundle.putString("name", pharmacy_list.get(position).getPharmacy_name());
                bundle.putString("price", pharmacy_list.get(position).getPharmacy_price());
                bundle.putString("rating" , pharmacy_list.get(position).getPharmacy_rating());
                bundle.putString("discription" , pharmacy_list.get(position).getPharmacy_discription());
                bundle.putString("stock",pharmacy_list.get(position).getPharmacy_stock());

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
        return pharmacy_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView user_home_grocery_category_imageview;
        TextView user_home_grocery_category_textview,user_home_grocery_category_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_grocery_category_imageview = itemView.findViewById(R.id.user_home_grocery_category_imageview);
            user_home_grocery_category_textview = itemView.findViewById(R.id.user_home_grocery_category_textview);
            user_home_grocery_category_price = itemView.findViewById(R.id.user_home_grocery_category_price);

        }
    }


}
