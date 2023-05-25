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
import com.example.shopease.product_details.Product_deatils_grocery_page;
import com.example.shopease.R;
import com.example.shopease.model.Grocery;

import java.util.ArrayList;
import java.util.List;

public class Category_grocery_card_adapter extends RecyclerView.Adapter<Category_grocery_card_adapter.ViewHolder>{

    private Context context;
    private List<Grocery> grocery_list;

    ArrayList<String> grocery_name_list;
    ArrayList<String> grocery_price_list;
    ArrayList<String> grocery_amount_list;

    public Category_grocery_card_adapter(List<Grocery> grocery_List, Context context, ArrayList<String> grocery_name_list,ArrayList<String> grocery_price_list,ArrayList<String> grocery_amount_list){
        this.context = context;
        this.grocery_list = grocery_List;
        this.grocery_name_list = grocery_name_list;
        this.grocery_price_list = grocery_price_list;
        this.grocery_amount_list = grocery_amount_list;

    }

    @NonNull
    @Override
    public Category_grocery_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_grocery_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_grocery_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_grocery_category_imageview = holder.user_home_grocery_category_imageview;
        TextView user_home_grocery_category_textview = holder.user_home_grocery_category_textview;
        TextView user_home_grocery_category_price = holder.user_home_grocery_category_price;
        TextView user_home_grocery_category_amount = holder.user_home_grocery_category_amount;


        user_home_grocery_category_textview.setText(grocery_list.get(position).getGrocery_name());
        user_home_grocery_category_price.setText("₹"+grocery_list.get(position).getGrocery_price());
        user_home_grocery_category_amount.setText(grocery_list.get(position).getGrocery_amount());

        Glide.with(context)
                .load(grocery_list.get(position).getGrocery_imageUrl())
                .into(user_home_grocery_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_deatils_grocery_page fragment = new Product_deatils_grocery_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", grocery_list.get(position).getGrocery_imageUrl());
                bundle.putString("name", grocery_list.get(position).getGrocery_name());
                bundle.putString("price", grocery_list.get(position).getGrocery_price());
                bundle.putString("amount", grocery_list.get(position).getGrocery_amount());
                bundle.putString("rating" , grocery_list.get(position).getGrocery_rating());
                bundle.putString("discription" , grocery_list.get(position).getGrocery_discription());
                bundle.putString("stock", grocery_list.get(position).getGrocery_stock());

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
        return grocery_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_home_grocery_category_imageview;
        TextView user_home_grocery_category_textview,user_home_grocery_category_price,user_home_grocery_category_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_grocery_category_imageview = itemView.findViewById(R.id.user_home_grocery_category_imageview);
            user_home_grocery_category_textview = itemView.findViewById(R.id.user_home_grocery_category_textview);
            user_home_grocery_category_price = itemView.findViewById(R.id.user_home_grocery_category_price);
            user_home_grocery_category_amount = itemView.findViewById(R.id.user_home_grocery_category_amount);

        }
    }


}
