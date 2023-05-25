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
import com.example.shopease.model.Deals;
import com.example.shopease.product_details.Product_details_appliance_page;
import com.example.shopease.product_details.Product_details_day_deals;

import java.util.ArrayList;
import java.util.List;

public class Deals_of_the_day_card_adapter extends RecyclerView.Adapter<Deals_of_the_day_card_adapter.ViewHolder>{

    private Context context;
    private List<Deals> deals_of_the_day_list;

    ArrayList<String> deals_of_the_day_name_list;
    ArrayList<String> deals_of_the_day_price_list;

    public Deals_of_the_day_card_adapter(List<Deals> deals_of_the_day_List, Context context, ArrayList<String> deals_of_the_day_name_list, ArrayList<String> deals_of_the_day_price_list){
        this.context = context;
        this.deals_of_the_day_list = deals_of_the_day_List;
        this.deals_of_the_day_name_list = deals_of_the_day_name_list;
        this.deals_of_the_day_price_list = deals_of_the_day_price_list;

    }
    
    @NonNull
    @Override
    public Deals_of_the_day_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_deals_of_the_day_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Deals_of_the_day_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        ImageView user_home_deals_of_the_day_imageview = holder.user_home_deals_of_the_day_imageview;
        TextView user_home_deals_of_the_day_textview = holder.user_home_deals_of_the_day_textview;
        TextView user_home_deals_of_the_day_price = holder.user_home_deals_of_the_day_price;
        TextView user_home_deals_of_the_day_discount = holder.user_home_deals_of_the_day_discount;


        user_home_deals_of_the_day_textview.setText(deals_of_the_day_list.get(position).getDeals_name());
        user_home_deals_of_the_day_price.setText("₹"+deals_of_the_day_list.get(position).getDeals_price());
        user_home_deals_of_the_day_discount.setText("-"+deals_of_the_day_list.get(position).getDeals_discount()+"%");

        Glide.with(context)
                .load(deals_of_the_day_list.get(position).getImageUrl())
                .into(user_home_deals_of_the_day_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_day_deals fragment = new Product_details_day_deals();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", deals_of_the_day_list.get(position).getImageUrl());
                bundle.putString("name", deals_of_the_day_list.get(position).getDeals_name());
                bundle.putString("price", deals_of_the_day_list.get(position).getDeals_price());
                bundle.putString("rating", deals_of_the_day_list.get(position).getDeals_rating());
                bundle.putString("discription", deals_of_the_day_list.get(position).getDeals_discription());
                bundle.putString("stock", deals_of_the_day_list.get(position).getDeals_stock());
                bundle.putString("discount", deals_of_the_day_list.get(position).getDeals_discount());


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
        return deals_of_the_day_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView user_home_deals_of_the_day_imageview;
        TextView user_home_deals_of_the_day_textview,user_home_deals_of_the_day_price,user_home_deals_of_the_day_discount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_deals_of_the_day_imageview = itemView.findViewById(R.id.user_home_deals_of_the_day_imageview);
            user_home_deals_of_the_day_textview = itemView.findViewById(R.id.user_home_deals_of_the_day_textview);
            user_home_deals_of_the_day_price = itemView.findViewById(R.id.user_home_deals_of_the_day_price);
            user_home_deals_of_the_day_discount = itemView.findViewById(R.id.user_home_deals_of_the_day_discount);

        }
    }
    
    
}
