package com.example.bingo.demo_client.view.svp3300.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bingo.demo_client.R;

import java.util.HashMap;

/**
 * Created by Bingo on 16/4/20.
 * 可以在xml文件中，设置自定义的属性有：
 * spinnerTitle
 * titleTvVisibility
 * valueTvPosition
 */

public class TitleSpinner extends LinearLayout {
    private static final String TAG = TitleSpinner.class.getCanonicalName();
    private Context mContext;
    private CustomizedAttrs mCustomizedAttrs;
    private TextView mSpinnerTitleTv;
    private TextView mSpinnerValueTv;
    private PopupWindow mPopupWindow;
    private int mPopWindowWidth;
    private ListView mListView;
    private String[] mListViewDatas;

    public TitleSpinner(Context context) {
        super(context);
    }

    public TitleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initModel(context);
        initView();
        initPopupWindow();
        initCustomizedAttr(attrs, 0);
        setView();
        initController();
    }

    private void initModel(Context context) {
        mContext = context;
    }

    private void initView() {
        inflate(mContext, R.layout.title_spinner_layout, this);
        mSpinnerTitleTv = (TextView)findViewById(R.id.spinnerTitle);
        mSpinnerValueTv = (TextView)findViewById(R.id.spinnerValue);
        getPopWindowWidth();
    }

    private void initCustomizedAttr(AttributeSet attrs, int defStyleAttr) {
        mCustomizedAttrs = new CustomizedAttrs();

        TypedArray typeArr = mContext.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.titleSpinnerStyle, defStyleAttr, 0);
        int attrCount = typeArr.getIndexCount();
        for(int attrIndex = 0; attrIndex < attrCount; attrIndex++) {
            int attr = typeArr.getIndex(attrIndex);
            switch (attr) {
                case R.styleable.titleSpinnerStyle_spinnerTitle:
                    mCustomizedAttrs.spinnerTitle = typeArr.getString(attr);
                    break;
                case R.styleable.titleSpinnerStyle_titleTvVisibility:
                    mCustomizedAttrs.titleTvVisibility = typeArr.getInt(attr, 0);
                    break;
                case R.styleable.titleSpinnerStyle_valueTvPosition:
                    mCustomizedAttrs.valueTvPosition = typeArr.getInt(attr, 0);
                    break;
            }
        }

        typeArr.recycle();
    }

    private void setView() {
        mSpinnerTitleTv.setText(mCustomizedAttrs.spinnerTitle);
        mSpinnerTitleTv.setVisibility(
                mCustomizedAttrs.titleTvVisibleMap.get(mCustomizedAttrs.titleTvVisibility));

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)mSpinnerValueTv.getLayoutParams();
        lp.addRule(mCustomizedAttrs.valueTvPositionMap.get(mCustomizedAttrs.valueTvPosition));
        mSpinnerValueTv.setLayoutParams(lp);
    }

    private void initPopupWindow() {
        View popupWindowRootView = inflate(mContext, R.layout.title_spinner_popupwindow_layout, null);
        mPopupWindow = new PopupWindow(popupWindowRootView, mPopWindowWidth, -2 ,true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mListView = (ListView)popupWindowRootView.findViewById(R.id.titleSpinnerPopListView);
    }


    private void initController() {
        mSpinnerValueTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else if(!mPopupWindow.isShowing() && mListViewDatas != null){
                    final ArrayAdapter adapter =
                            new ArrayAdapter(mContext, R.layout.title_spinner_list_item, mListViewDatas);
                    mListView.setAdapter(adapter);
                    mPopupWindow.showAsDropDown(view);
                }
            }
        });
    }

    private void getPopWindowWidth() {
        mSpinnerValueTv.post(new Runnable() {
            @Override
            public void run() {
                mPopWindowWidth = mSpinnerValueTv.getWidth();
                mPopupWindow.setWidth(mPopWindowWidth);
            }
        });
    }


    /**
     *  控件在 xml 文件中初始化后，需要调用此方法来设置 下拉列表的标题，下拉列表的数据，
     *  以及点击下拉列表中某个数据中触发的回调监听
     * @param spinnerItemArr    下拉列表的数据
     * @param listener          点击下拉列表中某个数据中触发的回调监听
     */
    public void initSpinner(String defSpinnerValue, String[] spinnerItemArr,
                            AdapterView.OnItemClickListener listener) {
        mSpinnerValueTv.setText(defSpinnerValue);
        mListViewDatas = spinnerItemArr;
        mListView.setOnItemClickListener(listener);

    }

    /**
     * 设置下拉框的标题
     * @param spinnerTitle 标题
     */
    public void setSpinnerTitle(String spinnerTitle) {
        mSpinnerTitleTv.setText(spinnerTitle);
    }

    /**
     * 获取下拉框的标题
     * @return
     */
    public String getSpinnerTitle() {
        return mSpinnerTitleTv.getText().toString();
    }
    /**
     *  设置 下拉列表当前显示的数据
     * @param value 显示的数据
     */
    public void setItemValue(String value) {
        mSpinnerValueTv.setText(value);
    }

    /**
     *
     * @return 返回当前下拉列表的数据
     */
    public String getItemValue() {
        return mSpinnerValueTv.getText().toString();
    }

    /**
     * 关闭下拉列表，需要在 initSpinner()中的 listener的onItemClick()方法中被调用，
     * 不然下拉列表项被点击后，下拉列表不会消失
     */
    public void dismissSpinner() {
        if(mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private class CustomizedAttrs {
        public static final int VISIBLE = 0;
        public static final int GONE = 1;

        public static final int CENTER_IN_PARENT = 0;
        public static final int ALIGN_PARENT_LEFT = 1;
        public static final int ALIGN_PARENT_RIGHT = 2;

        public String spinnerTitle;
        public String spinnerValue;

        public int titleTvVisibility;
        public int valueTvPosition;

        private HashMap<Integer, Integer> valueTvPositionMap = new HashMap<>();
        {
            valueTvPositionMap.put(CENTER_IN_PARENT, RelativeLayout.CENTER_IN_PARENT);
            valueTvPositionMap.put(ALIGN_PARENT_LEFT, RelativeLayout.ALIGN_PARENT_LEFT);
            valueTvPositionMap.put(ALIGN_PARENT_RIGHT, RelativeLayout.ALIGN_PARENT_RIGHT);
        }

        private HashMap<Integer, Integer> titleTvVisibleMap = new HashMap<>();
        {
            titleTvVisibleMap.put(VISIBLE, View.VISIBLE);
            titleTvVisibleMap.put(GONE, View.GONE);
        }
    }

}
