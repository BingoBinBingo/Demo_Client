package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.bingo.demo_client.R;

public class AdapterActivity extends Activity {
    public static final String TAG = AdapterActivity.class.getCanonicalName();
    public static final String TITLE = "AdapterActivity";
    public static final String ACTION_NAME = "com.bingo.demo.adapterActivity_action";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        this.listView = (ListView)findViewById(R.id.testedtList);
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Log.v(TAG, "----cursor.size=" + cursor.getCount());
        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                cursor, new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.IS_USER_PROFILE
        },
                new int[]{R.id.peopleName, R.id.peopleNumber});

        listView.setAdapter(listAdapter);

    }
}
