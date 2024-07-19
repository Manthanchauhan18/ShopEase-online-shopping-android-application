package com.example.shopease.Payment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.shopease.R;
import com.example.shopease.model.Cart_product_layout;
import com.example.shopease.model.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Razor_pay extends Activity implements PaymentResultWithDataListener, ExternalWalletListener {
    private static final String TAG = Razor_pay.class.getSimpleName();
    private AlertDialog.Builder alertDialogBuilder;

    TextView payment_status,payment_details_inr_rupees;
    EditText payment_enter_address;

    Button btn_my_order;
    ImageView payment_page_back;
    int flag = 0;

    String product_name,product_price,product_rating,product_discription,product_quantity,product_storage,product_image;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_razor_pay);

        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        payment_enter_address = findViewById(R.id.payment_enter_address);
        payment_details_inr_rupees = findViewById(R.id.payment_details_inr_rupees);

        String total_price = getIntent().getStringExtra("price");
        payment_details_inr_rupees.setText("INR. "+total_price);

        payment_status = findViewById(R.id.payment_status);
        payment_status.setVisibility(View.GONE);

        payment_page_back = findViewById(R.id.payment_page_back);
        payment_page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_my_order = findViewById(R.id.btn_my_order);
        btn_my_order.setVisibility(View.GONE);

        Intent intent_product = getIntent();
         product_name = intent_product.getStringExtra("name");
         product_price = intent_product.getStringExtra("price");
         product_rating = intent_product.getStringExtra("rating");
         product_image = intent_product.getStringExtra("image");
         product_discription = intent_product.getStringExtra("discription");
         product_quantity = intent_product.getStringExtra("quantity");
         product_storage = intent_product.getStringExtra("storage");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_enter_address.clearFocus();

                if(payment_enter_address.getText().toString().equals("")){
                    flag=1;
                    if(payment_enter_address.getText().toString().equals("")){
                        payment_enter_address.setBackgroundResource(R.drawable.red_border);
                    }else{
                        payment_enter_address.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }
                if(flag==0){
                    startPayment();
                }

            }
        });

        payment_enter_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    flag=1;

                    if(payment_enter_address.getText().toString().equals("")){
                        payment_enter_address.setBackgroundResource(R.drawable.red_border);
                        flag=1;
                    }else{
                        payment_enter_address.setBackgroundResource(R.drawable.green_border);
                        flag=0;
                    }
                }

            }
        });



        TextView privacyPolicy = (TextView) findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9712412622");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try{
            payment_status.setText("External Wallet Selected");
            payment_status.setVisibility(View.VISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            payment_status.setText("Payment Successful :\n\nPayment ID: "+s);
            payment_status.setVisibility(View.VISIBLE);

            // Get the current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            String currentDateTime = dateFormat.format(new Date());

            // Get a reference to the Firebase Realtime Database
            FirebaseDatabase database = FirebaseDatabase.getInstance();

// Get the current user's ID and use it to create a child node for the user
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            String userId = currentUser.getUid();
            DatabaseReference userRef = database.getReference("User").child(userId);

// Get a reference to the "saved addresses" node under the user's node
            DatabaseReference savedCartRef = userRef.child("Orders");

//                                      Generate a new key for the address node under the user's "saved addresses" node
                        String order_key = userRef.child("Orders").push().getKey();


                        String user_address = payment_enter_address.getText().toString().trim();
                        // Create a new cart item object with the product details
                        Orders orders = new Orders(order_key,s, product_image, product_name, product_price, product_quantity,user_address,currentDateTime);

//                                      Create a new child node for the address under the user's "saved addresses" node
                        DatabaseReference cart_ref = userRef.child("Orders").child(order_key);

//                           Save the address information to Firebase
                        cart_ref.setValue(orders);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            payment_status.setText("Payment Failed");
            payment_status.setVisibility(View.VISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}