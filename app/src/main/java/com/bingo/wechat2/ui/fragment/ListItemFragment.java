package com.bingo.wechat2.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bingo on 16/4/29.
 */
public class ListItemFragment extends ListFragment implements EvaluateFragmentByCallBack.EvaluateFragmentCallBack{
    private static final String TAG = ListItemFragment.class.getCanonicalName();
    public static final int SHOW_FRAGMENT_CODE= 1;
    private TextView tv;
    private List<String> dataList = Arrays.asList("Android", "C++", "JavaScript");
    private ArrayAdapter<String> mAdapter;
    public static final int LIST_ITEM_REQUEST_CODE = 2;
    public static String EVALUATE_DIALOG = "evaluate_dialog";
    private int mCurrPosition;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, dataList);
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCurrPosition = position;
        //方式一 利用 DialogFragment的方式 TODO: 16/4/29 这里进行模仿二级 到 三级菜单的过度
//        doEvaluationByDialogFragment(position);

        //方式二 利用fragmen + callback的方式 TODO: 16/4/29
        doEavaluationByFragmenAndCallBack(position);

    }

    private void doEvaluationByDialogFragment(int position) {
        EvaluateFragment dialog = EvaluateFragment.newInstance(dataList.get(position));
        dialog.setTargetFragment(this, LIST_ITEM_REQUEST_CODE);
        dialog.show(getFragmentManager(), EVALUATE_DIALOG);
    }

    private void doEavaluationByFragmenAndCallBack(int position) {
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.hide(this);
        Fragment fragment = EvaluateFragmentByCallBack.newInstance(dataList.get(position));
        fragment.setTargetFragment(this, LIST_ITEM_REQUEST_CODE);
        tx.add(R.id.fragmentContainer, fragment);
        tx.addToBackStack(null);
        tx.commit();
    }


    /**
     * //方式一 利用 DialogFragment的方式
     * 用于从 二级设置子界面界面到 三级选择的子界面
     * 接受从 DialogFragment返回的参数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO: 16/4/29 bingo 因为只有在特定 requestCode 和resultCode的情况下，
        // TODO: 16/4/29 才会去执行，所以可以被  复用  ，被复用时重点

        if(requestCode == SHOW_FRAGMENT_CODE && resultCode == ContentFragment.CONTENT_FRAG_RESPONSE) {
            String intentStr = data.getStringExtra("jiajia");
            tv.setText(intentStr);
        }

        if(requestCode == LIST_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String evaluateResult = data.getStringExtra(EvaluateFragment.EVALUATE_RESULT);
            dataList.set(mCurrPosition, dataList.get(mCurrPosition) + "---" + evaluateResult);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "-bingo-onStart--");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "-bingo-onResume--");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "-bingo-onPause--");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, "-bingo-onStop--");
    }

    @Override
    public void onEvaluateResult(String evaluateResult) {
        dataList.set(mCurrPosition, dataList.get(mCurrPosition) + "---" + evaluateResult);
        mAdapter.notifyDataSetChanged();
    }
}
