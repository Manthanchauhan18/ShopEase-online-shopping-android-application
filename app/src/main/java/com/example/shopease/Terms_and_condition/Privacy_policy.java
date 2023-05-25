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

public class Privacy_policy extends Fragment {

    ImageView btn_user_profile_privacy_back_image;
    TextView privacy_policy_shopease,information_we_collect_shopease,how_we_use_information_shopease,how_we_share_information_shopease,your_choices_shopease;
    TextView security_shopease,childrens_privacy_shopease,changes_to_this_policy_shopease,contact_us_shopease;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_privacy_policy, container, false);

        btn_user_profile_privacy_back_image = root.findViewById(R.id.btn_user_profile_privacy_back_image);

        btn_user_profile_privacy_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        privacy_policy_shopease = root.findViewById(R.id.privacy_policy_shopease);
        information_we_collect_shopease = root.findViewById(R.id.information_we_collect_shopease);
        how_we_use_information_shopease = root.findViewById(R.id.how_we_use_information_shopease);
        how_we_share_information_shopease = root.findViewById(R.id.how_we_share_information_shopease);
        your_choices_shopease = root.findViewById(R.id.your_choices_shopease);
        security_shopease = root.findViewById(R.id.security_shopease);
        childrens_privacy_shopease = root.findViewById(R.id.childrens_privacy_shopease);
        changes_to_this_policy_shopease = root.findViewById(R.id.changes_to_this_policy_shopease);
        contact_us_shopease = root.findViewById(R.id.contact_us_shopease);



        privacy_policy_shopease.setText("At ShopEase, we value your privacy and are committed to protecting your personal information." +
                " This Privacy Policy describes how we collect, use, and share information when you use our shopping Android application.");

        information_we_collect_shopease.setText("When you use ShopEase, we may collect the following types of information:\n" +
                "\n" +
                "\n- Personal Information: We may collect your name, email address, shipping address, and payment information when you create an account or place an order.\n" +
                "\n- Device Information: We may collect information about your device, including the type of device, operating system, and unique device identifier.\n" +
                "\n- Usage Information: We may collect information about how you use ShopEase, including the pages you view, the products you purchase, and the time and date of your activity.\n" +
                "\n- Location Information: We may collect your device's location if you enable location services in the app.");

        how_we_use_information_shopease.setText("We use the information we collect to provide and improve ShopEase, including:\n" +
                "\n" +
                "\n- Processing your orders and payments\n" +
                "\n- Communicating with you about your orders and account\n" +
                "\n- Personalizing your shopping experience\n" +
                "\n- Analyzing and improving the performance of ShopEase\n" +
                "\n- Complying with legal obligations");

        how_we_share_information_shopease.setText("We may share your information with third parties for the following purposes:\n" +
                "\n" +
                "\n- Service Providers: We may share your information with third-party service providers who help us operate ShopEase, such as payment processors and shipping providers.\n" +
                "\n- Legal Compliance: We may share your information to comply with legal obligations, such as responding to a subpoena or court order.\n" +
                "\n- Business Transfers: We may share your information in connection with a merger, acquisition, or sale of all or a portion of our assets.\n" +
                "\n- Aggregate Information: We may share aggregate information that does not personally identify you with third parties for research, marketing, or other purposes.");

        your_choices_shopease.setText("You can choose not to provide certain information to ShopEase, but this may limit your ability to use certain features of the app." +
                " You can also opt out of receiving marketing communications from us by following the instructions in the communication.");

        security_shopease.setText("We take reasonable measures to protect your information from unauthorized access, disclosure, or destruction. However," +
                " no security system is completely secure, and we cannot guarantee the security of your information.");


        childrens_privacy_shopease.setText("ShopEase is not intended for use by children under the age of 13. If we learn that we have collected personal information from a child under 13, " +
                "we will take steps to delete the information as soon as possible.");

        changes_to_this_policy_shopease.setText("We may update this Privacy Policy from time to time. If we make material changes, " +
                "we will notify you by email or by posting a notice in ShopEase.");


        contact_us_shopease.setText("If you have any questions or concerns about this Privacy Policy or our data practices, please contact us at shopEase@shopease-c4c3a.firebaseapp.com.");


        // Inflate the layout for this fragment
        return root;
    }
}