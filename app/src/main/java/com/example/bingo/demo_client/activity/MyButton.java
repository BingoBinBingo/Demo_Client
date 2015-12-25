package com.example.bingo.demo_client.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Bingo on 15/12/13.
 */
public class MyButton extends Button{
    public MyButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
