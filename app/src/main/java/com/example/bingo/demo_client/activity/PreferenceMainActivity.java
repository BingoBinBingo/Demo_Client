package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/12.
 */
public class PreferenceMainActivity extends Activity{
    public static final String TAG = PreferenceMainActivity.class.getCanonicalName();
    public static final String TITLE = "PreferenceMainActivity";
    public static final String ACTION_NAME = "com.bingo.demo.preferenceMainActivity_action";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_main);
        tv = (TextView)findViewById(R.id.showText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.preference_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_prefs:
                Intent intent = new Intent();
                intent.setClass(this, FlightPreferenceActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.menu_quit:
                finish();
                break;

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
