package com.example.bingo.demo_client.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bingo on 16/4/22.
 */
public class SimpleLayout extends ViewGroup {

    public SimpleLayout(Context context) {
        super(context);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getChildCount() > 0) {
            View childView = getChildAt(0);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(getChildCount() > 0) {
            View childView = getChildAt(0);
            childView.layout(50, 60, childView.getMeasuredWidth(), childView.getMeasuredHeight());
        }
    }
}
