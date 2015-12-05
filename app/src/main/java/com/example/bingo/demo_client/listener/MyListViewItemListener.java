package com.example.bingo.demo_client.listener;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by Bingo on 15/12/3.
 */
public class MyListViewItemListener implements OnItemClickListener {
    private static final String TAG = MyListViewItemListener.class.getCanonicalName();

    /**
     *
     * @param adapterView  发生点击动作的AdapterView
     * @param view     在AdapterView中被点击的视图(它是由adapter提供的一个视图)
     * @param positon  The position of the view in the adapter  == ListView中被点击的view的位置   || 视图在adapter中的位置
     * @param id       The row id of the item that was clicked  == adapter.getItemId(position) || 被点击元素的行id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int positon, long id) {
        String itemTtile = (String)adapterView.getItemAtPosition(positon);
        Log.v(TAG, "---onItemClick---itemTtile=" + itemTtile);
    }
}
