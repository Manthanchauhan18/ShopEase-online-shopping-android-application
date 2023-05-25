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
import com.example.shopease.model.Sports;
import com.example.shopease.product_details.Product_details_mobile_page;
import com.example.shopease.product_details.Product_details_sports_page;

import java.util.ArrayList;
import java.util.List;

public class Category_sports_card_adapter extends RecyclerView.Adapter<Category_sports_card_adapter.ViewHolder>{

    private Context context;
    private List<Sports> sports_list;

    ArrayList<String> sports_name_list;
    ArrayList<String> sports_price_list;

    public Category_sports_card_adapter(List<Sports> sports_list, Context context, ArrayList<String> sports_name_list,ArrayList<String> sports_price_list){
        this.context = context;
        this.sports_list = sports_list;
        this.sports_name_list = sports_name_list;
        this.sports_price_list = sports_price_list;

    }

    @NonNull
    @Override
    public Category_sports_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_furniture_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_sports_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_furniture_category_imageview = holder.user_home_furniture_category_imageview;
        TextView user_home_furniture_category_textview = holder.user_home_furniture_category_textview;
        TextView user_home_furniture_category_price = holder.user_home_furniture_category_price;


        user_home_furniture_category_textview.setText(sports_list.get(position).getSports_name());
        user_home_furniture_category_price.setText("₹"+sports_list.get(position).getSports_price());

        Glide.with(context)
                .load(sports_list.get(position).getSports_imageUrl())
                .into(user_home_furniture_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_sports_page fragment = new Product_details_sports_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", sports_list.get(position).getSports_imageUrl());
                bundle.putString("name", sports_list.get(position).getSports_name());
                bundle.putString("price", sports_list.get(position).getSports_price());
                bundle.putString("rating" , sports_list.get(position).getSports_rating());
                bundle.putString("discription" , sports_list.get(position).getSports_discription());
                bundle.putString("stock" , sports_list.get(position).getSports_stock());

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
        return sports_list.size();
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
