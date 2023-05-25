package com.example.shopease.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shopease.R;
import com.example.shopease.model.Category;
import com.example.shopease.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class Show_user_card_adapter extends RecyclerView.Adapter<Show_user_card_adapter.ViewHolder>{

    private Context context;
    private List<User> show_user_List;
    int counter = 1;

    ArrayList<String> show_user_email_list;
    private DatabaseReference mDatabase;

    public Show_user_card_adapter(List<User> user_List, Context context,ArrayList<String> user_email_list){
        this.context = context;
        this.show_user_List = user_List;
        this.show_user_email_list = user_email_list;

    }

    @NonNull
    @Override
    public Show_user_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_user_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Show_user_card_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        String email = show_user_List.get(position).getEmail();
        ImageView image_view_delete_user = holder.image_view_delete_user;
        TextView show_user_txt_user_email = holder.show_user_txt_user_email;
        TextView show_user_txt_index = holder.show_user_txt_index;

        show_user_txt_index.setText(counter+".");
        show_user_txt_user_email.setText(show_user_List.get(position).getEmail());


        // Set click listener on the delete icon
        image_view_delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Get the key of the product to delete
//                String cartKey = show_user_List.get(position).getId();
//
//                // Delete the product from Firebase
//                mDatabase.child("Cart Product").child(cartKey).removeValue();
//
//                // Remove the product from the list
//                show_user_List.remove(position);
//
//                // Notify the adapter that the data has changed
//                notifyDataSetChanged();

                // Delete the user from the list and update the adapter
                show_user_List.remove(position);
                notifyItemRemoved(position);
            }
        });

        counter++;


    }

    @Override
    public int getItemCount() {
        return show_user_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView show_user_txt_user_email,show_user_txt_index;
        ImageView image_view_delete_user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_view_delete_user = itemView.findViewById(R.id.image_view_delete_user);
            show_user_txt_user_email = itemView.findViewById(R.id.show_user_txt_user_email);
            show_user_txt_index = itemView.findViewById(R.id.show_user_txt_index);

        }
    }


}
