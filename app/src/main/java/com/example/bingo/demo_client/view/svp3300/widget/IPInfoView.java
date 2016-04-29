package com.example.bingo.demo_client.view.svp3300.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 16/4/20.
 */
public class IPInfoView extends LinearLayout {
    private static final String TAG = IPInfoView.class.getCanonicalName();
    private Context mContext;
    private CustomizedAttrs mCustomizedAttrs;
    private TextView mInfoNameTv;
    private EditText mInfoValueEt;

    public IPInfoView(Context context) {
        super(context);
        initModel(context);
        initView();
        initController();
    }

    public IPInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initModel(context);
        initCustomizedAttr(attrs, 0);
        initView();
        setView();
        initController();
    }

    public IPInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initModel(context);
        initCustomizedAttr(attrs, defStyleAttr);
        initView();
        setView();
        initController();
    }


    private void initModel(Context context) {
        mContext = context;
    }

    private void initCustomizedAttr(AttributeSet attrs, int defStyleAttr) {
        mCustomizedAttrs = new CustomizedAttrs();

        TypedArray typeArr = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.ipInfoViewStyle, defStyleAttr, 0);
        int attrCount = typeArr.getIndexCount();
        for(int attrIndex = 0; attrIndex < attrCount; attrIndex++) {
            int attr = typeArr.getIndex(attrIndex);
            switch (attr) {
                case R.styleable.ipInfoViewStyle_infoName:
                    mCustomizedAttrs.infoName = typeArr.getString(attrIndex);
                    break;
            }
        }

        typeArr.recycle();
    }

    private void initView() {
        inflate(mContext, R.layout.ip_info_view_layout, this);
        mInfoNameTv  = (TextView)findViewById(R.id.ipInfoNameTv);
        mInfoValueEt = (EditText)findViewById(R.id.ipInfoValueEt);
    }


    private void setView() {
        mInfoNameTv.setText(mCustomizedAttrs.infoName);
    }

    private void initController() {

    }

    private class CustomizedAttrs {
       public String infoName;
    }
}
