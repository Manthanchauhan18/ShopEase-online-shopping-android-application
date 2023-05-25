package com.example.shopease.ui.Home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.shopease.Adapter.Home_category_card_adapter;
import com.example.shopease.Adapter.Home_grocery_card_adapter;
import com.example.shopease.Adapter.Home_smartphone_card_adapter;
import com.example.shopease.Adapter.Profile_imageslider_card_adapter;
import com.example.shopease.R;
import com.example.shopease.categories.User_home_category_Electronics;
import com.example.shopease.categories.User_home_category_Tv;
import com.example.shopease.categories.User_home_category_grocery;
import com.example.shopease.categories.User_home_category_mobile;
import com.example.shopease.categories.User_home_deals_of_the_day;
import com.example.shopease.model.Category;
import com.example.shopease.model.Grocery;
import com.example.shopease.model.Offers;
import com.example.shopease.model.Upcoming_phones;
import com.example.shopease.ui.category.CategoriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    //initailize objects
    TextView txt_home_username;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_user;

    ImageView home_deal_image_1,home_deal_image_2,home_deal_image_3,home_deal_image_4;

    TextView home_txt_tv_1,home_txt_tv_2,home_txt_tv_3,home_txt_tv_4;
    ImageView home_image_tv_1,home_image_tv_2,home_image_tv_3,home_image_tv_4;

    ImageView home_headphone_brand_image_1,home_headphone_brand_image_2,home_headphone_brand_image_3,home_headphone_brand_image_4;
    TextView txt_home_headphone_brand_1,txt_home_headphone_brand_2,txt_home_headphone_brand_3,txt_home_headphone_brand_4;

    ImageView home_ac_image_1,home_ac_image_2,home_ac_image_3,home_ac_image_4;
    TextView txt_home_ac_1,txt_home_ac_2,txt_home_ac_3,txt_home_ac_4;

    TextView user_home_txt_home,user_home_deals_of_the_day_see_all;

    TextView txt_categories_view_more,txt_smartphone_see_all,txt_grocery_see_all,user_home_smart_tv_see_all;


    ImageSlider imageSlider_offer;
    ImageSlider imageslider_deals_of_the_day;

    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;


    String uid;
    FirebaseUser user;

    String imageUrl_tv;
    String tv_Name;

    String imageUrl_headphone_brand;
    String headphone_brand_Name;

    String ac_imageUrl;
    String ac_Name;

    RecyclerView user_home_categories_recyclerview,user_home_smartphone_recyclerview,user_home_grocery_recyclerview,user_home_imageslider_offer_recyclerview;
    ArrayList<Category> category_list;
    DatabaseReference dbref_category;
    DatabaseReference dbref_smartphone;
    ArrayList<Upcoming_phones> samrtphone_list;
    DatabaseReference dbref_grocery;
    ArrayList<Grocery> grocery_list;
    DatabaseReference dbref_offers;
    ArrayList<Offers> offers_list;

    ArrayList<String> category_name = new ArrayList<String>();
    ArrayList<String> smartphone_name = new ArrayList<String>();
    ArrayList<String> grocery_name = new ArrayList<String>();


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        //connect objects with xml file ids
        txt_home_username = root.findViewById(R.id.txt_home_username);
        txt_smartphone_see_all = root.findViewById(R.id.txt_smartphone_see_all);
        txt_grocery_see_all = root.findViewById(R.id.txt_grocery_see_all);


        user_home_txt_home = root.findViewById(R.id.user_home_txt_home);

