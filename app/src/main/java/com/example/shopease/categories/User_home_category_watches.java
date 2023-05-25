package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopease.R;

public class User_home_category_watches extends Fragment {

    ImageView btn_user_watches_category_back_image;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_home_category_watches, container, false);

        btn_user_watches_category_back_image = root.findViewById(R.id.btn_user_watches_category_back_image);

        btn_user_watches_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        return root;
    }
}