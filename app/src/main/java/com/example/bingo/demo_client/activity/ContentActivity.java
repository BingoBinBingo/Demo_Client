package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.bingo.wechat2.ui.fragment.ContentFragment;
import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 16/4/29.
 */
public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity_layout);
        Intent intent = getIntent();
        if(intent != null) {
            String intentValue = intent.getStringExtra("bingo");
            FragmentTransaction tx = getFragmentManager().beginTransaction();
            tx.add(R.id.fragmentContainer, ContentFragment.newInstance(intentValue));
            tx.addToBackStack(null);
            tx.commit();

        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//        }
//        return true;
//    }
}
