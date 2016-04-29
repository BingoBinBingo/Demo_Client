package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bingo.demo_client.R;

public class NetworkActivity extends Activity {

    public static final String TAG = NetworkActivity.class.getCanonicalName();
    public static final String TITLE = "NetworkActivity";
    public static final String ACTION_NAME = "com.bingo.demo.networkActivity_action";
    private TextView networkInfoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        networkInfoTv = (TextView)findViewById(R.id.networkInfoTv);
        showConnectivityManagerNetworkInfo();
        showWifiManagerInfo();
    }

    private void showConnectivityManagerNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        networkInfoTv.setText(activeInfo.toString() + "detailState=" + activeInfo.getDetailedState().toString() + ",avaliable=" + activeInfo.isAvailable() + "subtype="
                +activeInfo.getSubtypeName() + ",type=" + activeInfo.getTypeName() + ",isConnected=" + activeInfo.isConnected());
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        networkInfoTv.append("\n" + wifiInfo.toString() + "detailState=" + wifiInfo.getDetailedState().toString() + ",avaliable=" + wifiInfo.isAvailable()
        +  ",isConnected=" + wifiInfo.isConnected());

    }

    private void showWifiManagerInfo() {

    }
}
