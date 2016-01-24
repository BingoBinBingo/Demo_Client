package com.example.bingo.demo_client.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.bingo.demo_client.utils.ToastThread;

/**
 * Created by Bingo on 16/1/6.
 */
public class GestureActivity extends FragmentActivity {
    public static final String TAG = GestureActivity.class.getCanonicalName();
    public static final String TITLE = "GestureActivity";
    public static final String ACTION_NAME = "com.bingo.demo.gestureActivity_action";
    private GestureDetector detector;
    private MyGestureListener myGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGestureListener = new MyGestureListener();
        detector = new GestureDetector(this, myGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }

    class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            ToastThread.showMsg("onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
            ToastThread.showMsg("onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            ToastThread.showMsg("onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            ToastThread.showMsg("onScroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            ToastThread.showMsg("onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            ToastThread.showMsg("onFling");
            return false;
        }
    }


}
