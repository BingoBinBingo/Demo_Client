package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bingo.demo_client.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkEncodeActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String TITLE = "NetworkEncodeActivity";
    public static final String ACTION_NAME = "com.bingo.demo.networkEncodeActivity_action";

    private TextView netowrkContent;
    private Button queryBtn;

    private String urlStr = "http://www.imooc.com/api/teacher?type=4&num=40";
    private static final int GET_RESPONE = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_RESPONE:
                    netowrkContent.setText((String)msg.obj);
                    break;
            }
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private HttpURLConnection mConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_encode);
        SharedPreferences sp = getSharedPreferences("bingo", Context.MODE_PRIVATE);
        sp.registerOnSharedPreferenceChangeListener(this);
        sp.getInt("sds", 1);
        sp.edit().putString("name", "huangbin").apply();
        sp.edit().putString("name1", "huangbin").apply();
//        sp.edit().remove("name1").apply();

        initModel();
        initView();
        initController();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        netowrkContent = (TextView) findViewById(R.id.netowrkContent);
        queryBtn = (Button) findViewById(R.id.queryBtn);
    }

    private void initController() {
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                http_url_connection_get();
            }
        });
    }

    private void initModel() {

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Toast.makeText(this, s + "发生变化", Toast.LENGTH_SHORT).show();
    }

    private void http_url_connection_get() {
        try {
            //创建url
//            URL url = new URL("http://www.baidu.com");
            URL url = new URL("android-app://com.example.bingo.demo_client.activity/http://www.baidu.com");
            //打通url连接
            mConn = (HttpURLConnection) url.openConnection();

            //设置连接的方式
            mConn.setRequestMethod("GET");
            //设置超时时间
            mConn.setConnectTimeout(8000);
            mConn.setReadTimeout(3000);
            mConn.setDoInput(true);
            mConn.setDoOutput(true);
            mConn.setUseCaches(true);

            //关键的一步 进行连接
            mConn.connect();
            //设置输入流  这入 出是针对  app来说的，入说明数据走向    app   出表示数据从App走出去
            InputStream inputStream = mConn.getInputStream();
            InputStreamReader ir = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(ir);
            StringBuffer sb = new StringBuffer();
            String strline = "";

            while((strline = br.readLine()) != null) {
                sb.append(strline);
            }

            Message msg = handler.obtainMessage();
            msg.what = GET_RESPONE;
            msg.obj = strline;
            handler.sendMessage(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            mConn.disconnect();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NetworkEncode Page", // TODO: Define a nextLevelTitle for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.bingo.demo_client.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NetworkEncode Page", // TODO: Define a nextLevelTitle for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.bingo.demo_client.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
