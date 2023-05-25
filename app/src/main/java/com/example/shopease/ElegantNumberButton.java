package com.example.shopease;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

public class ElegantNumberButton extends androidx.appcompat.widget.AppCompatButton {

    private GradientDrawable gradientDrawable;
    private int number;
    int product_stock=10;

    public ElegantNumberButton(Context context) {
        super(context);
        init();
    }

    public ElegantNumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ElegantNumberButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.elegant_number_button_background);
        setGravity(Gravity.CENTER);
        setTextColor(Color.BLACK);
        gradientDrawable = (GradientDrawable) getBackground();
        number = Integer.parseInt(getText().toString());
    }

    public void setElegantNumberButtonColor(int color) {
        gradientDrawable.setColor(color);
    }

    public void incrementNumber() {

        if(number < product_stock) {
            number++;
            setText(String.valueOf(number));
        }
    }

    public void decrementNumber() {
        if (number > 0) {
            number--;
            setText(String.valueOf(number));
        }
    }

}
