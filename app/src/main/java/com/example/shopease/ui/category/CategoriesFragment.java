package com.example.shopease.ui.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopease.Adapter.Category_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    ArrayList<Category> category_list;
    RecyclerView categories_recyclerview;
    DatabaseReference dbref;

    ArrayList<String> category_name = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        dbref = FirebaseDatabase.getInstance().getReference("Category");

        categories_recyclerview = root.findViewById(R.id.categories_recyclerview);

        categories_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        categories_recyclerview.setItemAnimator(new DefaultItemAnimator());

        category_list = new ArrayList<>();
        categories_recyclerview.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category_model = snapshot.getValue(Category.class);
                    category_list.add(category_model);
                }
                categories_recyclerview.setAdapter(new Category_card_adapter(category_list, getActivity(),category_name));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        return root;
    }
}