package com.bingo.wechat2.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 16/4/29.
 */
public class EvaluateFragmentByCallBack extends Fragment{
    private static String TAG = EvaluateFragmentByCallBack.class.getCanonicalName();
    private ListView mListView;
    private TextView mTv;
    private String[] evaluateStrs = new String[] {"Good", "Just-so-so", "Bad"};
    private ArrayAdapter<String> mAdapter;
    private EvaluateFragmentByCallBack mCallBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.evaluate_fragment_by_call_back_layout, container, false);
        mListView = (ListView)view.findViewById(R.id.listViewId);
        mTv       = (TextView)view.findViewById(R.id.evaluateTtileTv);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, evaluateStrs);
        mTv.setText("对" + bundle.getString("codeName")+ ",进行评价--byFragmentVCallBack");
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment targetFrag = getTargetFragment();
                if(targetFrag != null && targetFrag instanceof EvaluateFragmentCallBack) {
                    ((EvaluateFragmentCallBack)targetFrag).onEvaluateResult(evaluateStrs[position]);
                    getFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    public static EvaluateFragmentByCallBack newInstance(String codeName) {

        Bundle args = new Bundle();
        args.putString("codeName", codeName);
        EvaluateFragmentByCallBack fragment = new EvaluateFragmentByCallBack();
        fragment.setArguments(args);
        return fragment;
    }


    public interface EvaluateFragmentCallBack {
        void onEvaluateResult(String evaluateResult);
    }
}
