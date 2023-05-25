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
import com.example.shopease.model.Books;
import com.example.shopease.product_details.Product_details_books_page;
import com.example.shopease.product_details.Product_details_mobile_page;

import java.util.ArrayList;
import java.util.List;

public class Category_books_card_adapter extends RecyclerView.Adapter<Category_books_card_adapter.ViewHolder>{

    private Context context;
    private List<Books> books_list;

    ArrayList<String> books_name_list;
    ArrayList<String> books_price_list;

    public Category_books_card_adapter(List<Books> books_List, Context context, ArrayList<String> books_name_list, ArrayList<String> books_price_list){
        this.context = context;
        this.books_list = books_List;
        this.books_name_list = books_name_list;
        this.books_price_list = books_price_list;

    }

    @NonNull
    @Override
    public Category_books_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_books_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_books_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_books_category_imageview = holder.user_home_books_category_imageview;
        TextView user_home_books_category_textview = holder.user_home_books_category_textview;
        TextView user_home_books_category_price = holder.user_home_books_category_price;


        user_home_books_category_textview.setText(books_list.get(position).getBooks_name());
        user_home_books_category_price.setText("₹"+books_list.get(position).getBooks_price());

        Glide.with(context)
                .load(books_list.get(position).getBooks_imageUrl())
                .into(user_home_books_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_books_page fragment = new Product_details_books_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", books_list.get(position).getBooks_imageUrl());
                bundle.putString("name", books_list.get(position).getBooks_name());
                bundle.putString("price", books_list.get(position).getBooks_price());
                bundle.putString("rating" , books_list.get(position).getBooks_rating());
                bundle.putString("discription" , books_list.get(position).getBooks_discription());
                bundle.putString("stock",books_list.get(position).getBooks_stock());

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
        return books_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_home_books_category_imageview;
        TextView user_home_books_category_textview,user_home_books_category_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_books_category_imageview = itemView.findViewById(R.id.user_home_books_category_imageview);
            user_home_books_category_textview = itemView.findViewById(R.id.user_home_books_category_textview);
            user_home_books_category_price = itemView.findViewById(R.id.user_home_books_category_price);


        }
    }


}
