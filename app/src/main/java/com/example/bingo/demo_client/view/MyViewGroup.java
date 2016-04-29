package com.example.bingo.demo_client.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.example.bingo.demo_client.utils.Log;

/**
 * Created by Bingo on 16/3/31.
 */
public class MyViewGroup extends ViewGroup{
    private static final String TAG = MyViewGroup.class.getCanonicalName();
    private Context mContext;
    private int screenHeight;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        screenHeight = getScreenSize((Activity)mContext)[1];
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // TODO: 16/4/22 bingo 
        //这句执行完，getMeasureWidth  getMeasureHeight 就会有数值了
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childCount = getChildCount();
        int leftFromParent = 0;
        for(int index = 0; index < childCount; index++) {
            View childView = getChildAt(index);
            int viewWidth = childView.getMeasuredWidth();
            int viewHeight = childView.getMeasuredHeight();
            int height = screenHeight / 2 - viewHeight / 2;
            childView.layout(leftFromParent, height, leftFromParent + viewWidth, height + viewHeight);
            leftFromParent += viewWidth;
        }
        Log.v(TAG, "--bingo--v");
        Log.i(TAG, "--bingo--i");
        Log.d(TAG, "--bingo--d");
    }

    private int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.v("自定义ViewGroup", "metrics=" + metrics.toString());
        return new int[] {metrics.widthPixels, metrics.heightPixels};
    }
}
