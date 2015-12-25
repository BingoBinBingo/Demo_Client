package com.example.bingo.demo_client.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/21.
 */
public class ScrollViewActivity extends FragmentActivity{
    public static final String TAG = ScrollViewActivity.class.getCanonicalName();
    public static final String TITLE = "ScrollViewActivity";
    public static final String ACTION_NAME = "com.bingo.demo.scrollViewActivity_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_activity);
    }
}
