package com.example.bingo.demo_client.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.fragment.BlankFragment1;

/**
 * Created by Bingo on 15/12/3.
 */
public class FragmentTestActivity extends Activity {
    public static final String TAG = FragmentTestActivity.class.getCanonicalName();
    public static final String TITLE = "FragmentTestActivity";
    public static final String ACTION_NAME = "com.bingo.demo.fragmentTestActivity_action";

    private Button btn1;
    private Button btn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_activity);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, new BlankFragment1());
                transaction.addToBackStack("fragment1");
                transaction.commit();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
