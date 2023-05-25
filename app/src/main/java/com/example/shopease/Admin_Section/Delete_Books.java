package com.example.shopease.Admin_Section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shopease.R;
import com.example.shopease.model.Books;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Delete_Books extends AppCompatActivity {

    ImageView btn_admin_Delete_books_back;
    Spinner spinner_admin_delete_books;
    TextView txt_delete_books_error;
    Button btn_admin_delete_books;
    ProgressBar admin_delete_books_progressbar;

    List<String> list_delete_books_name = new ArrayList<String>();
    List<Books> list_books = new ArrayList<Books>();

    DatabaseReference dbRef;
    StorageReference storage;

    String books_id,books_name,books_imageurl;

    Handler handler_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_books);

        btn_admin_Delete_books_back = findViewById(R.id.btn_admin_delete_books_back);
        spinner_admin_delete_books = findViewById(R.id.spinner_admin_delete_books);
        btn_admin_delete_books = findViewById(R.id.btn_admin_delete_books);

        txt_delete_books_error = findViewById(R.id.txt_delete_books_error);
        txt_delete_books_error.setVisibility(View.GONE);

        admin_delete_books_progressbar = findViewById(R.id.admin_delete_books_progressbar);
        admin_delete_books_progressbar.setVisibility(View.GONE);

        handler_error = new Handler();


//        connect with firebase offers section
        dbRef = FirebaseDatabase.getInstance().getReference("Books");
//        connect with firebase storage offer section
        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Books");


//        if delete offer back button press then return to admin home
        btn_admin_Delete_books_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_delete_offer_admin_home = new Intent(Delete_Books.this , Admin_Home.class);
                startActivity(intent_delete_offer_admin_home);
                finish();

            }
        });


//        get offer name and add into offers_name list
        dbRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                list_delete_books_name.clear();
                list_books.clear();

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Books books = shot.getValue(Books.class);

                    list_delete_books_name.add(books.getBooks_name());
                    list_books.add(books);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list_delete_books_name);
                spinner_admin_delete_books.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        set offer_name on spinner
        spinner_admin_delete_books.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                books_id = list_books.get(position).getBooks_id();
                books_name = list_delete_books_name.get(position); // listObj.get(position).getgName();
                books_imageurl = list_books.get(position).getBooks_imageUrl();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


//        if delete offer button press then delete offer from firebase offer section
        btn_admin_delete_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                admin_delete_books_progressbar.setVisibility(View.VISIBLE);

//                connect with firebase offers section
                dbRef = FirebaseDatabase.getInstance().getReference("Books");
//                connect with firebase offers storage for image
                storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://shopease-c4c3a.appspot.com/Books");

                Query delQ = dbRef.orderByChild("books_id").equalTo(books_id);

                delQ.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                        {

                            snap.getRef().removeValue();            // used to remove record from realtimeDatabase

                            storage = storage.child(books_name);

                            storage.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    txt_delete_books_error.setText("Book Removed Successfully");
                                    txt_delete_books_error.setVisibility(View.VISIBLE);

                                    handler_error.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            txt_delete_books_error.setVisibility(View.GONE);

                                        }
                                    },5000);

                                    admin_delete_books_progressbar.setVisibility(ProgressBar.GONE);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });


            }
        });




    }
}