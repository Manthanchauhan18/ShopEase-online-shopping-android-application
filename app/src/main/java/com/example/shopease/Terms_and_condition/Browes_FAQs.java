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

public class Browes_FAQs extends Fragment {

    ImageView btn_user_profile_FAQs_back_image;
    TextView what_is_shopease,download_shopease,is_use_shopease,create_account_shopease,payment_system_shopease,return_shopease,track_order_shopease,customer_service_shopease;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_browes__f_a_qs, container, false);

        btn_user_profile_FAQs_back_image = root.findViewById(R.id.btn_user_profile_FAQs_back_image);

        btn_user_profile_FAQs_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        what_is_shopease = root.findViewById(R.id.what_is_shopease);
        download_shopease = root.findViewById(R.id.download_shopease);
        is_use_shopease = root.findViewById(R.id.is_use_shopease);
        create_account_shopease = root.findViewById(R.id.create_account_shopease);
        payment_system_shopease = root.findViewById(R.id.payment_system_shopease);
        return_shopease = root.findViewById(R.id.return_shopease);
        track_order_shopease = root.findViewById(R.id.track_order_shopease);
        customer_service_shopease = root.findViewById(R.id.customer_service_shopease);


        what_is_shopease.setText("ShopEase is a shopping application for Android devices that allows you to browse and purchase a variety of products from different retailers.");
        download_shopease.setText(" You can download ShopEase from the Google Play Store on your Android device.");
        is_use_shopease.setText("Yes, ShopEase is free to download and use. However, some retailers may charge for their products.");
        create_account_shopease.setText("Yes, you can create a ShopEase account to save your payment information, shipping address, and order history.");
        payment_system_shopease.setText("ShopEase accepts most major credit and debit cards, as well as payment services like PayPal and Google Pay.");
        return_shopease.setText("Each retailer on ShopEase has its own return and exchange policies. You can view these policies before making a purchase.");
        track_order_shopease.setText("You can track your orders on ShopEase by going to the \"Orders\" section of the app. From there, you can view the status of your orders and track any shipments.");
        customer_service_shopease.setText("You can contact customer service on ShopEase by going to the \"Help\" section of the app. From there, you can submit a support request or chat with a customer service representative.");


        // Inflate the layout for this fragment
        return root;
    }
}