package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcast2Receiver extends BroadcastReceiver {

    String TAG = MyBroadcast2Receiver.class.getSimpleName();
    public static final String ACTION = "MY_BROADCAST_RECEIVER";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"接受第二种广播消息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));
    }
}
