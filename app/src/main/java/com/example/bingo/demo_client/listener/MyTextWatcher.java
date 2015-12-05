package com.example.bingo.demo_client.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Bingo on 15/12/6.
 */
public class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        System.out.println("beforeTextChanged变化前的内容：" + charSequence.toString()
        +"\n开始位置：" + start
        + "\n被改变的旧数目：" + count
        + "\n改变后内容数目：" + after);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        System.out.println("onTextChanged改变之后的内容：" + charSequence.toString()
                +"\n开始位置：" + start
                + "\n改变前的内容数目：" + before
                + "\n新增的内容数目：" + count);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
