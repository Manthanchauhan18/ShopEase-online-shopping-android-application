package com.example.shopease.Terms_and_condition;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.R;

public class Return_policy extends Fragment {

    ImageView btn_user_profile_return_back_image;
    TextView return_policy_shopease,initialize_return_exchange_shopease,contact_us_return_shopease;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_return_policy, container, false);

        btn_user_profile_return_back_image = root.findViewById(R.id.btn_user_profile_return_back_image);

        btn_user_profile_return_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        return_policy_shopease = root.findViewById(R.id.return_policy_shopease);
        initialize_return_exchange_shopease = root.findViewById(R.id.initialize_return_exchange_shopease);
        contact_us_return_shopease = root.findViewById(R.id.contact_us_return_shopease);

        return_policy_shopease.setText("- We want you to be completely satisfied with your purchase on myShop. " +
                "If you are not satisfied, we offer a hassle-free return policy to ensure your complete satisfaction.\n" +
                "\n" +
                "- For any product purchased from myShop, you can return it within 30 days of delivery for a full refund or exchange. " +
                "However, certain products such as electronics and accessories are subject to specific return conditions which will be mentioned in the product description.");


        initialize_return_exchange_shopease.setText("Contact our customer support team within 30 days of delivery and explain the reason for your return or exchange.\n" +
                "\n" +
                "1. Once your return is approved, you will receive a return authorization code along with instructions on how to return your item.\n" +
                "\n" +
                "2. Please ensure that the item is in its original packaging, unused, and in the same condition as you received it.\n" +
                "\n" +
                "3. Ship the item back to us using a reliable shipping service with tracking information. Once we receive the item and verify its condition, we will process your refund or exchange within 3 business days.");


        contact_us_return_shopease.setText("Please note that we do not cover the cost of return shipping unless the item was received damaged, defective or if the wrong item was sent to you.\n" +
                "\n" +
                "If you have any further questions or concerns about our return policy, please don't hesitate to contact our customer support team. We're always here to help!");



        // Inflate the layout for this fragment
        return root;
    }
}