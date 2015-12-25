package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/18.
 */
public class AudioActivity extends Activity{
    public static final String TAG = AudioActivity.class.getCanonicalName();
    public static final String TITLE = "AudioActivity";
    public static final String ACTION_NAME = "com.bingo.demo.audioActivity_action";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_call_layout);
    }
}
