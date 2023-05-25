//package com.example.shopease.Payment;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.shopease.Profile_of_user.My_Order;
//import com.example.shopease.R;
//import com.razorpay.Checkout;
//import com.razorpay.ExternalWalletListener;
//import com.razorpay.PaymentData;
//import com.razorpay.PaymentResultWithDataListener;
//
//import org.json.JSONObject;
//
//public class Razor_payment extends Fragment implements PaymentResultWithDataListener, ExternalWalletListener {
//
//    private static final String TAG = Razor_payment.class.getSimpleName();
//    private AlertDialog.Builder alertDialogBuilder;
//    TextView payment_status;
//    Button btn_my_order;
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.activity_razor_pay, container, false);
//
//        /*
//         To ensure faster loading of the Checkout form,
//          call this method as early as possible in your checkout flow.
//         */
//        Checkout.preload(getContext());
//
//        // Payment button created by you in XML layout
//        Button button = (Button) rootView.findViewById(R.id.btn_pay);
//
//        payment_status = rootView.findViewById(R.id.payment_status);
//        payment_status.setVisibility(View.GONE);
//
//        btn_my_order = rootView.findViewById(R.id.btn_my_order);
//        btn_my_order.setVisibility(View.GONE);
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startPayment();
//            }
//        });
//
//        TextView privacyPolicy = (TextView) rootView.findViewById(R.id.txt_privacy_policy);
//
//        privacyPolicy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
//                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
//                startActivity(httpIntent);
//            }
//        });
//
//        btn_my_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent_my_order = new Intent(getContext() , My_Order.class);
////                startActivity(intent_my_order);
//
//                // get the FragmentManager and start a FragmentTransaction
//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                // replace the current fragment with the My_Order fragment
//                My_Order myOrderFragment = new My_Order();
//                fragmentTransaction.replace(R.id.frame_layout, myOrderFragment);
//                fragmentTransaction.commit();
//
//            }
//        });
//
//        return rootView;
//    }
//
//    public void startPayment() {
//        /*
//          You need to pass current activity in order to let Razorpay create CheckoutActivity
//         */
//        final Activity activity = getActivity();
//
//        final Checkout co = new Checkout();
//
//        try {
//            JSONObject options = new JSONObject();
//            options.put("name", "Razorpay Corp");
//            options.put("description", "Demoing Charges");
//            options.put("send_sms_hash",true);
//            options.put("allow_rotation", true);
//            //You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("currency", "INR");
//            options.put("amount", "100");
//
//            JSONObject preFill = new JSONObject();
//            preFill.put("email", "test@razorpay.com");
//            preFill.put("contact", "9712412622");
//
//            options.put("prefill", preFill);
//
//            co.open(activity, options);
//        } catch (Exception e) {
//            Toast.makeText(getContext(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
//                    .show();
//            e.printStackTrace();
//        }
//    }
//
//    public void onExternalWalletSelected(String s, PaymentData paymentData) {
//
//        try{
//            payment_status.setText("External Wallet Selected:\nPayment Data: "+paymentData.getData());
//            payment_status.setVisibility(View.VISIBLE);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void onPaymentSuccess(String s, PaymentData paymentData) {
//
//        try{
//            payment_status.setText("Payment Successful :\n\nPayment ID: "+s+"\n\nPayment Data: "+paymentData.getData());
//            payment_status.setVisibility(View.VISIBLE);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void onPaymentError(int i, String s, PaymentData paymentData) {
//
//        try{
//            payment_status.setText("Payment Failed:\nPayment Data: "+paymentData.getData());
//            payment_status.setVisibility(View.VISIBLE);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}