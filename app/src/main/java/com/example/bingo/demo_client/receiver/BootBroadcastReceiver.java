package com.example.bingo.demo_client.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.bingo.demo_client.MainActivity;


/**
 * Created by Bingo on 15/11/20.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = BootBroadcastReceiver.class.getCanonicalName();
    private static final String action_boot = "android.intent.action.BOOT_COMPLETED";
    // TODO: 15/11/20 接受安卓发送的制定

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(action_boot)) {
            Intent startViewIntent = new Intent(context, MainActivity.class);
            startViewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startViewIntent);
            Log.v(TAG, "--BootBroadcastReceiver---onReceive");
        }

    }
    public BootBroadcastReceiver() {
        Log.v(TAG,"first-boot-when-register-BootBroadcastReceiver");
    }


}
