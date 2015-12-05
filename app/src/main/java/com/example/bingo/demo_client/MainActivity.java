package com.example.bingo.demo_client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bingo.demo_server.aidl_model.Apple;
import com.example.bingo.demo_server.aidl_model.Fruit;
import com.example.bingo.demo_server.aidl_model.IPerson;
import com.example.bingo.demo_server.aidl_model.IPersonCallback;


public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String TITLE = "MainActivity";
    public static final String ACTION_NAME = "com.bingo.demo.mainActivity_action";

    private Button queryBtn;
    private EditText queryResultText;
    private Button queryFruitBtn;
    private EditText queryFruitText;
    private Button setfruitBtn;
    private Button queryApple;
    private AIDLServiceConnection conn = new AIDLServiceConnection();
    private IPerson person;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1) {
                Bundle bundle = msg.getData();
                String queryResult = bundle.getString("queryResult");
                queryResultText.setText(queryResult);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        queryBtn = (Button) findViewById(R.id.queryBtn);

        queryResultText = (EditText) findViewById(R.id.queryResultText);
        queryResultText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.v(TAG, "----------onFocusChange----hasFocus" + hasFocus);
            }
        });
        queryBtn.setOnClickListener(new MyOnClickListener());

        queryFruitBtn = (Button) findViewById(R.id.queryFruit);
        queryFruitText = (EditText) findViewById(R.id.queryFruitText);
        queryFruitBtn.setOnClickListener(new MyOnClickListener());

        queryApple = (Button) findViewById(R.id.queryApple);
        queryApple.setOnClickListener(new MyOnClickListener());

        setfruitBtn = (Button) findViewById(R.id.setFruitBtn);
//        setfruitBtn.setOnClickListener(new MyOnClickListener());

        setfruitBtn.setClickable(true);
        setfruitBtn.setSelected(false);

        setfruitBtn.setFocusable(false);
        //一启动就连接服务器
        Intent intent = new Intent();
        intent.setAction("com.example.bingo.demo_server.AIDLService");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            try {
                switch (view.getId()) {
                    case R.id.queryBtn:
                        person.query("bingo");
                        queryResultText.setText("客户端已经调用了query,并且服务端已经进行了返回");
                        break;
                    case R.id.queryFruit:
                        Fruit fruit = person.getFruit();
                        queryFruitText.setText(fruit.toString());
                        break;

                    case R.id.setFruitBtn:
                        Fruit fruit2 = new Fruit("西瓜", 20.0f, "绿色");
                        person.setFruit(fruit2);
                        break;
                    case R.id.queryApple:
                        Apple apple = person.getApple();
                        queryFruitText.setText(apple.toString());
                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    class AIDLServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(TAG, "[2]已经与服务端连接上");
            person =  IPerson.Stub.asInterface(iBinder);
            try {
                //马上进行注册
                person.registerCallback(new AIDLCallback());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            person = null;
        }
    }

    class AIDLCallback extends IPersonCallback.Stub {

        @Override
        public void setQueryResult(final String queryResult) throws RemoteException {
            Log.v(TAG, "是不是在主线程" + (Looper.myLooper() == Looper.getMainLooper()));
            Message msg = handler.obtainMessage();
            msg.what = 1;
            Bundle bundle = new Bundle();
            bundle.putString("queryResult", queryResult);
            msg.setData(bundle);
//            handler.sendMessage(msg);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    queryResultText.setText(queryResult + "from post");
                }
            });

        }
    }
}
