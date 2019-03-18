package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcast4Receiver extends BroadcastReceiver {


    String TAG = MyBroadcast4Receiver.class.getSimpleName();
    public static final String ACTION = "MY_BROADCAST_RECEIVER";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "接收到第四种广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));
//
        String data = getResultData();
        Log.i(TAG, "data=" + data);
    }
}
