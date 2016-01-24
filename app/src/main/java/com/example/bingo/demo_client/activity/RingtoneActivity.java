package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.RingtoneUtils;
import com.example.bingo.demo_client.utils.SongUtil;
import com.example.bingo.demo_client.utils.bean.Song;

import java.util.List;

/**
 * Created by Bingo on 15/12/27.
 */
public class RingtoneActivity extends Activity{
    public static final String TAG = RingtoneActivity.class.getCanonicalName();
    public static final String TITLE = "RingtoneActivity";
    public static final String ACTION_NAME = "com.bingo.demo.ringtoneActivity_action";
    private List<Song> mSongList;
    private List<String> mRingtoneTitleList;
    private List<String> mSongTtileList;
    private ListView mListView;
    private List<String> mRingtoneUriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_tone_layout);
        setTitle("黄彬2.0");

        Log.v(TAG, "--bBin---getDefault.titile=" + RingtoneUtils.getDefaultRingtone(RingtoneManager.TYPE_RINGTONE).getTitle(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().getAbsolutePath()}, null, null);
        initData();
        initView();
        initController();
    }

    private void initView() {
        this.mListView = (ListView)findViewById(R.id.ringtoneListView);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.ring_tone_item_layout, mRingtoneTitleList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.ring_tone_item_layout, mSongTtileList);
        this.mListView.setAdapter(arrayAdapter);
    }

    private void initData() {
        this.mRingtoneUriList = RingtoneUtils.getRingtoneUriList(RingtoneManager.TYPE_RINGTONE);
        this.mRingtoneTitleList = RingtoneUtils.getRingtoneTitleList(RingtoneManager.TYPE_RINGTONE);
        this.mSongList = SongUtil.getAllSongs(getApplication());
        this.mSongTtileList = SongUtil.getRingtoneTitleList(this.mSongList);
    }
    private void initController() {
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.v(TAG, "---bBin--onItemClick--fileUri=" + mSongList.get(position).getFileUrl());
                Log.v(TAG, "---bBin--onItemClick--getRingtoneUriPath=" + RingtoneUtils.getRingtoneUriPath(RingtoneManager.TYPE_RINGTONE, position, "DEF"));
                RingtoneUtils.setRingtoneFromMedia(mSongList.get(position).getFileUrl(), RingtoneManager.TYPE_RINGTONE);
//                RingtoneUtils.setRingtoneFromMedia(mRingtoneUriList.get(position), RingtoneManager.TYPE_RINGTONE);
            }
        });
    }

}
