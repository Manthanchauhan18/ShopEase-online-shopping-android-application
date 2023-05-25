package com.example.shopease.categories;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopease.Adapter.Category_appliance_card_adapter;
import com.example.shopease.Adapter.Category_books_card_adapter;
import com.example.shopease.R;
import com.example.shopease.model.Appliance;
import com.example.shopease.model.Books;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class User_home_category_books extends Fragment {

    ImageView btn_user_books_category_back_image;
    DatabaseReference dbref;
    ArrayList<String> books_name = new ArrayList<String>();
    ArrayList<String> books_price = new ArrayList<String>();
    ArrayList<Books> books_list;
    RecyclerView user_books_category_recycler_view;


    SearchView category_books_searchview;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_user_home_category_books, container, false);

        // Apply the theme to the fragment
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Mytheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View themedView = localInflater.inflate(R.layout.fragment_user_home_category_books, container, false);

         user_books_category_recycler_view = themedView.findViewById(R.id.user_books_category_recycler_view);
        category_books_searchview = themedView.findViewById(R.id.category_books_searchview);


        btn_user_books_category_back_image = themedView.findViewById(R.id.btn_user_books_category_back_image);

        btn_user_books_category_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Books");

        user_books_category_recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        user_books_category_recycler_view.setItemAnimator(new DefaultItemAnimator());

        books_list = new ArrayList<Books>();
        user_books_category_recycler_view.setHasFixedSize(true);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Books books = snapshot.getValue(Books.class);
                    books_list.add(books);
                }
                user_books_category_recycler_view.setAdapter(new Category_books_card_adapter(books_list, getActivity(),books_name,books_price));
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        category_books_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (optional)
//                searchFirebase(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (books_list != null) {
                    ArrayList<Books> filteredList_books = new ArrayList<>();
                    for (Books books : books_list) {
                        if (books.getBooks_name().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList_books.add(books);
                        }
                    }
                    user_books_category_recycler_view.setAdapter(new Category_books_card_adapter(filteredList_books, getActivity(),books_name,books_price));
                }
                return true;
            }

        });



        return themedView;
    }
}