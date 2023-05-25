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
import com.example.shopease.R;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Upcoming_phones;
import com.example.shopease.product_details.Product_deatils_grocery_page;
import com.example.shopease.product_details.Product_details_mobile_page;

import java.util.ArrayList;
import java.util.List;

public class Home_grocery_card_adapter extends RecyclerView.Adapter<Home_grocery_card_adapter.ViewHolder>{

    private Context context;
    private List<Grocery> home_grocery_List;

    ArrayList<String> home_grocery_name_list;

    public Home_grocery_card_adapter(List<Grocery>home_grocery_List, Context context,ArrayList<String> grocery_name_list){
        this.context = context;
        this.home_grocery_List =home_grocery_List;
        this.home_grocery_name_list = grocery_name_list;

    }
    
    @NonNull
    @Override
    public Home_grocery_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_smartphone_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Home_grocery_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Grocery grocery = home_grocery_List.get(position);
        ImageView home_category_card_image_1 = holder.home_smartphone_card_image_1;
        TextView home_category_card_txt_1 = holder.home_smartphone_card_txt_1;

        String name = home_grocery_List.get(position).getGrocery_name();


        home_category_card_txt_1.setText(home_grocery_List.get(position).getGrocery_name());

        Glide.with(context)
                .load(home_grocery_List.get(position).getGrocery_imageUrl())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(home_category_card_image_1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_deatils_grocery_page fragment = new Product_deatils_grocery_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", home_grocery_List.get(position).getGrocery_imageUrl());
                bundle.putString("name",home_grocery_List.get(position).getGrocery_name());
                bundle.putString("price",home_grocery_List.get(position).getGrocery_price());
                bundle.putString("amount",home_grocery_List.get(position).getGrocery_amount());
                bundle.putString("rating" ,home_grocery_List.get(position).getGrocery_rating());
                bundle.putString("discription" ,home_grocery_List.get(position).getGrocery_discription());
                bundle.putString("stock",home_grocery_List.get(position).getGrocery_stock());

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
        return Math.min(home_grocery_List.size(), 4);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView home_smartphone_card_image_1;
        TextView home_smartphone_card_txt_1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            home_smartphone_card_image_1 = itemView.findViewById(R.id.home_smartphone_card_image_1);
            home_smartphone_card_txt_1 = itemView.findViewById(R.id.home_smartphone_card_txt_1);

        }
    }


}
