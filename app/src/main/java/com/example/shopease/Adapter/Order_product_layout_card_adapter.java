package com.example.shopease.Adapter;

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
import com.example.shopease.model.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Order_product_layout_card_adapter extends RecyclerView.Adapter<Order_product_layout_card_adapter.ViewHolder>{

    private Context context;
    private List<Orders> order_list;

    ArrayList<String> orderPriceList;
    ArrayList<String> orderNameList;
    ArrayList<String> orderRatingList;
    ArrayList<String> user_address;

    int product_stock;

    private DatabaseReference mDatabase;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public Order_product_layout_card_adapter(List<Orders> order_list, Context context,ArrayList<String> orderPriceList,ArrayList<String> orderNameList,ArrayList<String> orderRatingList,ArrayList<String> user_address) {
        this.context = context;
        this.order_list = order_list;
        this.orderNameList = orderNameList;
        this.orderPriceList = orderPriceList;
        this.orderRatingList = orderRatingList;
        this.user_address = user_address;

    }
    
    @NonNull
    @Override
    public Order_product_layout_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_product_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Order_product_layout_card_adapter.ViewHolder holder, int position) {

        ImageView order_product_layout_image = holder.order_product_layout_image;
        TextView order_product_layout_product_name = holder.order_product_layout_product_name;
        TextView order_product_layout_price = holder.order_product_layout_price;
        TextView order_product_layout_quantity = holder.order_product_layout_quantity;
        TextView order_product_layout_order_id = holder.order_product_layout_order_id;
        TextView order_product_layout_transaction_id = holder.order_product_layout_transaction_id;
        TextView order_product_layout_address = holder.order_product_layout_address;
        TextView order_product_layout_order_time = holder.order_product_layout_order_time;

        order_product_layout_product_name.setText(order_list.get(position).getProduct_name());
        order_product_layout_price.setText("₹ "+order_list.get(position).getProduct_price());
        order_product_layout_quantity.setText(order_list.get(position).getProduct_quantity());
        order_product_layout_transaction_id.setText(order_list.get(position).getTransaction_id());
        order_product_layout_order_id.setText(order_list.get(position).getOrder_id());
        order_product_layout_address.setText(order_list.get(position).getUser_address());
        order_product_layout_order_time.setText(order_list.get(position).getTimestrap());

        Glide.with(context)
                .load(order_list.get(position).getImageUrl())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(order_product_layout_image);
        
    }

    @Override
    public int getItemCount() {
        return order_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        ImageView order_product_layout_image;
        TextView order_product_layout_product_name,order_product_layout_price,order_product_layout_quantity,order_product_layout_order_id,order_product_layout_transaction_id,order_product_layout_address,order_product_layout_order_time;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            order_product_layout_image = itemView.findViewById(R.id.order_product_layout_image);
            order_product_layout_product_name = itemView.findViewById(R.id.order_product_layout_product_name);
            order_product_layout_price = itemView.findViewById(R.id.order_product_layout_price);
            order_product_layout_quantity = itemView.findViewById(R.id.order_product_layout_quantity);
            order_product_layout_order_id = itemView.findViewById(R.id.order_product_layout_order_id);
            order_product_layout_transaction_id = itemView.findViewById(R.id.order_product_layout_transaction_id);
            order_product_layout_address = itemView.findViewById(R.id.order_product_layout_address);
            order_product_layout_order_time = itemView.findViewById(R.id.order_product_layout_order_time);

            
            
        }
    }
}
