package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcast3Receiver extends BroadcastReceiver {
    String TAG = MyBroadcast3Receiver.class.getSimpleName();

    public static final String ACTION = "MY_BROADCAST_RECEIVER";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "接收到第三种广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));
    }
}
