package com.example.bingo.demo_client.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.bingo.demo_client.utils.LayoutUtils;

/**
 * Created by Bingo on 15/12/26.
 */
public class MultiViewPager extends ViewPager {
    private static final String TAG = MultiViewPager.class.getCanonicalName();

    public MultiViewPager(Context context) {
        super(context);
        initView();
    }

    public MultiViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
//        setPageTransformer(true ,new DepthPageTransformer());
//        setPageTransformer(true ,new ZoomOutPageTransformer());
        setPageTransformer(true ,new MultiViewPagerPageTransformer());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
    class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @SuppressLint("NewApi")
        public void transformPage(View view, float position)
        {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            Log.e("TAG", view + " , " + position + "");

            if (position < -1)
            { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0)
                {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else
                {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else
            { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
    class MultiViewPagerPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float paramAnonymousFloat) {
            if (paramAnonymousFloat < -1F || paramAnonymousFloat > 1.0F) {
                return;
            }
            int mWidth = LayoutUtils.getScreenSize().x;
            Log.v(TAG, "mWidth=" + mWidth + "----bBin----transformPage,view=" + view + "||paramAnonymousFloat=" + paramAnonymousFloat);

            int pivotX,pivotY;
            float tempFloatA,tempFloatX,rotationY;
            switch (mWidth)
            {
                case 1920:
                    Log.v(TAG, "----bBin--bBin--1920");
                    if (paramAnonymousFloat < 0.0F)
                    {
                        pivotX = view.getMeasuredWidth() / 5;
                        tempFloatA = 1.0F + paramAnonymousFloat * 2.0F;
                        tempFloatX = 1.0F - paramAnonymousFloat * 2.0F;
                    }
                    else
                    {
                        pivotX = view.getMeasuredWidth() * 4 / 5;
                        tempFloatA = 1.0F - paramAnonymousFloat * 2.0F;
                        tempFloatX = 1.0F + paramAnonymousFloat * 2.0F;
                    }
                    pivotY = view.getMeasuredHeight() / 2;
                    rotationY = (-90F * paramAnonymousFloat * 4F) / 3F;
                    break;
                case 1024:
                    Log.v(TAG, "----bBin--bBin--1024");
                    if (paramAnonymousFloat < 0.0F)
                    {
                        pivotX = view.getMeasuredWidth() / 5;
                        tempFloatA = 1.0F + paramAnonymousFloat;
                        tempFloatX = 1.0F - paramAnonymousFloat;
                    }
                    else
                    {
                        pivotX = view.getMeasuredWidth() * 4 / 5;
                        tempFloatA = 1.0F - paramAnonymousFloat;
                        tempFloatX = 1.0F + paramAnonymousFloat;
                    }
                    pivotY = view.getMeasuredHeight() / 2;
                    rotationY = (-90F * paramAnonymousFloat * 6F) / 5F;
                    break;

                default:	//1280...
                    Log.v(TAG, "----bBin--bBin--default");
                    if (paramAnonymousFloat < 0.0F)
                    {
                        pivotX = view.getMeasuredWidth() / 6;
                        tempFloatA = 1.0F + paramAnonymousFloat*2.5F;
                        tempFloatX = 1.0F - paramAnonymousFloat;
                    }
                    else
                    {
                        pivotX = view.getMeasuredWidth() * 5 / 6;
                        tempFloatA = 1.0F - paramAnonymousFloat*2.5F;
                        tempFloatX = 1.0F + paramAnonymousFloat;
                    }
                    pivotY = view.getMeasuredHeight() / 2;
                    rotationY = (-90F * paramAnonymousFloat * 8F) / 5F;
                    break;
            }
            view.setPivotX(pivotX);
            view.setPivotY(pivotY);
            view.setRotationY(rotationY);
            view.setAlpha(tempFloatA);
            view.setScaleX(tempFloatX);
        }
    }
}
