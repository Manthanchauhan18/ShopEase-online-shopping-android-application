package com.example.shopease.Terms_and_condition;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.R;


public class Infringement_Policy extends Fragment {

    ImageView btn_user_profile_infringement_back_image;
    TextView infringement_policy_shopease,copyright_shopease,trademark_shopease,conturefeit_shopease,patent_shopease,repeat_shopease,contact_us_infrigement_shopease;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_infringement__policy, container, false);

        btn_user_profile_infringement_back_image = root.findViewById(R.id.btn_user_profile_infringement_back_image);

        btn_user_profile_infringement_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        infringement_policy_shopease = root.findViewById(R.id.infringement_policy_shopease);
        copyright_shopease = root.findViewById(R.id.copyright_shopease);
        trademark_shopease = root.findViewById(R.id.trademark_shopease);
        conturefeit_shopease = root.findViewById(R.id.conturefeit_shopease);
        patent_shopease = root.findViewById(R.id.patent_shopease);
        repeat_shopease = root.findViewById(R.id.repeat_shopease);
        contact_us_infrigement_shopease = root.findViewById(R.id.contact_us_infrigement_shopease);



        infringement_policy_shopease.setText("At ShopEase, we respect the intellectual property rights of others and expect our users to do the same." +
                " We have established this infringement policy to ensure that our platform is a safe and legal environment for everyone.");

        copyright_shopease.setText("ShopEase does not permit users to post any content that infringes upon any third-party copyright. If we receive a notice of copyright infringement," +
                " we may remove the infringing material and take appropriate action against the user who posted it.");

        trademark_shopease.setText("ShopEase does not permit users to use any trademark or logo that belongs to another company or entity without the permission of the owner. " +
                "If we receive a notice of trademark infringement, we may remove the infringing content and take appropriate action against the user who posted it.");

        conturefeit_shopease.setText("ShopEase strictly prohibits the sale of counterfeit goods on our platform." +
                " If we discover any products that are suspected of being counterfeit, we may remove the product and take appropriate action against the seller.");

        patent_shopease.setText("ShopEase does not permit users to post any content that infringes upon any third-party patent. If we receive a notice of patent infringement," +
                " we may remove the infringing content and take appropriate action against the user who posted it.");

        repeat_shopease.setText("ShopEase may terminate the accounts of users who repeatedly infringe upon the intellectual property rights of others.");

        contact_us_infrigement_shopease.setText("If you believe that your intellectual property rights have been infringed upon on ShopEase, please contact us immediately at shopEase@shopease-c4c3a.firebaseapp.com." +
                " We take all claims of infringement seriously and will take appropriate action as necessary.");


        // Inflate the layout for this fragment
        return root;
    }
}