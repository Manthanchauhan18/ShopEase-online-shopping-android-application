package com.example.shopease.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopease.ElegantNumberButton;
import com.example.shopease.R;
import com.example.shopease.model.Address;
import com.example.shopease.model.Cart_product_layout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class saved_address_card_view extends RecyclerView.Adapter<saved_address_card_view.ViewHolder>{

    private Context context;
    private List<Address> address_List;

    ArrayList<String> addressMobileList;
    ArrayList<String> addressNameList;
    ArrayList<String> addressAddressList;

    int product_stock;

    private DatabaseReference mDatabase;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public saved_address_card_view(List<Address> address_List, Context context,ArrayList<String> addressNameList,ArrayList<String> addressMobileList,ArrayList<String> addressAddressList) {
        this.context = context;
        this.address_List = address_List;
        this.addressNameList = addressNameList;
        this.addressMobileList = addressMobileList;
        this.addressAddressList = addressAddressList;

    }

    @NonNull
    @Override
    public saved_address_card_view.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_addresses_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull saved_address_card_view.ViewHolder holder, int position) {

        TextView saved_address_card_view_name = holder.saved_address_card_view_name;
        TextView saved_address_card_view_address = holder.saved_address_card_view_address;
        TextView saved_address_card_view_mobile = holder.saved_address_card_view_mobile;

        saved_address_card_view_name.setText(address_List.get(position).getName());
        saved_address_card_view_mobile.setText("+91 "+address_List.get(position).getMobile());
        saved_address_card_view_address.setText(address_List.get(position).getFull_address());

    }

    @Override
    public int getItemCount() {
        return address_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView saved_address_card_view_name,saved_address_card_view_mobile,saved_address_card_view_address;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            saved_address_card_view_name = itemView.findViewById(R.id.saved_address_card_view_name);
            saved_address_card_view_mobile = itemView.findViewById(R.id.saved_address_card_view_mobile);
            saved_address_card_view_address = itemView.findViewById(R.id.saved_address_card_view_address);



        }
    }


}
