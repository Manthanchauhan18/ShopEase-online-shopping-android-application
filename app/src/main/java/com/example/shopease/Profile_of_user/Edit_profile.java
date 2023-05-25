package com.example.shopease.Profile_of_user;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopease.R;
import com.example.shopease.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_profile extends Fragment {

    ImageView btn_profile_edit_profile_back_image;
    EditText edittext_profile_edit_profile_name,edittext_profile_edit_profile_email,edittext_profile_edit_profile_mobile,edittext_profile_edit_profile_old_password,edittext_profile_edit_profile_new_password,edittext_profile_edit_profile_confirm_password;
    TextView txt_profile_edit_profile_error;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseUser user;
    String uid,old_password,name,email,mobile;

    ProgressBar profile_edit_profile_progressbar;
    Button btn_edit_profile_save_changes;
    DatabaseReference dbRef;

    int flag=0,name_change=0,mobile_change=0;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        btn_profile_edit_profile_back_image = root.findViewById(R.id.btn_profile_edit_profile_back_image);
        edittext_profile_edit_profile_name = root.findViewById(R.id.edittext_profile_edit_profile_name);
        edittext_profile_edit_profile_email = root.findViewById(R.id.edittext_profile_edit_profile_email);
        edittext_profile_edit_profile_mobile = root.findViewById(R.id.edittext_profile_edit_profile_mobile);

        edittext_profile_edit_profile_old_password = root.findViewById(R.id.edittext_profile_edit_profile_old_password);
        edittext_profile_edit_profile_new_password = root.findViewById(R.id.edittext_profile_edit_profile_new_password);
        edittext_profile_edit_profile_confirm_password = root.findViewById(R.id.edittext_profile_edit_profile_confirm_password);

        profile_edit_profile_progressbar = root.findViewById(R.id.profile_edit_profile_progressbar);
        profile_edit_profile_progressbar.setVisibility(View.GONE);
        btn_edit_profile_save_changes = root.findViewById(R.id.btn_edit_profile_save_changes);
        txt_profile_edit_profile_error = root.findViewById(R.id.txt_profile_edit_profile_error);
        txt_profile_edit_profile_error.setVisibility(View.GONE);


        //get refrence of the user from firebase storage
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        //get name of the user from firebase user section
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name = dataSnapshot.child(uid).child("name").getValue(String.class);
                edittext_profile_edit_profile_name.setText(name);

                email = dataSnapshot.child(uid).child("email").getValue(String.class);
                edittext_profile_edit_profile_email.setText(email);

                mobile = dataSnapshot.child(uid).child("mobile").getValue(String.class);
                edittext_profile_edit_profile_mobile.setText(mobile);

                old_password = dataSnapshot.child(uid).child("password").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_profile_edit_profile_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });


        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_edit_24);

        edittext_profile_edit_profile_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edittext_profile_edit_profile_name.getRight() - drawable.getBounds().width())) {

                        name_change = 1;
                        edittext_profile_edit_profile_name.setEnabled(true);
                        edittext_profile_edit_profile_name.setFocusable(true);
                        edittext_profile_edit_profile_name.setFocusableInTouchMode(true);
                        edittext_profile_edit_profile_name.requestFocus();
                        return true;
                    }
                }
                return false;
            }
        });

        edittext_profile_edit_profile_mobile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edittext_profile_edit_profile_mobile.getRight() - drawable.getBounds().width())) {

                        mobile_change=1;
                        edittext_profile_edit_profile_mobile.setEnabled(true);
                        edittext_profile_edit_profile_mobile.setFocusable(true);
                        edittext_profile_edit_profile_mobile.setFocusableInTouchMode(true);
                        edittext_profile_edit_profile_mobile.requestFocus();
                        return true;
                    }
                }
                return false;
            }
        });


        btn_edit_profile_save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_edit_profile_progressbar.setVisibility(View.VISIBLE);
                edittext_profile_edit_profile_old_password.clearFocus();
                edittext_profile_edit_profile_new_password.clearFocus();
                edittext_profile_edit_profile_confirm_password.clearFocus();

                String edit_old_password = edittext_profile_edit_profile_old_password.getText().toString();
                String edit_new_password = edittext_profile_edit_profile_new_password.getText().toString();
                String edit_confirm_password = edittext_profile_edit_profile_confirm_password.getText().toString();
                String edit_name = edittext_profile_edit_profile_name.getText().toString();
                String edit_mobile = edittext_profile_edit_profile_mobile.getText().toString();

                if(edit_old_password.equals("") && edit_new_password.equals("") && edit_confirm_password.equals("")){
                    if(name_change==1 && mobile_change == 1){

                        dbRef = FirebaseDatabase.getInstance().getReference("User");
                        User user = new User(uid,edit_name,email,edit_mobile,old_password);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                profile_edit_profile_progressbar.setVisibility(View.GONE);
                                dbRef.child(uid).setValue(user);
                                txt_profile_edit_profile_error.setText("Name and Mobile Updated");
                                txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        txt_profile_edit_profile_error.setVisibility(View.GONE);
                                    }
                                },5000);

                            }
                        },2000);

                    }


                }
                if(name_change==0 && mobile_change == 0){
                    if(!edit_old_password.equals("") && !edit_new_password.equals("") && !edit_confirm_password.equals("") && edit_confirm_password.equals(edit_new_password) && edit_old_password.equals(old_password)){

                        edittext_profile_edit_profile_old_password.setBackgroundResource(R.drawable.green_border);
                        edittext_profile_edit_profile_new_password.setBackgroundResource(R.drawable.green_border);
                        edittext_profile_edit_profile_confirm_password.setBackgroundResource(R.drawable.green_border);

                        dbRef = FirebaseDatabase.getInstance().getReference("User");
                        User user = new User(uid,name,email,mobile,edit_new_password);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                profile_edit_profile_progressbar.setVisibility(View.GONE);
                                dbRef.child(uid).setValue(user);
                                txt_profile_edit_profile_error.setText("Password Updated");
                                txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        txt_profile_edit_profile_error.setVisibility(View.GONE);
                                    }
                                },5000);

                            }
                        },2000);

                    }else if(!edit_old_password.equals(old_password) && !edit_new_password.equals("") && !edit_confirm_password.equals("")){

                        profile_edit_profile_progressbar.setVisibility(View.GONE);
                        edittext_profile_edit_profile_old_password.setText("");
                        edittext_profile_edit_profile_old_password.requestFocus();
                        edittext_profile_edit_profile_old_password.setBackgroundResource(R.drawable.red_border);

                        txt_profile_edit_profile_error.setText("Old Password not matched");
                        txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_profile_edit_profile_error.setVisibility(View.GONE);
                            }
                        },2000);

                    }else if((edit_new_password.equals("") && edit_confirm_password.equals("") && !edit_old_password.equals("")) || (edit_new_password.equals("") && !edit_confirm_password.equals("")) ){

                        profile_edit_profile_progressbar.setVisibility(View.GONE);
                        edittext_profile_edit_profile_new_password.setText("");
                        edittext_profile_edit_profile_new_password.requestFocus();
                        edittext_profile_edit_profile_old_password.setBackgroundResource(R.drawable.green_border);
                        edittext_profile_edit_profile_new_password.setBackgroundResource(R.drawable.red_border);
                        edittext_profile_edit_profile_confirm_password.setBackgroundResource(R.drawable.red_border);

                        txt_profile_edit_profile_error.setText("Please enter new Password");
                        txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_profile_edit_profile_error.setVisibility(View.GONE);
                            }
                        },2000);

                    }else if(!edit_new_password.equals("") && !edit_confirm_password.equals(edit_new_password)){

                        profile_edit_profile_progressbar.setVisibility(View.GONE);
                        edittext_profile_edit_profile_confirm_password.setText("");
                        edittext_profile_edit_profile_confirm_password.setBackgroundResource(R.drawable.red_border);

                        txt_profile_edit_profile_error.setText("new password not matched");
                        txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_profile_edit_profile_error.setVisibility(View.GONE);
                            }
                        },2000);

                    }else if(name_change == 0 && mobile_change==0 && edit_old_password.equals("") && edit_new_password.equals("") && edit_confirm_password.equals("")){

                        profile_edit_profile_progressbar.setVisibility(View.GONE);
                        txt_profile_edit_profile_error.setText("There is no Changed");
                        txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_profile_edit_profile_error.setVisibility(View.GONE);
                            }
                        },5000);

                    }


                }

                if(name_change==1 && mobile_change == 1 && !edit_old_password.equals("") && !edit_new_password.equals("") && !edit_confirm_password.equals("") && edit_old_password.equals(old_password) && edit_confirm_password.equals(edit_new_password)){

                    dbRef = FirebaseDatabase.getInstance().getReference("User");
                    User user = new User(uid,edit_name,email,edit_mobile,edit_new_password);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            profile_edit_profile_progressbar.setVisibility(View.GONE);
                            dbRef.child(uid).setValue(user);

                            edittext_profile_edit_profile_old_password.setText("");
                            edittext_profile_edit_profile_new_password.setText("");
                            edittext_profile_edit_profile_confirm_password.setText("");

                            txt_profile_edit_profile_error.setText("Profile Updated");
                            txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_edit_profile_error.setVisibility(View.GONE);
                                }
                            },5000);

                        }
                    },2000);

                }

                if(name_change==1 && !edit_old_password.equals("") && !edit_new_password.equals("") && !edit_confirm_password.equals("") && edit_old_password.equals(old_password) && edit_confirm_password.equals(edit_new_password)){

                    dbRef = FirebaseDatabase.getInstance().getReference("User");
                    String edit_name_2 = edittext_profile_edit_profile_name.getText().toString();
                    User user = new User(uid,edit_name_2,email,mobile,edit_new_password);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            profile_edit_profile_progressbar.setVisibility(View.GONE);
                            dbRef.child(uid).setValue(user);

                            edittext_profile_edit_profile_old_password.setText("");
                            edittext_profile_edit_profile_new_password.setText("");
                            edittext_profile_edit_profile_confirm_password.setText("");

                            txt_profile_edit_profile_error.setText("Profile Updated");
                            txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_edit_profile_error.setVisibility(View.GONE);
                                }
                            },5000);

                        }
                    },2000);

                }

                if(mobile_change == 1 && !edit_old_password.equals("") && !edit_new_password.equals("") && !edit_confirm_password.equals("") && edit_old_password.equals(old_password) && edit_confirm_password.equals(edit_new_password)){

                    dbRef = FirebaseDatabase.getInstance().getReference("User");
                    User user = new User(uid,name,email,edit_mobile,edit_new_password);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            profile_edit_profile_progressbar.setVisibility(View.GONE);
                            dbRef.child(uid).setValue(user);

                            edittext_profile_edit_profile_old_password.setText("");
                            edittext_profile_edit_profile_new_password.setText("");
                            edittext_profile_edit_profile_confirm_password.setText("");

                            txt_profile_edit_profile_error.setText("Profile Updated");
                            txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_edit_profile_error.setVisibility(View.GONE);
                                }
                            },5000);

                        }
                    },2000);

                }

                if(name_change == 0 && mobile_change == 1 && edit_old_password.equals("") && edit_new_password.equals("") && edit_confirm_password.equals("")){

                    dbRef = FirebaseDatabase.getInstance().getReference("User");
                    User user = new User(uid,name,email,edit_mobile,old_password);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            profile_edit_profile_progressbar.setVisibility(View.GONE);
                            dbRef.child(uid).setValue(user);

                            edittext_profile_edit_profile_old_password.setText("");
                            edittext_profile_edit_profile_new_password.setText("");
                            edittext_profile_edit_profile_confirm_password.setText("");

                            txt_profile_edit_profile_error.setText("Profile Updated");
                            txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_edit_profile_error.setVisibility(View.GONE);
                                }
                            },5000);

                        }
                    },2000);

                }

                if(name_change == 1 && mobile_change == 0 && edit_old_password.equals("") && edit_new_password.equals("") && edit_confirm_password.equals("")){

                    dbRef = FirebaseDatabase.getInstance().getReference("User");
                    User user = new User(uid,edit_name,email,mobile,old_password);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            profile_edit_profile_progressbar.setVisibility(View.GONE);
                            dbRef.child(uid).setValue(user);

                            edittext_profile_edit_profile_old_password.setText("");
                            edittext_profile_edit_profile_new_password.setText("");
                            edittext_profile_edit_profile_confirm_password.setText("");

                            txt_profile_edit_profile_error.setText("Profile Updated");
                            txt_profile_edit_profile_error.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    txt_profile_edit_profile_error.setVisibility(View.GONE);
                                }
                            },5000);

                        }
                    },2000);

                }




            }
        });




        // Inflate the layout for this fragment
        return root;
    }
}