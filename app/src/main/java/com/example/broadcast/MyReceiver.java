package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.intent.action.MyReceiver";//定义了一个静态变量ACTION，以方便程序指定该receiver
    @Override
    public void onReceive(Context context, Intent intent) {//onReceive()方法的第二个参数intent可以获取到传递过来的数据
        System.out.print("接受到的消息为：" + intent.getStringExtra("data"));

    }



}
