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
import com.example.shopease.model.Appliance;
import com.example.shopease.model.Furniture;
import com.example.shopease.product_details.Product_details_furniture_page;
import com.example.shopease.product_details.Product_details_mobile_page;

import java.util.ArrayList;
import java.util.List;

public class Category_furniture_card_adapter extends RecyclerView.Adapter<Category_furniture_card_adapter.ViewHolder>{

    private Context context;
    private List<Furniture> furniture_list;

    ArrayList<String> furniture_name_list;
    ArrayList<String> furniture_price_list;

    public Category_furniture_card_adapter(List<Furniture> furniture_List, Context context, ArrayList<String> furniture_name_list, ArrayList<String> furniture_price_list){
        this.context = context;
        this.furniture_list = furniture_List;
        this.furniture_name_list = furniture_name_list;
        this.furniture_price_list = furniture_price_list;

    }

    @NonNull
    @Override
    public Category_furniture_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_furniture_category_card_view,parent,false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull Category_furniture_card_adapter.ViewHolder holder, int position) {

        ImageView user_home_furniture_category_imageview = holder.user_home_furniture_category_imageview;
        TextView user_home_furniture_category_textview = holder.user_home_furniture_category_textview;
        TextView user_home_furniture_category_price = holder.user_home_furniture_category_price;


        user_home_furniture_category_textview.setText(furniture_list.get(position).getFurniture_name());
        user_home_furniture_category_price.setText("₹"+furniture_list.get(position).getFurniture_price());

        Glide.with(context)
                .load(furniture_list.get(position).getFurniture_imageUrl())
                .into(user_home_furniture_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_furniture_page fragment = new Product_details_furniture_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", furniture_list.get(position).getFurniture_imageUrl());
                bundle.putString("name", furniture_list.get(position).getFurniture_name());
                bundle.putString("price", furniture_list.get(position).getFurniture_price());
                bundle.putString("rating" , furniture_list.get(position).getFurniture_rating());
                bundle.putString("discription" , furniture_list.get(position).getFurniture_discription());
                bundle.putString("stock",furniture_list.get(position).getFurniture_stock());

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
        return furniture_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_home_furniture_category_imageview;
        TextView user_home_furniture_category_textview,user_home_furniture_category_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_furniture_category_imageview = itemView.findViewById(R.id.user_home_furniture_category_imageview);
            user_home_furniture_category_textview = itemView.findViewById(R.id.user_home_furniture_category_textview);
            user_home_furniture_category_price = itemView.findViewById(R.id.user_home_furniture_category_price);


        }
    }


}