//        connect all imageview and textview with the xml file
        home_deal_image_1 = root.findViewById(R.id.home_deal_image_1);
        home_deal_image_2 = root.findViewById(R.id.home_deal_image_2);
        home_deal_image_3 = root.findViewById(R.id.home_deal_image_3);
        home_deal_image_4 = root.findViewById(R.id.home_deal_image_4);


        user_home_categories_recyclerview = root.findViewById(R.id.user_home_categories_recyclerview);
        user_home_smartphone_recyclerview = root.findViewById(R.id.user_home_smartphone_recyclerview);
        user_home_grocery_recyclerview = root.findViewById(R.id.user_home_grocery_recyclerview);
        user_home_imageslider_offer_recyclerview = root.findViewById(R.id.user_home_imageslider_offer_recyclerview);
        user_home_deals_of_the_day_see_all = root.findViewById(R.id.user_home_deals_of_the_day_see_all);
        user_home_smart_tv_see_all = root.findViewById(R.id.user_home_smart_tv_see_all);

        home_txt_tv_1 = root.findViewById(R.id.home_txt_tv_1);
        home_txt_tv_2 = root.findViewById(R.id.home_txt_tv_2);
        home_txt_tv_3 = root.findViewById(R.id.home_txt_tv_3);
        home_txt_tv_4 = root.findViewById(R.id.home_txt_tv_4);

        home_image_tv_1 = root.findViewById(R.id.home_image_tv_1);
        home_image_tv_2 = root.findViewById(R.id.home_image_tv_2);
        home_image_tv_3 = root.findViewById(R.id.home_image_tv_3);
        home_image_tv_4 = root.findViewById(R.id.home_image_tv_4);

        txt_home_headphone_brand_1 = root.findViewById(R.id.txt_home_headphone_brand_1);
        txt_home_headphone_brand_2 = root.findViewById(R.id.txt_home_headphone_brand_2);
        txt_home_headphone_brand_3 = root.findViewById(R.id.txt_home_headphone_brand_3);
        txt_home_headphone_brand_4 = root.findViewById(R.id.txt_home_headphone_brand_4);

        home_headphone_brand_image_1 = root.findViewById(R.id.home_headphone_brand_image_1);
        home_headphone_brand_image_2 = root.findViewById(R.id.home_headphone_brand_image_2);
        home_headphone_brand_image_3 = root.findViewById(R.id.home_headphone_brand_image_3);
        home_headphone_brand_image_4 = root.findViewById(R.id.home_headphone_brand_image_4);


        txt_home_ac_1 = root.findViewById(R.id.txt_home_ac_1);
        txt_home_ac_2 = root.findViewById(R.id.txt_home_ac_2);
        txt_home_ac_3 = root.findViewById(R.id.txt_home_ac_3);
        txt_home_ac_4 = root.findViewById(R.id.txt_home_ac_4);

        home_ac_image_1 = root.findViewById(R.id.home_ac_image_1);
        home_ac_image_2 = root.findViewById(R.id.home_ac_image_2);
        home_ac_image_3 = root.findViewById(R.id.home_ac_image_3);
        home_ac_image_4 = root.findViewById(R.id.home_ac_image_4);

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
                txt_home_username.setText("Hello, "+name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        imageslider_deals_of_the_day = root.findViewById(R.id.home_image_slider_deals_of_the_day);
//
//        //take list for offers and deals of the day
//        List<SlideModel> slideModels_offer = new ArrayList<>();
        List<SlideModel> slideModels_day_deals = new ArrayList<>();
//
//
//        //for offers import images from firebase offers section and display on imageslider
//        firebaseDatabase.getInstance().getReference().child("Offers")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot data_offer : snapshot.getChildren() ){
//
//                            //get image from firebase and add into list
//                            slideModels_offer.add(new SlideModel(data_offer.child("imageUrl").getValue().toString() , ScaleTypes.FIT));
//                            //set list on imageslider
//                            imageSlider_offer.setImageList(slideModels_offer , ScaleTypes.CENTER_CROP);
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });

        firebaseDatabase = FirebaseDatabase.getInstance();

        dbref_offers = FirebaseDatabase.getInstance().getReference("Offers");

        LinearLayoutManager layoutManager_offer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        user_home_imageslider_offer_recyclerview.setLayoutManager(layoutManager_offer);

        user_home_imageslider_offer_recyclerview.setItemAnimator(new DefaultItemAnimator());

        offers_list = new ArrayList<Offers>();
        user_home_categories_recyclerview.setHasFixedSize(true);

        dbref_offers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Offers offers = snapshot.getValue(Offers.class);
                    offers_list.add(offers);
                }
                Profile_imageslider_card_adapter adapter = new Profile_imageslider_card_adapter(offers_list, getActivity());
                user_home_imageslider_offer_recyclerview.setAdapter(adapter);

                // Initialize image slider timer
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new ImageSliderTask(adapter), 2000, 2000);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            class ImageSliderTask extends TimerTask {
                private Profile_imageslider_card_adapter mAdapter;
                private Handler mHandler;

                public ImageSliderTask(Profile_imageslider_card_adapter adapter) {
                    mAdapter = adapter;
                    mHandler = new Handler();
                }
                @Override
                public void run() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            int currentPosition = layoutManager_offer.findFirstVisibleItemPosition();
                            if (currentPosition < mAdapter.getItemCount() - 1) {
                                user_home_imageslider_offer_recyclerview.smoothScrollToPosition(currentPosition + 1);
                            } else {
                                user_home_imageslider_offer_recyclerview.smoothScrollToPosition(0);
                            }
                        }
                    });
                }
            }

        });



        dbref_category = FirebaseDatabase.getInstance().getReference("Category");

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        user_home_categories_recyclerview.setLayoutManager(layoutManager2);

        user_home_categories_recyclerview.setItemAnimator(new DefaultItemAnimator());

        category_list = new ArrayList<>();
        user_home_categories_recyclerview.setHasFixedSize(true);

        dbref_category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category_model = snapshot.getValue(Category.class);
                    category_list.add(category_model);
                }
                user_home_categories_recyclerview.setAdapter(new Home_category_card_adapter(category_list, getActivity(),category_name));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


