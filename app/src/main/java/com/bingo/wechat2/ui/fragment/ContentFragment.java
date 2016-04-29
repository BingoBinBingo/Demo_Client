package com.bingo.wechat2.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.utils.Log;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    private static final String TAG = ContentFragment.class.getCanonicalName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final int CONTENT_FRAG_RESPONSE = 2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Intent intent = new Intent();
            intent.putExtra("jiajia", "我是从ContentFragment页面返回的数据");
            getActivity().setResult(CONTENT_FRAG_RESPONSE, intent);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView tv = (TextView)view.findViewById(R.id.showListFragTv);
        tv.setText("从第一个fragment传递过来的参数=" + mParam1);
        view.findViewById(R.id.backToListActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().finish();
                getFragmentManager().popBackStack();
            }
        });
        Log.v(TAG, "--hbin--fragment-id=" + getFragmentManager().findFragmentById(R.id.fragmentContainer));
        return view;
    }

}
