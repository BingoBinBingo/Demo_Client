package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.ToastThread;

import java.util.ArrayList;

/**
 * Created by Bingo on 16/4/13.
 */
public class TestDialogFragment extends Activity {
    private String TAG  = TestDialogFragment.class.getCanonicalName();
    public static final String TITLE = "TestDialogFragment";
    public static final String ACTION_NAME = "com.bingo.demo.testDialogFragment_action";
    private int horizontalVal = 0;
    private int horizontalVal2 = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dialog_layout);
    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewLayout = LayoutInflater.from(this).inflate(R.layout.dialog_content_layout, null);
        View titleViewLayout = LayoutInflater.from(this).inflate(R.layout.dialog_titile_view, null);


        AlertDialog alertDialog = builder.setMessage("你好").setCustomTitle(titleViewLayout)
                .setPositiveButton("dsd", null)
                .setInverseBackgroundForced(true)
                .setCancelable(false)
                .create();
        alertDialog.setView(viewLayout, 100, 100, 100, 100);
        alertDialog.show();
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "我是按钮", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ToastThread.showMsg("我是按钮，我被触发了");
            }
        });
    }

    public void showSecondDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("one");
        stringArrayList.add("two");
        stringArrayList.add("three");

        final ListAdapter adapter = new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return stringArrayList.size();
            }

            @Override
            public Object getItem(int i) {
                return stringArrayList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return stringArrayList.size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        View viewLayout = LayoutInflater.from(this).inflate(R.layout.dialog_content_layout, null);
        builder.setView(viewLayout).setAdapter(adapter, null).show();
    }

    public void showProgressDialog(View view) {
        final ProgressDialog dialog = new ProgressDialog(this, ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("正在等待...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setMax(100);
        dialog.setProgress(horizontalVal);
        dialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.setProgress(++horizontalVal);
                horizontalVal2 +=2;
                dialog.setProgress(++horizontalVal);
                dialog.setSecondaryProgress(horizontalVal2);
                        handler.postDelayed(this, 500);
                if(horizontalVal == 100) {
                    dialog.dismiss();
                    return;
                }
            }
        },1000);

    }
}
