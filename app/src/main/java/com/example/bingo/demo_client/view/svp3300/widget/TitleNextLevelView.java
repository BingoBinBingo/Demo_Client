package com.example.bingo.demo_client.view.svp3300.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.Log;

import java.util.HashMap;

/**
 * Created by Bingo on 16/4/21.
 * 可以在xml文件中，设置自定义的属性有：
 * nextLevelTitle
 * nextLevelValue
 * valueTvVisibility
 */
public class TitleNextLevelView extends LinearLayout{
    private static final String TAG = TitleNextLevelView.class.getCanonicalName();
    private Context mContext;
    private CustomizedAttrs mCustomizedAttrs;
    private TextView mTitleTv;
    private TextView mValueTv;

    public TitleNextLevelView(Context context) {
        this(context, null);
    }

    public TitleNextLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initModel(context);
        initView();
        initCustomizedAttr(attrs, 0);
        setView();
        initController();
    }

    private void initModel(Context context) {
        mContext = context;
    }

    private void initView() {
        inflate(mContext, R.layout.title_next_level_view_layout, this);
        mTitleTv = (TextView)findViewById(R.id.titleTv);
        mValueTv = (TextView)findViewById(R.id.valueTv);
    }

    private void initCustomizedAttr(AttributeSet attrs, int defStyleAttr) {
        mCustomizedAttrs = new CustomizedAttrs();

        TypedArray typeArr = mContext.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.titleNextLevelViewStyle, defStyleAttr, 0);
        int attrCount = typeArr.getIndexCount();
        for(int attrIndex = 0; attrIndex < attrCount; attrIndex++) {
            int attr = typeArr.getIndex(attrIndex);
            switch (attr) {
                case R.styleable.titleNextLevelViewStyle_nextLevelTitle:
                    mCustomizedAttrs.nextLevelTitle = typeArr.getString(attr);
                    break;
                case R.styleable.titleNextLevelViewStyle_nextLevelValue:
                    mCustomizedAttrs.nextLevelValue = typeArr.getString(attr);
                    break;
                case R.styleable.titleNextLevelViewStyle_valueTvVisibility:
                    mCustomizedAttrs.valueTvVisibility = typeArr.getInt(attr, 0);
                    break;
            }
        }

        typeArr.recycle();

    }

    private void setView() {
        Log.v(TAG, "--bingo--setView--mCustomizedAttrs.nextLevelTitle=" + mCustomizedAttrs.nextLevelTitle);
        Log.v(TAG, "--bingo--setView--mCustomizedAttrs.nextLevelValue=" + mCustomizedAttrs.nextLevelValue);
        mTitleTv.setText(mCustomizedAttrs.nextLevelTitle);
        mValueTv.setText(mCustomizedAttrs.nextLevelValue);

        mValueTv.setVisibility(
                mCustomizedAttrs.valueTvVisibleMap.get(mCustomizedAttrs.valueTvVisibility));

    }

    private void initController() {

    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
    }

    public void initNextLevelView(OnClickListener onClickListener) {

    }

    /**
     * 设置下一级控的标题
     * @param title
     */
    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    /**
     * 获取下一级控件的标题
     * @return
     */
    public String getTitle() {
        return mTitleTv.getText().toString();
    }

    /**
     * 设置下一级控件，当前显示数据
     * @param value
     */
    public void setValue(String value) {
        mValueTv.setText(value);
    }

    /**
     * 获取下一级控件，当前显示数据
     * @return
     */
    public String getValue() {
        return mValueTv.getText().toString();
    }

    private class CustomizedAttrs {
        public static final int VISIBLE = 0;
        public static final int GONE = 1;

        public static final int CENTER_IN_PARENT = 0;
        public static final int ALIGN_PARENT_LEFT = 1;
        public static final int ALIGN_PARENT_RIGHT = 2;

        public String nextLevelTitle;
        public String nextLevelValue;

        public int titleTvVisibility;
        public int valueTvPosition;

        public int valueTvVisibility;

        private HashMap<Integer, Integer> valueTvPositionMap = new HashMap<>();
        {
            valueTvPositionMap.put(CENTER_IN_PARENT, RelativeLayout.CENTER_IN_PARENT);
            valueTvPositionMap.put(ALIGN_PARENT_LEFT, RelativeLayout.ALIGN_PARENT_LEFT);
            valueTvPositionMap.put(ALIGN_PARENT_RIGHT, RelativeLayout.ALIGN_PARENT_RIGHT);
        }

        private HashMap<Integer, Integer> valueTvVisibleMap = new HashMap<>();
        {
            valueTvVisibleMap.put(VISIBLE, View.VISIBLE);
            valueTvVisibleMap.put(GONE, View.GONE);
        }
    }
}
