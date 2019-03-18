package com.example.broadcast;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServiceMain extends AppCompatActivity  {
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;

    private MyAIDLInterface myAIDLInterface;

    private MyService.MyBinder myBinder;
    private boolean IsBind = false;

    //新建一个ServiceConnection的匿名类，重写onServiceConnected和onServoceDisconnected方法
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //使用了MyAIDInterface.Stub.asInterface()方法将传入的IBinder对象传换成了MyAIDLInterface对象
            myAIDLInterface = MyAIDLInterface.Stub.asInterface(service);
           // 接下来就可以调用在MyAIDLInterface.aidl文件中定义的所有接口了
            try {
                int result = myAIDLInterface.plus(3,5);
                String upperStr = myAIDLInterface.toUpperCase("hello world");
                Log.d("TAG","result us " +result);
                Log.d("TAG","upperStr is " + upperStr);
                //成功实现跨进程通信了，在一个进程中访问到了另外一个进程中的方法
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }


//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {//建立关联时调用
//            myBinder = (MyService.MyBinder)service;//向下转型
//            myBinder.startDownload();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {//解除关联时调用
//
//        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        mBtn1 = (Button)findViewById(R.id.Btn_1);
        mBtn2 = (Button)findViewById(R.id.Btn_2);
        mBtn3 = (Button)findViewById(R.id.Btn_3);
        mBtn4 = (Button)findViewById(R.id.Btn_4);
        setlisteners();
    }







    private void setlisteners(){
        OnClick onClick = new OnClick();
        mBtn1.setOnClickListener(onClick);
        mBtn2.setOnClickListener(onClick);
        mBtn3.setOnClickListener(onClick);
        mBtn4.setOnClickListener(onClick);

    }




    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.Btn_1:
                    Intent startIntent = new Intent(ServiceMain.this,MyService.class);
                    startService(startIntent);
                    break;
                case R.id.Btn_2:
                    Intent stopIntent = new Intent(ServiceMain.this,MyService.class);
                    stopService(stopIntent);
                    break;
                case R.id.Btn_3:
                    IsBind = true;
                    Intent bindIntent = new Intent(ServiceMain.this,MyService.class);
                    //bindService()方法接收三个参数，第一个参数就是刚刚构建出的Intent对象，
                    // 第二个参数是前面创建出的ServiceConnection的实例，第三个参数是一个标志位，
                    // 这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，
                    // 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行。
                    bindService(bindIntent,connection,BIND_AUTO_CREATE);
                    break;
                case R.id.Btn_4:
                    //如果bindService没有成功就直接unbindServoce或者unbindService成功后还多次进行unbindService
                    //就会报错java.lang.IllegalArgumentException: Service not registered
                    //所以每次绑定服务时用一个布尔值记录状态，这样即使多次解除服务也不会报错了
                    if (IsBind) {
                        unbindService(connection);
                        IsBind = false;
                    }
                    break;
                    //点击Stop Service按钮只会让Service停止，点击Unbind Service按钮只会让Service和Activity解除关联，
                    // 一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁。
                default:
                    break;

            }

        }
    }
}
