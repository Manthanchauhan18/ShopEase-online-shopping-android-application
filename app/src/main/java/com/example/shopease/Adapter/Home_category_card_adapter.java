package com.example.shopease.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shopease.R;
import com.example.shopease.categories.User_home_category_Appliance;
import com.example.shopease.categories.User_home_category_Electronics;
import com.example.shopease.categories.User_home_category_Tv;
import com.example.shopease.categories.User_home_category_books;
import com.example.shopease.categories.User_home_category_fashion;
import com.example.shopease.categories.User_home_category_furniture;
import com.example.shopease.categories.User_home_category_mobile;
import com.example.shopease.categories.User_home_category_pharmacy;
import com.example.shopease.categories.User_home_category_toys;
import com.example.shopease.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Home_category_card_adapter extends RecyclerView.Adapter<Home_category_card_adapter.ViewHolder>{

    private Context context;
    private List<Category> home_category_List;

    ArrayList<String> home_category_name_list;

    public Home_category_card_adapter(List<Category> category_List, Context context,ArrayList<String> category_name_list){
        this.context = context;
        this.home_category_List = category_List;
        this.home_category_name_list = category_name_list;

    }

    @NonNull
    @Override
    public Home_category_card_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Home_category_card_adapter.ViewHolder holder, int position) {
        Category category = home_category_List.get(position);
        ImageView home_category_card_image_1 = holder.home_category_card_image_1;
        TextView home_category_card_txt_1 = holder.home_category_card_txt_1;

        String name = home_category_List.get(position).getCategory_name();


        home_category_card_txt_1.setText(home_category_List.get(position).getCategory_name());

        Glide.with(context)
                .load(home_category_List.get(position).getImageUrl())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(home_category_card_image_1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment newFragment = null;
                switch (name){
                    case "Mobile":
                        newFragment = new User_home_category_mobile();
                        break;

                    case "Fashion":
                        newFragment = new User_home_category_fashion();
                        break;

                    case "Furniture ":
                        newFragment = new User_home_category_furniture();
                        break;

                    case "Electronics":
                        newFragment = new User_home_category_Electronics();
                        break;

                    case "Appliances":
                        newFragment = new User_home_category_Appliance();
                        break;

                    case "Tv":
                        newFragment = new User_home_category_Tv();
                        break;

                    case "Pharmacy ":
                        newFragment = new User_home_category_pharmacy();
                        break;

                    case "Books":
                        newFragment = new User_home_category_books();
                        break;

                    case "Toys":
                        newFragment = new User_home_category_toys();
                        break;

                    default:
                        Toast.makeText(context, ""+name, Toast.LENGTH_SHORT).show();
                        return;
                }

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return Math.min(home_category_List.size(), 9);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView home_category_card_image_1;
        TextView home_category_card_txt_1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            home_category_card_image_1 = itemView.findViewById(R.id.home_category_card_image_1);
            home_category_card_txt_1 = itemView.findViewById(R.id.home_category_card_txt_1);

        }
    }



}
