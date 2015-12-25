package com.example.bingo.demo_client.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.fragment.BlankFragment1;
import com.example.bingo.demo_client.fragment.BlankFragment2;
import com.example.bingo.demo_client.utils.ToastThread;

/**
 * Created by Bingo on 15/12/3.
 */
public class FragmentTestActivity extends FragmentActivity implements BlankFragment1.OnFragmentInteractionListener ,BlankFragment2.OnFragmentInteractionListener{
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
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.frameLayoutContainer, new BlankFragment1(), "fragment1");
                transaction.addToBackStack("fragment1");
                transaction.commit();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.frameLayoutContainer, new BlankFragment2(), "fragment2");
                transaction.addToBackStack("fragment2");
                transaction.commit();
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void getFramengCount(View view) {
        int count = getSupportFragmentManager().getFragments().size();
        ToastThread.showMsg("fragment.list.size=" + count);
    }

    public void removeFragment1(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("fragment1");
        transaction.remove(fragment1);
        transaction.commit();
    }

    public void removeFragment2(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag("fragment2");
        transaction.remove(fragment2);
        transaction.commit();
    }

    public void popBackStack(View view){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    public void popBackStackToFrag2_0(View view){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack("fragment2",0);//方法一,通过TAG回退
        // manager.popBackStack(stackID2,0);//方法二,通过Transaction ID回退
    }

    public void popBackStackToFrag2_Inclusive(View view){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack("fragment2",FragmentManager.POP_BACK_STACK_INCLUSIVE);//方法一,通过TAG回退
// manager.popBackStack(stackID2,FragmentManager.POP_BACK_STACK_INCLUSIVE);//方法二,通过Transaction ID回退
    }
}
