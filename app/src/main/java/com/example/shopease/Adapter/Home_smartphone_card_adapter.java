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
import com.example.shopease.model.Category;
import com.example.shopease.model.Upcoming_phones;
import com.example.shopease.product_details.Product_details_mobile_page;

import java.util.ArrayList;
import java.util.List;

public class Home_smartphone_card_adapter extends RecyclerView.Adapter<Home_smartphone_card_adapter.ViewHolder>{

    private Context context;
    private List<Upcoming_phones> home_smartphone_List;

    ArrayList<String> home_smartphone_name_list;

    public Home_smartphone_card_adapter(List<Upcoming_phones> smartphone_List, Context context,ArrayList<String> smartphone_name_list){
        this.context = context;
        this.home_smartphone_List = smartphone_List;
        this.home_smartphone_name_list = smartphone_name_list;

    }

    @NonNull
    @Override
    public Home_smartphone_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_smartphone_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Home_smartphone_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Upcoming_phones upcoming_phones = home_smartphone_List.get(position);
        ImageView home_category_card_image_1 = holder.home_smartphone_card_image_1;
        TextView home_category_card_txt_1 = holder.home_smartphone_card_txt_1;

        String name = home_smartphone_List.get(position).getSmartphone_name();


        home_category_card_txt_1.setText(home_smartphone_List.get(position).getSmartphone_name());

        Glide.with(context)
                .load(home_smartphone_List.get(position).getImageUrl_phone())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(home_category_card_image_1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_mobile_page fragment = new Product_details_mobile_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", home_smartphone_List.get(position).getImageUrl_phone());
                bundle.putString("name", home_smartphone_List.get(position).getSmartphone_name());
                bundle.putString("price",home_smartphone_List.get(position).getSmartphone_price());
                bundle.putString("storage",home_smartphone_List.get(position).getSmartphone_storage());
                bundle.putString("rating" ,home_smartphone_List.get(position).getSmartphone_rating());
                bundle.putString("discription" ,home_smartphone_List.get(position).getSmartphone_discription());
                bundle.putString("summary" ,home_smartphone_List.get(position).getSmartphone_summary());
                bundle.putString("stock" ,home_smartphone_List.get(position).getSmartphone_stock());

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
        return Math.min(home_smartphone_List.size(), 4);
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
