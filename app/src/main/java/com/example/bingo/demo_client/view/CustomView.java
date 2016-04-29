package com.example.bingo.demo_client.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Bingo on 16/3/29.
 */
public class CustomView extends TextView {
    private String TAG = CustomView.class.getCanonicalName();
    private String mTitleText;
    private int mTitleColor;
    private int mTitleSize;

    private Rect mBound;
    private Paint mPaint;
    private Canvas canvas;
    private Button button;
    private ImageView imageView;


    public CustomView(Context context) {
        this(context, null);
        Log.v(TAG, "--bin--1--");
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.v(TAG, "--bin--2--");
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.v(TAG, "--bin--3--");
        TypedArray typeArr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.customStyle, defStyleAttr, 0);
        int indexCount = typeArr.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int arr = typeArr.getIndex(i);
            switch (arr) {
                case R.styleable.customStyle_titleColor:
                    this.mTitleColor = typeArr.getColor(arr, Color.BLACK);
                    break;
                case R.styleable.customStyle_titleSize:
                    this.mTitleSize = typeArr.getDimensionPixelSize(arr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.customStyle_titleText:
                    this.mTitleText = typeArr.getString(arr);
                    break;
            }
        }

        typeArr.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText = randomText();
                mPaint.setColor(Color.YELLOW);
                postInvalidate();
            }
        });

    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }
        return sb.toString();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO: 16/3/29
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

        // TODO: 16/4/23 绘画 奥运五环
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(130, 150, 60, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle((float) 175.5, 150, 60, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(245, 150, 60, mPaint);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO: 16/3/29
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
