package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.listener.MyTextWatcher;

/**
 * Created by Bingo on 15/12/5.
 */
public class UIActivity extends Activity{
    public static final String TAG = UIActivity.class.getCanonicalName();
    public static final String TITLE = "UIActivity";
    public static final String ACTION_NAME = "com.bingo.demo.UIActivity_action";
    private PopupWindow popupWindow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity);
        final View root = (View)getLayoutInflater().inflate(R.layout.popup_layout, null);
        //加载 popupWindow
        popupWindow = new PopupWindow(root, 700, 700);
        Button popupWindowBtn = (Button)findViewById(R.id.popupWindowBtn);
        popupWindowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAsDropDown(view);
            }
        });

        Button dismissBtn = (Button)root.findViewById(R.id.popupWindowBtn);
        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        Button createDialogBtn = (Button)findViewById(R.id.createDialogbtn);
        createDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UIActivity.this);
                builder.setIcon(R.drawable.bg_button_blue_pressed);
                builder.setTitle("简单的对话框").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView show = ((TextView) UIActivity.this.findViewById(R.id.alterText));
                        show.addTextChangedListener(new MyTextWatcher());
                        show.setText(show.getText() + "＝＝我是弹出来的对话框");
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        return super.onTouchEvent(event);
    }
}
