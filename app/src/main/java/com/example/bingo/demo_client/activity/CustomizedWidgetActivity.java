package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.ToastThread;
import com.example.bingo.demo_client.view.svp3300.widget.TitleNextLevelView;
import com.example.bingo.demo_client.view.svp3300.widget.TitleSpinner;

public class CustomizedWidgetActivity extends Activity {
    public static final String TAG = CustomizedWidgetActivity.class.getCanonicalName();
    public static final String TITLE = "CustomizedWidgetActivity";
    public static final String ACTION_NAME = "com.bingo.demo.customizedWidgetActivity_action";
    private TitleSpinner mTitleSpinner;
    private TitleNextLevelView mTitleNextLevelView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_widget);
        mTitleSpinner = (TitleSpinner)findViewById(R.id.textSizeSpinner);
        mTitleNextLevelView = (TitleNextLevelView)findViewById(R.id.timeNextLevelView);
        final String[] strArr = new String[]{"大", "中", "小"};
        mTitleSpinner.initSpinner(strArr[0], strArr, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mTitleSpinner.dismissSpinner();
                mTitleSpinner.setItemValue(strArr[position]);
            }
        });

        initController();
    }

    private void initController() {
        mTitleNextLevelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastThread.showMsg("标题=" + mTitleNextLevelView.getTitle() +
                        ",textSize=" + mTitleSpinner.getItemValue());
            }
        });
    }


    public void showInfo(View view) {
        ToastThread.showMsg("标题=" + mTitleNextLevelView.getTitle() +
                ",textSize=" + mTitleSpinner.getItemValue());
    }
}
