package com.example.shopease.ui.Profile;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopease.Profile_of_user.Coupons;
import com.example.shopease.Profile_of_user.Change_language;
import com.example.shopease.Profile_of_user.Edit_profile;
import com.example.shopease.Login_Screen;
import com.example.shopease.Profile_of_user.My_Order;
import com.example.shopease.Profile_of_user.My_wishlist;
import com.example.shopease.R;
import com.example.shopease.Profile_of_user.Saved_wallet_and_cards;
import com.example.shopease.Profile_of_user.Saved_addresses;
import com.example.shopease.Terms_and_condition.Browes_FAQs;
import com.example.shopease.Terms_and_condition.Infringement_Policy;
import com.example.shopease.Terms_and_condition.Privacy_policy;
import com.example.shopease.Terms_and_condition.Return_policy;
import com.example.shopease.Terms_and_condition.terms_of_use;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    TextView btn_profile_logout;
    TextView txt_profile_username;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    String uid;
    FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_user;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    TextView user_profile_feedback_terms_of_use,user_profile_feedback_privacy_policy,user_profile_feedback_infringement_policy,user_profile_feedback_return_policy,user_profile_feedback_browse_FAQs;
    Button user_profile_btn_orders,user_profile_btn_wishlist,user_profile_btn_coupons;

    TextView text_profile_edit_profile,text_profile_select_language,text_profile_saved_cards,text_profile_saved_address;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        loginPreferences = getActivity().getSharedPreferences("loginPrefs", MODE_PRIVATE);

        txt_profile_username = root.findViewById(R.id.txt_profile_username);
        user_profile_feedback_terms_of_use = root.findViewById(R.id.user_profile_feedback_terms_of_use);
        user_profile_feedback_privacy_policy = root.findViewById(R.id.user_profile_feedback_privacy_policy);
        user_profile_feedback_infringement_policy = root.findViewById(R.id.user_profile_feedback_infringement_policy);
        user_profile_feedback_return_policy = root.findViewById(R.id.user_profile_feedback_return_policy);
        user_profile_feedback_browse_FAQs = root.findViewById(R.id.user_profile_feedback_browse_FAQs);

        user_profile_btn_orders = root.findViewById(R.id.user_profile_btn_orders);
        text_profile_edit_profile = root.findViewById(R.id.text_profile_edit_profile);
        user_profile_btn_wishlist = root.findViewById(R.id.user_profile_btn_wishlist);
        text_profile_select_language = root.findViewById(R.id.text_profile_select_language);
        text_profile_saved_cards = root.findViewById(R.id.text_profile_saved_cards);
        user_profile_btn_coupons = root.findViewById(R.id.user_profile_btn_coupons);
        text_profile_saved_address = root.findViewById(R.id.text_profile_saved_address);


        firebaseDatabase = FirebaseDatabase.getInstance();
        //get refrence of the user from firebase storage
        databaseReference_user = firebaseDatabase.getReference("User");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        //get name of the user from firebase user section
        databaseReference_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child(uid).child("name").getValue(String.class);
                txt_profile_username.setText("Hello, "+name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        btn_profile_logout = root.findViewById(R.id.btn_profile_logout);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getContext() , googleSignInOptions);


        btn_profile_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a dialog box to confirm user logout
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = getLayoutInflater().inflate(R.layout.rounded_dialog, null);
                builder.setView(dialogView);
                AlertDialog alert = builder.create();

                TextView messageTextView = dialogView.findViewById(android.R.id.message);
                messageTextView.setText("Are you sure you want to Logout?");

                Button positiveButton = dialogView.findViewById(R.id.dialog_positive_button);
                positiveButton.setOnClickListener(view -> {
                    // User clicked the Yes button, perform logout
                    loginPrefsEditor = loginPreferences.edit();
                    loginPrefsEditor.putBoolean("isLoggedIn", false);
                    loginPrefsEditor.apply();

                    googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            getActivity().finish();
                            startActivity(new Intent(getContext() , Login_Screen.class));
                        }
                    });

                    Intent intent_logout = new Intent(getContext(), Login_Screen.class);
                    startActivity(intent_logout);
                    getActivity().finish();
                });

                Button negativeButton = dialogView.findViewById(R.id.dialog_negative_button);
                negativeButton.setOnClickListener(view -> {
                    // Dismiss the dialog and do nothing
                    alert.dismiss();
                });

                alert.show();
            }
        });

        user_profile_feedback_terms_of_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new terms_of_use());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        user_profile_feedback_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Privacy_policy());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        user_profile_feedback_infringement_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Infringement_Policy());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        user_profile_feedback_return_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Return_policy());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        user_profile_feedback_browse_FAQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Browes_FAQs());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        user_profile_btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new My_Order());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });


        text_profile_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Edit_profile());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        user_profile_btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new My_wishlist());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        text_profile_select_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Change_language());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        text_profile_saved_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Saved_wallet_and_cards());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        user_profile_btn_coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Coupons());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        text_profile_saved_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Saved_addresses());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });


        return root;

    }


}