//        cardview_see_all = root.findViewById(R.id.cardview_see_all);
        txt_categories_view_more = root.findViewById(R.id.txt_categories_view_more);

        txt_categories_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new CategoriesFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setSelectedItemId(R.id.menu_categories);

            }
        });



        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        FirebaseDatabase.getInstance().getReference("Day Deals")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot data_day_deals : snapshot.getChildren() ){
                            // add the slide model to the image slider
                            slideModels_day_deals.add(new SlideModel(data_day_deals.child("imageUrl").getValue().toString() , ScaleTypes.FIT));

                            // display the image in the first ImageView
                            if(slideModels_day_deals.size() == 1) {
                                Glide.with(HomeFragment.this)
                                        .load(data_day_deals.child("imageUrl").getValue().toString())
                                        .into(home_deal_image_1);

                            }

                            // display the image in the second ImageView
                            if(slideModels_day_deals.size() == 2) {
                                Glide.with(HomeFragment.this)
                                        .load(data_day_deals.child("imageUrl").getValue().toString())
                                        .into(home_deal_image_2);
                            }

                            // display the image in the third ImageView
                            if(slideModels_day_deals.size() == 3) {
                                Glide.with(HomeFragment.this)
                                        .load(data_day_deals.child("imageUrl").getValue().toString())
                                        .into(home_deal_image_3);
                            }

                            // display the image in the fourth ImageView
                            if(slideModels_day_deals.size() == 4) {
                                Glide.with(HomeFragment.this)
                                        .load(data_day_deals.child("imageUrl").getValue().toString())
                                        .into(home_deal_image_4);
                            }
                        }

                        // set the images in the image slider
                        imageslider_deals_of_the_day.setImageList(slideModels_day_deals , ScaleTypes.CENTER_INSIDE);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                    user_home_deals_of_the_day_see_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, new User_home_deals_of_the_day());
                        transaction.addToBackStack(null);
                        transaction.commit();
        //
        //                        BottomNavigationView bottomNavigationView_smartphone = getActivity().findViewById(R.id.bottom_navigation);
        //                        bottomNavigationView_smartphone.setSelectedItemId(R.id.menu_categories);

                    }
                });



                txt_smartphone_see_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, new User_home_category_mobile());
                        transaction.addToBackStack(null);
                        transaction.commit();
//
//                        BottomNavigationView bottomNavigationView_smartphone = getActivity().findViewById(R.id.bottom_navigation);
//                        bottomNavigationView_smartphone.setSelectedItemId(R.id.menu_categories);

                    }
                });


                dbref_smartphone = FirebaseDatabase.getInstance().getReference("Upcoming Phones");

                user_home_smartphone_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                user_home_smartphone_recyclerview.setItemAnimator(new DefaultItemAnimator());

                user_home_smartphone_recyclerview.setItemAnimator(new DefaultItemAnimator());

                samrtphone_list = new ArrayList<Upcoming_phones>();
                user_home_smartphone_recyclerview.setHasFixedSize(true);

                dbref_smartphone.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Upcoming_phones smartphone_model = snapshot.getValue(Upcoming_phones.class);
                            samrtphone_list.add(smartphone_model);
                        }
                        user_home_smartphone_recyclerview.setAdapter(new Home_smartphone_card_adapter(samrtphone_list, getActivity(),smartphone_name));
        //                recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });



