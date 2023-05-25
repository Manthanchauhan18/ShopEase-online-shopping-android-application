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
import com.example.shopease.product_details.Product_details_tv_page;
import com.example.shopease.R;
import com.example.shopease.model.Tv;

import java.util.ArrayList;
import java.util.List;

public class Category_tv_card_adapter extends RecyclerView.Adapter<Category_tv_card_adapter.ViewHolder>{

    private Context context;
    private List<Tv> tv_list;

    ArrayList<String> tv_name_list;
    ArrayList<String> tv_price_list;
    ArrayList<String> tv_size_list;

    public Category_tv_card_adapter(List<Tv> tv_List, Context context, ArrayList<String> tv_name_list,ArrayList<String> tv_price_list,ArrayList<String> tv_size_list){
        this.context = context;
        this.tv_list = tv_List;
        this.tv_name_list = tv_name_list;
        this.tv_price_list = tv_price_list;
        this.tv_size_list = tv_size_list;

    }

    @NonNull
    @Override
    public Category_tv_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_tv_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_tv_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        ImageView user_home_tv_category_imageview = holder.user_home_tv_category_imageview;
        TextView user_home_tv_category_textview = holder.user_home_tv_category_textview;
        TextView user_home_tv_category_price = holder.user_home_tv_category_price;
        TextView user_home_tv_category_size = holder.user_home_tv_category_size;


        user_home_tv_category_textview.setText(tv_list.get(position).getTv_name());
        user_home_tv_category_price.setText("₹"+tv_list.get(position).getTv_price());
        user_home_tv_category_size.setText(tv_list.get(position).getTv_size());

        Glide.with(context)
                .load(tv_list.get(position).getImageUrl_tv())
                .into(user_home_tv_category_imageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new instance of the fragment
                Product_details_tv_page fragment = new Product_details_tv_page();

                // Create a new bundle to pass the data
                Bundle bundle = new Bundle();
                bundle.putString("image", tv_list.get(position).getImageUrl_tv());
                bundle.putString("name", tv_list.get(position).getTv_name());
                bundle.putString("price", tv_list.get(position).getTv_price());
                bundle.putString("size", tv_list.get(position).getTv_size());
                bundle.putString("rating" , tv_list.get(position).getTv_rating());
                bundle.putString("discription" , tv_list.get(position).getTv_discription());
                bundle.putString("stock" , tv_list.get(position).getTv_stock());

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
        return tv_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView user_home_tv_category_imageview;
        TextView user_home_tv_category_textview,user_home_tv_category_price,user_home_tv_category_size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_home_tv_category_imageview = itemView.findViewById(R.id.user_home_tv_category_imageview);
            user_home_tv_category_textview = itemView.findViewById(R.id.user_home_tv_category_textview);
            user_home_tv_category_price = itemView.findViewById(R.id.user_home_tv_category_price);
            user_home_tv_category_size = itemView.findViewById(R.id.user_home_tv_category_size);

        }
    }


}
