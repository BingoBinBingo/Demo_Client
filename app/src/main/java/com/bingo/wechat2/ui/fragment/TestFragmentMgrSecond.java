package com.bingo.wechat2.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 16/4/12.
 */
public class TestFragmentMgrSecond extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_fragment_mgr_second, container, false);
        Button btn = (Button)view.findViewById(R.id.second_frag_btn);
        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.hide(this);
        tx.add(R.id.fragment_content, new TestFragmentMgrThird(), "Fragment_Three");
        tx.addToBackStack(null);
        tx.commit();
    }
}
