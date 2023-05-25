package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopease.R;
import com.example.shopease.model.Address;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_new_address extends Fragment {

    ImageView btn_admin_saved_address_new_address_back;
    EditText edit_profile_add_new_address_name,edit_profile_add_new_address_mobile,edit_profile_add_new_address_home_number,edit_profile_add_new_address_Street_name,edit_profile_add_new_address_Landmark,edit_profile_add_new_address_Pincode,edit_profile_add_new_address_city;
    Button btn_profile_add_new_address;
    TextView txt_profile_add_new_address_error;
    ProgressBar profile_add_new_address_progressbar;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_user;

    String uid;
    FirebaseUser user;

    int flag=0;

    String name,mobile,house_no,street,landmark,pincode,city;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_new_address, container, false);

        btn_admin_saved_address_new_address_back = root.findViewById(R.id.btn_admin_saved_address_new_address_back);
        edit_profile_add_new_address_name = root.findViewById(R.id.edit_profile_add_new_address_name);
        edit_profile_add_new_address_home_number = root.findViewById(R.id.edit_profile_add_new_address_home_number);
        edit_profile_add_new_address_Street_name = root.findViewById(R.id.edit_profile_add_new_address_Street_name);
        edit_profile_add_new_address_Landmark = root.findViewById(R.id.edit_profile_add_new_address_Landmark);
        edit_profile_add_new_address_Pincode = root.findViewById(R.id.edit_profile_add_new_address_Pincode);
        edit_profile_add_new_address_city = root.findViewById(R.id.edit_profile_add_new_address_city);
        btn_profile_add_new_address = root.findViewById(R.id.btn_profile_add_new_address);
        edit_profile_add_new_address_mobile = root.findViewById(R.id.edit_profile_add_new_address_mobile);
        profile_add_new_address_progressbar = root.findViewById(R.id.profile_add_new_address_progressbar);
        profile_add_new_address_progressbar.setVisibility(View.GONE);

        txt_profile_add_new_address_error = root.findViewById(R.id.txt_profile_add_new_address_error);
        txt_profile_add_new_address_error.setVisibility(View.GONE);



        btn_admin_saved_address_new_address_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        btn_profile_add_new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profile_add_new_address_progressbar.setVisibility(View.VISIBLE);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        edit_profile_add_new_address_name.clearFocus();
                        edit_profile_add_new_address_home_number.clearFocus();
                        edit_profile_add_new_address_Street_name.clearFocus();
                        edit_profile_add_new_address_Landmark.clearFocus();
                        edit_profile_add_new_address_Pincode.clearFocus();
                        edit_profile_add_new_address_city.clearFocus();
                        edit_profile_add_new_address_mobile.clearFocus();

                        name = edit_profile_add_new_address_name.getText().toString();
                        mobile = edit_profile_add_new_address_mobile.getText().toString();
                        house_no = edit_profile_add_new_address_home_number.getText().toString();
                        street = edit_profile_add_new_address_Street_name.getText().toString();
                        landmark = edit_profile_add_new_address_Landmark.getText().toString();
                        pincode = edit_profile_add_new_address_Pincode.getText().toString();
                        city = edit_profile_add_new_address_city.getText().toString();


                        if(name.equals("") || mobile.equals("") || house_no.equals("") || street.equals("") || landmark.equals("") || pincode.equals("") || city.equals("")){
                            profile_add_new_address_progressbar.setVisibility(View.GONE);

                            flag=1;
                            txt_profile_add_new_address_error.setText("Please Enter Valid Address");
                            txt_profile_add_new_address_error.setVisibility(View.VISIBLE);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_add_new_address_error.setVisibility(View.GONE);
                                }
                            },5000);

                            if(name.equals("")){
                                edit_profile_add_new_address_name.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_name.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_name.setBackgroundResource(R.drawable.green_border);
                            }

                            if(mobile.equals("")){
                                edit_profile_add_new_address_mobile.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_mobile.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_mobile.setBackgroundResource(R.drawable.green_border);
                            }


                            if(house_no.equals("")){
                                edit_profile_add_new_address_home_number.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_home_number.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_home_number.setBackgroundResource(R.drawable.green_border);
                            }

                            if(street.equals("")){
                                edit_profile_add_new_address_Street_name.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_Street_name.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_Street_name.setBackgroundResource(R.drawable.green_border);
                            }

                            if(landmark.equals("")){
                                edit_profile_add_new_address_Landmark.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_Landmark.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_Landmark.setBackgroundResource(R.drawable.green_border);
                            }

                            if(pincode.equals("")){
                                edit_profile_add_new_address_Pincode.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_Pincode.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_Pincode.setBackgroundResource(R.drawable.green_border);
                            }

                            if(city.equals("")){
                                edit_profile_add_new_address_city.setBackgroundResource(R.drawable.red_border);
                                edit_profile_add_new_address_city.requestFocus();
                            }else{
                                flag=0;
                                edit_profile_add_new_address_city.setBackgroundResource(R.drawable.green_border);
                            }

                        }else{
                            profile_add_new_address_progressbar.setVisibility(View.GONE);

                            edit_profile_add_new_address_name.setBackgroundResource(R.drawable.green_border);
                            edit_profile_add_new_address_mobile.setBackgroundResource(R.drawable.green_border);
                            edit_profile_add_new_address_home_number.setBackgroundResource(R.drawable.green_border);
                            edit_profile_add_new_address_Street_name.setBackgroundResource(R.drawable.green_border);
                            edit_profile_add_new_address_Landmark.setBackgroundResource(R.drawable.green_border);
                            edit_profile_add_new_address_Pincode.setBackgroundResource(R.drawable.green_border);
                            edit_profile_add_new_address_city.setBackgroundResource(R.drawable.green_border);


//                          Get a reference to the Firebase Realtime Database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            String full_address = house_no+" , "+street+" , "+landmark+" , "+city+" , "+pincode;
//                          Get the user's name, address, and mobile number from EditText fields



//                                      Get a reference to the Firebase Realtime Database
                            FirebaseDatabase database_address = FirebaseDatabase.getInstance();

//                                      Get the current user's ID and use it to create a child node for the user
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = currentUser.getUid();
                            DatabaseReference userRef = database_address.getReference("User").child(userId);


//                                      Generate a new key for the address node under the user's "saved addresses" node
                            String addressKey = userRef.child("Saved_Addresses").push().getKey();

//                                       Create a new instance of the Address model with the name, address, and mobile fields
                            Address address = new Address(addressKey,name, full_address, mobile);

//                                      Create a new child node for the address under the user's "saved addresses" node
                            DatabaseReference addressRef = userRef.child("Saved_Addresses").child(addressKey);

//                                       Save the address information to Firebase
                            addressRef.setValue(address);

                            edit_profile_add_new_address_name.setText("");
                            edit_profile_add_new_address_mobile.setText("");
                            edit_profile_add_new_address_home_number.setText("");
                            edit_profile_add_new_address_Street_name.setText("");
                            edit_profile_add_new_address_Landmark.setText("");
                            edit_profile_add_new_address_Pincode.setText("");
                            edit_profile_add_new_address_city.setText("");

                            txt_profile_add_new_address_error.setText("Address Added Successfully");
                            txt_profile_add_new_address_error.setVisibility(View.VISIBLE);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_add_new_address_error.setVisibility(View.GONE);
                                }
                            },5000);

                        }


                    }
                },2000);




            }
        });


        // Inflate the layout for this fragment
        return root;
    }
}