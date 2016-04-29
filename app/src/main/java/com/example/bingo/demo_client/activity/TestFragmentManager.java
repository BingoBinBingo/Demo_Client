package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.bingo.wechat2.ui.fragment.TestFragmentMgrFirst;
import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.Log;
import com.example.bingo.demo_client.utils.ToastThread;

public class TestFragmentManager extends Activity {
    private String TAG  = TestFragmentManager.class.getCanonicalName();
    public static final String TITLE = "TestFragmentManager";
    public static final String ACTION_NAME = "com.bingo.demo.testFragmentManager_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            Log.v(TAG, "--bingobingo--savedInstanceState== null");
        } else {
            Log.v(TAG, "--bingobingo--savedInstanceState != null---savedInstanceState.get('name')=" + savedInstanceState.get("name"));
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_fragment_manager);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.add(R.id.fragment_content, new TestFragmentMgrFirst(), "Fragment_One");
        tx.commit();
        Intent intent = getIntent();
        ToastThread.showMsg("savedInstanceState=" + savedInstanceState + ",intent=" + intent + ",extras=" + intent.getStringExtra("name"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "--bingo--我被调用--onResume--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "--bingo--我被调用--onStart--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "--bingo--我被调用--onPause--");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", "bingo");

        Log.v(TAG, "--bingo--我被调用--onSaveInstanceState--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "--bingo--我被调用--onStop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "--bingo--我被调用--onDestroy--");
    }
}
