package com.example.bingo.demo_client.listView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bingo.demo_client.MainActivity;
import com.example.bingo.demo_client.activity.AdapterActivity;
import com.example.bingo.demo_client.activity.AnimationActivity;
import com.example.bingo.demo_client.activity.AudioActivity;
import com.example.bingo.demo_client.activity.ConvertParamActivity;
import com.example.bingo.demo_client.activity.FlightPreferenceActivity;
import com.example.bingo.demo_client.activity.FragmentTestActivity;
import com.example.bingo.demo_client.activity.PercentLayoutActivity;
import com.example.bingo.demo_client.activity.PreferenceMainActivity;
import com.example.bingo.demo_client.activity.ScrollViewActivity;
import com.example.bingo.demo_client.activity.UIActivity;
import com.example.bingo.demo_client.viewpager.MainViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bingo on 15/12/3.
 */
public class MyListView extends ListActivity {
    private static final String TAG = MyListView.class.getCanonicalName();
    private List<String> listItemTitles = new ArrayList<String>();
    private HashMap<String, String> actionMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitles();
        initMap();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, listItemTitles);
        setListAdapter(arrayAdapter);
    }
    private void initTitles() {
        listItemTitles.add(MainActivity.TITLE);
        listItemTitles.add(FragmentTestActivity.TITLE);
        listItemTitles.add(UIActivity.TITLE);
        listItemTitles.add(AdapterActivity.TITLE);
        listItemTitles.add(PercentLayoutActivity.TITLE);
        listItemTitles.add(com.bingo.wechat2.ui.activity.MainActivity.TITLE);
        listItemTitles.add(PreferenceMainActivity.TITLE);
        listItemTitles.add(FlightPreferenceActivity.TITLE);
        listItemTitles.add(ConvertParamActivity.TITLE);
        listItemTitles.add(AudioActivity.TITLE);
        listItemTitles.add(AnimationActivity.TITLE);
        listItemTitles.add(ScrollViewActivity.TITLE);
        listItemTitles.add(MainViewPager.TITLE);
    }

    private void initMap() {
        actionMap.put(MainActivity.TITLE, MainActivity.ACTION_NAME );
        actionMap.put(FragmentTestActivity.TITLE, FragmentTestActivity.ACTION_NAME);
        actionMap.put(UIActivity.TITLE, UIActivity.ACTION_NAME);
        actionMap.put(AdapterActivity.TITLE, AdapterActivity.ACTION_NAME);
        actionMap.put(PercentLayoutActivity.TITLE, PercentLayoutActivity.ACTION_NAME);
        actionMap.put(com.bingo.wechat2.ui.activity.MainActivity.TITLE, com.bingo.wechat2.ui.activity.MainActivity.ACTION_NAME);
        actionMap.put(PreferenceMainActivity.TITLE,PreferenceMainActivity.ACTION_NAME);
        actionMap.put(FlightPreferenceActivity.TITLE,FlightPreferenceActivity.ACTION_NAME);
        actionMap.put(ConvertParamActivity.TITLE,ConvertParamActivity.ACTION_NAME);
        actionMap.put(AudioActivity.TITLE,AudioActivity.ACTION_NAME);
        actionMap.put(AnimationActivity.TITLE,AnimationActivity.ACTION_NAME);
        actionMap.put(ScrollViewActivity.TITLE,ScrollViewActivity.ACTION_NAME);
        actionMap.put(MainViewPager.TITLE,MainViewPager.ACTION_NAME);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        String itemTtitle = (String)listView.getItemAtPosition(position);
        Log.v(TAG, "----onListItemClick---itemTtitle=" + itemTtitle);
        String actionName = actionMap.get(itemTtitle);
        Log.v(TAG, "----onListItemClick---actionName=" + actionName);
        Intent intent = new Intent();
        intent.setAction(actionName);
        startActivity(intent);
    }
}
