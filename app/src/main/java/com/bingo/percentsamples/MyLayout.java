package com.bingo.percentsamples;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bingo on 15/12/30.
 */
public class MyLayout extends ViewGroup {

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 先计算自己的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        //计算该控件所占据的大小
        int width  = 0;
        int height = 0;
        int count  = getChildCount();
        for(int index = 0; index < count; index++) {
            View childView = getChildAt(index);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams)childView.getLayoutParams();
            int childViewWidth = childView.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            int childViewHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            height += childViewHeight;
            width = Math.max(width, childViewWidth);
        }

        setMeasuredDimension(
                (measureWidthMode == MeasureSpec.EXACTLY ? measureWidth : width),
                (measureHeightMode == MeasureSpec.EXACTLY ? measureHeight : height)
        );
    }

    /**
     * 因为要设置到 垂直布局，所以onLayout中元素特定排列
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int count = getChildCount();
        int widthParent = getMeasuredWidth();
        int tempLeft = 0;
        for(int index = 0; index < count; index++) {
            View childView = getChildAt(index);
            MarginLayoutParams lp = (MarginLayoutParams)childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if(widthParent >= (tempLeft + childWidth)) {
            } else {
                tempLeft = 0;
                top += childHeight;
            }
            childView.layout(tempLeft, top, tempLeft + childWidth, top + childHeight);
            tempLeft += childWidth;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
