package com.example.bingo.demo_client.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.example.bingo.demo_client.application.DemoApplication;


public class ToastThread extends Thread
{
	public static Handler mHandler;
	
	private static ToastThread mThread;
	
	public ToastThread() {
		mThread = this;
		mThread.start();
	}
	
	
	public static void  showMsg(String Msg)
	{
		if(mThread == null ){
			new ToastThread();
		}
		 if (null == mHandler) {
			 return;
	        }  
		Message show = mHandler.obtainMessage();
		show.obj = Msg;
	    mHandler.sendMessage(show);
	}
	

	public void run()
	{
		Looper.prepare();
		
			mHandler = new Handler(){
			    public void handleMessage(Message msg) 
			    {
			    	if(DemoApplication.getContext() != null)
			    		Toast.makeText(DemoApplication.getContext(), (String) msg.obj, Toast.LENGTH_LONG).show();
			    }
			};

		Looper.loop();
	}
}