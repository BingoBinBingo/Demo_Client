package com.bingo.wechat2.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.Log;

/**
 * Created by Bingo on 16/4/12.
 */
public class TestFragmentMgrFirst extends Fragment implements OnClickListener{
    private String TAG = TestFragmentMgrFirst.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_fragment_mgr_first, container, false);
        Button btn = (Button)view.findViewById(R.id.first_farg_btn);
        setHasOptionsMenu(true);
        btn.setOnClickListener(this);

//        btn.setEnabled(false);
        btn.setClickable(false);

        return view;
    }

    @Override
    public void onClick(View view) {
        Log.v(TAG, "--bingo--onClick---");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();

        tx.replace(R.id.fragment_content, new TestFragmentMgrSecond(), "Fragment_Two");
        tx.addToBackStack(null);
        tx.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
    }
}
