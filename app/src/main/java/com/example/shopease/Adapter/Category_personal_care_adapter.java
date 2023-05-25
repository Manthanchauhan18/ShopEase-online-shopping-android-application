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
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Personal_Care;
import com.example.shopease.product_details.Product_details_mobile_page;
import com.example.shopease.product_details.Product_details_pesonal_care_page;

import java.util.ArrayList;
import java.util.List;

public class Category_personal_care_adapter extends RecyclerView.Adapter<Category_personal_care_adapter.ViewHolder>{

    private Context context;
    private List<Personal_Care> personal_care_list;

    ArrayList<String> personal_care_name_list;
    ArrayList<String> personal_care_price_list;
    ArrayList<String> personal_care_weight_list;

    public Category_personal_care_adapter(ArrayList<Personal_Care> personal_care_List, Context context, ArrayList<String> personal_care_name_list, ArrayList<String> personal_care_price_list, ArrayList<String> personal_care_weight_list){
        this.context = context;
        this.personal_care_list = personal_care_List;
        this.personal_care_name_list = personal_care_name_list;
        this.personal_care_price_list = personal_care_price_list;
        this.personal_care_weight_list = personal_care_weight_list;

    }

    @NonNull
    @Override
    public Category_personal_care_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_personal_care_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_personal_care_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_personal_care_category_imageview = holder.user_home_personal_care_category_imageview;
        TextView user_home_personal_care_category_textview = holder.user_home_personal_care_category_textview;
        TextView user_home_personal_care_category_price = holder.user_home_personal_care_category_price;
        TextView user_home_personal_care_category_weight = holder.user_home_personal_care_category_weight;


        user_home_personal_care_category_textview.setText(personal_care_list.get(position).getPersonal_care_name());
        user_home_personal_care_category_price.setText("₹"+personal_care_list.get(position).getPersonal_care_price());
        user_home_personal_care_category_weight.setText(personal_care_list.get(position).getPersonal_care_weight());

        Glide.with(context)
                .load(personal_care_list.get(position).getPersonal_care_imageUrl())
                .into(user_home_personal_care_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_pesonal_care_page fragment = new Product_details_pesonal_care_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", personal_care_list.get(position).getPersonal_care_imageUrl());
                bundle.putString("name", personal_care_list.get(position).getPersonal_care_name());
                bundle.putString("price", personal_care_list.get(position).getPersonal_care_price());
                bundle.putString("weight", personal_care_list.get(position).getPersonal_care_weight());
                bundle.putString("rating" , personal_care_list.get(position).getPersonal_care_rating());
                bundle.putString("discription" , personal_care_list.get(position).getPersonal_care_discription());
                bundle.putString("stock",personal_care_list.get(position).getPersonal_care_stock());

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
        return personal_care_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_home_personal_care_category_imageview;
        TextView user_home_personal_care_category_textview,user_home_personal_care_category_price,user_home_personal_care_category_weight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_personal_care_category_imageview = itemView.findViewById(R.id.user_home_personal_care_category_imageview);
            user_home_personal_care_category_textview = itemView.findViewById(R.id.user_home_personal_care_category_textview);
            user_home_personal_care_category_price = itemView.findViewById(R.id.user_home_personal_care_category_price);
            user_home_personal_care_category_weight = itemView.findViewById(R.id.user_home_personal_care_category_weight);

        }
    }


}
