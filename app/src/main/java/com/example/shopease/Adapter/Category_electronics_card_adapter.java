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
import com.example.shopease.model.Electronics;
import com.example.shopease.model.Upcoming_phones;
import com.example.shopease.product_details.Product_details_electronics_page;
import com.example.shopease.product_details.Product_details_mobile_page;

import java.util.ArrayList;
import java.util.List;

public class Category_electronics_card_adapter extends RecyclerView.Adapter<Category_electronics_card_adapter.ViewHolder>{

    private Context context;
    private List<Electronics> electronics_list;

    ArrayList<String> electronics_name_list;
    ArrayList<String> electronics_price_list;

    public Category_electronics_card_adapter(List<Electronics> electronics_List, Context context, ArrayList<String> electronics_name_list,ArrayList<String> electronics_price_list){
        this.context = context;
        this.electronics_list = electronics_List;
        this.electronics_name_list = electronics_name_list;
        this.electronics_price_list= electronics_price_list;

    }
    

    @NonNull
    @Override
    public Category_electronics_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_appliance_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_electronics_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView user_home_appliance_category_imageview = holder.user_home_appliance_category_imageview;
        TextView user_home_appliance_category_textview = holder.user_home_appliance_category_textview;
        TextView user_home_appliance_category_price = holder.user_home_appliance_category_price;


        user_home_appliance_category_textview.setText(electronics_list.get(position).getElectronics_name());
        user_home_appliance_category_price.setText("₹"+electronics_list.get(position).getElectronics_price());

        Glide.with(context)
                .load(electronics_list.get(position).getImageUrl_electronics())
                .into(user_home_appliance_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_electronics_page fragment = new Product_details_electronics_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", electronics_list.get(position).getImageUrl_electronics());
                bundle.putString("name", electronics_list.get(position).getElectronics_name());
                bundle.putString("price", electronics_list.get(position).getElectronics_price());
                bundle.putString("rating" , electronics_list.get(position).getElectronics_rating());
                bundle.putString("discription" , electronics_list.get(position).getElectronics_discription());
                bundle.putString("stock",electronics_list.get(position).getElectronics_stock());

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
        return electronics_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView user_home_appliance_category_imageview;
        TextView user_home_appliance_category_textview,user_home_appliance_category_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_appliance_category_imageview = itemView.findViewById(R.id.user_home_appliance_category_imageview);
            user_home_appliance_category_textview = itemView.findViewById(R.id.user_home_appliance_category_textview);
            user_home_appliance_category_price = itemView.findViewById(R.id.user_home_appliance_category_price);

        }
    }


}