//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Upcoming Phones");
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 1;
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    // Get the image URL and name from Firebase Database
//                    imageUrl_phone = postSnapshot.child("imageUrl_phone").getValue().toString();
//                     phoneName = postSnapshot.child("smartphone_name").getValue().toString();
//
//                    // Get a reference to the image file in Firebase Storage
////                    imageRef_phone = mStorageRef.child("Upcoming Phones");
//
//                    // Load the image into the ImageView
//                    switch (i) {
//                        case 1:
//                            txt_home_smartphone_1.setText(phoneName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl_phone").getValue().toString())
//                                    .into(home_smartphone_image_1);
//                            break;
//                        case 2:
//                            txt_home_smartphone_2.setText(phoneName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl_phone").getValue().toString())
//                                    .into(home_smartphone_image_2);
//                            break;
//                        case 3:
//                            txt_home_smartphone_3.setText(phoneName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl_phone").getValue().toString())
//                                    .into(home_smartphone_image_3);
//                            break;
//                        case 4:
//                            txt_home_smartphone_4.setText(phoneName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl_phone").getValue().toString())
//                                    .into(home_smartphone_image_4);
//                            break;
//                    }
//
//                    i++;
//                    if (i > 4) {
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle errors
//            }
//        });



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Home_tv");
        mStorageRef = FirebaseStorage.getInstance().getReference();

// Declare an array of ImageView objects to store references to each ImageView that displays a product image
        ImageView[] tvImageViews = { home_image_tv_1, home_image_tv_2, home_image_tv_3, home_image_tv_4 };

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // Get the image URL and name from Firebase Database
                    imageUrl_tv = postSnapshot.child("imageUrl").getValue().toString();
                    tv_Name = postSnapshot.child("tv_name").getValue().toString();

                    // Load the image into the ImageView
                    switch (i) {
                        case 0:
                            home_txt_tv_1.setText(tv_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_tv)
                                    .into(home_image_tv_1);

                            // Add click listener to the ImageView
                            tvImageViews[0].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create a new fragment instance
                                    User_home_category_Tv fragment = new User_home_category_Tv();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("8999", "8999");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                            break;
                        case 1:
                            home_txt_tv_2.setText(tv_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_tv)
                                    .into(home_image_tv_2);

                            // Add click listener to the ImageView
                            tvImageViews[1].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    // Create a new fragment instance
                                    User_home_category_Tv fragment = new User_home_category_Tv();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("14999", "14999");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();


                                }
                            });
                            break;
                        case 2:
                            home_txt_tv_3.setText(tv_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_tv)
                                    .into(home_image_tv_3);

                            // Add click listener to the ImageView
                            tvImageViews[2].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create a new fragment instance
                                    User_home_category_Tv fragment = new User_home_category_Tv();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("24999", "24999");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                            break;
                        case 3:
                            home_txt_tv_4.setText(tv_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_tv)
                                    .into(home_image_tv_4);

                            // Add click listener to the ImageView
                            tvImageViews[3].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create a new fragment instance
                                    User_home_category_Tv fragment = new User_home_category_Tv();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("49999", "49999");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                            break;
                    }

                    i++;
                    if (i > 3) {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

        user_home_smart_tv_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new User_home_category_Tv());
                transaction.addToBackStack(null);
                transaction.commit();
//
//                        BottomNavigationView bottomNavigationView_smartphone = getActivity().findViewById(R.id.bottom_navigation);
//                        bottomNavigationView_smartphone.setSelectedItemId(R.id.menu_categories);

            }
        });






//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Category");
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 1;
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    // Get the image URL and name from Firebase Database
//                    imageUrl_category = postSnapshot.child("imageUrl").getValue().toString();
//                    categoryName = postSnapshot.child("category_name").getValue().toString();
//
//                    // Get a reference to the image file in Firebase Storage
////                    imageRef_phone = mStorageRef.child("Upcoming Phones");
//
//                    // Load the image into the ImageView
//                    switch (i) {
//                        case 1:
//                            txt_home_category_1.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_1);
//                            break;
//                        case 2:
//                            txt_home_category_2.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_2);
//                            break;
//                        case 3:
//                            txt_home_category_3.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_3);
//                            break;
//                        case 4:
//                            txt_home_category_4.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_4);
//                            break;
//                        case 5:
//                            txt_home_category_5.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_5);
//                            break;
//
//                        case 6:
//                            txt_home_category_6.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_6);
//                            break;
//
//                        case 7:
//                            txt_home_category_7.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_7);
//                            break;
//
//                        case 8:
//                            txt_home_category_8.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_8);
//                            break;
//
//                        case 9:
//                            txt_home_category_9.setText(categoryName);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("imageUrl").getValue().toString())
//                                    .into(image_home_category_9);
//                            break;
//
//                    }
//
//                    i++;
//                    if (i > 9) {
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle errors
//            }
//        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Headphone_brands");
        mStorageRef = FirebaseStorage.getInstance().getReference();

