package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/10.
 */
public class PercentLayoutActivity extends Activity {
    public static final String TAG = PercentLayoutActivity.class.getCanonicalName();
    public static final String TITLE = "PercentLayoutActivity";
    public static final String ACTION_NAME = "com.bingo.demo.percentLayoutActivity_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.percent_layout_layout);
        setContentView(R.layout.percent_relative_layout_layout);
        setContentView(R.layout.percent_linearlayout_layout_layout);
    }
}
