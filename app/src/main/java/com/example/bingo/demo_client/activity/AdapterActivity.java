package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.application.DemoApplication;

import java.util.ArrayList;

public class AdapterActivity extends Activity {
    public static final String TAG = AdapterActivity.class.getCanonicalName();
    public static final String TITLE = "AdapterActivity";
    public static final String ACTION_NAME = "com.bingo.demo.adapterActivity_action";
    private ListView listView;
    private MyBaseAdapter myBaseAdapter;
    private WifiManager mWifiManager;
    private ArrayList<Pair<String, String>> mSettingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        this.listView = (ListView)findViewById(R.id.testedtList);
        initModel();
        myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
    }

    private void initModel() {
        mWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        mSettingList = new ArrayList<>();
        boolean isOpenLan = Settings.Secure.getInt(getContentResolver(), "ethernet_on", 1) == 1;
        boolean isOpenWifi = mWifiManager.isWifiEnabled();
        boolean isUseStaicIp = Settings.System.getInt(getContentResolver(), "ethernet_use_static_ip", 0) == 1;
        boolean isUseStaticDNS = Settings.System.getString(getContentResolver(), "ethernet_use_dhcp_dns").equals("true");
        mSettingList.add(new Pair<String, String>("ethernet_on", isOpenLan + ""));
        mSettingList.add(new Pair<String, String>("wifi_on", isOpenWifi + ""));
        mSettingList.add(new Pair<String, String>("isUseStaicIp", isUseStaicIp + ""));
        mSettingList.add(new Pair<String, String>("isUseSisUseStaticDNStaicIp", isUseStaticDNS + ""));

    }

    public void changeListData(View view) {
        myBaseAdapter.notifyDataSetChanged();
//        listView.setAdapter(myBaseAdapter);//这句会是的listview始终保持在第一行

    }

    private class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mSettingList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v =  LayoutInflater.from(DemoApplication.getContext()).inflate(R.layout.activity_adapter_item, null);
            TextView settingProName = (TextView)v.findViewById(R.id.settingProName);
            TextView settingProValue = (TextView)v.findViewById(R.id.settingProValue);
            settingProName.setText(mSettingList.get(position).first);
            settingProValue.setText(mSettingList.get(position).second);
            return v;
        }
    }
}
