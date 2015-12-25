package com.example.bingo.demo_client.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/16.
 */
public class ConvertParamActivity extends FragmentActivity {
    public static final String TAG = ConvertParamActivity.class.getCanonicalName();
    public static final String TITLE = "ConvertParamActivity";
    public static final String ACTION_NAME = "com.bingo.demo.convertParamActivity_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_param_frag);
        View view = new View(this);
    }
}
