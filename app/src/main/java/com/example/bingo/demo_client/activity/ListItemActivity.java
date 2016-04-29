package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.bingo.wechat2.ui.fragment.ListItemFragment;
import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 16/4/29.
 */
public class ListItemActivity extends Activity {
    public static final String TAG = ListItemActivity.class.getCanonicalName();
    public static final String TITLE = "ListItemActivity";
    public static final String ACTION_NAME = "com.bingo.demo.listItemActivity_action";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity_layout);

        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.add(R.id.fragmentContainer, new ListItemFragment());
//        tx.addToBackStack(null);
        tx.commit();
    }
}
