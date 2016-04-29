package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bingo.wechat2.ui.fragment.TestFragmentMgrFirst;
import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.ToastThread;

public class CustomViewActivity extends Activity implements ComponentCallbacks{
    private String TAG  = CustomViewActivity.class.getCanonicalName();
    public static final String TITLE = "CustomViewActivity";
    public static final String ACTION_NAME = "com.bingo.demo.customViewActivity_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        setContentView(R.layout.simple_layout);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.add(R.id.testFragmentLayout, new TestFragmentMgrFirst());
        tx.addToBackStack("firstFragBack");
        tx.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.may_menu, menu);
        return true; //返回 true 为显示出来
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                ToastThread.showMsg("add");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1001986"));
                startActivity(intent);
                break;
            case R.id.removeItem:
                ToastThread.showMsg("remove");
                break;
            case R.id.settingsItem:
                ToastThread.showMsg("setting");
                break;
            //如果希望Fragment自己处理MenuItem点击事件，一定不要忘了调用super.xxx
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }
}
