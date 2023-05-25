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

public class terms_of_use extends Fragment {

    ImageView btn_user_profile_terms_back_image;
    TextView terms_of_use_shopease , acceptance_of_terms_shopease,use_of_shopease,user_account_shopease,itellectual_property_shopease,user_generated_content_shopease;
    TextView disclaimers_and_limitaion_shopease,termination_shopease,governing_law_shopease,changes_to_terms_shopease;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_terms_of_use, container, false);

        btn_user_profile_terms_back_image = root.findViewById(R.id.btn_user_profile_terms_back_image);

        btn_user_profile_terms_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        terms_of_use_shopease = root.findViewById(R.id.terms_of_use_shopease);
        acceptance_of_terms_shopease = root.findViewById(R.id.acceptance_of_terms_shopease);
        use_of_shopease = root.findViewById(R.id.use_of_shopease);
        user_account_shopease = root.findViewById(R.id.user_account_shopease);
        itellectual_property_shopease = root.findViewById(R.id.itellectual_property_shopease);
        user_generated_content_shopease = root.findViewById(R.id.user_generated_content_shopease);

        disclaimers_and_limitaion_shopease = root.findViewById(R.id.disclaimers_and_limitaion_shopease);
        termination_shopease = root.findViewById(R.id.termination_shopease);
        governing_law_shopease = root.findViewById(R.id.governing_law_shopease);
        changes_to_terms_shopease = root.findViewById(R.id.changes_to_terms_shopease);


        terms_of_use_shopease.setText("Welcome to ShopEase, an online shopping application operated by ShopEase." +
                " By using ShopEase, you agree to be bound by the following terms and conditions:");

        acceptance_of_terms_shopease.setText("By accessing or using ShopEase, you agree to be bound by these Terms of Use and all applicable laws and regulations. " +
                "If you do not agree to these Terms of Use, you should not use ShopEase.");

        use_of_shopease.setText("You may use ShopEase for lawful purposes only. You are prohibited from using ShopEase for any illegal or unauthorized purpose, " +
                "including but not limited to violating any intellectual property rights, infringing upon privacy rights, or engaging in fraudulent activity.");

        user_account_shopease.setText("In order to use certain features of ShopEase, you may be required to create a user account." +
                " You are responsible for maintaining the confidentiality of your account information and for all activities that occur under your account.");

        itellectual_property_shopease.setText("All content and materials available on ShopEase, including but not limited to text, graphics, logos, images, and software, are the property of ShopEase" +
                " or its licensors and are protected by copyright, trademark, and other intellectual property laws. You may not use or reproduce any content or materials from ShopEase without the prior written consent of ShopEase.");


        user_generated_content_shopease.setText("You may have the ability to post or upload user-generated content to ShopEase, such as reviews, comments, or product images. " +
                "By submitting such content, you grant ShopEase a non-exclusive, royalty-free, perpetual, irrevocable, and fully sublicensable right to use, reproduce, " +
                "modify, adapt, publish, translate, create derivative works from, distribute, and display such content in any media.\n" +
                "\n" +
                "You are solely responsible for the content you post on ShopEase and agree to indemnify ShopEase for any damages resulting from your content. " +
                "You must not post content that is defamatory, obscene, threatening, or infringes upon the intellectual property rights of others.");

        disclaimers_and_limitaion_shopease.setText("ShopEase is provided on an \"as is\" and \"as available\" basis without any warranties, express or implied. " +
                "ShopEase does not warrant that ShopEase will be uninterrupted or error-free or that any defects will be corrected.\n" +
                "\n" +
                "In no event shall ShopEase be liable for any damages arising out of or in connection with the use of ShopEase, " +
                "including but not limited to direct, indirect, incidental, consequential, or punitive damages.");

        termination_shopease.setText("ShopEase may terminate your access to ShopEase at any time for any reason without notice.");

        governing_law_shopease.setText("These Terms of Use shall be governed by and construed in accordance with the laws of [Your State/Country]," +
                " without giving effect to any principles of conflicts of law.");

        changes_to_terms_shopease.setText("ShopEase reserves the right to modify these Terms of Use at any time without notice. " +
                "Your continued use of ShopEase after any such modifications shall constitute your acceptance of the revised Terms of Use.\n" +
                "\n" +
                "If you have any questions or concerns about these Terms of Use, please contact us at [Contact Information].");

        // Inflate the layout for this fragment
        return root;
    }
}