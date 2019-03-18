package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcast5Receiver extends BroadcastReceiver {
    String TAG = MyBroadcast2Receiver.class.getSimpleName();

    public static final String ACTION = "MY_BROADCAST_RECEIVER";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "接收到第五种广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));

        //abortBroadcast()抛弃广播：
        //普通的广播是没有办法抛弃的，否则会抛出RuntimeException的异常。
//        abortBroadcast();
//        Log.i(TAG,"丢弃广播");
        setResult(004, "我是老5传来的消息", null);

    }
}
