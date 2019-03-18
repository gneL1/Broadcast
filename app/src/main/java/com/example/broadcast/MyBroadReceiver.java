package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MyBroadReceiver extends BroadcastReceiver {




    String TAG = MyBroadReceiver.class.getSimpleName();
    public static final String ACTION = "MY_BROADCAST_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"接受到第一种广播信息：" + intent.getStringExtra(BroadcastTestActivity.INTENT_INFO));

    }
}