// Declare an array of ImageView objects to store references to each ImageView that displays a product image
        ImageView[] headphonwImageViews = { home_headphone_brand_image_1, home_headphone_brand_image_2, home_headphone_brand_image_3, home_headphone_brand_image_4 };

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // Get the image URL and name from Firebase Database
                    imageUrl_headphone_brand = postSnapshot.child("headphone_brand_imageUrl").getValue().toString();
                    headphone_brand_Name = postSnapshot.child("headphone_brand_name").getValue().toString();

                    // Load the image into the ImageView
                    switch (i) {
                        case 0:
                            txt_home_headphone_brand_1.setText(headphone_brand_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_headphone_brand)
                                    .into(home_headphone_brand_image_1);

                            // Add click listener to the ImageView
                            headphonwImageViews[0].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create a new fragment instance
                                    User_home_category_Electronics fragment = new User_home_category_Electronics();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("jbl", "jbl");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                            break;
                        case 1:
                            txt_home_headphone_brand_2.setText(headphone_brand_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_headphone_brand)
                                    .into(home_headphone_brand_image_2);

                            // Add click listener to the ImageView
                            headphonwImageViews[1].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    // Create a new fragment instance
                                    User_home_category_Electronics fragment = new User_home_category_Electronics();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("sony", "sony");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();

                                }
                            });
                            break;
                        case 2:
                            txt_home_headphone_brand_3.setText(headphone_brand_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_headphone_brand)
                                    .into(home_headphone_brand_image_3);

                            // Add click listener to the ImageView
                            headphonwImageViews[2].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create a new fragment instance
                                    User_home_category_Electronics fragment = new User_home_category_Electronics();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("boat", "boat");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                            break;
                        case 3:
                            txt_home_headphone_brand_4.setText(headphone_brand_Name);
                            Glide.with(HomeFragment.this)
                                    .load(imageUrl_headphone_brand)
                                    .into(home_headphone_brand_image_4);

                            // Add click listener to the ImageView
                            headphonwImageViews[3].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Create a new fragment instance
                                    User_home_category_Electronics fragment = new User_home_category_Electronics();

//                                  Create a bundle to store the data
                                    Bundle args = new Bundle();
                                    args.putString("boult", "boult");

//                                  Set the bundle as the fragment's arguments
                                    fragment.setArguments(args);

//                                  Start a transaction to replace the current fragment with the new one
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame_layout, fragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                            break;
                    }

                    i++;
                    if (i > 3) {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });



//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Headphone_brands");
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 1;
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    // Get the image URL and name from Firebase Database
//                    imageUrl_headphone_brand = postSnapshot.child("headphone_brand_imageUrl").getValue().toString();
//                    headphone_brand_Name = postSnapshot.child("headphone_brand_name").getValue().toString();
//
//                    // Get a reference to the image file in Firebase Storage
////                    imageRef_phone = mStorageRef.child("Upcoming Phones");
//
//                    // Load the image into the ImageView
//                    switch (i) {
//                        case 1:
//                            txt_home_headphone_brand_1.setText(headphone_brand_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("headphone_brand_imageUrl").getValue().toString())
//                                    .into(home_headphone_brand_image_1);
//                            break;
//                        case 2:
//                            txt_home_headphone_brand_2.setText(headphone_brand_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("headphone_brand_imageUrl").getValue().toString())
//                                    .into(home_headphone_brand_image_2);
//                            break;
//                        case 3:
//                            txt_home_headphone_brand_3.setText(headphone_brand_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("headphone_brand_imageUrl").getValue().toString())
//                                    .into(home_headphone_brand_image_3);
//                            break;
//                        case 4:
//                            txt_home_headphone_brand_4.setText(headphone_brand_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("headphone_brand_imageUrl").getValue().toString())
//                                    .into(home_headphone_brand_image_4);
//                            break;
//                    }
//
//                    i++;
//                    if (i > 4) {
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle errors
//            }
//        });


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Top_ac");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // Get the image URL and name from Firebase Database
                    ac_imageUrl = postSnapshot.child("ac_imageUrl").getValue().toString();
                    ac_Name = postSnapshot.child("ac_name").getValue().toString();

                    // Get a reference to the image file in Firebase Storage
