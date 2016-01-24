package com.example.bingo.demo_client.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.ToastThread;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bingo on 16/1/4.
 */
public class ReadWriteActivity extends FragmentActivity {
    public static final String TAG = ReadWriteActivity.class.getCanonicalName();
    public static final String TITLE = "ReadWriteActivity";
    public static final String ACTION_NAME = "com.bingo.demo.readWriteActivity_action";
    private TextView tv;
    private TextView currDirTv;
    private ListView dirListView;
    private File currentParent;
    private File[] currentFiles;
    private Button backImgBtn;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_write_layout);
        tv = (TextView)findViewById(R.id.readwritesd);
        currDirTv = (TextView)findViewById(R.id.currDirName);
        dirListView = (ListView)findViewById(R.id.dirListView);
        backImgBtn = (Button)findViewById(R.id.backBtn);

//        Log.v(TAG, "--bBin--getir()=" + getDir("bingo", Context.MODE_PRIVATE));
        Log.v(TAG, "--bBin--getir()=" + getDir("bingo", Context.MODE_PRIVATE).getAbsolutePath());
        Log.v(TAG, "--bBin--getFilesDir().getAbsolutePath()=" + getFilesDir().getAbsolutePath());
        Log.v(TAG, "--bBin--getFilesDir().fileList().length=" + fileList().length);
        for(String str : fileList()) {
            Log.v(TAG, "--bBin--str=" + str);
        }

        //进sd的操作
        tv.setText("SD卡的目录=" + Environment.getExternalStorageDirectory().toString() + "\n" +
        "是否插入了SD卡＝" + Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
        );

        //获取系统的sd卡目录
        File file = new File("/mnt/sdcard/");
        //如sd卡存在
        if(file.exists()) {
            currentParent = file;
            currentFiles = file.listFiles();
            inflateListView(currentFiles);
        }

        //进行创建数据库
        initDataBase();

        initController();

    }
    private void initDataBase() {
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().getAbsolutePath() + "/mydb.db3", null);
    }

    private void inflateListView(File[] currentFiles) {
        List<Map<String, Object>> listItems = new ArrayList<>();
        for(File file : currentFiles) {
            Map<String, Object> listItem = new HashMap<>();
            if(file.isFile()) {
                listItem.put("icon", R.drawable.file);
            } else {
                listItem.put("icon", R.drawable.folder);
            }
            listItem.put("filename", file.getName());

            listItems.add(listItem);
        }

        //纯感simpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(this,
                listItems, R.layout.line, new String[] {"icon", "filename"},
                new int[]{R.id.fileIconImg, R.id.fileNameTv});
        dirListView.setAdapter(adapter);

//        try {
            currDirTv.setText("当前路径为：" + currentParent.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void initController() {
        dirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(currentFiles[position].isFile()) {
                    return;
                }

                File[] tmp = currentFiles[position].listFiles();
                if(tmp == null || tmp.length == 0) {
                    ToastThread.showMsg("当前路径下不存在可访问的文件夹");
                } else {
                    currentParent = currentFiles[position];
                    currentFiles = currentParent.listFiles();
                    inflateListView(currentFiles);
                }

            }
        });

        this.backImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!currentParent.getAbsolutePath().equals("/mnt/sdcard")) {
                    currentParent = currentParent.getParentFile();
                    currentFiles = currentParent.listFiles();
                    inflateListView(currentFiles);
                }
            }
        });
    }
}
