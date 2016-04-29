package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.ToastThread;

public class WigetActivity extends Activity implements View.OnClickListener{
    public static final String TAG = WigetActivity.class.getCanonicalName();
    public static final String TITLE = "WigetActivity";
    public static final String ACTION_NAME = "com.bingo.demo.wigetActivity_action";
    private WebView mWebView;
    private Button mSendBtn;
    private Button mShowTime;
    private Button mShowDate;
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private Notification mNotification;
    private NotificationManager manager;
    private final int ID = 1;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(this);

        setContentView(R.layout.activity_wiget);
//        mWebView = (WebView)findViewById(R.id.webContent);

        mWebView.loadUrl("http://www.baidu.com");
//        mWebView.loadUrl("file://assets/hello.html");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                } else {
                }
            }
        });



//        setContentView(mWebView);
        initNotification();
    }

    private void initNotification() {
        mTimePicker = (TimePicker)findViewById(R.id.timepicker);
        mDatePicker = (DatePicker)findViewById(R.id.datePicker);

        mSendBtn = (Button)findViewById(R.id.sendBtn);
        mShowDate = (Button)findViewById(R.id.showDate);
        mShowTime = (Button)findViewById(R.id.showTime);
        mSendBtn.setOnClickListener(this);
        mShowDate.setOnClickListener(this);
        mShowTime.setOnClickListener(this);

        manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification = new Notification();

        mNotification.icon = R.drawable.ic_delete;
        mNotification.tickerText = "打叉的标题栏";
        mNotification.when = System.currentTimeMillis();
        mNotification.number = 64;
        long[] vibrate = {0,1000,2000,3000};
        mNotification.vibrate = vibrate;
        mNotification.defaults |= Notification.DEFAULT_SOUND;
//        mNotification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotification.defaults |= Notification.DEFAULT_LIGHTS;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendBtn:
                Intent intent = new Intent(WigetActivity.this, AsyncTaskActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(WigetActivity.this,0, intent, 0);
                mNotification.setLatestEventInfo(WigetActivity.this, "表情", "内容", pendingIntent);
                manager.notify(ID, mNotification);
                break;
            case R.id.showDate:
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                ToastThread.showMsg("year:" + year + ",mounth:" + month);
                break;
            case R.id.showTime:
                int hour = mTimePicker.getCurrentHour();
                int second = mTimePicker.getCurrentMinute();

                ToastThread.showMsg("hour:" + hour + ",second:" + second);
                break;
        }
    }


}
