package com.example.bingo.demo_client.activity;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.bean.MyEntry;
import com.example.bingo.demo_client.utils.ToastThread;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by Bingo on 16/1/9.
 */
public class AudioAndVideoActivity extends FragmentActivity implements View.OnClickListener{
    public static final String TAG = AudioAndVideoActivity.class.getCanonicalName();
    public static final String TITLE = "AudioAndVideoActivity";
    public static final String ACTION_NAME = "com.bingo.demo.audioAndVideoActivity_action";
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    //记录视频当前的位置
    private int position;
    private Button playBtn;
    private Button stopBtn;
    private Button pauseBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_and_video);
        initView();
        initModel();
        initController();
        doGsonTest();
    }


    private void doGsonTest() {
        Gson gson = new Gson();
        MyEntry myEntry = new MyEntry(24, "huangbin11");
        String jsonStr = gson.toJson(myEntry);
        ToastThread.showMsg(jsonStr);

        MyEntry yourEntry = gson.fromJson(jsonStr, MyEntry.class);
        ToastThread.showMsg("yourEntry.getName()=" + yourEntry.getName());
        ToastThread.showMsg("yourEntry.getAge()=" + yourEntry.getAge());
    }

    private void initView() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            ToastThread.showMsg("SDK_INT");
            setTranslucentStatus(true);
        }
        mSurfaceView = (SurfaceView)findViewById(R.id.mySurfaceView);
        playBtn = (Button)findViewById(R.id.playBtn);
        stopBtn = (Button)findViewById(R.id.stopBtn);
        pauseBtn = (Button)findViewById(R.id.pauseBtn);
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams windowParam = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(on) {
            windowParam.flags |= bits;
        } else {
            windowParam.flags &= ~bits;
        }
        win.setAttributes(windowParam);

    }
    private void initModel() {
//        mMediaPlayer = MediaPlayer.create(this, R.raw.haidaowaisheng);
        mMediaPlayer = new MediaPlayer();
        //设置播放时打开频幕
        mSurfaceView.getHolder().setKeepScreenOn(true);
        mSurfaceView.getHolder().addCallback(new SurfaceListener());
    }

    private void initController() {
        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.playBtn:
                play();
                break;
            case R.id.pauseBtn:
                if(mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                } else {
                    mMediaPlayer.start();
                }
                break;
            case R.id.stopBtn:
                if(mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                break;
        }
    }

    private void play() {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            String uriStr = "android.resource://" + getPackageName() + "/" + R.raw.haidaowaisheng;
            mMediaPlayer.setDataSource(this, Uri.parse(uriStr));
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepare();
            //获取窗口管理器
            WindowManager windowManager = getWindowManager();
            DisplayMetrics metrics = new DisplayMetrics();
            //获取屏幕的大小
            windowManager.getDefaultDisplay().getMetrics(metrics);
            //设置视频占满整个屏幕
            mSurfaceView.setLayoutParams(new LinearLayout.LayoutParams(metrics.widthPixels,
                    metrics.widthPixels));
//            mSurfaceView.setLayoutParams(new LinearLayout.LayoutParams(metrics.widthPixels,
//                    mMediaPlayer.getVideoHeight() * metrics.widthPixels / mMediaPlayer.getVideoWidth()));
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SurfaceListener implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if(position > 0) {
                //开始播放
                play();
                //并从指定位置开始播放
                mMediaPlayer.seekTo(position);
                position = 0;
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }


    //当其他应用启动的时候  暂停播放
    @Override
    protected void onPause() {
        if(mMediaPlayer.isPlaying()) {
            position = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.release();
        super.onDestroy();
    }
}