//                    imageRef_phone = mStorageRef.child("Upcoming Phones");

                    // Load the image into the ImageView
                    switch (i) {
                        case 1:
                            txt_home_ac_1.setText(ac_Name);
                            Glide.with(HomeFragment.this)
                                    .load(postSnapshot.child("ac_imageUrl").getValue().toString())
                                    .into(home_ac_image_1);
                            break;
                        case 2:
                            txt_home_ac_2.setText(ac_Name);
                            Glide.with(HomeFragment.this)
                                    .load(postSnapshot.child("ac_imageUrl").getValue().toString())
                                    .into(home_ac_image_2);
                            break;
                        case 3:
                            txt_home_ac_3.setText(ac_Name);
                            Glide.with(HomeFragment.this)
                                    .load(postSnapshot.child("ac_imageUrl").getValue().toString())
                                    .into(home_ac_image_3);
                            break;
                        case 4:
                            txt_home_ac_4.setText(ac_Name);
                            Glide.with(HomeFragment.this)
                                    .load(postSnapshot.child("ac_imageUrl").getValue().toString())
                                    .into(home_ac_image_4);
                            break;
                    }

                    i++;
                    if (i > 4) {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });



        dbref_grocery = FirebaseDatabase.getInstance().getReference("Grocery");

        user_home_grocery_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_home_grocery_recyclerview.setItemAnimator(new DefaultItemAnimator());

        user_home_grocery_recyclerview.setItemAnimator(new DefaultItemAnimator());

        grocery_list = new ArrayList<Grocery>();
        user_home_grocery_recyclerview.setHasFixedSize(true);

        dbref_grocery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Grocery grocery_model = snapshot.getValue(Grocery.class);
                    grocery_list.add(grocery_model);
                }
                user_home_grocery_recyclerview.setAdapter(new Home_grocery_card_adapter(grocery_list, getActivity(),grocery_name));
                //                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        txt_grocery_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new User_home_category_grocery());
                transaction.addToBackStack(null);
                transaction.commit();
//
//                        BottomNavigationView bottomNavigationView_smartphone = getActivity().findViewById(R.id.bottom_navigation);
//                        bottomNavigationView_smartphone.setSelectedItemId(R.id.menu_categories);

            }
        });



//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Grocery");
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 1;
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    // Get the image URL and name from Firebase Database
//                    grocery_imageUrl = postSnapshot.child("grocery_imageUrl").getValue().toString();
//                    grocery_Name = postSnapshot.child("grocery_name").getValue().toString();
//
//                    // Get a reference to the image file in Firebase Storage
////                    imageRef_phone = mStorageRef.child("Upcoming Phones");
//
//                    // Load the image into the ImageView
//                    switch (i) {
//                        case 1:
//                            txt_home_grocery_offer_1.setText(grocery_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("grocery_imageUrl").getValue().toString())
//                                    .into(home_grocery_offer_image_1);
//                            break;
//                        case 2:
//                            txt_home_grocery_offer_2.setText(grocery_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("grocery_imageUrl").getValue().toString())
//                                    .into(home_grocery_offer_image_2);
//                            break;
//                        case 3:
//                            txt_home_grocery_offer_3.setText(grocery_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("grocery_imageUrl").getValue().toString())
//                                    .into(home_grocery_offer_image_3);
//                            break;
//                        case 4:
//                            txt_home_grocery_offer_4.setText(grocery_Name);
//                            Glide.with(HomeFragment.this)
//                                    .load(postSnapshot.child("grocery_imageUrl").getValue().toString())
//                                    .into(home_grocery_offer_image_4);
//                            break;
//                    }
//
//                    i++;
//                    if (i > 4) {
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle errors
//            }
//        });





        return root;
    }

}