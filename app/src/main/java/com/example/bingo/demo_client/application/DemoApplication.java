package com.example.bingo.demo_client.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * 为了放一些全局的和一些上下文都要用到变量和方法之类的
 */

public class DemoApplication extends Application {
    private static final String TAG = DemoApplication.class.getCanonicalName();
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Log.v(TAG, "-DemoApplication-我被创建--onCreate-");
    }

    public static Context getContext() {
        return sContext;
    }
}
