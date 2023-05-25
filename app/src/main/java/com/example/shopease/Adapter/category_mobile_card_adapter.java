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
import com.example.shopease.product_details.Product_details_mobile_page;
import com.example.shopease.R;
import com.example.shopease.model.Upcoming_phones;

import java.util.ArrayList;
import java.util.List;

public class category_mobile_card_adapter extends RecyclerView.Adapter<category_mobile_card_adapter.ViewHolder>{

    private Context context;
    private List<Upcoming_phones> phone_list;

    ArrayList<String> phone_name_list;
    ArrayList<String> phone_price_list;
    ArrayList<String> phone_storage_list;

    public category_mobile_card_adapter(List<Upcoming_phones> phone_List, Context context, ArrayList<String> phone_name_list,ArrayList<String> phone_price_list,ArrayList<String> phone_storage_list){
        this.context = context;
        this.phone_list = phone_List;
        this.phone_name_list = phone_name_list;
        this.phone_price_list = phone_price_list;
        this.phone_storage_list = phone_storage_list;

    }

    @NonNull
    @Override
    public category_mobile_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_mobile_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull category_mobile_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_mobile_category_imageview = holder.user_home_mobile_category_imageview;
        TextView user_home_mobile_category_textview = holder.user_home_mobile_category_textview;
        TextView user_home_mobile_category_price = holder.user_home_mobile_category_price;
        TextView user_home_mobile_category_storage = holder.user_home_mobile_category_storage;


        user_home_mobile_category_textview.setText(phone_list.get(position).getSmartphone_name());
        user_home_mobile_category_price.setText("₹"+phone_list.get(position).getSmartphone_price());
        user_home_mobile_category_storage.setText(phone_list.get(position).getSmartphone_storage()+" GB");

        Glide.with(context)
                .load(phone_list.get(position).getImageUrl_phone())
                .into(user_home_mobile_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_mobile_page fragment = new Product_details_mobile_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", phone_list.get(position).getImageUrl_phone());
                bundle.putString("name", phone_list.get(position).getSmartphone_name());
                bundle.putString("price", phone_list.get(position).getSmartphone_price());
                bundle.putString("storage", phone_list.get(position).getSmartphone_storage());
                bundle.putString("rating" , phone_list.get(position).getSmartphone_rating());
                bundle.putString("discription" , phone_list.get(position).getSmartphone_discription());
                bundle.putString("summary" , phone_list.get(position).getSmartphone_summary());
                bundle.putString("stock" , phone_list.get(position).getSmartphone_stock());

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
        return phone_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_home_mobile_category_imageview;
        TextView user_home_mobile_category_textview,user_home_mobile_category_price,user_home_mobile_category_storage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_mobile_category_imageview = itemView.findViewById(R.id.user_home_mobile_category_imageview);
            user_home_mobile_category_textview = itemView.findViewById(R.id.user_home_mobile_category_textview);
            user_home_mobile_category_price = itemView.findViewById(R.id.user_home_mobile_category_price);
            user_home_mobile_category_storage = itemView.findViewById(R.id.user_home_mobile_category_storage);

        }
    }


}
