package com.example.shopease.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shopease.R;
import com.example.shopease.model.Offers;
import com.example.shopease.model.Wishlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Profile_imageslider_card_adapter extends RecyclerView.Adapter<Profile_imageslider_card_adapter.ViewHolder>{

    private Context context;
    private List<Offers> offers_List;

    private DatabaseReference mDatabase;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public Profile_imageslider_card_adapter(List<Offers> offers_List, Context context) {
        this.context = context;
        this.offers_List = offers_List;

    }

    @NonNull
    @Override
    public Profile_imageslider_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imageslider_home_offer,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Profile_imageslider_card_adapter.ViewHolder holder, int position) {
        ImageView cardview_imageslider_offer = holder.cardview_imageslider_offer;

        Glide.with(context)
                .load(offers_List.get(position).getImageUrl())
                .into(cardview_imageslider_offer);
    }

    @Override
    public int getItemCount() {
        return offers_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cardview_imageslider_offer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardview_imageslider_offer = itemView.findViewById(R.id.cardview_imageslider_offer);

        }
    }


}
